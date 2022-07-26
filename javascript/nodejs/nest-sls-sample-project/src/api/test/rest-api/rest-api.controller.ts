import {
  Body,
  Controller,
  Delete,
  Get,
  HostParam,
  Headers,
  HttpStatus,
  Ip,
  Param,
  Patch,
  Post,
  Put,
  Query,
} from '@nestjs/common';
import { RestApiService } from './rest-api.service';
import { RestApiRequestDto } from './dto/rest-api.request.dto';
import { ApiTags } from '@nestjs/swagger';

const TAG = 'REST-API_TEST_CONTROLLER';

@ApiTags('open/rest-api')
@Controller({
  path: '/v1/rest-api',
  // host: ':sample.example.com',
})
export class RestApiController {
  constructor(private readonly restApiService: RestApiService) {}

  @Post('/post')
  public async testRestApiPost(
    @Body() params: RestApiRequestDto,
  ): Promise<any> {
    console.log(TAG, 'post', params);
    return this.restApiService.create(params);
  }

  /**
   * ex) localhost:3000/api/v1/test/v1/rest-api/get/abcd-123456-efgh-1235
   * @param userId
   */
  @Get('/get/:id')
  public async testRestApiGetPath(@Param('id') userId: any): Promise<any> {
    console.log(TAG, `get-path-variable: ${userId}`);
    return this.restApiService.get(userId);
  }

  /**
   * ex) localhost:3000/api/test/v1/rest-api/get?id=abcd-123456-efgh-1235
   * @param userId
   */
  @Get('/get')
  public async testRestApiGetQuery(@Query('id') userId: any): Promise<any> {
    console.log(TAG, `get-query-param: ${userId}`);
    return this.restApiService.get(userId);
  }

  @Get('/get-all')
  public async testRestApiGetAll(): Promise<any> {
    console.log(TAG, `get-all`);
    return this.restApiService.getAll();
  }

  @Put('/put/:id')
  public async testRestApiPut(
    @Param('id') userId: string,
    @Body() params: any,
  ): Promise<any> {
    console.log(TAG, 'put', userId, params);
    return this.restApiService.update(userId);
  }

  @Patch('/patch/:id')
  public async testRestApiPatch(
    @Param('id') userId: string,
    @Body() params: any,
  ): Promise<any> {
    console.log(TAG, 'patch', userId, params);
    return this.restApiService.update(userId);
  }

  @Delete('/delete/:id')
  public async testRestApiDelete(@Param('id') userId: string): Promise<any> {
    console.log(TAG, 'delete', userId);
    return this.restApiService.delete(userId);
  }

  @Get('/check/request')
  public checkRequest(
    @Ip() ip: any,
    @HostParam() host: string[],
    @Headers() headers: any,
  ): HttpStatus {
    console.log(TAG, 'ip', ip);
    console.log(TAG, 'url-host-param', JSON.stringify(host));
    console.log(TAG, 'headers', headers, headers['user-agent']);
    return HttpStatus.OK;
  }
}
