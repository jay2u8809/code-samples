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
      memberJoinDto.memberPw = 'asdfqwer12';
      memberJoinDto.memberEmail = 'test@email.com';
      const savedMemberSn: Promise<bigint> = service.saveMember(memberJoinDto);
      console.log(`Saved MemberSn : ${savedMemberSn}`);
    });
  });
});

test('Save Test1', () => {
  const member: Member = new Member();
  const memberJoinDto: MemberJoinDto = new MemberJoinDto();

  memberJoinDto.memberId = 'TestID';
  memberJoinDto.memberEmail = 'testMEail@fdsaf.com';
  Object.assign(member, memberJoinDto);

  console.log(`MemberJoinDto ID : ${memberJoinDto.memberId}, Member ID : ${member.memberId}`);
  console.log(`MemberJoinDto Email : ${memberJoinDto.memberEmail}, Member Email : ${member.emailAddress}`);
});
