import {Column} from "typeorm";

export abstract class BaseEntity {

  @Column("character varying", { name: "created_by", length: 50 })
  createdBy: string;

  @Column("timestamp with time zone", { name: "created_dt" })
  createdDt: Date;

  @Column("character varying", { name: "modified_by", length: 50 })
  modifiedBy: string;

  @Column("timestamp with time zone", { name: "modified_dt" })
  modifiedDt: Date;
}
