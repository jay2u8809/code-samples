import { Injectable } from '@nestjs/common';
import * as AWS from 'aws-sdk';
import { DynamodbInterface } from './dynamodb.interface';
import { AwsRegion, QueryResult } from './config/aws.config';

const TAG = 'DYNAMODB_SERVICE';

@Injectable()
export class DynamodbService implements DynamodbInterface {
  private readonly docClient: AWS.DynamoDB.DocumentClient;

  constructor() {
    AWS.config.update({ region: AwsRegion.TOKYO });
    this.docClient = new AWS.DynamoDB.DocumentClient({
      httpOptions: {
        timeout: 5000,
      },
      endpoint: 'http://localhost:8000',
    });
  }

  async create(param: any): Promise<AWS.DynamoDB.DocumentClient.PutItemOutput> {
    return await this.docClient
      .put(param)
      .promise()
      .then((data) => {
        return data;
      })
      .catch((err) => {
        console.error(TAG, `Fail to create : ${err}`);
        return undefined;
      });
  }

  async getOneById(param: any): Promise<any> {
    return await this.docClient
      .get(param)
      .promise()
      .then((data) => {
        return data.Item;
      })
      .catch((err) => {
        console.error(TAG, `Fail to getOneById : ${err}`);
        return undefined;
      });
  }

  async get(param: any): Promise<any> {
    return await this.fetch(param, false)
      .then((data) => {
        return data;
      })
      .catch((err) => {
        console.error(TAG, `Fail to get : ${err}`);
        return undefined;
      });
  }

  async getAll(params: any): Promise<any[] | any> {
    return await this.fetch(params, true)
      .then((data) => {
        return data;
      })
      .catch((err) => {
        console.error(TAG, `Fail to getAll : ${err}`);
        return undefined;
      });
  }

  async update(
    param: any,
  ): Promise<AWS.DynamoDB.DocumentClient.UpdateItemOutput> {
    return await this.docClient
      .update(param)
      .promise()
      .then((data) => {
        return data;
      })
      .catch((err) => {
        console.error(TAG, `Fail to update : ${err}`);
        return undefined;
      });
  }

  async delete(
    param: any,
  ): Promise<AWS.DynamoDB.DocumentClient.DeleteItemOutput> {
    return await this.docClient
      .delete(param)
      .promise()
      .then((data) => {
        return data;
      })
      .catch((err) => {
        console.error(TAG, `Fail to delete : ${err}`);
        return undefined;
      });
  }

  // === private ===
  private async fetch(param: any, isScan: boolean): Promise<QueryResult> {
    const result: QueryResult = {
      Count: 0,
      ScannedCount: 0,
      Items: [],
    };

    const value: any = isScan
      ? await this.docClient.scan(param).promise()
      : await this.docClient.query(param).promise();

    result.Items.push(...value.Items);
    result.Count = result.Items.length;
    result.ScannedCount = value.ScannedCount;
    if (param.Limit) {
      result.Items.splice(~~param.Limit);
    }

    return result;
  }
}
