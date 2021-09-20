import { Module } from '@nestjs/common';
import { MemberSqlService } from './member/member.sql.service';
import { MemberController } from './member/member.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Member } from '../entities/member/member';
import { DynamodbService } from '../db/nosql/dynamodb/dynamodb.service';
import { MemberNosqlService } from './member/member.nosql.service';

@Module({
  imports: [TypeOrmModule.forFeature([Member])],
  providers: [MemberSqlService, MemberNosqlService, DynamodbService],
  controllers: [MemberController],
})
export class DomainModule {}
