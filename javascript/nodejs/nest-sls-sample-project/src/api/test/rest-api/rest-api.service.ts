import { BadRequestException, Injectable } from '@nestjs/common';

const TAG = 'REST-API_TEST_SERVICE';

@Injectable()
export class RestApiService {
  async create(params: any): Promise<any> {
    console.assert(!!params, `${TAG}, ${params}`);
    return new Promise((resolve, reject) => {
      if (!params) {
        reject(new BadRequestException('Empty params'));
      }
      resolve(params);
    });
  }

  async get(params: any): Promise<any> {
    console.assert(!!params, `${TAG}, ${params}`);
    return Promise.resolve(params);
  }

  async getAll(params?: any): Promise<any> {
    console.assert(!!params, `${TAG}, ${params}`);
    return Promise.resolve(params);
  }

  async update(params: any): Promise<any> {
    console.assert(!!params, `${TAG}, ${params}`);
    if (!params) {
      return Promise.reject(new BadRequestException('Empty params'));
    }
    return Promise.resolve(params);
  }

  async delete(params: any): Promise<any> {
    console.assert(!!params, `${TAG}, ${params}`);
    if (!params) {
      return Promise.reject(new BadRequestException('Empty params'));
    }
    return Promise.resolve(params);
  }
}
