export interface AwsConfig {
  region: AwsRegion;
  table: AwsDbTable;
}

export enum AwsRegion {
  TOKYO = 'ap-northeast-1',
}

export enum AwsDbTable {
  MEMBER = 'member',
  TEST = 'students',
}

export enum TestAwsTable {
  TEST_MEMBER = 'dummy_member',
}

export enum AwsDbIndex {
  ID = 'id',
}

export interface QueryResult {
  Count: number;
  Items: any[];
}

// ref: https://qiita.com/Fujimon_fn/items/66be7b807a8329496899
export interface QueryParam {
  TableName?: AwsDbTable | TestAwsTable;
  IndexName?: string;
  Key?: any;
  Item?: any;
  ExpressionAttributeNames?: any;
  ExpressionAttributeValues?: any;
  UpdateExpression?: string;
  KeyConditionExpression?: string;
  ConditionExpression?: string;
  FilterExpression?: string;
  ProjectionExpression?: string;
  RequestItems?: any;
}
