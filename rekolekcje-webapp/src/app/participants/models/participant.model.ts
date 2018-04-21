import { Moment } from 'moment';

export class Participant {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public pesel?: number,
    public address?: string,
    public fatherName?: string,
    public motherName?: string,
    public christeningPlace?: string,
    public christeningDate?: Moment,
    public parishId?: number,
    public currentTreatment?: string,
    public medications?: string,
    public allergies?: string,
    public other?: string
  ) {}

  static mapFromForm(input: any): Participant {
    const p: Participant = new Participant();
    p.firstName = input.result.firstName;
    p.lastName = input.result.lastName;
    p.pesel = input.result.pesel;
    p.address = input.result.address;
    p.parishId = input.result.parishId;
    p.fatherName = input.result.fatherName;
    p.motherName = input.result.motherName;
    p.christeningPlace = input.result.christeningPlace;
    p.christeningDate = input.result.christeningDate;
    p.currentTreatment = input.result.currentTreatment;
    p.medications = input.result.medications;
    p.allergies = input.result.allergies;
    p.other = input.result.other;

    return p;
  }
}
