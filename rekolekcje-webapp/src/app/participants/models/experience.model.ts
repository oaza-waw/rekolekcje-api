import { Moment } from 'moment';

export class Experience {
  constructor(
    public kwcStatus?: string,
    public kwcSince?: Moment,
    public numberOfCommunionDays?: number,
    public numberOfPrayerRetreats?: number
  ) {}

  static mapFromForm(input: any): Experience {
    const experience: Experience = new Experience();
    experience.kwcStatus = input.result.experience.kwcStatus;
    experience.kwcSince = input.result.experience.kwcSince;
    experience.numberOfCommunionDays = input.result.experience.numberOfCommunionDays;
    experience.numberOfPrayerRetreats = input.result.experience.numberOfPrayerRetreats;
    return experience;
  }
}
