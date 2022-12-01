import dayjs from 'dayjs/esm';

import { ISamplePatient, NewSamplePatient } from './sample-patient.model';

export const sampleWithRequiredData: ISamplePatient = {
  id: 52600,
};

export const sampleWithPartialData: ISamplePatient = {
  id: 17938,
  firstName: 'Tessie',
  dob: dayjs('2022-12-01'),
  clientSampleId: 'Pakistan',
  clientContact: 'Program blockchains Jewelery',
  sampleTypeName: 'Keyboard',
  analysisServiceId: 'Cyprus',
  analysisServiceName: 'Armenia PNG',
  dateCollected: dayjs('2022-12-01'),
  dateRegistered: dayjs('2022-12-01'),
  dateTested: dayjs('2022-11-30'),
  datePublished: dayjs('2022-12-01'),
};

export const sampleWithFullData: ISamplePatient = {
  id: 76475,
  firstName: 'Katherine',
  lastName: 'Kuhic',
  dob: dayjs('2022-12-01'),
  age: 'Car',
  gender: 'quantifying Tools viral',
  primaryReferrer: 'COM Chicken Massachusetts',
  clientPatientId: 'Account Health Usability',
  clientSampleId: 'Bermuda Keyboard',
  clientContact: 'portals SMTP Illinois',
  sampleTypeId: 'Avon',
  sampleTypeName: 'Berkshire synthesize',
  analysisServiceId: 'Gorgeous withdrawal',
  analysisServiceName: 'asynchronous',
  dateCollected: dayjs('2022-12-01'),
  dateRegistered: dayjs('2022-11-30'),
  dateTested: dayjs('2022-11-30'),
  result: 'calculate Industrial Investor',
  unit: 'Berkshire orchestration',
  datePublished: dayjs('2022-11-30'),
  state: 'Plastic',
};

export const sampleWithNewData: NewSamplePatient = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
