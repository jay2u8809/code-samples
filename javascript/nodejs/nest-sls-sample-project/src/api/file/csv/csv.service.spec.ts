import { Test, TestingModule } from '@nestjs/testing';
import { CsvService } from './csv.service';
import { DataType } from '../../../common/code/data-type';

describe('CsvService', () => {
  let service: CsvService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [CsvService],
    }).compile();

    service = module.get<CsvService>(CsvService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  it('convertJsonToCsv', async () => {
    // object
    const dataA: any = [
      {
        id: 'id-001',
        nickname: 'nickname1',
        email: 'emailtest1@testmail1.com',
        createdAt: new Date().toISOString(),
      },
      {
        id: 'id-002',
        nickname: 'nickname2',
        email: 'emailtest2@testmail2.com',
        createdAt: new Date().toISOString(),
      },
      {
        id: 'id-003',
        nickname: 'nickname3',
        email: 'emailtest3@testmail3.com',
        createdAt: new Date().toISOString(),
      },
    ];
    // json
    const dataB: any = [
      {
        "id": "id-001",
        "nickname": "nickname1",
        "email": "emailtest1@testmail1.com",
        "createdAt": "202101"
      },
      {
        "id": "id-002",
        "nickname": "nickname2",
        "email": "emailtest2@testmail1.com",
        "createdAt": "202102"
      },
      {
        "id": "id-003",
        "nickname": "nickname3",
        "email": "emailtest3@testmail1.com",
        "createdAt": "202103"
      }
    ];
    await service.convertToCsv(dataA, DataType.JSON);
  });
});
