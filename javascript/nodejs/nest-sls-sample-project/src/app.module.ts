import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ApiModule } from './api/api.module';
import { DomainModule } from './domain/domain.module';
// import configuration from './config/configuration';

// const useSql: boolean = configuration().db.useSql;
@Module({
  imports: [
    ApiModule,
    DomainModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
