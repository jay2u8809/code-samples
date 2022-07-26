import { NestFactory } from '@nestjs/core';
import { ValidationPipe } from '@nestjs/common';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import { AppModule } from 'src/app.module';
import { CommonUtils } from 'src/utils/common.utils';

const TAG = 'SwaggerMain';

async function bootstrapServer(): Promise<void> {
  const app = await NestFactory.create(AppModule);

  // class-validator setting
  app.useGlobalPipes(new ValidationPipe());
  app.setGlobalPrefix('/api');

  // swagger settings
  const options = new DocumentBuilder()
    .addBearerAuth()
    .setTitle('Code Samples APP Swagger')
    .setDescription('Code Samples API documentation')
    .setVersion('1.0')
    .addTag('code-samples')
    .build();
  const document = SwaggerModule.createDocument(app, options);
  SwaggerModule.setup('/swagger/api', app, document);   // localhost:3001/swagger/api/

  // config
  const config: Record<string, any> = await CommonUtils.loadConfigByYaml('src/swagger/swagger-config.yaml', TAG);
  // port setting
  const port = +config?.Swagger?.port || 3001;
  console.log(TAG, 'port-running', port);
  await app.listen(port);
}

if (process.argv.length > 0 && process.argv[2] === 'swagger'){
  console.log(TAG, 'local-running');
  bootstrapServer();
}
