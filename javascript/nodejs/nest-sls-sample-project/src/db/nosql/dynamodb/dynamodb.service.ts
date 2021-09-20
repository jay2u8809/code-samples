import { Injectable } from '@nestjs/common';
import * as AWS from 'aws-sdk';
import { DynamodbInterface } from './dynamodb.interface';
import { AwsRegion } from './aws.config';

const TAG = 'DynamoDB_Service';

@Injectable()
export class DynamodbService implements DynamodbInterface {
  private readonly docClient: AWS.DynamoDB.DocumentClient;

  constructor() {
    AWS.config.update({ region: AwsRegion.TOKYO });
    this.docClient = new AWS.DynamoDB.DocumentClient({
      httpOptions: {
        timeout: 5000,
      },
    });
  }

  async create(
    param: any,
    table?: string,
  ): Promise<AWS.DynamoDB.DocumentClient.PutItemOutput | null> {
    if (!table || param) return null;

    return await this.docClient
      .put(param)
      .promise()
      .catch((err) => {
        console.log(TAG, `Fail to create : ${err}`);
        return null;
      });
  }

  async get(
    param: any,
  ): Promise<AWS.DynamoDB.DocumentClient.GetItemOutput | null> {
    return await this.fetch(param, false).catch((err) => {
      console.log(TAG, `Fail to get : ${err}`);
      return null;
    });
  }

  async getAll(params: any): Promise<any[] | null> {
    return await this.fetch(params, true).catch((err) => {
      console.log(TAG, `Fail to getAll : ${err}`);
      return null;
    });
  }

  async update(
    table: string,
    param: any,
  ): Promise<AWS.DynamoDB.DocumentClient.UpdateItemOutput | null> {
    if (!table || param) return null;

    return await this.docClient
      .update(param)
      .promise()
      .catch((err) => {
        console.log(TAG, `Fail to update : ${err}`);
        return null;
      });
  }

  async delete(
    table: string,
    param: any,
  ): Promise<AWS.DynamoDB.DocumentClient.DeleteItemOutput | null> {
    if (!table || param) return null;

    return await this.docClient
      .delete(param)
      .promise()
      .catch((err) => {
        console.log(TAG, `Fail to delete : ${err}`);
        return null;
      });
  }

  // === private ===
  private async fetch(
    param: any,
    isScan: boolean,
  ): Promise<any | any[] | null> {
    const result: any = {
      Count: 0,
      Items: [],
    };

    const value: any = isScan
      ? await this.docClient.scan(param).promise()
      : await this.docClient.get(param).promise();

    result.Items.push(...value.Items);
    result.Count = result.Items.length;

    return result;
  }
}
