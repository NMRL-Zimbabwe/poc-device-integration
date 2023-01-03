import dayjs from 'dayjs/esm';

export interface IPatient {
  patientId:  string;
  firstName?: string | null;
  lastName?: string | null;
  dob?: dayjs.Dayjs | null;
  age?: string | null;
  gender?: string | null;
  primaryReferrer?: string | null;
  clientPatientId?: string | null;
}

export type NewPatient = Omit<IPatient, 'patientId'> & { patientId: null };
