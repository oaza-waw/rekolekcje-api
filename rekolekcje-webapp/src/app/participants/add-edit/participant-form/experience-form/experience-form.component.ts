import { Component, Input } from '@angular/core';
import { Experience } from '../../../models/experience.model';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'experience-form',
  templateUrl: './experience-form.component.html',
  styleUrls: ['./experience-form.component.css']
})
export class ExperienceFormComponent {

  @Input() public experienceData: Experience;
  @Input() public experienceForm: FormGroup;

}
