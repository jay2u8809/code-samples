import dayjs from 'dayjs';
import { DayjsUtil } from '../../utils/dayjs.utils';

const TAG = 'DayJsSpec';

/**
 * command
 * $> npm i dayjs
 * refs: https://github.com/iamkun/dayjs/blob/dev/docs/ko/README-ko.md
 * api docs kr: https://github.com/iamkun/dayjs/blob/dev/docs/ko/API-reference.md
 */

describe('TAG', () => {
  beforeEach(() => {
    console.log(TAG, DayjsUtil.koreaDate().toISOString());
  });

  it.skip('dayjs test', () => {
    console.log(TAG, `Today(Date) : ${new Date().toISOString()}`);
    // [===== RESULT =====] 2022-02-05T16:59:10.117Z

    const today = dayjs();
    console.log(TAG, `Today(DayJs) : ${today.toISOString()}`);
    // [===== RESULT =====] 2022-02-05T16:59:10.119Z
    console.log(TAG, `Basic Format : ${dayjs().format()}`);
    // [===== RESULT =====] 2022-02-06T01:59:10+09:00
    console.log(TAG, `Format : ${dayjs().format('YYYY-MM-DD, hh:mm:ss ddd')}`);
    // [===== RESULT =====] 2022-02-06, 01:59:10 Sun
    console.log(TAG, `Format DateType : ${dayjs(new Date()).format()}`);
    // [===== RESULT =====] 2022-02-06T01:59:10+09:00
    console.log(TAG, `-30 days : ${today.subtract(30, 'd').toISOString()}`);
    // [===== RESULT =====] 2022-01-06T16:59:10.119Z
    console.log(TAG, `-1 Month : ${today.subtract(1, 'M').toISOString()}`);
    // [===== RESULT =====] 2022-01-05T16:59:10.119Z
  });

  describe('Dayjs Util test', () => {
    it.skip('toKoreaAwsDate', async () => {
      let stamp = '2022-01-31T15:00:00.000Z';
      let result: dayjs.Dayjs = DayjsUtil.parseKoreaDate(stamp);
      console.log(TAG, DayjsUtil.toKoreaStampDate(result));
      // [===== RESULT =====] 2022-02-01
      stamp = '2022-01-31T02:00:00.000Z';
      result = DayjsUtil.parseKoreaDate(stamp);
      console.log(TAG, DayjsUtil.toKoreaStampDate(result));
      // [===== RESULT =====] 2022-01-31
    });
    it.skip('start of test', async () => {
      // const stamp = '2022-01-26T15:00:00.000Z';
      // let result: dayjs.Dayjs =
      //   DayjsUtil.parseKoreaDate(stamp).startOf('isoWeek');
      // console.log(TAG, DayjsUtil.toKoreaStampDate(result));
      // // [===== RESULT =====] 2022-01-24
      // result = DayjsUtil.parseKoreaDate(stamp).startOf('week');
      // console.log(TAG, DayjsUtil.toKoreaStampDate(result));
      // // [===== RESULT =====] 2022-01-23
    });
  });
});
