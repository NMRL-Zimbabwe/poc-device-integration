import dayjs from 'dayjs/esm';

export interface ISamplePatient {
  id: number;
  firstName?: string | null;
  lastName?: string | null;
  dob?: dayjs.Dayjs | null;
  age?: string | null;
  gender?: string | null;
  primaryReferrer?: string | null;
  clientPatientId?: string | null;
  clientSampleId?: string | null;
  clientContact?: string | null;
  sampleTypeId?: string | null;
  sampleTypeName?: string | null;
  analysisServiceId?: string | null;
  analysisServiceName?: string | null;
  dateCollected?: dayjs.Dayjs | null;
  dateRegistered?: dayjs.Dayjs | null;
  dateTested?: dayjs.Dayjs | null;
  result?: string | null;
  unit?: string | null;
  datePublished?: dayjs.Dayjs | null;
  state?: string | null;
}

export type NewSamplePatient = Omit<ISamplePatient, 'id'> & { id: null };
