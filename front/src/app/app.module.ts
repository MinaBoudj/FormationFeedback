import { AppComponent } from './app.component';
import {NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";
import {AjoutAvisComponent} from "./composant/modal/ajout-avis/ajout-avis.component";
import {ItemAvisComponent} from "./composant/item-avis/item-avis.component";
import {ListeAvisComponent} from "./composant/liste-avis/liste-avis.component";
import {NotationEtoileComponent} from "./composant/notation-etoile/notation-etoile.component";
import {MatDialogModule} from "@angular/material/dialog";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RechercheAvisComponent} from "./composant/recherche-avis/recherche-avis.component";
import {MatSelectModule} from "@angular/material/select";

@NgModule({
    declarations: [],
    imports: [
        AppComponent,
        AjoutAvisComponent,
        ItemAvisComponent,
        ListeAvisComponent,
        NotationEtoileComponent,
        RechercheAvisComponent,
        BrowserModule,
        HttpClientModule,
        MatDialogModule,
        MatSelectModule,
        FormsModule,
        CommonModule,
    ],
    providers: [ ],
    bootstrap: [AppComponent],
    exports:[
    ]
})
export class AppModule {}
