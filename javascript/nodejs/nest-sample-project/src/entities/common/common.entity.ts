import {Column} from "typeorm";

export abstract class CommonEntity {

  @Column("character varying", { name: "created_by", length: 50 })
  createdBy: string;

  @Column("timestamp with time zone", { name: "created_dt" })
  createdDt: Date;

  @Column("character varying", { name: "updated_by", length: 50 })
  updatedBy: string;

  @Column("timestamp with time zone", { name: "updated_dt" })
  updatedDt: Date;
}
