import { Injectable, InternalServerErrorException } from '@nestjs/common';

const TAG = 'QrcodeServie';

@Injectable()
export class QrcodeService {
  // QrCode Url
  private readonly QRCODE_API_URL: string =
    'https://api.qrserver.com/v1/create-qr-code/';

  // QrCode Image Default Size
  private readonly QRCODE_SIZE: number = 150;

  private readonly URL_REGEX: RegExp =
    /^(((http(s:?))\:\/\/)?)([0-9a-zA-Z\-]+\.)+[a-zA-Z]{2,6}(\:[0-9]+)?(\/\S*)?/;

  private readonly _URL_REGEX: RegExp =
    /^(https?):\/\/([^:\/\s]+)(:([^\/]*))?((\/[^\s/\/]+)*)?\/([^#\s\?]*)(\?([^#\s]*))?(#(\w*))?$/;

  private readonly MAX_SIZE: number = 1000;
  private readonly MIN_SIZE: number = 0;

  generateQrCodeByImageUri(uriPath: string, imageSize?: number): string {
    console.debug(TAG, 'params', uriPath, imageSize);
    if (!uriPath || uriPath.length === 0) {
      console.log(TAG, 'path-not-exist', uriPath?.length);
      return;
    }

    // check URI Expression
    if (this.URL_REGEX.test(uriPath)) {
      throw new InternalServerErrorException('Encoding Fail');
    }

    // UTF-8 Encoding
    const encodedUrl = encodeURIComponent(uriPath);

    // QrCode Image Size
    const size = String(
      !imageSize || imageSize <= this.MIN_SIZE || this.MAX_SIZE < imageSize
        ? this.QRCODE_SIZE
        : imageSize,
    );
    const result = `${this.QRCODE_API_URL}?size=${size}x${size}&data=${encodedUrl}`;
    console.debug(TAG, 'result', result);

    return `${this.QRCODE_API_URL}?size=${size}x${size}&data=${encodedUrl}`;
  }
}
