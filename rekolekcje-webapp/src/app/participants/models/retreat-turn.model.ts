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
    retreatTurn.stage = input.result.experience.historicalRetreats[0].stage;
    retreatTurn.location = input.result.experience.historicalRetreats[0].location;
    retreatTurn.year = input.result.experience.historicalRetreats[0].year;
    return retreatTurn;
  }

  static parseForm(id: number, form: FormGroup) {
    let retreatTurn = new RetreatTurn();
    retreatTurn.id = id;
    // console.log('get all', form.get('experience.historicalRetreats'));
    // console.log('get one', form.get('experience.historicalRetreats.0'));
    retreatTurn.stage = form.get('experience.historicalRetreats.0.stage').value;
    // console.log('stage: ', retreatTurn.stage);
    retreatTurn.location = form.get('experience.historicalRetreats.0.location').value;
    retreatTurn.year = form.get('experience.historicalRetreats.0.year').value;
    return retreatTurn;
  }
}
