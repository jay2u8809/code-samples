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

  async create(
    param: any,
  ): Promise<AWS.DynamoDB.DocumentClient.PutItemOutput | null> {
    return await this.docClient
      .put(param)
      .promise()
      .then((data) => {
        return data;
      })
      .catch((err) => {
        console.error(TAG, `Fail to create : ${err}`);
        return null;
      });
  }

  async get(param: any): Promise<any> {
    return await this.fetch(param, false)
      .then((data) => {
        return data;
      })
      .catch((err) => {
        console.error(TAG, `Fail to get : ${err}`);
      });
  }

  async getAll(params: any): Promise<any> {
    return await this.fetch(params, true)
      .then((data) => {
        return data;
      })
      .catch((err) => {
        console.error(TAG, `Fail to getAll : ${err}`);
      });
  }

  async update(
    param: any,
  ): Promise<AWS.DynamoDB.DocumentClient.UpdateItemOutput | null> {
    return await this.docClient
      .update(param)
      .promise()
      .then((data) => {
        return data;
      })
      .catch((err) => {
        console.error(TAG, `Fail to update : ${err}`);
        return null;
      });
  }

  async delete(
    param: any,
  ): Promise<AWS.DynamoDB.DocumentClient.DeleteItemOutput | null> {
    return await this.docClient
      .delete(param)
      .promise()
      .then((data) => {
        return data;
      })
      .catch((err) => {
        console.error(TAG, `Fail to delete : ${err}`);
        return null;
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

    return result;
  }
}
