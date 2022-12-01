export interface ISampleType {
  id: number;
  name?: string | null;
}

export type NewSampleType = Omit<ISampleType, 'id'> & { id: null };
