import { Module } from '@nestjs/common';
import { RestApiController } from './rest-api/rest-api.controller';
import { RestApiService } from './rest-api/rest-api.service';
import { IsItDecoratorController } from './decorators/is-it.decorator.controller';
import { StringDecoratorsController } from './decorators/string-decorators.controller';

@Module({
  imports: [],
  controllers: [
    RestApiController,
    IsItDecoratorController,
    StringDecoratorsController,
  ],
  providers: [RestApiService],
})
export class TestApiModule {}
