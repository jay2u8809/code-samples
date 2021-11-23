import { NestFactory } from '@nestjs/core';
import { ExpressAdapter } from '@nestjs/platform-express';
import { Server } from 'http';
import * as serverless from 'aws-serverless-express';
import express from 'express';
import { Context, Handler } from 'aws-lambda';
import { ValidationPipe } from '@nestjs/common';
import { ApiModule } from './api.module';
import configuration from '../config/configuration';

const TAG = 'API_MAIN';

let cachedServer: Server;

async function bootstrapServer(): Promise<Server> {
  const expressApp = express();
  const adapter = new ExpressAdapter(expressApp);
  const app = await NestFactory.create(ApiModule, adapter);
  app.enableCors();
  app.useGlobalPipes(new ValidationPipe());
  // configure
  const config = configuration();
  await app.listen(config.http.port);
  await app.init();
  console.log(TAG);
  return serverless.createServer(expressApp);
}

export const handler: Handler = async (event: any, context: Context) => {
  if (!cachedServer) {
    cachedServer = await bootstrapServer();
  }
  return serverless.proxy(cachedServer, event, context, 'PROMISE').promise;
};

if (process.argv.length > 0 && process.argv[2] === 'l'){
  console.log(TAG, 'LOCAL');
  bootstrapServer();
}
