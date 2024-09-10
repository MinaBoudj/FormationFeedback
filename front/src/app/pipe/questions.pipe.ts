import { Pipe, PipeTransform } from '@angular/core';
import {AvisService} from "../services/avis/avis.service";
import {Question} from "../models/Question";

@Pipe({
  name: 'questions',
  standalone: true
})
export class QuestionsPipe implements PipeTransform {

  questions: Question[] = [];

  constructor(private avisService: AvisService) {
    this.avisService.recupererToutesLesQuestions().subscribe(
        data => { this.questions = data; }
    );
  }

  transform(id: number): string {
    if(id === undefined) return "";
    else
      return this.questions[id].texte;
  }

}

