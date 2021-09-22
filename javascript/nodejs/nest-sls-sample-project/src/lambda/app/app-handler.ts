import { NestFactory } from '@nestjs/core';
import { AppModule } from '../../app.module';
import { ExpressAdapter } from '@nestjs/platform-express';
import { Server } from 'http';
import * as serverless from 'aws-serverless-express';
import express from 'express';
import { Context, Handler } from 'aws-lambda';

let cachedServer: Server;

async function bootstrapServer(): Promise<Server> {
  const expressApp = express();
  const adapter = new ExpressAdapter(expressApp);

  const app = await NestFactory.create(AppModule, adapter);
  app.enableCors();

  await app.init();

  return serverless.createServer(expressApp);
}

export const handler: Handler = async (event: any, context: Context) => {
  if (!cachedServer) {
    cachedServer = await bootstrapServer();
  }
  return serverless.proxy(cachedServer, event, context, 'PROMISE').promise;
};
