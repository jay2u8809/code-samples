import { Body, Controller, Post } from '@nestjs/common';
import { CsvService } from './csv/csv.service';
import { DataType } from '../../common/code/data-type';

@Controller('/api/v1/file')
export class FileController {
  constructor(private readonly csvService: CsvService) {}

  @Post('/json/convert/csv/')
  async convertJsonToCsv(@Body() data: any): Promise<any> {
    return this.csvService.convertToCsv(data, DataType.JSON);
  }
}
