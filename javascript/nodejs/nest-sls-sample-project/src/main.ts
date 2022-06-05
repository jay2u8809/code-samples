import { NestFactory } from '@nestjs/core';
import { ValidationPipe } from '@nestjs/common';
import { AppModule } from './app.module';
import { CommonUtils } from './utils/common.utils';

const TAG = 'Main';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // class-validator setting
  app.useGlobalPipes(new ValidationPipe());
  // await app.init();

  //
  const config: Record<string, any> =  await CommonUtils.loadConfigByYaml('config.yaml', TAG);
  // port setting
  const port = +config?.default?.port || 3000;
  console.log(TAG, 'port-running', port);
  await app.listen(port);
}
bootstrap();