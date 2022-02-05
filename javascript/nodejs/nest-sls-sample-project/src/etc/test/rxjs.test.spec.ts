import { Observable, from, of } from 'rxjs';

describe('RxJs Test', () => {
  // beforeEach(() => {
  // });

  // https://medium.com/@kangsuroro2907/rxjs%EB%9E%80-2-operators-43eadd21eea0?p=43eadd21eea0
  describe('RxJs Test', () => {
    it('observable test', async () => {
      const observable: Observable<any> = new Observable((subscriber) => {
        subscriber.next(1);
        subscriber.next(2);
        subscriber.next(3);
        subscriber.next([5, 4, 3, 2, 1]);
        subscriber.complete();
      });

      observable
        .subscribe({
          next: (value) => {
            if (value instanceof Array) {
              console.log('ARRY', value);
            } else {
              console.log('Not Arry', value);
            }
          },
          error: (error) => {
            console.error(error);
          },
          complete: () => {
            console.log('completed');
          },
        })
        .unsubscribe();
    });

    it('of test', async () => {
      of(0, 2, 3, 4).subscribe({
        next: console.log,
        error: console.error,
        complete: () => {
          console.log('complete!');
        },
      });
    });

    it('from test', async () => {
      // Array
      from([0, 2, 3]).subscribe((value) => console.log(value));
      // Map
      from(
        new Map([
          ['key1', 'value1'],
          ['key2', 'value2'],
          ['key3', 'value3'],
        ]),
      ).subscribe((value) => console.log(value));
      // Promise
      from(Promise.resolve([100, 200])).subscribe((value) =>
        console.log(value),
      );
    });
  });
});
