import { Test, TestingModule } from '@nestjs/testing';
import { MemberService } from './member.service';
import {MemberJoinDto} from "./dto/member.join.dto";
import {Member} from "../../entities/member/member";

describe('MemberService', () => {
  let service: MemberService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [MemberService],
    }).compile();

    service = module.get<MemberService>(MemberService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  describe('Member Save Test', () => {
    it('Save Test', () => {
      const memberJoinDto: MemberJoinDto = new MemberJoinDto();
      memberJoinDto.memberId = 'testID';
      memberJoinDto.memberPassword = 'asdfqwer12';
      memberJoinDto.email = 'test@email.com';
      const saved: Promise<Member> = service.joinMember(memberJoinDto);
      console.log(`Member Saved : ${saved}`);
    });
  });
});
