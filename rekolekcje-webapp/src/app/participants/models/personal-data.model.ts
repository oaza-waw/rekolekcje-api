import {Moment} from "moment";

export class PersonalData {
  constructor(
    public fatherName?: string,
    public motherName?: string,
    public christeningPlace?: string,
    public christeningDate?: Moment,
    public closeRelativeName?: string,
    public closeRelativeNumber?: number,
  ) {
  }

  static mapFromForm(input: any): PersonalData {
    const personalData: PersonalData = new PersonalData();
    personalData.fatherName = input.result.personalData.fatherName;
    personalData.motherName = input.result.personalData.motherName;
    personalData.christeningPlace = input.result.personalData.christeningPlace;
    personalData.christeningDate = input.result.personalData.christeningDate;
    personalData.closeRelativeName = input.result.personalData.closeRelativeName;
    personalData.closeRelativeNumber = input.result.personalData.closeRelativeNumber;
    return personalData;
  }
}
