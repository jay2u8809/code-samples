import bcrypt from 'bcrypt';
// const bcrypt = require('bcrypt');

export function isEmpty(obj: any): boolean {
  if (obj === null) {
    return true;
  }

  if (obj === undefined) {
    return true;
  }

  if (obj instanceof Array) {
    return obj.length === 0;
  }

  if (obj instanceof Map) {
    return obj.size === 0;
  }

  if (obj instanceof Set) {
    return obj.size === 0;
  }

  if (obj instanceof Buffer) {
    return obj.length === 0;
  }

  // else
  return false;
}

export const generateHash = (pw: string, saltRounds?: number): string => {
  const _saltRounds = saltRounds || 10;
  console.log('SALT ROUND: ', _saltRounds);
  const salt = bcrypt.genSaltSync(_saltRounds);
  const hash = bcrypt.hashSync(pw, salt);
  // const hash = bcrypt.hashSync(pw, _saltRounds);
  console.log('GENERATED HASH: ', hash);

  return hash;
};

export const compareHash = (pw: string, hash: string): boolean => {
  // Load hash from your password DB.
  const result: boolean = bcrypt.compareSync(pw, hash);
  console.log('COMPARE HASH RESULT: ', result);
  return result;
};
