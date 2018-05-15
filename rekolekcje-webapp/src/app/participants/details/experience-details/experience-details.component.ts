import { Component, Input } from '@angular/core';
import { Experience } from '../../models/experience.model';

@Component({
  selector: 'experience-details',
  templateUrl: './experience-details.component.html',
  styleUrls: ['../participant-details.component.css', './experience-details.component.css']
})
export class ExperienceDetailsComponent {

  @Input() experienceData: Experience;

}
