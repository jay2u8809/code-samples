import { isEmpty } from './common.utils';

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

    it.only('Set Null or Size 0 Check', () => {
      const sizeZeroSet = new Set<string>();
      expect(isEmpty(sizeZeroSet)).toBeTruthy();

      const sizeIsNotZeroSet = new Set<string>();
      sizeIsNotZeroSet.add('Test1');
      sizeIsNotZeroSet.add('Test2');
      expect(isEmpty(sizeIsNotZeroSet)).toBeFalsy();
    });

    it.skip('File Buffer Null or Size 0 Check', () => {
      const sizeZeroBuffer = new Buffer(0);
      expect(isEmpty(sizeZeroBuffer)).toBeTruthy();

      const sizeIsNotZeroBuffer = new Buffer('buffertest', 'utf8');
      expect(isEmpty(sizeIsNotZeroBuffer)).toBeFalsy();
    });
  });
});
