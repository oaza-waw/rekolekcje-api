import { FormGroup } from '@angular/forms';

export class CurrentApplication {

  constructor(
    public stage?: string,
    public turn?: number,
  ) { }

  static mapFromForm(input: any): CurrentApplication {
    const currentApplication = new CurrentApplication();
    currentApplication.stage = input.result.currentApplication.stage;
    currentApplication.turn = input.result.currentApplication.turn;
    return currentApplication;
  }

  static parseForm(form: FormGroup): CurrentApplication {
    const currentApplication = new CurrentApplication();
    currentApplication.stage = form.get('currentApplication.stage').value;
    currentApplication.turn = form.get('currentApplication.turn').value;
    return currentApplication;
  }
}
