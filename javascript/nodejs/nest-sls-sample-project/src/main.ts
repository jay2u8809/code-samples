import { ValidationPipe } from '@nestjs/common';
import { NestFactory } from '@nestjs/core';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import { AppModule } from './app.module';

const TAG = 'MAIN';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  app.setGlobalPrefix('api');

  // class-validator setting
  // app.useGlobalPipes(new ValidationPipe());
  // await app.init();

  const options = new DocumentBuilder()
  .addBearerAuth()
  .setTitle('Todo APP')
  .setDescription('Todo API documentation')
  .setVersion('1.0')
  .addTag('Todo')
  .build();
  
  const document = SwaggerModule.createDocument(app, options);
  SwaggerModule.setup('api', app, document);
  
  const port = +process.env.APP_PORT || 3000;
  console.log(TAG, 'Port running on: ', port);
  await app.listen(port);
}
bootstrap();