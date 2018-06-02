import { Moment } from 'moment';
import { FormGroup } from '@angular/forms';
import { RetreatTurn } from './retreat-turn.model';

export class Experience {
  constructor(
    public kwcStatus?: string,
    public kwcSince?: Moment,
    public numberOfCommunionDays?: number,
    public numberOfPrayerRetreats?: number,
    public formationMeetingsInMonth?: number,
    public leadingGroupToFormationStage?: string,
    public deuterocatechumenateYear?: number,
    public stepsTaken?: number,
    public stepsPlannedThisYear?: number,
    public celebrationsTaken?: number,
    public celebrationsPlannedThisYear?: number,
    public historicalRetreats?: RetreatTurn[],
  ) {}

  static mapFromForm(input: any): Experience {
    const experience: Experience = new Experience();
    experience.kwcStatus = input.result.experience.kwcStatus;
    experience.kwcSince = input.result.experience.kwcSince;
    experience.numberOfCommunionDays = input.result.experience.numberOfCommunionDays;
    experience.numberOfPrayerRetreats = input.result.experience.numberOfPrayerRetreats;
    experience.formationMeetingsInMonth = input.result.experience.formationMeetingsInMonth;
    experience.leadingGroupToFormationStage = input.result.experience.leadingGroupToFormationStage;
    experience.deuterocatechumenateYear = input.result.experience.deuterocatechumenateYear;
    experience.stepsTaken = input.result.experience.stepsTaken;
    experience.stepsPlannedThisYear = input.result.experience.stepsPlannedThisYear;
    experience.celebrationsTaken = input.result.experience.celebrationsTaken;
    experience.celebrationsPlannedThisYear = input.result.experience.celebrationsPlannedThisYear;
    experience.historicalRetreats = [RetreatTurn.mapFromForm(input)];
    console.log('historical: ', experience.historicalRetreats);
    return experience;
  }

  static parseForm(form: FormGroup): Experience {
    const experience = new Experience();
    experience.kwcStatus = form.get('experience.kwcStatus').value;
    experience.kwcSince = form.get('experience.kwcSince').value;
    experience.numberOfCommunionDays = form.get('experience.numberOfCommunionDays').value;
    experience.numberOfPrayerRetreats = form.get('experience.numberOfPrayerRetreats').value;
    experience.formationMeetingsInMonth = form.get('experience.formationMeetingsInMonth').value;
    experience.leadingGroupToFormationStage = form.get('experience.leadingGroupToFormationStage').value;
    experience.deuterocatechumenateYear = form.get('experience.deuterocatechumenateYear').value;
    experience.stepsTaken = form.get('experience.stepsTaken').value;
    experience.stepsPlannedThisYear = form.get('experience.stepsPlannedThisYear').value;
    experience.celebrationsTaken = form.get('experience.celebrationsTaken').value;
    experience.celebrationsPlannedThisYear = form.get('experience.celebrationsPlannedThisYear').value;
    experience.historicalRetreats = [RetreatTurn.parseForm(123, form)];
    return experience;
  }
}
