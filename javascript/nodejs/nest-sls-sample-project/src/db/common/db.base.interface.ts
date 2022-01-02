export interface DbBaseInterface {
  create(param: any, table?: string): Promise<any | null>;

  get(param: any): Promise<any>;

  getAll(param?: any): Promise<any[] | any>;

  update(param: any, table?: string): Promise<any>;

  delete(param: any, table?: string): Promise<any>;
}
