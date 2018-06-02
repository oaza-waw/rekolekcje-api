import { Component, Input } from '@angular/core';
import { Experience } from '../../../models/experience.model';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { HistoricalRetreatFormComponent } from '../historical-retreat-form/historical-retreat-form.component';

@Component({
  selector: 'experience-form',
  templateUrl: './experience-form.component.html',
  styleUrls: ['./experience-form.component.css']
})
export class ExperienceFormComponent {

  @Input() public experienceData: Experience;
  @Input() public experienceForm: FormGroup;

  static buildFormConfig(experience: Experience) {
    if (experience != null) {
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
        historicalRetreats: new FormArray(experience.historicalRetreats ? experience.historicalRetreats.map(it => HistoricalRetreatFormComponent.buildFormConfig(it)) : [HistoricalRetreatFormComponent.buildFormConfig(null)]),
      })
    } else {
      return new FormGroup({
        kwcStatus: new FormControl(''),
        kwcSince: new FormControl(''),
        numberOfCommunionDays: new FormControl(''),
        numberOfPrayerRetreats: new FormControl(''),
        formationMeetingsInMonth: new FormControl(''),
        leadingGroupToFormationStage: new FormControl(''),
        deuterocatechumenateYear: new FormControl(''),
        stepsTaken: new FormControl(''),
        stepsPlannedThisYear: new FormControl(''),
        celebrationsTaken: new FormControl(''),
        celebrationsPlannedThisYear: new FormControl(''),
        historicalRetreats: new FormArray([HistoricalRetreatFormComponent.buildFormConfig(null)]),
      })
    }
  }
}
