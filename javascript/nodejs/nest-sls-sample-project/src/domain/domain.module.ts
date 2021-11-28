import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Member } from 'src/entities/member/member';
import { MemberController } from './member/member.controller';
import { MemberNosqlService } from './member/member.nosql.service';
import { MemberSqlService } from './member/member.sql.service';
import { DynamodbService } from '../db/nosql/dynamodb/dynamodb.service';

@Module({
  imports: [TypeOrmModule.forFeature([Member])],
  controllers: [MemberController],
  providers: [MemberSqlService, MemberNosqlService, DynamodbService],
  exports: [TypeOrmModule],
})
export class DomainModule {}
