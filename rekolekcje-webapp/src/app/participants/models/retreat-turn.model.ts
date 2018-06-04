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
    retreatTurn.stage = input.stage;
    retreatTurn.location = input.location;
    retreatTurn.year = input.year;
    return retreatTurn;
  }

  static parseForm(form: FormGroup, index: number) {
    let retreatTurn = new RetreatTurn();
    retreatTurn.stage = form.get('experience.historicalRetreats.' + index + '.stage').value;
    retreatTurn.location = form.get('experience.historicalRetreats.' + index + '.location').value;
    retreatTurn.year = form.get('experience.historicalRetreats.' + index + '.year').value;
    return retreatTurn;
  }
}
