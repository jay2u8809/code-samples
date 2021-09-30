import csvParser = require('csv-parser');
// import csvWriter = require('csv-writer');
import fs from 'fs';
import { plainToClass } from 'class-transformer';
// import { createObjectCsvWriter as createCsvWriter } from 'csv-writer';

const csvFilePath: string = __dirname + '/files/csv/';
const jsonFilePath: string = __dirname + '/files/json/';

enum Separator {
  LF = '\n',
  CR = '\r',
  CRLF= '\r\n',
  COMMA = ',',
  VERTICAL = '|'
}

describe.skip('CSV File Export (csv-writer) Test', () => {
  let createCsvWriter: any;
  let data: any;
  let TAG: string;

  beforeEach( () => {
    /**
     * ref:
     *  https://www.npmjs.com/package/csv-writer/v/1.0.1
     *  https://blog.katsubemakito.net/nodejs/csv-writer
     *  https://tech.atsu-maru.co.jp/entry/2017/09/02/070000
     *  https://qiita.com/banaoh/items/4119c0e23053b1cfa80b
     */

    createCsvWriter = require('csv-writer').createObjectCsvWriter;
    // dummy data
    data = [
      { id: 'id-001', nickname: 'nickname1', email: 'emailtest1@testmail1.com', createdAt: new Date().toISOString()  },
      { id: 'id-002', nickname: 'nickname2', email: 'emailtest2@testmail2.com', createdAt: new Date().toISOString()  },
      { id: 'id-003', nickname: 'nickname3', email: 'emailtest3@testmail3.com', createdAt: new Date().toISOString()  },
    ];
    // tag
    TAG = 'CSV_FILE_EXPORT_(csv-writer)';
  });

  it('no header name test', async () => {
    // generate param
    const csvName: string = 'convertToCsv_' + new Date().getTime().toString() + '.csv';
    console.log(TAG, `CSV FILE NAME: ${csvName}`);
    const param: any = {
      path: csvFilePath + csvName,
      header: ['id', 'nickname', 'email', 'createdAt'],
      encoding: 'utf8',
      // fieldDelimiter: Separator.COMMA,
      // recordDelimiter: Separator.CRLF,
      alwaysQuote: true,
      // append: false,
    };
    // make csv file
    const csvWriter = createCsvWriter(param);
    await csvWriter.writeRecords(data)
      .then(() => {
        console.log(`${data}`);
      });

    expect(true);
  });

  it('specify header name test', async () => {
    // generate param
    const csvName: string = 'convertToCsv_' + new Date().getTime().toString() + '.csv';
    console.log(TAG, `CSV FILE NAME: ${csvName}`);
    const param: any = {
      path: csvFilePath + csvName,
      header: [
        { id: 'id',         title: 'ID' },
        { id: 'nickname',   title: 'NickName' },
        { id: 'email',      title: 'E-Mail'  },
        { id: 'createdAt',  title: 'CreatedAt'},
      ],
      encoding: 'utf8',
      // fieldDelimiter: Separator.COMMA,
      // recordDelimiter: Separator.CRLF,
      alwaysQuote: true,
      // append: false,
    };
    // make csv file
    const csvWriter = createCsvWriter(param);
    await csvWriter.writeRecords(data)
      .then(() => {
        console.log(`${data}`);
      });

    expect(true);
  });

  it('specify header name2 test', async () => {
    // generate param
    const csvName: string = 'convertToCsv_' + new Date().getTime().toString() + '.csv';
    console.log(TAG, `CSV FILE NAME: ${csvName}`);
    const headers: any[] = [];
    Object.keys(data[0]).forEach(h => {
      headers.push({
        id: `${h}`,
        title: `${h.toUpperCase()}`,
      });
    });
    const param: any = {
      path: csvFilePath + csvName,
      header: headers,
      encoding: 'utf8',
      // fieldDelimiter: Separator.COMMA,
      // recordDelimiter: Separator.CRLF,
      alwaysQuote: true,
      // append: false,
    };
    // make csv file
    const csvWriter = createCsvWriter(param);
    await csvWriter.writeRecords(data)
      .then(() => {
        console.log(`${data}`);
      });

    expect(true);
  });

  it('read json file test', async () => {
    // generate param
    const csvName: string = 'convertToCsv_' + new Date().getTime().toString() + '.csv';
    console.log(TAG, `CSV FILE NAME: ${csvName}`);
    data = await JSON.parse(fs.readFileSync(jsonFilePath + 'sample.json', {encoding: 'utf8'}));
    const headers: any[] = [];
    Object.keys(data[0]).forEach(h => {
      headers.push({
        id: `${h}`,
        title: `${h.toUpperCase()}`,
      });
    });
    const param: any = {
      path: csvFilePath + csvName,
      header: headers,
      encoding: 'utf8',
      // fieldDelimiter: Separator.COMMA,
      // recordDelimiter: Separator.CRLF,
      alwaysQuote: true,
      // append: false,
    };
    // make csv file
    const csvWriter = createCsvWriter(param);
    await csvWriter.writeRecords(data)
      .then(() => {
        console.log(`${data}`);
      });

    expect(true);
  });
});


describe.skip('CSV File Export Test', () => {
  let data: any;
  let TAG: string;
  const convertToCsv = (data: any): boolean => {
    const title: string[] = [];
    Object.keys(data[0]).forEach(h => {
      title.push(`"${h.toUpperCase()}"`);
    });
    const header: string = title.join(',');

    const csvLines: string[] = [header];
    data.map(f => {
      const values: string[] = [];
      Object.values(f).forEach(v => {
        values.push(`"${v}"`);
      });
      csvLines.push(values.join(','));
    });

    const csvName: string = 'convertToCsv_' + new Date().getTime().toString() + '.csv';
    console.log(TAG, `CSV FILE NAME: ${csvName}`);
    fs.writeFileSync(csvFilePath + csvName, csvLines.join('\n'));

    return true;
  };

  beforeEach( () => {
    // dummy data
    data = [
      { id: 'id-001', nickname: 'nickname1', email: 'emailtest1@testmail1.com', createdAt: new Date().toISOString()  },
      { id: 'id-002', nickname: 'nickname2', email: 'emailtest2@testmail2.com', createdAt: new Date().toISOString()  },
      { id: 'id-003', nickname: 'nickname3', email: 'emailtest3@testmail3.com', createdAt: new Date().toISOString()  },
    ];
    // tag
    TAG = 'CSV_FILE_EXPORT';
  });

  it('normal test', async () => {

    await convertToCsv(data);

    expect(true);
  });

  it('read json file test', async () => {
    data = await JSON.parse(fs.readFileSync(jsonFilePath + 'sample.json', {encoding: 'utf8'}));
    await convertToCsv(data);

    expect(true);
  });
});
