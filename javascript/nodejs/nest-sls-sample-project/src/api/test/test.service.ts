import { Injectable } from '@nestjs/common';
import { RequestDto } from './dto/request.dto';
import { DbBaseInterface } from '../../db/common/db.base.interface';
import { DynamodbService } from '../../db/nosql/dynamodb/dynamodb.service';
import {
  AwsDbTable,
  QueryParam,
} from '../../db/nosql/dynamodb/config/aws.config';

@Injectable()
export class TestService implements DbBaseInterface {
  constructor(private readonly dynamodb: DynamodbService) {}

  create(param: any, table?: string): Promise<any> {
    return Promise.resolve(undefined);
  }

  delete(param: any, table?: string): Promise<any> {
    return Promise.resolve(undefined);
  }

  get(param: any): Promise<any> {
    // 0. make query
    const getParam: QueryParam = {
      TableName: AwsDbTable.TEST_MEMBER,
      Key: {
        id: param,
      },
    };
    return this.dynamodb.getOneById(getParam);
  }

  getAll(param?: any): Promise<any> {
    // 0. make query
    const getParam: QueryParam = {
      TableName: AwsDbTable.TEST_MEMBER,
    };
    // 1. get data list
    return this.dynamodb.getAll(getParam);
  }

  update(param: any, table?: string): Promise<any> {
    return Promise.resolve(undefined);
  }

  async postStub(dummy: RequestDto): Promise<any> {
    console.log(dummy.name1);
    console.log(dummy.name2);
    console.log(dummy.name3);
    return dummy;
  }
}
