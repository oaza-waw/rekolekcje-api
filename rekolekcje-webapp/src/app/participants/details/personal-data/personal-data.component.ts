import { Component, Input } from '@angular/core';
import * as moment from 'moment';
import { Moment } from 'moment';
import { PersonalData } from '../../models/personal-data.model';
import { Address } from '../../models/address.model';
import {HealthReport} from "../../models/heath-report.model";

@Component({
  selector: 'personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['../participant-details.component.css', './personal-data.component.css']
})
export class PersonalDataComponent {

  @Input() firstName: string;
  @Input() lastName: string;
  @Input() pesel: number;
  @Input() address: Address;
  @Input() personalData: PersonalData;
  @Input() healthReport: HealthReport;

  constructor() {
  }

  formatDate(date: Moment): string {
    return moment(date).local().format('LL');
  }

}
