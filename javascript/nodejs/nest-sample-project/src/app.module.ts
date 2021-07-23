import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
// import { DatabaseModule } from './database/database.module';
import {Connection} from "typeorm";
import {TypeOrmModule} from "@nestjs/typeorm";
import { ApiModule } from './api/api.module';

@Module({
  imports: [TypeOrmModule.forRoot(), ApiModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {
  constructor(private connection: Connection) {}
}
