import { Module } from '@nestjs/common';
import { MemberService } from './member/member.service';
import { MemberController } from './member/member.controller';
import {TypeOrmModule} from "@nestjs/typeorm";
import {Member} from "../entities/member/member";

@Module({
  imports: [
    TypeOrmModule.forFeature([Member]),
  ],
  providers: [MemberService],
  controllers: [MemberController]
})
export class DomainModule {}
