import {Column} from "typeorm";

export class BaseEntity {

  @Column("character varying", { name: "created_by", length: 50 })
  createdBy: string;

  @Column("timestamp with time zone", { name: "created_dt" })
  createdDt: Date;

  @Column("character varying", { name: "modified_by", length: 50 })
  modifiedBy: string;

  @Column("timestamp with time zone", { name: "modified_dt" })
  modifiedDt: Date;
}

/**
 * 기본 정보 설정
 * デフォルト値設定
 * @param obj
 */
export function setBaseDefaultFields(obj: Object): void {

  if (!(obj && obj instanceof BaseEntity)) {
    return;
  }

  obj.createdBy = 'admin';
  obj.createdDt = new Date();
  obj.modifiedBy = 'admin';
  obj.modifiedDt = new Date();
}
