import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Member } from 'src/entities/member/member';
import { MemberController } from './member/member.controller';
import { MemberNosqlService } from './member/member.nosql.service';
import { MemberSqlService } from './member/member.sql.service';
import { DynamodbService } from '../db/nosql/dynamodb/dynamodb.service';
import { AuthController } from './auth/auth.controller';
import { AuthService } from './auth/auth.service';

@Module({
  imports: [TypeOrmModule.forFeature([Member])],
  controllers: [MemberController, AuthController],
  providers: [
    MemberSqlService,
    MemberNosqlService,
    DynamodbService,
    AuthService,
  ],
  exports: [MemberSqlService],
})
export class DomainModule {}
