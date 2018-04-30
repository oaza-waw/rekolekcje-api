import {Component, Input} from "@angular/core";
import moment from "moment";
import {Moment} from "moment";
import {PersonalData} from "../../models/personal-data.model";
import {Address} from "../../models/address.model";

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
