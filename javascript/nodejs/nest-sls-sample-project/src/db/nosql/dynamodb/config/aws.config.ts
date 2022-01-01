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

  TEST_MEMBER = 'dummy_member',
}

export enum AwsDbIndex {
  MEMBER_ID = 'memberId-index',
  EMAIL = 'emailAddress-index',
  NICKNAME = 'nickName-index',
  MEMBER_STATUS = 'memberStatus-index',
}

export interface QueryResult {
  /*
    Filter 후 데이터 수
   */
  Count: number;
  /*
    Filter 전 데이터 수
   */
  ScannedCount: number;
  Items: any[];
}

// ref: https://qiita.com/Fujimon_fn/items/66be7b807a8329496899
export interface QueryParam {
  TableName?: AwsDbTable;
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
