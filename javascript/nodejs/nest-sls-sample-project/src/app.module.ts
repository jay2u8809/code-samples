import { Module } from '@nestjs/common';
import { ApiModule } from './api/api.module';
import { DomainModule } from './domain/domain.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import * as ormconfig from './db/sql/ormconfig';
// import configuration from './config/configuration';

// const useSql: boolean = configuration().db.useSql;
@Module({
  imports: [ApiModule, DomainModule, TypeOrmModule.forRoot(ormconfig)],
  controllers: [],
  providers: [],
  exports: [],
})
export class AppModule {}
