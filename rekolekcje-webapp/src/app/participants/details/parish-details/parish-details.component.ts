import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Parish} from "../../../parish/models/parish.model";
import {Subject} from "rxjs/Subject";
import {Store} from "@ngrx/store";
import {Parishes} from "../../../parish/store/parish-reducer";
import {AppSelectors} from "../../../core/store/app-selectors";

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
