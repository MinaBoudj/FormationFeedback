import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Avis} from "../../models/Avis";
import {Observable, of} from "rxjs";
import {Question} from "../../models/Question";

@Injectable({
    providedIn: 'root'
})
export class AvisService {

    constructor(private http: HttpClient) {
    }

    /*GET*/
    public recupererTousLesAvis(): Observable<Avis[]> {
        return this.http.get<Avis[]>('api/avis/list');
    }

    /*POST*/
    /**
     * Enregistrer un nouvel avis dans la base de donnée du serveur backend
     * @param avis
     * */
    ajouterAvis(avis: Avis): Observable<Avis> {
        return this.http.post<Avis>('api/avis/add', avis);
    }

    /**
     * rechercher avis par formation
     * @param formation
     * */
    chercherAvisByFormation(formation: string): Observable<Avis[]> {
        if (!formation.trim()) {
            return of([]);
        }
        return this.http.get<Avis[]>('api/avis/chercherByformation?formation=' + formation);
    }

    /**
     * rechercher avis par organisme
     * @param organisme
     * */
    chercherAvisByOrganisme(organisme: string): Observable<Avis[]> {
        if (!organisme.trim()) {
            return of([]);
        }
        return this.http.get<Avis[]>('api/avis/chercherByOrganisme?organisme=' + organisme);
    }
}
