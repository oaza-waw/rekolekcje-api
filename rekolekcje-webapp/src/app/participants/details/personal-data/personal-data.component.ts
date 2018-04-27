import {Component, Input} from "@angular/core";
import moment = require("moment");
import {Moment} from "moment";
import {Address, PersonalData} from "../../models/participant.model";

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

  constructor(
  ) { }

  formatDate(date: Moment): string {
    return moment(date).local().format('LL');
  }

}
