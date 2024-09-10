import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgClass, NgFor, NgIf} from "@angular/common";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatTooltip} from "@angular/material/tooltip";

@Component({
    selector: 'app-notation-etoile',
    standalone: true,
    imports: [
        NgFor,
        MatIcon,
        MatIconButton,
        MatTooltip,
        NgClass,
        NgIf,
    ],
    templateUrl: './notation-etoile.component.html',
    styleUrl: './notation-etoile.component.scss'
})
export class NotationEtoileComponent implements OnInit {
    @Input() note!: number;
    @Output() changementNote: EventEmitter<number> = new EventEmitter<number>();

    noteMax: number = 5;
    @Input() modeLecture!: boolean;

    ngOnInit(): void {
    }

    donnerNote(value: number): void {
        this.note = value;
        this.changementNote.emit(this.note);
    }

    estNote(index: number): boolean {
        return index < this.note;
    }

    afficherIcon(index: number) {
        if (this.note >= index + 1)
            return 'star';
        else
            return 'star_border';
    }
}
