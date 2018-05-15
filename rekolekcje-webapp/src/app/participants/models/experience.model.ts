import { Moment } from 'moment';

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
    public celebrationsPlannedThisYear?: number
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
    return experience;
  }
}
