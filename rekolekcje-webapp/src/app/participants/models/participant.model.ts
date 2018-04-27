import { Moment } from 'moment';

export class Participant {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public pesel?: number,
    public address?: Address,
    public parishId?: number,
    public personalData?: PersonalData
  ) {}

  static mapFromForm(input: any): Participant {
    const p: Participant = new Participant();
    p.firstName = input.result.firstName;
    p.lastName = input.result.lastName;
    p.pesel = input.result.pesel;
    p.address = Address.mapFromForm(input);
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

export class Address {
  constructor(
    public street?: string,
    public number?: number,
    public flat?: number,
    public code?: string,
    public city?: string,
  ) {}

  static mapFromForm(input: any): Address {
    const address = new Address();
    address.street = input.result.address.street;
    address.number = input.result.address.number;
    address.flat = input.result.address.flat;
    address.code = input.result.address.code;
    address.city = input.result.address.city;
    return address;
  }
}
