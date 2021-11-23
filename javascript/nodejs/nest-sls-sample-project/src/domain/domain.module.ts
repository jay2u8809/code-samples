import { Module } from '@nestjs/common';
import { DynamodbService } from 'src/db/nosql/dynamodb/dynamodb.service';
import { MemberController } from './member/member.controller';
import { MemberNosqlService } from './member/member.nosql.service';

@Module({
  imports: [],
  controllers: [MemberController],
  providers: [MemberNosqlService, DynamodbService],
  // exports: [],
})
export class DomainModule {}
