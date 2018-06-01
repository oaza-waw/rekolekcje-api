import { FormGroup } from '@angular/forms';

export class RetreatTurn {
  constructor(
    public id?: number,
    public stage?: string,
    public location?: string,
    public year?: number
  ) {}

  static mapFromForm(input: any): RetreatTurn {
    const retreatTurn: RetreatTurn = new RetreatTurn();
    retreatTurn.stage = input.result.stage;
    retreatTurn.location = input.result.location;
    retreatTurn.year = input.result.year;
    return retreatTurn;
  }

  static parseForm(id: number, form: FormGroup) {
    let retreatTurn = new RetreatTurn();
    retreatTurn.id = id;
    retreatTurn.stage = form.get('experience.historicalRetreats.stage').value;
    retreatTurn.location = form.get('experience.historicalRetreats.location').value;
    retreatTurn.year = form.get('experience.historicalRetreats.year').value;
    return retreatTurn;
  }
}
