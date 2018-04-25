import { Moment } from 'moment';

export class Participant {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public pesel?: number,
    public address?: string,
    public parishId?: number,
    public personalData?: PersonalData
  ) {}

  static mapFromForm(input: any): Participant {
    const p: Participant = new Participant();
    p.firstName = input.result.firstName;
    p.lastName = input.result.lastName;
    p.pesel = input.result.pesel;
    p.address = input.result.address;
    p.parishId = input.result.parishId;
    p.personalData = PersonalData.mapFromForm(input);
    return p;
  }
}

export class PersonalData {
  constructor(
    public fatherName?: string,
    public motherName?: string,
    public christeningPlace?: string,
    public christeningDate?: Moment,
    public closeRelativeName?: string,
    public closeRelativeNumber?: number,
  ) {}

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
