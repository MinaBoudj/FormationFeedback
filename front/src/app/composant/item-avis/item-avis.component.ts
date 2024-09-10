import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgFor, NgIf} from '@angular/common';
import {MatLabel} from "@angular/material/form-field";
import {NotationEtoileComponent} from "../notation-etoile/notation-etoile.component";
import {FormsModule} from "@angular/forms";
import {Reponse} from "../../models/Reponse";
import {MatInput} from "@angular/material/input";

@Component({
    selector: 'app-item-avis',
    standalone: true,
    imports: [
        MatLabel,
        NgFor,
        NotationEtoileComponent,
        FormsModule,
        MatInput,
        NgIf
    ],
    templateUrl: './item-avis.component.html',
    styleUrl: './item-avis.component.scss'
})
export class ItemAvisComponent implements OnInit {
    @Input() reponse!: Reponse;
    @Input() modeLecture!: boolean;

    @Output() reponseConstruite = new EventEmitter<Reponse>();

    constructor() {
    }

    ngOnInit(): void {
    }

    emettreNote(note: number) {
        this.reponse.note = note;   //note recuperer du composant etoile
        this.reponseConstruite.emit(this.reponse);
    }


}
