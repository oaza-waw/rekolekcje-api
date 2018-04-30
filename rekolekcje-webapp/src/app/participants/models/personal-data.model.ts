import {Moment} from "moment";

export class PersonalData {
  constructor(
    public fatherName?: string,
    public motherName?: string,
    public christeningPlace?: string,
    public christeningDate?: Moment,
    public emergencyContactName?: string,
    public emergencyContactNumber?: number,
  ) {
  }

  static mapFromForm(input: any): PersonalData {
    const personalData: PersonalData = new PersonalData();
    personalData.fatherName = input.result.personalData.fatherName;
    personalData.motherName = input.result.personalData.motherName;
    personalData.christeningPlace = input.result.personalData.christeningPlace;
    personalData.christeningDate = input.result.personalData.christeningDate;
    personalData.emergencyContactName = input.result.personalData.emergencyContactName;
    personalData.emergencyContactNumber = input.result.personalData.emergencyContactNumber;
    return personalData;
  }
}
