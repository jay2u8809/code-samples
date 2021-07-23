import { Injectable } from '@nestjs/common';
import {isEmpty} from "../../common/common.utils";

@Injectable()
export class QrcodeService {

  // QrCode Url
  private readonly QRCODE_API_URL: string =
    "https://api.qrserver.com/v1/create-qr-code/";

  // QrCode Image Default Size
  private readonly QRCODE_SIZE: number = 50;

  private readonly URL_REGEX: RegExp =
    /^(((http(s:?))\:\/\/)?)([0-9a-zA-Z\-]+\.)+[a-zA-Z]{2,6}(\:[0-9]+)?(\/\S*)?/;

  private readonly _URL_REGEX: RegExp =
    /^(https?):\/\/([^:\/\s]+)(:([^\/]*))?((\/[^\s/\/]+)*)?\/([^#\s\?]*)(\?([^#\s]*))?(#(\w*))?$/;

  private readonly MAX_SIZE: number = 1000;
  private readonly MIN_SIZE: number = 0;

  generateQrCodeByImageUri(uriPath:string, imageSize?: number): string {

    if (isEmpty(uriPath)) {
      return null;
    }

    // check URI Expression
    if (this.URL_REGEX.test(uriPath)) {
      throw new Error("Encoding Fail");
    }

    // UTF-8 Encoding
    const encodedUrl = encodeURIComponent(uriPath);

    // QrCode Image Size
    const size = String(
                  (isEmpty(imageSize) || imageSize <= this.MIN_SIZE || this.MAX_SIZE < imageSize)
                  ? this.QRCODE_SIZE
                  : imageSize);

    return this.QRCODE_API_URL
          + '?'
          + '&size=' + size + 'x' + size
          + '&data=' + encodedUrl;
  }
}
