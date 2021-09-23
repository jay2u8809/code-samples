import { Module } from '@nestjs/common';
import { MemberSqlService } from './member/member.sql.service';
import { MemberController } from './member/member.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Member } from '../entities/member/member';
import { DynamodbService } from '../db/nosql/dynamodb/dynamodb.service';
import { MemberNosqlService } from './member/member.nosql.service';
import configuration from 'src/config/configuration';

const useSql: boolean = configuration().db.useSql;
const importModules: any[] = [];
const providerList: any[] = [
  MemberNosqlService,
  DynamodbService,
];
if (useSql) {
  importModules.push(TypeOrmModule.forFeature([Member]));
  providerList.push(MemberSqlService);
}

@Module({
  imports: importModules,
  providers: providerList,
  controllers: [MemberController],
})
export class DomainModule {}
