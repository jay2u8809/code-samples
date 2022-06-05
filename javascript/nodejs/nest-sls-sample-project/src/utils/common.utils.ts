import bcrypt from 'bcrypt';
// const bcrypt = require('bcrypt');
import * as yaml from 'js-yaml';
import fs from 'fs';
import * as path from 'path';
import { NotFoundException } from '@nestjs/common';

export class CommonUtils {
  static transferFromRaw = (raw: any): any => {
    const result: any = {};
    Object.keys(raw).map((key) => {
      const before: string[] = key.split('_');
      let after = before[0];
      for (let i = 1; i < before.length; i++) {
        const name = before[i].charAt(0).toUpperCase() + before[i].substr(1);
        after += name;
      }
      result[after] = raw[key];
    });
    return result;
  };

  static checkUrl = (data: string): boolean => {
    const urlReg =
      /https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)/g;
    return urlReg.test(data);
  };

  /**
   * Load YAML File Data
   * @param path file path(absolute path of project)
   * @param tag 
   * @returns 
   */
  static async loadConfigByYaml(path: string, tag?: string): Promise<Record<string, any>> {
    // console.debug('config-path', path);

    if (!path) {
      throw new NotFoundException('file path is nothing', path);
    }

    path = path[0] === '/' ? path.slice(1) : path;
    // console.debug('fixed-path', path);

    if (!fs.existsSync(path)) {
      throw new NotFoundException('file is nothing', path);
    }

    const config: Record<string, any> = yaml.load(fs.readFileSync(path, {
      encoding: 'utf-8',
    })) as Record<string, any>;
    // console.debug(tag?.toUpperCase() || 'YamlConfig', 'load-yaml-config', JSON.stringify(config));

    return config;
  }
}
