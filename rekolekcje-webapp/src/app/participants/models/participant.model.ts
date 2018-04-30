import {Address} from "./address.model";
import {PersonalData} from "./personal-data.model";

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
