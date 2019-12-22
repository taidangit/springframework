import { BookCapitalizePipe } from './book-capitalize.pipe';

describe('BookCapitalizePipe', () => {
  it('create an instance', () => {
    const pipe = new BookCapitalizePipe();
    expect(pipe).toBeTruthy();
  });
});
