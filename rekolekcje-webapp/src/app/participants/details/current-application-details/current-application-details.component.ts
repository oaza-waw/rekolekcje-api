import { Component, Input } from '@angular/core';
import { CurrentApplication } from '../../models/current-application.model';

@Component({
  selector: 'reko-current-application-details',
  templateUrl: './current-application-details.component.html',
  styleUrls: ['./current-application-details.component.css']
})
export class CurrentApplicationDetailsComponent {

  @Input() currentApplicationData: CurrentApplication;
}
