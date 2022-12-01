import { ISampleType, NewSampleType } from './sample-type.model';

export const sampleWithRequiredData: ISampleType = {
  id: 64866,
};

export const sampleWithPartialData: ISampleType = {
  id: 11163,
};

export const sampleWithFullData: ISampleType = {
  id: 3929,
  name: 'auxiliary',
};

export const sampleWithNewData: NewSampleType = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
