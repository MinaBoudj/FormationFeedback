import {Component, ElementRef, EventEmitter, HostListener, OnInit, Output, ViewChild} from '@angular/core';
import {map, Subject, switchMap} from "rxjs";
import {AvisService} from "../../services/avis/avis.service";
import {Avis} from '../../models/Avis';
import {AsyncPipe, DatePipe, NgFor, NgIf} from "@angular/common";
import {NotationEtoileComponent} from "../notation-etoile/notation-etoile.component";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption} from "@angular/material/autocomplete";
import {MatSelect, MatSelectChange} from "@angular/material/select";
import {MatInput} from "@angular/material/input";

@Component({
    selector: 'app-recherche-avis',
    standalone: true,
    imports: [
        AsyncPipe,
        DatePipe,
        NgFor,
        NgIf,
        NotationEtoileComponent,
        MatFormField,
        MatOption,
        MatLabel,
        MatSelect,
        MatInput
    ],
    templateUrl: './recherche-avis.component.html',
    styleUrl: './recherche-avis.component.scss'
})
export class RechercheAvisComponent implements OnInit {
    private searchTerms = new Subject<string>();
    @ViewChild('nom', {static: true}) rechercheInput!: ElementRef;
    @Output() changementListe = new EventEmitter<Avis[]>();
    rechercheActive = false;
    critereRecherche = 'sansFiltres';

    constructor(private avisService: AvisService) {}

    ngOnInit(): void {
        this.loadInitialAvis();

        this.searchTerms.pipe(
            switchMap((term: string) => {
                if (term === '' && this.critereRecherche != 'note' && this.critereRecherche != 'date'){
                    return this.avisService.recupererTousLesAvis();
                }
                if (this.critereRecherche === 'formation') {
                    return this.avisService.chercherAvisByFormation(term);
                } else if (this.critereRecherche === 'organisme') {
                    return this.avisService.chercherAvisByOrganisme(term);
                } else if (this.critereRecherche === 'note') {
                    return this.avisService.recupererTousLesAvis().pipe(
                        map(arrayAvis => arrayAvis.sort((a, b) => b.noteGlobale - a.noteGlobale))
                    );
                } else if (this.critereRecherche === 'date') {
                    return this.avisService.recupererTousLesAvis().pipe(
                        map(arrayAvis => arrayAvis.sort((a, b) => new Date(b.datePublication).getTime() - new Date(a.datePublication).getTime()))
                    );
                }else {
                    return this.avisService.recupererTousLesAvis();
                }
            })
        ).subscribe(data => {
            if (this.rechercheActive || this.critereRecherche === 'sansFiltres' || this.critereRecherche === 'note' || this.critereRecherche === 'date') {
                this.changementListe.emit(data);
            }
        });
    }

    loadInitialAvis() {
        this.avisService.recupererTousLesAvis().subscribe(data => {
            this.changementListe.emit(data);
        });
    }

    changementCritere(change: MatSelectChange): void {
        this.critereRecherche = change.value;
        if (this.critereRecherche === 'sansFiltres') {
            this.loadInitialAvis();
            this.rechercheActive = false;
        } else {
            this.recherche(this.rechercheInput.nativeElement.value); // Réinitialisez la recherche avec le nouveau critère
        }
    }


    recherche(value: string) {
        this.searchTerms.next(value);
        this.rechercheActive = true;
    }

    @HostListener('document:click', ['$event'])
    ClickDocument(event: MouseEvent) {
        if (this.rechercheInput.nativeElement.contains(event.target))
            this.rechercheActive = true;
        else
            this.rechercheActive = false;
    }
}
