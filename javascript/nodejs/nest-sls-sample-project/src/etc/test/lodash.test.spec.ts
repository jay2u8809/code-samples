import _ from 'lodash';
import { DayjsUtil } from '../../common/Dayjs.util';

const TAG = 'LODASH_TEST';

describe('lodash Test', () => {
  beforeEach(() => {
    console.log(TAG, DayjsUtil.koreaDate().toISOString());
  });

  // Find Index
  it('findIndex test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40 },
      { name: 'james', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26 },
      { name: 'tom', job: 'analyst', age: 36 },
      { name: 'john', job: 'salesman', age: 32 },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result = 0;

    result = _.findIndex(myFriends, (friend: any): boolean => {
      return friend.age === 26;
    });
    console.log(TAG, `FIND INDEX METHOD, Callback: ${result}`);
    // [===== RESULT =====] 2

    result = _.findIndex(myFriends, {
      name: 'john',
      job: 'salesman',
      age: 32,
    });
    console.log(TAG, `FIND INDEX METHOD, Object: ${result}`);
    // [===== RESULT =====] 4

    result = _.findIndex(myFriends, { age: 46 });
    console.log(TAG, `FIND INDEX METHOD, Key: ${result}`);
    // [===== RESULT =====] 6

    result = _.findIndex(myFriends, { name: 'jake' });
    console.log(TAG, `FIND INDEX METHOD, Key: ${result}`);
    // [===== RESULT =====] -1
  });

  // Remove
  it('remove test', async () => {
    // sample data
    const arr: number[] = [1, 2, 3, 4, 5];
    const result: number[] = _.remove(arr, (num: number): boolean => {
      return num % 2 === 0;
    });
    console.log(TAG, `REMOVE METHOD, Original: ${arr}`);
    // [===== RESULT =====] 1,3,5
    console.log(TAG, `REMOVE METHOD, Result: ${result}`);
    // [===== RESULT =====] 2,4
  });

  // Find
  it('find test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40 },
      { name: 'james', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26 },
      { name: 'tom', job: 'analyst', age: 36 },
      { name: 'john', job: 'salesman', age: 32 },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result: any;

    result = _.find(myFriends, (friend: any): boolean => {
      return friend.age < 35;
    });
    console.log(TAG, `FIND METHOD, Callback: `, result);
    // [===== RESULT =====] { name: 'james', job: 'web designer', age: 23 }

    result = _.find(myFriends, { name: 'tom', job: 'analyst', age: 36 });
    console.log(TAG, `FIND METHOD, Object: `, result);
    // [===== RESULT =====] {  name: 'tom', job: 'analyst', age: 36 }

    result = _.find(myFriends, { name: 'jake', job: 'part', age: 33 });
    console.log(TAG, `FIND METHOD, Object: `, result);
    // [===== RESULT =====] undefined
  });

  it('filter test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40 },
      { name: 'james', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26 },
      { name: 'tom', job: 'analyst', age: 36 },
      { name: 'john', job: 'salesman', age: 32 },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result: any;

    result = _.filter(myFriends, (friend: any): boolean => {
      return friend.age < 30;
    });
    console.log(TAG, `FILTER METHOD, Callback: `, result);
    /*
    [===== RESULT =====]
      [
        { name: 'james', job: 'web designer', age: 23 },
        { name: 'fred', job: 'musician', age: 26 }
      ]
    */

    result = _.filter(myFriends, { job: 'analyst' });
    console.log(TAG, `FILTER METHOD, Object: `, result);
    // [===== RESULT =====] [ { name: 'tom', job: 'analyst', age: 36 } ]

    result = _.filter(myFriends, { name: 'jake' });
    console.log(TAG, `FILTER METHOD, Object: `, result);
    // [===== RESULT =====] []
  });

  it('map test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40 },
      { name: 'james', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26 },
      { name: 'tom', job: 'analyst', age: 36 },
      { name: 'john', job: 'salesman', age: 32 },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result: any;

    result = _.map(myFriends, (friend: any): string => {
      return friend.name;
    });
    console.log(TAG, `MAP METHOD, Callback: `, result);
    /*
    [===== RESULT =====]
      [
        'mike', 'james',
        'fred', 'tom',
        'john', 'taylor',
        'leo'
      ]
    */

    result = _.map(myFriends, 'job');
    console.log(TAG, `MAP METHOD, Object: `, result);
    /*
    [===== RESULT =====]
      [
        'pd',
        'web designer',
        'musician',
        'analyst',
        'salesman',
        'barista',
        'chef'
      ]
    */

    result = _.map(myFriends, (friend: any): number => {
      return friend.age * 10;
    });
    console.log(TAG, `MAP METHOD, Callback: `, result);
    /*
    [===== RESULT =====]
      [
        400, 230, 260,
        360, 320, 420,
        460
      ]
    */
  });

  it('include test', async () => {
    // sample data
    const myFriend: any = { name: 'leo', job: 'chef', age: 46 };
    let result: any;

    result = _.includes(myFriend, 'leo');
    console.log(TAG, `INCLUDES METHOD: `, result);
    // [===== RESULT =====] true

    result = _.includes(myFriend, 'jake');
    console.log(TAG, `INCLUDES METHOD: `, result);
    // [===== RESULT =====] false

    result = _.includes([1, 2, 3], 1);
    console.log(TAG, `INCLUDES METHOD: `, result);
    // [===== RESULT =====] true

    result = _.includes('spring boot', 'ng bo');
    console.log(TAG, `INCLUDES METHOD: `, result);
    // [===== RESULT =====] true
  });

  it('fill test', async () => {
    // sample data
    const result = _.fill(Array(3), 'leo');
    console.log(TAG, `FILL METHOD: `, result);
    // [===== RESULT =====] [ 'leo', 'leo', 'leo' ]
  });

  it('omit and remove test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40 },
      { name: 'james', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26 },
      { name: 'tom', job: 'analyst', age: 36 },
      { name: 'john', job: 'salesman', age: 32 },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result: any;

    // Target: obj
    result = _.omit(myFriends[0], 'age');
    console.log(TAG, `OMIT METHOD: `, result);
    // [===== RESULT =====] { name: 'mike', job: 'pd' }
    console.log(TAG, `OMIT METHOD: `, myFriends[0]);
    // [===== RESULT =====] { name: 'mike', job: 'pd', age: 40 }

    // Target: Array
    result = _.remove(myFriends, (friend: any): boolean => {
      return friend.age > 45;
    });
    console.log(TAG, `REMOVE METHOD: `, result);
    // [===== RESULT =====] [ { name: 'leo', job: 'chef', age: 46 } ]
    console.log(TAG, `REMOVE METHOD: `, myFriends);
    /*
    [===== RESULT =====]
      [
        { name: 'mike', job: 'pd', age: 40 },
        { name: 'james', job: 'web designer', age: 23 },
        { name: 'fred', job: 'musician', age: 26 },
        { name: 'tom', job: 'analyst', age: 36 },
        { name: 'john', job: 'salesman', age: 32 },
        { name: 'taylor', job: 'barista', age: 42 }
      ]
    */
  });

  it('join test', async () => {
    const result: any = _.join([1, 2, 4, 6, 5], ':');
    console.log(TAG, 'SAMPLE TEST: ', result, typeof result);
    // [===== RESULT =====] 1:2:4:6:5 string
  });

  it('get test', async () => {
    // sample data
    const sample: any = {
      a: [
        {
          b: {
            c: 3,
          },
        },
      ],
    };
    let result: any;

    result = _.get(sample, 'a[0].b.c');
    console.log(TAG, 'GET TEST: ', result, typeof result);
    // [===== RESULT =====] 3 number

    result = _.get(sample, 'a[0].b');
    console.log(TAG, 'GET TEST: ', result, typeof result);
    // [===== RESULT =====] { c: 3 } object
  });

  // remove dup
  it('uniqBy test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40 },
      { name: 'mike', job: 'web designer', age: 23 },
      { name: 'fred', job: 'musician', age: 26 },
      { name: 'tom', job: 'analyst', age: 26 },
      { name: 'john', job: 'salesman', age: 26 },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    let result: any;

    result = _.uniqBy(myFriends, 'name');
    console.log(TAG, 'UNIQ TEST: ', result, typeof result);
    /*
    [===== RESULT =====]
      [
        { name: 'mike', job: 'pd', age: 40 },
        { name: 'fred', job: 'musician', age: 26 },
        { name: 'tom', job: 'analyst', age: 36 },
        { name: 'john', job: 'salesman', age: 32 },
        { name: 'taylor', job: 'barista', age: 42 },
        { name: 'leo', job: 'chef', age: 46 }
      ] object
    */
    console.log(TAG, 'UNIQ TEST: ', myFriends);
    /*
    [===== RESULT =====]
      [
        { name: 'mike', job: 'pd', age: 40 },
        { name: 'mike', job: 'web designer', age: 23 },
        { name: 'fred', job: 'musician', age: 26 },
        { name: 'tom', job: 'analyst', age: 36 },
        { name: 'john', job: 'salesman', age: 32 },
        { name: 'taylor', job: 'barista', age: 42 },
        { name: 'leo', job: 'chef', age: 46 }
      ]
    */

    result = _.uniqBy(myFriends, 'age');
    console.log(TAG, 'UNIQ TEST: ', result, typeof result);
    /*
    [===== RESULT =====]
      [
        { name: 'mike', job: 'pd', age: 40 },
        { name: 'mike', job: 'web designer', age: 23 },
        { name: 'fred', job: 'musician', age: 26 },
        { name: 'taylor', job: 'barista', age: 42 },
        { name: 'leo', job: 'chef', age: 46 }
      ] object
    */
    console.log(TAG, 'UNIQ TEST: ', myFriends);
    /*
    [===== RESULT =====]
      [
        { name: 'mike', job: 'pd', age: 40 },
        { name: 'mike', job: 'web designer', age: 23 },
        { name: 'fred', job: 'musician', age: 26 },
        { name: 'tom', job: 'analyst', age: 36 },
        { name: 'john', job: 'salesman', age: 32 },
        { name: 'taylor', job: 'barista', age: 42 },
        { name: 'leo', job: 'chef', age: 46 }
      ]
    */
  });

  it('groupBy test', async () => {
    // sample data
    const myFriends: any[] = [
      { name: 'mike', job: 'pd', age: 40 },
      { name: 'mike', job: 'pd', age: 23 },
      { name: 'fred', job: 'musician', age: 26 },
      { name: 'tom', job: 'analyst', age: 26 },
      { name: 'john', job: 'chef', age: 31 },
      { name: 'taylor', job: 'barista', age: 42 },
      { name: 'leo', job: 'chef', age: 46 },
    ];
    const result: any = _.groupBy(myFriends, 'job');
    console.log(TAG, result);
    /*
    [===== RESULT =====]
      {
        pd: [
          { name: 'mike', job: 'pd', age: 40 },
          { name: 'mike', job: 'pd', age: 23 }
        ],
          musician: [ { name: 'fred', job: 'musician', age: 26 } ],
        analyst: [ { name: 'tom', job: 'analyst', age: 26 } ],
        chef: [
        { name: 'john', job: 'chef', age: 31 },
        { name: 'leo', job: 'chef', age: 46 }
      ],
        barista: [ { name: 'taylor', job: 'barista', age: 42 } ]
      }
    */
  });
});
