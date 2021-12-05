import { Test, TestingModule } from '@nestjs/testing';
import { MemberSqlService } from './member.sql.service';
import { MemberJoinRequestDto } from './dto/member.join.request.dto';
import { Member } from '../../entities/member/member';
import { createConnections, getConnection } from 'typeorm';

describe('MemberService', () => {
  let sqlService: MemberSqlService;

  beforeAll(async () => {
    await createConnections();
  });

  afterAll(async () => {
    const defaultConnection = getConnection('default');
    await defaultConnection.close();
  });

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [MemberSqlService],
    }).compile();

    sqlService = module.get<MemberSqlService>(MemberSqlService);
  });

  it.skip('should be defined', () => {
    expect(sqlService).toBeDefined();
  });

  describe.skip('Member SQL Test', () => {
    it('getAll Test', async () => {
      const results: Member[] = await sqlService.getAll();
      const len: number = results.filter(
        (member) => member.memberId !== undefined,
      ).length;
      expect(results.length).toEqual(len);
    });

    it('get Test', async () => {
      const param = '';
      const result: Member = await sqlService.get(param);
      console.log(result.memberId);
      expect(result.memberSn).toEqual(String(param));
    });

    it('getByEmail Test', async () => {
      const param = '';
      const results: Member[] = await sqlService.getByEmail(param);
      const len: number = results.filter(
        (member) => member.memberId !== undefined,
      ).length;
      expect(results.length).toEqual(len);
    });
  });
});

test.skip('Save Test1', () => {
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
