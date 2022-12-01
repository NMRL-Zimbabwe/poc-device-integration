import dayjs from 'dayjs/esm';

export interface IPatient {
  id: number;
  firstName?: string | null;
  lastName?: string | null;
  dob?: dayjs.Dayjs | null;
  age?: string | null;
  gender?: string | null;
  primaryReferrer?: string | null;
  clientPatientId?: string | null;
}

export type NewPatient = Omit<IPatient, 'id'> & { id: null };
