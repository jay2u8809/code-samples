import { Test, TestingModule } from '@nestjs/testing';
import { QrcodeService } from './qrcode.service';

describe('QrcodeService', () => {
  let service: QrcodeService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [QrcodeService],
    }).compile();

    service = module.get<QrcodeService>(QrcodeService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  describe('QrCode Generate Test', () => {
    it('generateQrCodeByImageUriTest : SizeIsNotZero', () => {
      const result = service.generateQrCodeByImageUri('http://www.naver.com/', 80);
      console.log(`QRCODE RESULT : ${result}`);
    });
    it('generateQrCodeByImageUriTest : SizeIsZero', () => {
      const result = service.generateQrCodeByImageUri('http://www.naver.com/');
      console.log(`QRCODE RESULT : ${result}`);
    });
    it('generateQrCodeByImageUriTest : SizeIsMinus', () => {
      const result = service.generateQrCodeByImageUri('http://www.naver.com/', -30);
      console.log(`QRCODE RESULT : ${result}`);
    });
    it('generateQrCodeByImageUriTest : SizeIsOver1000', () => {
      const result = service.generateQrCodeByImageUri('http://www.naver.com/', 1001);
      console.log(`QRCODE RESULT : ${result}`);
    });
  });

});
