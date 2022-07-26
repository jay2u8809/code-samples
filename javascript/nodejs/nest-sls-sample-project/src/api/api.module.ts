import { Module } from '@nestjs/common';
import { OpenApiModule } from './open/open-api.module';
import { InnerApiModule } from './inner/inner-api.module';
import { FileController } from './file/file.controller';
import { CsvService } from './file/csv/csv.service';
import { TestApiModule } from './test/test-api.module';

@Module({
  imports: [OpenApiModule, InnerApiModule, TestApiModule],
  controllers: [FileController],
  providers: [CsvService],
})
export class ApiModule {}
