import { Column } from 'typeorm';

const ADMIN_ACCOUNT = 'admin';

export class BaseEntity {
  @Column('character varying', { name: 'created_by', length: 50 })
  createdBy: string;

  @Column('timestamp with time zone', { name: 'created_dt' })
  createdDt: Date;

  @Column('character varying', { name: 'modified_by', length: 50 })
  modifiedBy: string;

  @Column('timestamp with time zone', { name: 'modified_dt' })
  modifiedDt: Date;
}

/**
 * 기본 정보 설정
 * デフォルト値設定
 * @param obj
 * @param modifiedBy
 */
export const setBaseDefaultFields = (obj: any, modifiedBy?: string): void => {
  if (!(obj && obj instanceof BaseEntity)) {
    return;
  }

  const date: Date = new Date(new Date().toISOString());
  if (obj.createdBy && obj.createdDt) {
    obj.modifiedBy = modifiedBy;
    obj.modifiedDt = date;
  }
  obj.createdBy = ADMIN_ACCOUNT;
  obj.createdDt = date;
  obj.modifiedBy = ADMIN_ACCOUNT;
  obj.modifiedDt = date;
};
