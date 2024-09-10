import {Reponse} from "./Reponse";
import {Agent} from "./Agent";

export class Avis{
    formation: string = '';
    organisme: string = '';
    agent:  Agent = new Agent();
    anonyme: boolean = false;
    datePublication: Date = new Date();
    avisGlobal: String = '';
    noteGlobale: number = 0;
    reponses:  Reponse[] = [];
}