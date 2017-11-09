import { HedgehogWebappPage } from './app.po';

describe('hedgehog-webapp App', () => {
  let page: HedgehogWebappPage;

  beforeEach(() => {
    page = new HedgehogWebappPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
