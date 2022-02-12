import { DayjsUtil } from '../../common/Dayjs.util';

const TAG = 'REGEXP_TEST';

describe('regexp Test', () => {
  let after;
  beforeEach(() => {
    console.log(TAG, DayjsUtil.koreaDate().toISOString());
    const faceBookId = 'f_432fd0f3l9fde3ef3de';
    const googleId = 'g_eF93fDe0ofd3sck975li';
    const amazonId = 'az_332100543543123';
    const appleId = 'ap_432423.349of33sd9232r3.2343';
    const mailAddrId = 'mail_3c540daf-4322-fe3d-eca9-2439dca8dfe9';
    after = `backup/development/production/${faceBookId}/2022-01-28.json`;
  });

  it.skip('find user id - literal 1', async () => {
    console.log(TAG, 'START');

    const reg =
      /^g_[0-9a-zA-Z]+$|f_[0-9a-z]+$|az_[0-9]+$|ap_[0-9]{6}.[0-9a-z]+.[0-9]{4}$|mail_[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/g;
    const afterUserId = after.split('/').find((s) => reg.test(s));
    console.log(TAG, 'after-user-id', afterUserId);

    expect(true);
    console.log(TAG, 'END');
  });

  it.skip('find user id - literal 2', async () => {
    console.log(TAG, 'START');

    const reg =
      /^g[\d\w]+$|f_[\da-z]+$|az_\d+$|ap_\d{6}.[\da-z]+.\d{4}$|mail_[\da-f]{8}-[\da-f]{4}-[\da-f]{4}-[\da-f]{4}-[\da-f]{12}$/g;
    const afterUserId = after.split('/').find((s) => reg.test(s));
    console.log(TAG, 'after-user-id', afterUserId);

    expect(true);
    console.log(TAG, 'END');
  });

  it.skip('find user id - RegExp 1', async () => {
    console.log(TAG, 'START');

    const facebookReg = 'f_[0-9a-z]+$';
    const googleReg = 'g_[0-9a-zA-z]+$';
    const amazonReg = 'az_\\d+$';
    const appleReg = 'ap_\\d{6}.[0-9a-z]+.\\d{4}$';
    const mailAddrReg =
      'mail_[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$';
    const reg = new RegExp(
      `^${googleReg}|${facebookReg}|${amazonReg}|${appleReg}|${mailAddrReg}`,
      'g',
    );
    const afterUserId = after.split('/').find((s) => reg.test(s));
    console.log(TAG, 'after-user-id', afterUserId);

    expect(true);
    console.log(TAG, 'END');
  });

  it.skip('find user id - RegExp 2', async () => {
    console.log(TAG, 'START');

    const facebookReg = 'f_[\\da-z]+$';
    const googleReg = 'g[\\d\\w]+$';
    const amazonReg = 'az_\\d+$';
    const appleReg = 'ap_\\d{6}.[\\da-z]+.\\d{4}$';
    const mailAddrReg =
      'mail_[\\da-f]{8}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{12}$';
    const reg = new RegExp(
      `^${googleReg}|${facebookReg}|${amazonReg}|${appleReg}|${mailAddrReg}`,
      'g',
    );
    const afterUserId = after.split('/').find((s) => reg.test(s));
    console.log(TAG, 'after-user-id', afterUserId);

    expect(true);
    console.log(TAG, 'END');
  });

  it.skip('find user id - RegExp 3, i flag', async () => {
    console.log(TAG, 'START');

    let userId = 't_432FD0F3L9FDE3EF3ED';
    let reg = new RegExp(`^t_[0-9a-z]+$`, 'i');
    let result = reg.test(userId);
    console.log(TAG, 'result1', result);

    userId = 't_432fd0f3l9fde3ef3de';
    reg = new RegExp(`^t_[0-9A-Z]+$`, 'i');
    result = reg.test(userId);
    console.log(TAG, 'result2', result);

    expect(true);
    console.log(TAG, 'END');
  });
});
