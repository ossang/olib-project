import { OlibFrontend2Page } from './app.po';

describe('olib-frontend2 App', () => {
  let page: OlibFrontend2Page;

  beforeEach(() => {
    page = new OlibFrontend2Page();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
