import { Component, Input } from '@angular/core';

@Component({
  selector: 'reko-details-text-field',
  templateUrl: './details-text-field.component.html',
  styleUrls: ['./details-text-field.component.css']
})
export class DetailsTextFieldComponent {
  @Input() fieldValue: string;
  @Input() label: string;
}
