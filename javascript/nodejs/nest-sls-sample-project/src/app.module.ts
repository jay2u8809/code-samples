import { Module } from '@nestjs/common';
import { ApiModule } from './api/api.module';
@Module({
  imports: [
    ApiModule,
  ],
  controllers: [],
  providers: [],
  exports: [],
})
export class AppModule {}
