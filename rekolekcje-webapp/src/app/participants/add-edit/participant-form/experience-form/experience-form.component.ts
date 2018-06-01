import { Component, Input } from '@angular/core';
import { Experience } from '../../../models/experience.model';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'experience-form',
  templateUrl: './experience-form.component.html',
  styleUrls: ['./experience-form.component.css']
})
export class ExperienceFormComponent {

  @Input() public experienceData: Experience;
  @Input() public experienceForm: FormGroup;

  static buildFormConfig(experience: Experience) {
    return new FormGroup({
      kwcStatus: new FormControl(experience.kwcStatus ? experience.kwcStatus : ''),
      kwcSince: new FormControl(experience.kwcSince ? experience.kwcSince : ''),
      numberOfCommunionDays: new FormControl(experience.numberOfCommunionDays ? experience.numberOfCommunionDays : ''),
      numberOfPrayerRetreats: new FormControl(experience.numberOfPrayerRetreats ? experience.numberOfPrayerRetreats : ''),
      formationMeetingsInMonth: new FormControl(experience.formationMeetingsInMonth ? experience.formationMeetingsInMonth : ''),
      leadingGroupToFormationStage: new FormControl(experience.leadingGroupToFormationStage ? experience.leadingGroupToFormationStage : ''),
      deuterocatechumenateYear: new FormControl(experience.deuterocatechumenateYear ? experience.deuterocatechumenateYear : ''),
      stepsTaken: new FormControl(experience.stepsTaken ? experience.stepsTaken : ''),
      stepsPlannedThisYear: new FormControl(experience.stepsPlannedThisYear ? experience.stepsPlannedThisYear : ''),
      celebrationsTaken: new FormControl(experience.celebrationsTaken ? experience.celebrationsTaken : ''),
      celebrationsPlannedThisYear: new FormControl(experience.celebrationsPlannedThisYear ? experience.celebrationsPlannedThisYear : ''),
    })
  }
}
