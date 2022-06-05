import { NestFactory } from '@nestjs/core';
import { ValidationPipe } from '@nestjs/common';
import { ExpressAdapter } from '@nestjs/platform-express';
import { Server } from 'http';
import express from 'express';
import { Context, Handler } from 'aws-lambda';
import * as serverless from 'aws-serverless-express';
import { ApiModule } from './api.module';
import { CommonUtils } from 'src/common/common.utils';

const TAG = 'ApiMain';

let cachedServer: Server;

async function bootstrapServer(): Promise<Server> {
  const expressApp = express();
  const adapter = new ExpressAdapter(expressApp);
  const app = await NestFactory.create(ApiModule, adapter);
  app.enableCors();
  app.setGlobalPrefix('/api');
  
  // class-validator setting
  app.useGlobalPipes(new ValidationPipe());

  // config
  const config: Record<string, any> = await CommonUtils.loadConfigByYaml('src/api/api-config.yaml', TAG);
  // port setting
  const port = +config?.default?.port || 3000;
  console.log(TAG, 'port-running', port);
  await app.listen(port);
  await app.init();
  console.log(TAG, 'app-start');

  return serverless.createServer(expressApp);
}

/**
 * serverless lambda
 * @param event 
 * @param context 
 * @returns 
 */
export const handler: Handler = async (event: any, context: Context) => {
  if (!cachedServer) {
    cachedServer = await bootstrapServer();
  }
  return serverless.proxy(cachedServer, event, context, 'PROMISE').promise;
};

if (process.argv.length > 0 && process.argv[2] === 'main-api'){
  console.log(TAG, 'local-running');
  bootstrapServer();
}
