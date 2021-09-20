import { Test, TestingModule } from '@nestjs/testing';
import { MemberService } from './member.service';
import { MemberJoinRequestDto } from './dto/member.join.request.dto';
import { Member } from '../../entities/member/member';
import { MemberRepository } from './member.repository';

describe('MemberService', () => {
  let service: MemberService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      imports: [MemberRepository],
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
      joinRequestDto.emailAddress = 'test@email.com';
      const savedMemberSn: Promise<bigint> = service.create(joinRequestDto);
      console.log(`Saved MemberSn : ${savedMemberSn}`);
    });
    expect(true);
  });
});

test('Save Test1', () => {
  const member: Member = new Member();
  const joinRequestDto: MemberJoinRequestDto = new MemberJoinRequestDto();

  joinRequestDto.memberId = 'TestID';
  joinRequestDto.emailAddress = 'testMEail@fdsaf.com';
  Object.assign(member, joinRequestDto);

  console.log(
    `MemberJoinDto ID : ${joinRequestDto.memberId}, Member ID : ${member.memberId}`,
  );
  console.log(
    `MemberJoinDto Email : ${joinRequestDto.emailAddress}, Member Email : ${member.emailAddress}`,
  );
});
