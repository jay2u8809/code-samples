import { Member } from '../../entities/member/member';
import { DynamodbService } from '../../db/nosql/dynamodb/dynamodb.service';
import { AwsDbIndex, AwsDbTable, QueryParam } from '../../db/nosql/dynamodb/aws.config';
import { MemberJoinRequestDto, saveMember } from './dto/member.join.request.dto';
import { Injectable, NotFoundException } from '@nestjs/common';
import { MemberInterface } from '../../db/common/domain/member/member.interface';
import { plainToClass } from 'class-transformer';

const TAG = 'MEMBER_NOSQL_SERVICE';

@Injectable()
export class MemberNosqlService implements MemberInterface {
  constructor(private readonly dynamodb: DynamodbService) {}

  /**
   * Register Member
   * @param param
   */
  async create(param: MemberJoinRequestDto): Promise<string> {
    // 0. check param
    if (!param) 
      return null;
    // 1. make check exist query
    const getParam: QueryParam = {
      TableName: AwsDbTable.MEMBER,
      IndexName: AwsDbIndex.ID,
      KeyConditionExpression: '#memberId = :val',
      ExpressionAttributeNames: {
        '#memberId': 'member_id'
      },
      ExpressionAttributeValues: {
        ':val': param.memberId
      },
    };
    // 2. check exist
    const isExist: boolean = await this.isExist(getParam);
    if (!isExist) {
      throw new NotFoundException(
        `Already Exist Member ID :  ${param.memberId}`,
      );
    }
    // 3. make query
    const putParam: QueryParam = {
      TableName: AwsDbTable.MEMBER,
      Item: plainToClass(Member, {
        ...saveMember(param),
      }),
    };
    // 4. create
    await this.dynamodb.create(putParam)
      .then((data) => {
        console.log(TAG, `Attributes : ${data.Attributes}`);
        console.log(TAG, `ConsumedCapacity : ${data.ConsumedCapacity}`);
        console.log(TAG, `ConsumedCapacity TableName : ${data.ConsumedCapacity.TableName}`);
        console.log(TAG, `ConsumedCapacity CapacityUnits : ${data.ConsumedCapacity.CapacityUnits}`);
        console.log(TAG, `ConsumedCapacity ReadCapacityUnits : ${data.ConsumedCapacity.ReadCapacityUnits}`);
        console.log(TAG, `ConsumedCapacity WriteCapacityUnits : ${data.ConsumedCapacity.WriteCapacityUnits}`);
        console.log(TAG, `ConsumedCapacity Table : ${data.ConsumedCapacity.Table}`);
        console.log(TAG, `ConsumedCapacity Table ReadCapacityUnits : ${data.ConsumedCapacity.Table.ReadCapacityUnits}`);
        console.log(TAG, `ConsumedCapacity Table WriteCapacityUnits : ${data.ConsumedCapacity.Table.WriteCapacityUnits}`);
        console.log(TAG, `ConsumedCapacity Table CapacityUnits : ${data.ConsumedCapacity.Table.CapacityUnits}`);
        console.log(TAG, `ConsumedCapacity LocalSecondaryIndexes : ${data.ConsumedCapacity.LocalSecondaryIndexes}`);
        console.log(TAG, `ConsumedCapacity LocalSecondaryIndexes ReadCapacityUnits : ${data.ConsumedCapacity.LocalSecondaryIndexes.ReadCapacityUnits}`);
        console.log(TAG, `ConsumedCapacity LocalSecondaryIndexes WriteCapacityUnits : ${data.ConsumedCapacity.LocalSecondaryIndexes.WriteCapacityUnits}`);
        console.log(TAG, `ConsumedCapacity LocalSecondaryIndexes CapacityUnits : ${data.ConsumedCapacity.LocalSecondaryIndexes.CapacityUnits}`);
        console.log(TAG, `ConsumedCapacity GlobalSecondaryIndexes : ${data.ConsumedCapacity.GlobalSecondaryIndexes}`);
        console.log(TAG, `ConsumedCapacity GlobalSecondaryIndexes ReadCapacityUnits : ${data.ConsumedCapacity.GlobalSecondaryIndexes.ReadCapacityUnits}`);
        console.log(TAG, `ConsumedCapacity GlobalSecondaryIndexes WriteCapacityUnits : ${data.ConsumedCapacity.GlobalSecondaryIndexes.WriteCapacityUnits}`);
        console.log(TAG, `ConsumedCapacity GlobalSecondaryIndexes CapacityUnits : ${data.ConsumedCapacity.GlobalSecondaryIndexes.CapacityUnits}`);
        console.log(TAG, `ItemCollectionMetrics : ${data.ItemCollectionMetrics}`);
        console.log(TAG, `ItemCollectionMetrics ItemCollectionKey : ${data.ItemCollectionMetrics.ItemCollectionKey}`);
        console.log(TAG, `ItemCollectionMetrics SizeEstimateRangeGB : ${data.ItemCollectionMetrics.SizeEstimateRangeGB}`);
      });

    return putParam.Item.id;
  }

