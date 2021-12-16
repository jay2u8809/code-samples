import { Test, TestingModule } from '@nestjs/testing';
import { TestService } from './test.service';
import _ from 'lodash';
import { from, Observable, of } from 'rxjs';

describe('TestService', () => {
  let service: TestService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [TestService],
    }).compile();

    service = module.get<TestService>(TestService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  describe('lodash Test', () => {
    // Find Index
    it ('findIndex test', async () => {
      // sample data
      const myFriends: any[] = [
        { name: 'mike', job: 'pd', age: 40  },
        { name: 'james', job: 'web designer', age: 23 },
        { name: 'fred', job: 'musician', age: 26  },
        { name: 'tom', job: 'analyst', age: 36  },
        { name: 'john', job: 'salesman', age: 32  },
        { name: 'taylor', job: 'barista', age: 42 },
        { name: 'leo', job: 'chef', age: 46 },
      ];
      let result: number = 0;

      result = _.findIndex(myFriends, (friend: any): boolean => {
        return friend.age === 26;
      });
      console.log(`FIND INDEX METHOD, Callback: ${result}`);  // 2

      result = _.findIndex(myFriends, {
        name: 'john',
        job: 'salesman',
        age: 32,
      });
      console.log(`FIND INDEX METHOD, Object: ${result}`);  // 4

      result = _.findIndex(myFriends, { age: 46 });
      console.log(`FIND INDEX METHOD, Key: ${result}`);  // 6

      result = _.findIndex(myFriends, { name: 'jake'  });
      console.log(`FIND INDEX METHOD, Key: ${result}`);  // -1
    });
  });

  // Remove
  it ('remove test', async () => {
    // sample data
    const arr: number[] = [1, 2, 3, 4, 5];
    let result: number[] = _.remove(arr, (num: number): boolean => {
      return num % 2 === 0;
    });
    console.log(`REMOVE METHOD, Original: ${arr}`);
    console.log(`REMOVE METHOD, Result: ${result}`);
  });

  // Every
  it ('every test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40  },
      { name: 'james', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26  },
      { name: 'tom', job: 'analyst', age: 36  },
      { name: 'john', job: 'salesman', age: 32  },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result: boolean = false;

    result = _.every(myFriends, {  name: 'tom', job: 'analyst', age: 36 }); // true
    console.log(`EVERY METHOD, Object: ${result}`);

    result = _.every(myFriends, {  name: 'tom', job: 'musician', age: 26 }); // false
    console.log(`EVERY METHOD, Object: ${result}`);
  });

  // Find
  it ('find test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40  },
      { name: 'james', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26  },
      { name: 'tom', job: 'analyst', age: 36  },
      { name: 'john', job: 'salesman', age: 32  },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result: any;

    result = _.find(myFriends, (friend: any): boolean => {
      return friend.age < 35;
    }); // { name: 'james', job: 'web designer', age: 23 }
    console.log(`FIND METHOD, Callback: `, result);

    result = _.find(myFriends, {  name: 'tom', job: 'analyst', age: 36 }); // {  name: 'tom', job: 'analyst', age: 36 }
    console.log(`FIND METHOD, Object: `, result);

    result = _.find(myFriends, {  name: 'jake', job: 'part', age: 33 }); // undefined
    console.log(`FIND METHOD, Object: `, result);
  });

  it ('filter test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40  },
      { name: 'james', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26  },
      { name: 'tom', job: 'analyst', age: 36  },
      { name: 'john', job: 'salesman', age: 32  },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result: any;

    result = _.filter(myFriends, (friend: any): boolean => {
      return friend.age < 30;
    });
    console.log(`FILTER METHOD, Callback: `, result);
    // [
    //   { name: 'james', job: 'web designer', age: 23 },
    //   { name: 'fred', job: 'musician', age: 26 }
    // ]

    result = _.filter(myFriends, { job: 'analyst' });
    console.log(`FILTER METHOD, Object: `, result); //  [ { name: 'tom', job: 'analyst', age: 36 } ]

    result = _.filter(myFriends, { name: 'jake' });
    console.log(`FILTER METHOD, Object: `, result); // []
  });

  it ('map test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40  },
      { name: 'james', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26  },
      { name: 'tom', job: 'analyst', age: 36  },
      { name: 'john', job: 'salesman', age: 32  },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result: any;

    result = _.map(myFriends, (friend: any): string => {
      return friend.name;
    });
    console.log(`MAP METHOD, Callback: `, result);

    result = _.map(myFriends, 'job');
    console.log(`MAP METHOD, Object: `, result); 

    result = _.map(myFriends, (friend: any): number => {
      return friend.age * 10;
    });
    console.log(`MAP METHOD, Callback: `, result); 
  });

  it ('include test', async () => {
    // sample data
    const myFriend: any = { name: 'leo', job: 'chef', age: 46 };
    let result: any;

    result = _.includes(myFriend, 'leo');
    console.log(`INCLUDES METHOD: `, result); // true

    result = _.includes(myFriend, 'jake');
    console.log(`INCLUDES METHOD: `, result); // false

    result = _.includes([1, 2, 3], 1);
    console.log(`INCLUDES METHOD: `, result); // true

    result = _.includes('spring boot', 'ng bo');
    console.log(`INCLUDES METHOD: `, result); // true
  });

  it ('fill test', async () => {
    // sample data
    let result: any;

    result = _.fill(Array(3), 'leo');
    console.log(`FILL METHOD: `, result); //  [ 'leo', 'leo', 'leo' ]
  });

  it ('omit and remove test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40  },
      { name: 'james', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26  },
      { name: 'tom', job: 'analyst', age: 36  },
      { name: 'john', job: 'salesman', age: 32  },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result: any;

    // Target: obj
    result = _.omit(myFriends[0], 'age');
    console.log(`OMIT METHOD: `, result);         // { name: 'mike', job: 'pd' 
    console.log(`OMIT METHOD: `, myFriends[0]);   // { name: 'mike', job: 'pd', age: 40 }

    // Target: Array
    result = _.remove(myFriends, (friend: any): boolean => {
      return friend.age > 45;
    });
    console.log(`REMOVE METHOD: `, result);       // [ { name: 'leo', job: 'chef', age: 46 } ]
    console.log(`REMOVE METHOD: `, myFriends);    
    // [
    //   { name: 'mike', job: 'pd', age: 40 },
    //   { name: 'james', job: 'web designer', age: 23 },
    //   { name: 'fred', job: 'musician', age: 26 },
    //   { name: 'tom', job: 'analyst', age: 36 },
    //   { name: 'john', job: 'salesman', age: 32 },
    //   { name: 'taylor', job: 'barista', age: 42 }
    // ]
  });

  it ('join test', async () => {
    const result: any = _.join([1, 2, 4, 6, 5], ':');
    console.log('SAMPLE TEST: ', result, typeof result);  // 1:2:4:6:5 string
  });

  it ('get test', async () => {
    // sample data
    const sample: any = { 
      'a': [
        { 
          'b' : { 
            'c' : 3 
          },
        },
      ],
    };
    let result: any;

    result = _.get(sample, 'a[0].b.c');
    console.log('GET TEST: ', result, typeof result);  // 3 number

    result = _.get(sample, 'a[0].b');
    console.log('GET TEST: ', result, typeof result);  // { c: 3 } object
  });

  // remove dup
  it ('uniqBy test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40  },
      { name: 'mike', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26  },
      { name: 'tom', job: 'analyst', age: 36  },
      { name: 'john', job: 'salesman', age: 32  },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result: any;

    result = _.uniqBy(myFriends, 'name');
    console.log('UNIQ TEST: ', result, typeof result);
    // [
    //   { name: 'mike', job: 'pd', age: 40 },
    //   { name: 'fred', job: 'musician', age: 26 },
    //   { name: 'tom', job: 'analyst', age: 36 },
    //   { name: 'john', job: 'salesman', age: 32 },
    //   { name: 'taylor', job: 'barista', age: 42 },
    //   { name: 'leo', job: 'chef', age: 46 }
    // ] object
    console.log('UNIQ TEST: ', myFriends);
    // [
    //   { name: 'mike', job: 'pd', age: 40 },
    //   { name: 'mike', job: 'web designer', age: 23 },
    //   { name: 'fred', job: 'musician', age: 26 },
    //   { name: 'tom', job: 'analyst', age: 36 },
    //   { name: 'john', job: 'salesman', age: 32 },
    //   { name: 'taylor', job: 'barista', age: 42 },
    //   { name: 'leo', job: 'chef', age: 46 }
    // ]

    result = _.uniqBy(myFriends, 'age');
    console.log('UNIQ TEST: ', result, typeof result);
    // [
    //   { name: 'mike', job: 'pd', age: 40 },
    //   { name: 'mike', job: 'web designer', age: 23 },
    //   { name: 'fred', job: 'musician', age: 26 },
    //   { name: 'tom', job: 'analyst', age: 36 },
    //   { name: 'john', job: 'salesman', age: 32 },
    //   { name: 'taylor', job: 'barista', age: 42 },
    //   { name: 'leo', job: 'chef', age: 46 }
    // ] object
    console.log('UNIQ TEST: ', myFriends);
    // [
    //   { name: 'mike', job: 'pd', age: 40 },
    //   { name: 'mike', job: 'web designer', age: 23 },
    //   { name: 'fred', job: 'musician', age: 26 },
    //   { name: 'tom', job: 'analyst', age: 36 },
    //   { name: 'john', job: 'salesman', age: 32 },
    //   { name: 'taylor', job: 'barista', age: 42 },
    //   { name: 'leo', job: 'chef', age: 46 }
    // ]
  });

  // https://medium.com/@kangsuroro2907/rxjs%EB%9E%80-2-operators-43eadd21eea0?p=43eadd21eea0
  describe('RxJs Test', () => {
    it ('observable test', async () => {
      const observable = new Observable(subscriber => {
        subscriber.next(1);
        subscriber.next(2);
        subscriber.next(3);
        subscriber.next([5,4,3,2,1]);
        setTimeout(() => {
          subscriber.next(4);
          subscriber.complete();
        }, 1000);
      });
    });

    it ('of test', async () => {
      of(1, 2, 3, 4)
        .subscribe({
          next: console.log,
          error: console.error,
          complete: () => console.log('complete!')
        })
    });

    it ('from test', async () => {
      // Array
      from([1, 2, 3])
      .subscribe((value) => console.log(value));

      // Obj
      from(new Map([[1, 2], [2, 4], [4, 8]]))
      .subscribe((value) => console.log(value));
      
      // Promise
      from(Promise.resolve(100))
      .subscribe((value) => console.log(value));
    });
  });
});
