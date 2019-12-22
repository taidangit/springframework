import { BookDescriptionPipe } from './book-description.pipe';

describe('BookDescriptionPipe', () => {
  it('create an instance', () => {
    const pipe = new BookDescriptionPipe();
    expect(pipe).toBeTruthy();
  });
});
