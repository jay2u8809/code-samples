import { NestFactory } from '@nestjs/core';
import { ValidationPipe } from '@nestjs/common';
import { ApiModule } from '../api.module';
import { CommonUtils } from 'src/utils/common.utils';

const TAG = 'InnerApi';

async function bootstrapServer(): Promise<void> {
  const app = await NestFactory.create(ApiModule);
  app.enableCors();
  app.setGlobalPrefix('/api/inner');

  // class-validator setting
  app.useGlobalPipes(new ValidationPipe());

  // config
  const config: Record<string, any> = await CommonUtils.loadConfigByYaml('src/api/api-config.yaml', TAG);
  // port setting
  const port = +config?.inner?.port || 3000;
  console.log(TAG, 'port-running', port);
  await app.listen(port);
  await app.init();
}

if (process.argv.length > 0 && process.argv[2] === 'inner-api'){
  console.log(TAG, 'local-running');
  bootstrapServer();
}
