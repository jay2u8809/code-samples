import { Injectable } from '@nestjs/common';
import { RequestDto } from './dto/request.dto';

@Injectable()
export class TestService {
  async postStub(dummy: RequestDto): Promise<any> {
    console.log(dummy.name1);
    console.log(dummy.name2);
    console.log(dummy.name3);
    return dummy;
  }
}
