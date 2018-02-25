export class Parish {
  constructor(
    public id?: number,
    public name?: string,
    public address?: string,
  ) {}

  static mapFromForm(input: any): Parish {
    const p: Parish = new Parish();
    p.name = input.result.name;
    p.address = input.result.address;
    return p;
  }
}
