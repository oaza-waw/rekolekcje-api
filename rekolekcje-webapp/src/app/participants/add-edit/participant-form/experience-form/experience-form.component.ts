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

  static buildFormConfig(experience: Experience) {
    if (experience != null) {
      return {
        kwcStatus: [experience.kwcStatus],
        kwcSince: [experience.kwcSince],
        numberOfCommunionDays: [experience.numberOfCommunionDays],
        numberOfPrayerRetreats: [experience.numberOfPrayerRetreats],
        formationMeetingsInMonth: [experience.formationMeetingsInMonth],
        leadingGroupToFormationStage: [experience.leadingGroupToFormationStage],
        deuterocatechumenateYear: [experience.deuterocatechumenateYear],
        stepsTaken: [experience.stepsTaken],
        stepsPlannedThisYear: [experience.stepsPlannedThisYear],
        celebrationsTaken: [experience.celebrationsTaken],
        celebrationsPlannedThisYear: [experience.celebrationsPlannedThisYear],
      }
    } else {
      return {
        kwcStatus: '',
        kwcSince: '',
        numberOfCommunionDays: '',
        numberOfPrayerRetreats: '',
        formationMeetingsInMonth: '',
        leadingGroupToFormationStage: '',
        deuterocatechumenateYear: '',
        stepsTaken: '',
        stepsPlannedThisYear: '',
        celebrationsTaken: '',
        celebrationsPlannedThisYear: '',
      }
    }
  }
}
