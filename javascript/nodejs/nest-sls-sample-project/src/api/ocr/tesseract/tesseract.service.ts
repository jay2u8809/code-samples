import { Injectable } from '@nestjs/common';
import { createWorker } from 'tesseract.js';
import fs from 'fs';
import { LangType } from '../../../common/code/lang-type';

const TAG = 'TesseractService';

@Injectable()
export class TesseractService {
  async detectTextFromUrl(url: string, lang?: LangType): Promise<string> {
    const raw = await this.detect(url, lang);
    // const result = raw.replace(/\s/g, '');
    console.log(TAG, 'detect-by-url-result', raw);
    return raw;
  }

  async detectTextFromFilePath(path: string, lang?: LangType): Promise<string> {
    const isExist = fs.existsSync(path);
    if (!isExist) {
      throw new Error(`${path} is not exist`);
    }
    const result: string = await new Promise((resolve, reject) => {
      fs.createReadStream(path)
        .on('error', (err) => {
          console.error(TAG, 'read-file-error', JSON.stringify(err));
          reject(err);
        })
        .on('data', async (file: Buffer) => {
          const raw = await this.detect(file, lang);
          // const result = raw.replace(/\s/g, '');
          resolve(raw);
        });
    });
    console.log(TAG, 'detect-by-file-path-result', result);
    return result;
  }

  async detectTextFromFile(data: Buffer, lang?: LangType): Promise<string> {
    if (!data) {
      throw new Error(`file is not exist`);
    }
    const raw = await this.detect(data, lang);
    console.log(TAG, 'detect-by-file-result', raw);
    return raw;
  }

  // === private ===
  private async detect(
    file: Buffer | string,
    lang: LangType = LangType.ENG,
  ): Promise<string> {
    // init
    const worker = createWorker({
      // logger: (m) => console.log(m),
    });
    // read image
    await worker.load();
    await worker.loadLanguage(lang);
    await worker.initialize(lang);
    const {
      data: { text },
    } = await worker.recognize(file);
    console.log(TAG, 'tesseract-result-text', JSON.stringify(text, null, 2));
    await worker.terminate();

    return text;
  }
}
