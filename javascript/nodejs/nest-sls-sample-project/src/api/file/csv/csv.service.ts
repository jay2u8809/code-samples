import { Injectable } from '@nestjs/common';
import fs from 'fs';
import neatCsv from 'neat-csv';
import { json2csvAsync } from 'json-2-csv';

import { DataType } from '../../../common/code/data-type';

const tempSavePath: string = __dirname + '/temp/';
const fileSavePath: string = __dirname + '/storage/';

const TAG = 'CSV_SERVICE';

@Injectable()
export class CsvService {
  async convertToCsv(data: any, type: DataType): Promise<any> {
    let result: any;
    switch (type) {
      case DataType.JSON:
        // result = await this.convertJsonToCsv(data);
        result = await json2csvAsync(data, {
          emptyFieldValue: '',
          expandArrayObjects: true,
        });
        break;
    }

    try {
      // 0. filename
      const csvName: string =
        `convert${type}ToCsv_` + new Date().getTime().toString() + '.csv';
      // 1. check exist directory
      await this.checkExistDirectory(tempSavePath);
      // 2. write csv file
      fs.writeFileSync(tempSavePath + csvName, result);
      console.log(TAG, `csv-file-name: ${csvName}`);
      // 3. move csv file
      await this.moveFile(tempSavePath, csvName, fileSavePath);
      // return
      return true;
    } catch (e) {
      console.error(`Fail to convert to csv`, e);
      return false;
    }
  }

  async saveFile(file: Express.Multer.File, path?: string): Promise<boolean> {
    try {
      // 0. filename
      const savePath = path || tempSavePath;
      // 1. check exist directory
      await this.checkExistDirectory(savePath);
      console.log(TAG, 'file-save-path', savePath);
      // 2. write csv file
      fs.writeFileSync(`${savePath}${file.originalname}`, file.buffer);
      // 3. move csv file
      await this.moveFile(savePath, file.originalname, fileSavePath);
      // return
      return true;
    } catch (e) {
      console.error(TAG, 'fail-read-file', e);
      return false;
    }
  }

  async moveFile(
    srcPath: string,
    fileName: string,
    destPath?: string,
  ): Promise<boolean> {
    const src = srcPath + fileName;
    const dest = (destPath || fileSavePath) + fileName;
    try {
      // 1. check exist directory
      await this.checkExistDirectory(destPath || fileSavePath);
      // 2. copy file
      fs.copyFileSync(src, dest);
      // 3. remove temp file
      fs.rmSync(src);
      // return
      return true;
    } catch (e) {
      console.error(TAG, 'fail-move-file', e);
      return false;
    }
  }

  // === private ===
  // private convertJsonToCsv = (data: any): any => {
  //   // title
  //   const title: string[] = [];
  //   Object.keys(data[0]).forEach((key) => {
  //     title.push(`"${key.toUpperCase()}"`);
  //   });
  //   const headers: string = title.join(',');
  //   // contents
  //   const csvLines: string[] = [headers];
  //   data.map((f) => {
  //     const values: string[] = [];
  //     Object.values(f).forEach((value) => {
  //       values.push(`"${value}"`);
  //     });
  //     csvLines.push(values.join(','));
  //   });
  //   return csvLines.join('/n');
  // };

  private async checkExistDirectory(path: string): Promise<void> {
    const isExistFolder: boolean = fs.existsSync(path);
    if (!isExistFolder) {
      console.log(TAG, 'make-directory', path);
      fs.mkdirSync(path);
    }
  }
}