  /**
   * Get One Member Info By id (All Info)
   * @param id
   */
  async get(id: string): Promise<Member> {
    // 0. check param
    if (!id) 
      return null;
    // 1. make query
    const param: QueryParam = {
      TableName: AwsDbTable.MEMBER,
      Key: {
        id: id,
      },
    };
    // 2. get data
    return this.dynamodb.get(param);
  }

  /**
   * Get All Members
   * @param param
   */
  async getAll(): Promise<Member[] | null> {
    // 0. make query
    const getParam: QueryParam = {
      TableName: AwsDbTable.MEMBER,
    }
    // 1. get data list
    return this.dynamodb.getAll(getParam);
  }

  /*
   * Get One Member Info By User ID (All Info)
   * @param memberId
   */
  async getById(memberId: string): Promise<Member | null> {
    // 0. check param
    if (!memberId) return null;
    // 1. make query
    const getParam: QueryParam = {
      TableName: AwsDbTable.MEMBER,
      IndexName: AwsDbIndex.ID,
      KeyConditionExpression: '#memberId = :val',
      ExpressionAttributeNames: {
        '#memberId': 'member_id'
      },
      ExpressionAttributeValues: {
        ':val': memberId
      },
    };
    // 2. get data
    return this.dynamodb.get(getParam);
  }

  async getByEmail(email: string): Promise<Member[] | null> {
    if (!email) 
      return null;
    return null;
  }

  async getByNickName(nickName: string): Promise<Member[] | null> {
    if (!nickName) 
      return null;
    return null;
  }

  /**
   * Get All Members Info
   */
  async getNormalMembers(): Promise<Member[] | null> {
    return null;
  }

  /**
   * Update Member Info
   * @param param
   * @param table
   */
  async update(param: MemberJoinRequestDto): Promise<Member | null> {
    if (!param) 
      return null;
    return null;
  }

  /**
   * Delete Member Info By memberSn
   * @param id
   */
  async delete(id: string): Promise<boolean | null> {
    // 0. check param
    if (!id) 
      return false;
    // 1. make query
    const param: QueryParam = {
      TableName: AwsDbTable.MEMBER,
      Key: {
        id: id,
      },
    };
    // 2. check exist
    const isExist = await this.isExist(param);
    if (!isExist)
      throw new NotFoundException(`Not Found Member Info : ${id}`);
    // 3. delete
    return this.dynamodb.delete(param)
    .then((data) => {
      return data !== null;
    });
  }

  async isExist(param: QueryParam): Promise<boolean> {
    if (!param) 
      return false;
    return this.dynamodb
      .get(param)
      .then((data) => {
        console.log(TAG, `Exist Member Count : ${data.Count}`);
        return data.Count > 0;
      }).catch((err) => {
        console.log(TAG, `Fail to check exist member data : ${JSON.stringify(err)}`);
        return false;
      });
  }
}
