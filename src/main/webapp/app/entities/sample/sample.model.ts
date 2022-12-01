import dayjs from 'dayjs/esm';

export interface ISample {
  id: number;
  patientId?: string | null;
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

export type NewSample = Omit<ISample, 'id'> & { id: null };
