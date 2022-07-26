'use strict';

import dayjs from 'dayjs';
import utc from 'dayjs/plugin/utc';
// import isoweek from 'dayjs/plugin/isoweek';
import cumtomParseFormat from 'dayjs/plugin/customParseFormat';
import { BadRequestException } from '@nestjs/common';

dayjs.extend(utc);
// dayjs.extend(isoweek);
dayjs.extend(cumtomParseFormat);

export class DayjsUtil {
  static koreaDate(): dayjs.Dayjs {
    return this.localDate(9);
  }

  static localDate(utcOffset: number): dayjs.Dayjs {
    return dayjs().utcOffset(utcOffset);
  }

  static koreaDateFromUnix(timeStamp: number): dayjs.Dayjs {
    return dayjs.unix(timeStamp).utcOffset(9);
  }

  static parseLocalDate(date: string, utcOffset: number): dayjs.Dayjs {
    const result = dayjs(date);
    if (!result.isValid()) {
      throw new BadRequestException();
    }
    return result.utcOffset(utcOffset);
  }

  static parseKoreaDate(date: string): dayjs.Dayjs {
    return DayjsUtil.parseLocalDate(date, 9);
  }

  static toKoreaStampDate(date: dayjs.Dayjs): string {
    return date.format('YYYY-MM-DD');
  }
}
