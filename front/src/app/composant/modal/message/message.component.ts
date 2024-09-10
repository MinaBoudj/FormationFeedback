import { Component } from '@angular/core';
import {MatLabel} from "@angular/material/form-field";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-message',
  standalone: true,
    imports: [
        MatLabel
    ],
  templateUrl: './message.component.html',
  styleUrl: './message.component.scss'
})
export class MessageComponent {

  constructor(private dialogRef: MatDialog){ }

  close() {
    this.dialogRef.closeAll();
  }

}
