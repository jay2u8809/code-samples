import { Module } from '@nestjs/common';
import { TestController } from './test.controller';
import { TestService } from './test.service';
import { IsItDecoratorController } from './decorators/is-it.decorator.controller';
import { StringDecoratorsController } from './decorators/string-decorators.controller';

@Module({
  imports: [],
  controllers: [
    TestController,
    IsItDecoratorController,
    StringDecoratorsController,
  ],
  providers: [TestService],
})
export class TestApiModule {}
