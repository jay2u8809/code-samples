import * as childProcess from 'child_process';
import fs from 'fs';

const TAG = 'CommandUtils';

export type CurlOptions = {
  headers: any;
  method: string;
  body?: any;
  file?: string;
  options?: string;
};

export class CommandUtils {
  static async curlByCommand(
    url: string,
    options: CurlOptions,
  ): Promise<string> {
    const headers = this.generateHeader(options?.headers);
    const method = `-X ${options.method.toUpperCase()}`;
    let command = `curl ${url} ${method} ${headers}`;
    if (!!options.body) {
      const body = ` -d '${JSON.stringify(options.body)}'`;
      command += body;
    }
    console.debug('TEST', options?.file);
    if (!!options.file) {
      // const isExist = fs.existsSync(options.file);
      // const file = isExist ? ` -F file=@${options.file}` : '';
      const file = ` -F 'file=@${options.file}'`;
      command += file;
    }
    if (!!options.options) {
      const option = ` ${options.options}`;
      command += option;
    }
    console.log(TAG, 'curl-command', command);

    const response = childProcess.execSync(command, {
      encoding: 'utf8',
    });
    console.log(TAG, 'curl-response', response);

    return response;
  }

  // === private ===
  private static generateHeader(headers: any): string {
    if (!headers) throw new Error('params is not exist');

    return Object.entries(headers)
      .map((header) => {
        return `-H '${header[0]}: ${header[1]}'`;
      })
      .join(' ');
  }
}
