import { Test, TestingModule } from '@nestjs/testing';
import { MemberSqlService } from './member.sql.service';
import { MemberJoinRequestDto } from './dto/member.join.request.dto';
import { Member } from '../../entities/member/member';
import { MemberRepository } from './member.repository';
import { MemberNosqlService } from './member.nosql.service';

describe('MemberService', () => {
  let sqlService: MemberSqlService;
  let nosqlService: MemberNosqlService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      imports: [MemberRepository],
      providers: [MemberSqlService, MemberNosqlService],
    }).compile();

    sqlService = module.get<MemberSqlService>(MemberSqlService);
    nosqlService = module.get<MemberNosqlService>(MemberNosqlService);
  });

  it('should be defined', () => {
    expect(sqlService).toBeDefined();
    expect(nosqlService).toBeDefined();
  });

  describe('Member Save Test', () => {
    it('Save Test', () => {
      const joinRequestDto: MemberJoinRequestDto = new MemberJoinRequestDto();
      joinRequestDto.memberId = 'testID';
      joinRequestDto.memberPw = 'asdfqwer12';
      joinRequestDto.emailAddress = 'test@email.com';
      const savedMemberSn: Promise<bigint> = sqlService.create(joinRequestDto);
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
