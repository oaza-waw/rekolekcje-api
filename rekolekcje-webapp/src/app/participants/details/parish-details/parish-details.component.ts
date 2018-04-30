import { Component, Input } from '@angular/core';
import { Parish } from '../../../parish/models/parish.model';

@Component({
  selector: 'parish-details',
  templateUrl: './parish-details.component.html',
  styleUrls: ['./parish-details.component.css']
})
export class ParishDetailsComponent {

  @Input() parishId: number;
  @Input() parishes: Parish[];

  getParishName(parishId: number): string {
    let parish = this.findParish(parishId);
    return parish ? parish.name : '';
  }

  getParishAddress(parishId: number): string {
    let parish = this.findParish(parishId);
    return parish ? parish.address : '';
  }

  private findParish(parishId: number): Parish {
    return this.parishes.find(p => p.id === parishId);
  }
}
