import {Component} from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {AjoutAvisComponent} from "./composant/modal/ajout-avis/ajout-avis.component";
import {ListeAvisComponent} from "./composant/liste-avis/liste-avis.component";

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [RouterOutlet, AjoutAvisComponent, RouterLink, ListeAvisComponent],
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss'
})
export class AppComponent {
    title = 'Avis des agents sur leurs formations';

}
