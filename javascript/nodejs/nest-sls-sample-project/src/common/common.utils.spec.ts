import {
  compareHash,
  generateHash,
  isEmpty,
  transferFromRaw,
} from './common.utils';

describe('CommonUtils', () => {
  beforeEach(async () => {
    console.log(`Common Utils Test`);
  });

  describe('isEmpty Method Test', () => {
    it('Object Null Check', () => {
      const nullObj = null;
      expect(isEmpty(nullObj)).toBeTruthy();

      const notNullObj = {};
      expect(isEmpty(notNullObj)).toBeFalsy();
    });

    it('Object Undefined Check', () => {
      const undefinedObj = undefined;
      expect(isEmpty(undefinedObj)).toBeTruthy();

      const notUndefinedObj = {};
      expect(isEmpty(notUndefinedObj)).toBeFalsy();
    });

    it('Array Null or Size 0 Check', () => {
      const lengthZeroArr = [];
      expect(isEmpty(lengthZeroArr)).toBeTruthy();

      const lengthIsNotZeroArr = ['Zero', 'One'];
      expect(isEmpty(lengthIsNotZeroArr)).toBeFalsy();
    });

    it('Map Null or Size 0 Check', () => {
      const sizeZeroMap = new Map<number, string>();
      expect(isEmpty(sizeZeroMap)).toBeTruthy();

      const sizeIsNotZeroMap = new Map<number, string>();
      sizeIsNotZeroMap.set(0, 'Test1');
      sizeIsNotZeroMap.set(1, 'Test2');
      expect(isEmpty(sizeIsNotZeroMap)).toBeFalsy();
    });

    it('Set Null or Size 0 Check', () => {
      const sizeZeroSet = new Set<string>();
      expect(isEmpty(sizeZeroSet)).toBeTruthy();

      const sizeIsNotZeroSet = new Set<string>();
      sizeIsNotZeroSet.add('Test1');
      sizeIsNotZeroSet.add('Test2');
      expect(isEmpty(sizeIsNotZeroSet)).toBeFalsy();
    });

    it('File Buffer Null or Size 0 Check', () => {
      const sizeZeroBuffer = new Buffer(0);
      expect(isEmpty(sizeZeroBuffer)).toBeTruthy();

      const sizeIsNotZeroBuffer = new Buffer('buffertest', 'utf8');
      expect(isEmpty(sizeIsNotZeroBuffer)).toBeFalsy();
    });
  });

  describe.skip('bcrypt test', () => {
    let hash: string;
    const pw = 'abcdef1234';

    it('generate hash test', () => {
      const generated: string = generateHash(pw, 12);
      hash = generated;
      console.log(generated);
    });

    it('compare hash test', () => {
      const result: boolean = compareHash(pw, hash);
      console.log(result);
    });
  });

  describe.skip('transferFromRaw test', () => {
    const raw: any = {
      member_sn: 32143,
      member_id: 'testid',
      member_email: 'test@test.com',
    };

    it('transferFromRaw test', async () => {
      const result: any = transferFromRaw(raw);
      console.log(result);
    });
  });
});
