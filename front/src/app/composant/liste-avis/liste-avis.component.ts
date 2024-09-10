import {Component, OnInit} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatFormField} from '@angular/material/form-field';
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {Avis} from '../../models/Avis';
import {DatePipe, NgFor, NgIf} from '@angular/common';
import {ItemAvisComponent} from '../item-avis/item-avis.component';
import {AvisService} from "../../services/avis/avis.service";
import {NotationEtoileComponent} from "../notation-etoile/notation-etoile.component";
import {AjoutAvisComponent} from "../modal/ajout-avis/ajout-avis.component";
import {RechercheAvisComponent} from "../recherche-avis/recherche-avis.component";


@Component({
    selector: 'app-liste-avis',
    standalone: true,
    imports: [
        MatIconModule,
        MatFormField,
        NgFor,
        ItemAvisComponent, DatePipe, NotationEtoileComponent, AjoutAvisComponent, NgIf, RechercheAvisComponent],
    templateUrl: './liste-avis.component.html',
    styleUrl: './liste-avis.component.scss'
})
export class ListeAvisComponent implements OnInit {

    title = 'Évalue ta formation ! ';
    avis: Avis[] = [];

    constructor(private dialogRef: MatDialog,
                private avisService: AvisService) {
    }

    ngOnInit(): void {
        this.avisService.recupererTousLesAvis().subscribe(data => {
            this.avis = data;
        })
    }

    ouvrirAvis(selectedAvis: Avis | undefined) {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = true;
        dialogConfig.hasBackdrop = false;
        dialogConfig.autoFocus = true;
        dialogConfig.maxHeight = '90vh';
        dialogConfig.minWidth = '70vw';
        dialogConfig.width = 'content';
        dialogConfig.data = {avis: selectedAvis};
        this.dialogRef.open(AjoutAvisComponent, dialogConfig);
    }

    changerListe(avis: Avis[]) {
        this.avis = avis;
    }
}
