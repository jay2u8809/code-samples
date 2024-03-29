import csv from 'csv-parser';
import fs from 'fs';
import { DayjsUtil } from '../../utils/dayjs.utils';
import dayjs from 'dayjs';
import { createObjectCsvWriter as createCsvWriter } from 'csv-writer';

const TAG = 'CsvFileSpec';

const csvFilePath = __dirname + '/exports/csv/';
const jsonFilePath = __dirname + '/exports/json/';

enum Separator {
  LF = '\n',
  CR = '\r',
  CRLF = '\r\n',
  COMMA = ',',
  VERTICAL = '|',
}

describe(TAG, () => {
  let date;
  let tail;
  let fileName;

  beforeEach(() => {
    console.log(TAG, DayjsUtil.koreaDate().toISOString());
    date = dayjs().format('YYYY-MM-DD');
    tail = new Date().getTime().toString();
  });

  describe('Read/Write Csv File Test', () => {
    it.skip('Write csv file test - from object', async () => {
      // get data
      const data = generateArrayDummy(20);
      // make csv data
      const csv = generateCsvData(data, {
        headerFlag: true,
      });
      console.log(TAG, 'csv-file', csv);
      // write file
      fileName = `${csvFilePath}write_csv_file_object_${date}.csv`;
      fs.writeFileSync(fileName, csv, {
        encoding: 'utf8',
        flag: 'w',
      });
      expect(true);
    });

    it.skip('Write csv file test - from json', async () => {
      // get data
      const json = generateJsonDummy(); // json file
      const data = JSON.parse(json);
      console.log(TAG, 'pre-data', data);
      // make csv data
      const csv = generateCsvData(data);
      console.log(TAG, 'csv-file', csv);
      // write file
      fileName = `${csvFilePath}write_csv_file_json_${date}.csv`;
      fs.writeFileSync(fileName, csv, {
        encoding: 'utf8',
        flag: 'w',
      });
      expect(true);
    });

    it.skip('Write json file test', async () => {
      // get data
      const json = generateJsonDummy();
      // write file
      fileName = `${jsonFilePath}write_json_file_${date}.json`;
      fs.writeFileSync(fileName, json, {
        encoding: 'utf8',
        flag: 'w',
      });
      expect(true);
    });

    it.skip('Read file test - csv file', async () => {
      const exist = fs.existsSync(fileName);
      if (!exist) {
        expect(false);
        return;
      }
      // read file
      fileName = `${csvFilePath}write_csv_file_object_${date}.csv`;
      const file = fs.readFileSync(fileName, {
        encoding: 'utf8',
        flag: 'r',
      });
      const data = file.split(Separator.LF);
      data
        .filter((d) => d !== data[0])
        .forEach((d) => console.log(TAG, 'read-file-data', d));
      expect(true);
    });

    it.skip('Read file test - json file', async () => {
      const exist = fs.existsSync(fileName);
      if (!exist) {
        expect(false);
        return;
      }
      // read file
      fileName = `${jsonFilePath}write_json_file_${date}.json`;
      const file = fs.readFileSync(fileName, {
        encoding: 'utf8',
        flag: 'r',
      });
      const data = JSON.parse(file);
      data.forEach((d) => console.log(TAG, 'read-file-data', d));
      expect(true);
    });
  });

  describe('Csv Parser and NeatCsv Test', () => {
    /**
     * refs
     * https://www.npmjs.com/package/neat-csv
     * https://www.npmjs.com/package/csv-parser
     * https://github.com/mafintosh/csv-parser#options
     */
    it.skip('Csv Parser Test - Write', async () => {
      // get data
      const data = generateArrayDummy(30);
      // make csv data
      const csv = generateCsvData(data);
      console.log(TAG, 'csv-file', csv);
      // write file
      fileName = `${csvFilePath}write_csv_file_object_${date}.csv`;
      const writeStream = fs.createWriteStream(fileName, 'utf8');
      writeStream.write(csv);
      writeStream.end();
      expect(true);
    });

    it.only('Csv Parser Test - Read', async () => {
      fileName = `${csvFilePath}write_csv_file_object_${date}.csv`;
      console.log(TAG, 'read-file-name', fileName);
      const exist = fs.existsSync(fileName);
      if (!exist) {
        console.log(TAG, 'not-found-file', exist);
        expect(false);
        return;
      }
      // write stream
      const wStream = fs.createWriteStream(
        `${csvFilePath}write_csv_file_object_new_${date}.csv`,
        'utf8',
      );
      // read stream
      // 처리 우선 순위 : mapHeaders < headers
      fs.createReadStream(fileName)
        .on('error', (err) => console.error(TAG, 'ERROR', err))
        .pipe(
          csv({
            headers: false,
            // headers: ['Id', 'Nickname', 'Email', 'CreatedAt'],
            // mapHeaders: ({ header, index }) => header.toUpperCase(),
          }),
        )
        .on('data', (row) => {
          const num = ~~row[0].split('-')[1];
          // const num = ~~row['Id'].split('-')[1];
          if (num % 2 !== 0) {
            return;
          }
          // add data
          wStream.write(`${row[0]},${row[1]},${row[2]}${Separator.LF}`);
          // wStream.write(
          //   `${row['Id']},${row['Nickname']},${row['Email']}${Separator.LF}`,
          // );
        })
        .on('end', async () => {
          // finish write stream
          wStream.end();
          expect(true);
        });
    });
  });
  describe('Csv Writer Test', () => {
    /**
     * refs
     * https://www.npmjs.com/package/csv-writer/v/1.0.1
     * https://blog.katsubemakito.net/nodejs/csv-writer
     */

    it.skip('Write csv parser test', async () => {
      // get data
      const data = generateArrayDummy();
      // make header
      const headerFlag = false;
      const headers: any[] = Object.keys(data[0]).map((key) => {
        // no header
        if (!headerFlag) {
          return key;
        }
        // header customize
        return {
          id: `${key}`,
          title: `${key.toUpperCase()}`,
        };
      });
      fileName = `${csvFilePath}write_csv_parser_object_${date}.csv`;
      const params: any = {
        path: fileName,
        header: headers,
        encoding: 'utf8',
        fieldDelimiter: Separator.COMMA,
        recordDelimiter: Separator.LF,
        alwaysQuote: false,
        // append: false,
      };
      // make csv file
      const csvWriter = createCsvWriter(params);
      await csvWriter
        .writeRecords(data)
        .catch((err) => console.error(TAG, 'csv-parser-write-error', err));
      expect(true);
    });
  });

  const generateCsvData = (
    data: any,
    options?: {
      headerFlag?: boolean | undefined;
      separator?: Separator | undefined;
    } | null,
  ): string => {
    let header = undefined;
    if (!!options && !!options.headerFlag) {
      // make csv header
      const title: string[] = [];
      Object.keys(data[0]).forEach((h) => {
        title.push(`"${h.toUpperCase()}"`);
      });
      header = title.join(',');
    }
    // make csv data
    const csvLines: string[] = !!header ? [header] : [];
    data.forEach((data) => {
      const values: string[] = [];
      Object.values(data).forEach((value) => {
        values.push(`"${value}"`);
      });
      csvLines.push(values.join(','));
    });
    return csvLines.join(
      !!options && !!options.separator ? options.separator : Separator.LF,
    );
  };

  const generateArrayDummy = (count?: number): any => {
    const result: any[] = [];
    count = !!count ? count : 10;
    for (let idx = 0; idx < count; idx++) {
      const obj: any = {
        id: `id-${idx}`,
        nickname: `nickname${idx}`,
        email: `emailtest${idx}@testmail${idx}.com`,
        createdAt: dayjs().format('YYYY-MM-DD'),
      };
      result.push(obj);
    }
    return result;
  };

  const generateJsonDummy = (count?: number): any => {
    return JSON.stringify(generateArrayDummy(count));
  };
});
