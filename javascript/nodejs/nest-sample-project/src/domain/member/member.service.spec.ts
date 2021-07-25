import { Test, TestingModule } from '@nestjs/testing';
import { MemberService } from './member.service';
import {MemberJoinRequestDto} from "./dto/member.join.request.dto";
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
      const joinRequestDto: MemberJoinRequestDto = new MemberJoinRequestDto();
      joinRequestDto.memberId = 'testID';
      joinRequestDto.memberPw = 'asdfqwer12';
      joinRequestDto.memberEmail = 'test@email.com';
      const savedMemberSn: Promise<bigint> = service.saveMember(joinRequestDto);
      console.log(`Saved MemberSn : ${savedMemberSn}`);
    });
  });
});

test('Save Test1', () => {
  const member: Member = new Member();
  const joinRequestDto: MemberJoinRequestDto = new MemberJoinRequestDto();

  joinRequestDto.memberId = 'TestID';
  joinRequestDto.memberEmail = 'testMEail@fdsaf.com';
  Object.assign(member, joinRequestDto);

  console.log(`MemberJoinDto ID : ${joinRequestDto.memberId}, Member ID : ${member.memberId}`);
  console.log(`MemberJoinDto Email : ${joinRequestDto.memberEmail}, Member Email : ${member.emailAddress}`);
});
