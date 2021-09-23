import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
// import { DatabaseModule } from './database/database.module';
import { Connection } from 'typeorm';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ApiModule } from './api/api.module';
import { DomainModule } from './domain/domain.module';
import configuration from './config/configuration';

const useSql: boolean = configuration().db.useSql;
const importModules: any[] = [
  ApiModule,
  DomainModule,
];
if (useSql) {
  importModules.push(TypeOrmModule.forRoot());
}

@Module({
  imports: importModules,
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {
  constructor(private connection: Connection) {}
}
