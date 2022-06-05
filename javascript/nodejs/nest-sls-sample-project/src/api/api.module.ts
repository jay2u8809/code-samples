import { Module } from '@nestjs/common';
import { OpenApiModule } from './open/open-api.module';
import { InnerApiModule } from './inner/inner-api.module';
import { TestController } from './test/test.controller';
import { FileController } from './file/file.controller';
import { CsvService } from './file/csv/csv.service';
import { TestService } from './test/test.service';

@Module({
  imports: [OpenApiModule, InnerApiModule],
  controllers: [
    FileController,
    TestController,
  ],
  providers: [
    CsvService,
    TestService
  ],
})
export class ApiModule {}
