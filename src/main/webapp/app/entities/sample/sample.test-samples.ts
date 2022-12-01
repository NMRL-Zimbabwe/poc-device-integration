import dayjs from 'dayjs/esm';

import { ISample, NewSample } from './sample.model';

export const sampleWithRequiredData: ISample = {
  id: 15510,
};

export const sampleWithPartialData: ISample = {
  id: 8391,
  clientContact: 'Soft',
  sampleTypeId: 'Music',
  analysisServiceName: 'Table',
  dateCollected: dayjs('2022-12-01'),
  dateRegistered: dayjs('2022-12-01'),
  dateTested: dayjs('2022-11-30'),
  datePublished: dayjs('2022-12-01'),
  state: 'Assimilated',
};

export const sampleWithFullData: ISample = {
  id: 64268,
  patientId: 'Buckinghamshire',
  clientSampleId: 'Technician Monitored',
  clientContact: 'Mouse Leu Pike',
  sampleTypeId: 'open-source',
  sampleTypeName: 'microchip Program',
  analysisServiceId: 'calculating Greece',
  analysisServiceName: 'haptic',
  dateCollected: dayjs('2022-11-30'),
  dateRegistered: dayjs('2022-11-30'),
  dateTested: dayjs('2022-11-30'),
  result: 'Savings Bedfordshire',
  unit: 'clicks-and-mortar',
  datePublished: dayjs('2022-11-30'),
  state: 'Identity Wooden Lebanon',
};

export const sampleWithNewData: NewSample = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
