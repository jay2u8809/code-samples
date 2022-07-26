import { Test, TestingModule } from '@nestjs/testing';
import { HashUtils } from './hash.utils';

const TAG = 'HashUtilsTest';

describe('Hash Uitls Test', () => {
  let service: HashUtils;
  const pw = 'abcdef1234!';

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [HashUtils],
    }).compile();

    service = module.get<HashUtils>(HashUtils);
  });

  it.skip('generate hash test', () => {
    // const generated = service.generateHash(pw, 10);
    // console.log(TAG, 'generated-hash', generated);
  });

  it.skip('compare hash test', () => {
    // const hash = service.generateHash(pw);
    // console.log(TAG, 'generated-hash', hash);
    // const result: boolean = service.compareHash(pw, hash);
    // console.log(TAG, 'compare-result', result);
  });
});
