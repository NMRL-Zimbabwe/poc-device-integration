import { IIdServer, NewIdServer } from './id-server.model';

export const sampleWithRequiredData: IIdServer = {
  id: 64866,
};

export const sampleWithPartialData: IIdServer = {
  id: 11163,
};

export const sampleWithFullData: IIdServer = {
  id: 3929,
  name: 'auxiliary',
};

export const sampleWithNewData: NewIdServer = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
