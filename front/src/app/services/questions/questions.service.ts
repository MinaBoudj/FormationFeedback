import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Question} from "../../models/Question";

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

  constructor(private http: HttpClient) { }

  public recupererToutesLesQuestions(): Observable<Question[]> {
    return this.http.get<Question[]>('api/question/list');
  }


}
