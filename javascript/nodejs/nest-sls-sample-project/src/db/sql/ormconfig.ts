import { TypeOrmModuleOptions } from '@nestjs/typeorm';
import dotenv from 'dotenv';

dotenv.config();
const config: TypeOrmModuleOptions = {
  name: 'default',
  type: 'postgres',
  host: 'localhost',
  port: 5432,
  username: 'devuser',  // process.env.DB_USERNAME,
  password: 'devuser',  // process.env.DB_PASSWORD,
  database: 'devdb',    // process.env.DB_DATABASE,
  schema: 'dev_schema',
  entities: ['../../../dist/entities/**/*.js'],
  migrations: [__dirname + '/migrations/*.ts'],
  cli: { migrationsDir: 'migrations' },
  autoLoadEntities: true,
  // charset: 'utf8mb4',
  synchronize: false,
  logging: true,
  keepConnectionAlive: true,
};

export = config;
