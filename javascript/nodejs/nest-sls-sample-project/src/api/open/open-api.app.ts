import { NestFactory } from '@nestjs/core';
import { ValidationPipe } from '@nestjs/common';
import { ApiModule } from '../api.module';
import { CommonUtils } from 'src/common/common.utils';

const TAG = 'OpenApi';

async function bootstrapServer(): Promise<void> {
  const app = await NestFactory.create(ApiModule);
  app.enableCors();
  app.setGlobalPrefix('/api/open');
  
  // class-validator setting
  app.useGlobalPipes(new ValidationPipe());

  // config
  const config: Record<string, any> = await CommonUtils.loadConfigByYaml('src/api/api-config.yaml', TAG);
  // port setting
  const port = +config?.open?.port || 3000;
  console.log(TAG, 'port-running', port);
  await app.listen(port);
  await app.init();
}

if (process.argv.length > 0 && process.argv[2] === 'open-api'){
  console.log(TAG, 'local-running');
  bootstrapServer();
}
