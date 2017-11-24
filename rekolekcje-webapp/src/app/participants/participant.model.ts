export class Participant {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public pesel?: number,
    public address?: string,
    public parish?: string
  ) {}
}
