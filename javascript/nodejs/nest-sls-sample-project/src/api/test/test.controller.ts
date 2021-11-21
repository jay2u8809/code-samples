import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Patch,
  Post,
  Put,
  Query,
} from '@nestjs/common';
import { TestService } from './test.service';
import { RequestDto } from './dto/request.dto';

@Controller('/api/v1/test')
export class TestController {
  constructor(private readonly testService: TestService) {}

  @Post('/post/')
  async testPost(@Body() data: RequestDto): Promise<any> {
    console.log(data);
    return data;
  }

  @Get('/get/:userId')
  async testGet(@Param('userId') data: any): Promise<any> {
    console.log(data);
    return data;
  }

  @Get('/get')
  async testGetQuery(@Query('userId') data: any): Promise<any> {
    console.log(data);
    return data;
  }

  @Put('/put/')
  async testPut(@Body() data: any): Promise<any> {
    console.log(data);
    return data;
  }

  @Patch('/patch/')
  async testPatch(@Body() data: any): Promise<any> {
    console.log(data);
    return data;
  }

  @Delete('/delete/')
  async testDelete(@Body() data: any): Promise<any> {
    console.log(data);
    return data;
  }
}
