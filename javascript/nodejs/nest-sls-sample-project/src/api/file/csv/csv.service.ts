import { Injectable } from '@nestjs/common';
import fs from 'fs';
import { DataType } from '../../../common/code/data-type';

const csvFilePath: string = __dirname + '/temp/';

const TAG = 'CSV_SERVICE';

@Injectable()
export class CsvService {
  async convertToCsv(data: any, type: DataType): Promise<any> {
    let result: any;
    switch (type) {
      case DataType.JSON:
        result = await this.convertJsonToCsv(data);
        break;
    }
    // generate file
    try {
      const csvName: string =
        `convert${type}ToCsv_` + new Date().getTime().toString() + '.csv';
      // check exist directory
      const isExistFolder: boolean = fs.existsSync(csvFilePath);
      if (!isExistFolder) {
        console.log(TAG, 'make directory', csvFilePath);
        fs.mkdirSync(csvFilePath);
      }
      // write csv file
      fs.writeFileSync(csvFilePath + csvName, result.join('\n'));
      console.log(TAG, `CSV FILE NAME: ${csvName}`);
      return true;
    } catch (e) {
      console.error(`Fail to convert to csv`, e);
      return false;
    }
  }

  // === private ===
  private convertJsonToCsv = (data: any): any => {
    // title
    const title: string[] = [];
    Object.keys(data[0]).forEach((key) => {
      title.push(`"${key.toUpperCase()}"`);
    });
    const headers: string = title.join(',');
    // contents
    const csvLines: string[] = [headers];
    data.map((f) => {
      const values: string[] = [];
      Object.values(f).forEach((value) => {
        values.push(`"${value}"`);
      });
      csvLines.push(values.join(','));
    });
    return csvLines;
  };
}
