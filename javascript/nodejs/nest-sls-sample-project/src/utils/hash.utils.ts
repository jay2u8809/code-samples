import bcrypt from 'bcrypt';
// const bcrypt = require('bcrypt');

const TAG = 'HashUtils';

export class HashUtils {
  static generateHash = (pw: string, saltRound?: number): any => {
    const salt = bcrypt.genSaltSync(saltRound);
    const hash = bcrypt.hashSync(pw, salt);
    console.log(TAG, 'generated-hash', hash, 'generated-salt-round', salt);

    return {
      hash: hash,
      salt: salt,
    };
  };

  static compareHash = (pw: string, hash: string): boolean => {
    // Load hash from your password DB.
    const result: boolean = bcrypt.compareSync(pw, hash);
    console.log(TAG, 'compare-hash-result', result);
    return result;
  };

  static validateHash = (password: string, saltRound: number): boolean => {
    const hash = bcrypt.hashSync(password, saltRound);
    return hash === password;
  };
}
