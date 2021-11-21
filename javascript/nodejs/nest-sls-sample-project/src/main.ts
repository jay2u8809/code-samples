import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import configuration from './config/configuration';
import { ValidationPipe } from '@nestjs/common';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  // class-validator setting
  app.useGlobalPipes(new ValidationPipe());
  // configure
  const config = configuration();
  await app.listen(config.http.port);
}
bootstrap();
