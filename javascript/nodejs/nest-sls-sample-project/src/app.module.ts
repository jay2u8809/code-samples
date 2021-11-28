import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ApiModule } from './api/api.module';
import { DomainModule } from './domain/domain.module';
import { TypeOrmModule } from '@nestjs/typeorm';
// import configuration from './config/configuration';

// const useSql: boolean = configuration().db.useSql;
@Module({
  imports: [ApiModule, DomainModule, TypeOrmModule.forRoot()],
  controllers: [AppController],
  providers: [AppService],
  exports: [],
})
export class AppModule {}
