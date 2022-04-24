import { Test, TestingModule } from '@nestjs/testing';
import { TesseractService } from './tesseract.service';
import { ApiModule } from '../../api.module';
import { createWorker } from 'tesseract.js';
import fs from 'fs';
import { LangType } from '../../../common/code/lang-type';

const TAG = 'TesseractServiceSpec';

describe('TesseractServiceSpec', () => {
  let service: TesseractService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      imports: [ApiModule],
      providers: [TesseractService],
    }).compile();

    service = module.get<TesseractService>(TesseractService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
    expect(true);
  });

  it.skip('sample', async () => {
    const worker = createWorker({
      logger: (m) => console.log(TAG, 'init-worker', m),
    });

    await (async () => {
      await worker.load();
      await worker.loadLanguage('eng');
      await worker.initialize('eng');
      const {
        data: { text },
      } = await worker.recognize(
        'https://tesseract.projectnaptha.com/img/eng_bw.png',
      );
      console.log(TAG, 'tesseract-result', text);
      await worker.terminate();
    })();

    expect(true);
  }, 2000000);

  it.skip(`tesseract url test`, async () => {
    const data = 'https://tesseract.projectnaptha.com/img/eng_bw.png';
    const result = await service.detectTextFromUrl(data);
    console.log(TAG, 'tesseract-result', JSON.stringify(result, null, 2));

    expect(true);
  }, 2000000);

  it.skip(`tesseract file path test`, async () => {
    const data = __dirname + '/samples/' + 'FILE_NAME';
    const result = await service.detectTextFromFilePath(data, LangType.KOR);
    console.log(TAG, 'tesseract-result', JSON.stringify(result, null, 2));

    expect(true);
  }, 2000000);

  it.skip(`tesseract file test`, async () => {
    const data = __dirname + '/samples/' + 'FILE_NAME';
    const file = fs.readFileSync(data);
    const result = await service.detectTextFromFile(file, LangType.KOR);
    console.log(TAG, 'tesseract-result', JSON.stringify(result, null, 2));

    expect(true);
  }, 2000000);
});
