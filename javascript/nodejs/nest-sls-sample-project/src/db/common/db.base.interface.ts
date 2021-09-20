export interface DbBaseInterface {
  create(param: any, table?: string): Promise<any | null>;

  get(param: any): Promise<any | null>;

  getAll(param?: any): Promise<any[] | null>;

  update(param: any, table?: string): Promise<any | null>;

  delete(param: any, table?: string): Promise<any | null>;
}
