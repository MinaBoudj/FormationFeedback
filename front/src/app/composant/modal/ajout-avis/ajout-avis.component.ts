import {Component, Inject, Input, OnInit} from '@angular/core';
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {MatLabel} from "@angular/material/form-field";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {ItemAvisComponent} from '../../item-avis/item-avis.component';
import {Avis} from '../../../models/Avis';
import {CommonModule} from '@angular/common';
import {FormsModule} from "@angular/forms";
import {AvisService} from "../../../services/avis/avis.service";
import {NotationEtoileComponent} from "../../notation-etoile/notation-etoile.component";
import {Question} from "../../../models/Question";
import {MatInput} from "@angular/material/input";
import {Reponse} from "../../../models/Reponse";
import {MessageComponent} from "../message/message.component";
import {QuestionsService} from "../../../services/questions/questions.service";

@Component({
    selector: 'app-ajout-avis',
    standalone: true,
    imports: [
        MatRadioGroup,
        MatLabel,
        MatRadioButton,
        ItemAvisComponent,
        CommonModule,
        FormsModule,
        NotationEtoileComponent,
        MatInput,
        MessageComponent
    ],
    templateUrl: './ajout-avis.component.html',
    styleUrl: './ajout-avis.component.scss'
})
export class AjoutAvisComponent implements OnInit {
    @Input() avis!: Avis;
    questions: Question[] = [];
    modeLecture: boolean = false;
    submitted: boolean = false;

    constructor(private dialogRef: MatDialog,
                private avisService: AvisService, private questionsService: QuestionsService, @Inject(MAT_DIALOG_DATA) data: any) {
        this.avis = data.avis;
    }

    closePopUp() {
        this.dialogRef.closeAll();
    }

    ngOnInit(): void {
        this.submitted = false;
        if (this.avis === undefined) {
            this.avis = new Avis();
            this.modeLecture = false;
            this.questionsService.recupererToutesLesQuestions().subscribe(
                (questions: Question[]) => {
                    this.questions = questions;
                    //crée un item pour chaque question
                    this.avis.reponses = questions.map((question) => {
                        return {
                            id: parseInt(question.id, 10),
                            question: {id: question.id, texte: question.texte},
                            commentaire: "",
                            note: 0
                        };
                    });
                },
                error => {
                    console.log('error lors de la récupération des questions !!!!', error);
                }
            );
        } else {
            this.modeLecture = true;
        }
    }

    onSubmit() {
        if (!this.avisValid()) {
            console.log("ERREUR ! champs manquant");
        } else if (!this.submitted) {
            this.submitted = true;
            this.calculeNoteGlobale();
            this.avisService.ajouterAvis(this.avis).subscribe();
            this.openMessage();
        }
    }

    nouvelleReponse(reponse: Reponse) { //réponse récupérer du composant item-avis
        this.avis.reponses.forEach(occu => {
           if(occu.question.id === reponse.question.id)
               occu = reponse;
        });
    }

    calculeNoteGlobale() {
        let somme = 0;
        let moy = this.avis.reponses.length;
        for (var reponse of this.avis.reponses) {
            somme += reponse.note;
        }
        this.avis.noteGlobale = somme / moy;
    }

    avisValid(): boolean {
        return (this.avis.formation != '' && this.avis.organisme != '' && this.avis.anonyme != null
            && this.avis.avisGlobal != '' && this.reponseValide(this.avis.reponses));
    }

    reponseValide(reponses: Reponse[]): boolean {
        for (var reponse of reponses) {
            if (reponse.note === 0 || reponse.commentaire === '')
                return false;
        }
        return true;
    }

    openMessage(){
        this.dialogRef.open(MessageComponent);
    }

}
