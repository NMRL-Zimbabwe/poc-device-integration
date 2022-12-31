export interface IIdServer {
  id: number;
  name?: string | null;
  prefix?: string | null;
  description?: string | null;
  number?: number | null;

}

export type NewIdServer = Omit<IIdServer, 'id'> & { id: null };
