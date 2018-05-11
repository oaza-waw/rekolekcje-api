import { Moment } from 'moment';

export class Experience {
  constructor(
    public kwcStatus?: string,
    public kwcSince?: Moment,
    public numberOfSummerRetreats?: number,
    public numberOfPrayerRetreats?: number
  ) {}

  static mapFromForm(input: any): Experience {
    const experience: Experience = new Experience();
    experience.kwcStatus = input.result.experience.kwcStatus;
    experience.kwcSince = input.result.experience.kwcSince;
    experience.numberOfSummerRetreats = input.result.experience.numberOfSummerRetreats;
    experience.numberOfPrayerRetreats = input.result.experience.numberOfPrayerRetreats;
    return experience;
  }
}
