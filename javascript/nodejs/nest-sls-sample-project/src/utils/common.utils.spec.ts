import { CommonUtils } from './common.utils';

const TAG = 'CommmonUtilsSpec';

describe(TAG, () => {
  beforeEach(async () => {
    console.log(`Common Utils Test`);
  });

  describe('transferFromRaw test', () => {
    const raw: any = {
      member_sn: 32143,
      member_id: 'testid',
      member_email: 'test@test.com',
    };

    it.skip('transferFromRaw test', async () => {
      const result: any = CommonUtils.transferFromRaw(raw);
      console.log(result);
    });
  });

  it.skip('load yaml config', async () => {
    jest.setTimeout(99999999999);

    const result: Record<string, any> = await CommonUtils.loadConfigByYaml('src/swagger/swagger-config.yaml', 'Swagger');
    console.log(TAG, 'load-yaml-config-result', result?.Swagger?.port ?? 3002);

    expect(result).toBeDefined();
  })
});
