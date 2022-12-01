import dayjs from 'dayjs/esm';

import { IPatient, NewPatient } from './patient.model';

export const sampleWithRequiredData: IPatient = {
  id: 39843,
};

export const sampleWithPartialData: IPatient = {
  id: 21672,
  lastName: 'Murray',
  age: 'Associate Tennessee',
  primaryReferrer: 'up',
  clientPatientId: 'applications Aruba digital',
};

export const sampleWithFullData: IPatient = {
  id: 67802,
  firstName: 'Camilla',
  lastName: 'Cremin',
  dob: dayjs('2022-11-30'),
  age: 'Morocco Chips grey',
  gender: 'Account',
  primaryReferrer: 'synthesize Web',
  clientPatientId: 'Global programming Loan',
};

export const sampleWithNewData: NewPatient = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
