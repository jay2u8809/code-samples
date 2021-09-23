import csvParser = require('csv-parser');
import csvWriter = require('csv-writer');
import fs from 'fs';
import { plainToClass } from 'class-transformer';

const filePath: string = __dirname + '/files/csv/';

test('CSV File Export Test', async () => {
  /**
   * ref:
   *  https://www.npmjs.com/package/csv-writer/v/1.0.1
   *  https://blog.katsubemakito.net/nodejs/csv-writer
   *  https://tech.atsu-maru.co.jp/entry/2017/09/02/070000
   *  https://qiita.com/banaoh/items/4119c0e23053b1cfa80b
   */
  const fileName: string = 'convertToCsv_' + new Date().getTime().toString() + '.csv';
  const param: any = {
    path: filePath + fileName,
    // header: ['id', 'nickName', 'emaill', 'createdAt'],
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
  const createCsvWriter = require('csv-writer').createObjectCsvWriter;
  const csvWriter = createCsvWriter(param);

  const data: any = [
    { id: 'id-001', nickname: 'nickname1', email: 'email1@test.com', createdAt: new Date().toISOString()  },
    { id: 'id-002', nickname: 'nickname2', email: 'email2@test.com', createdAt: new Date().toISOString()  },
    { id: 'id-003', nickname: 'nickname3', email: 'email3@test.com', createdAt: new Date().toISOString()  },
  ];

  await csvWriter.writeRecords(data)
    .then(() => {
      console.log(`${data}`);
    });

  expect(true);
});

enum Separator {
  LF = '\n',
  CR = '\r',
  CRLF= '\r\n',
  COMMA = ',',
  VERTICAL = '|'
}
