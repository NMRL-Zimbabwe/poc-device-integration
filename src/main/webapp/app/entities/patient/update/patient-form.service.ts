import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IPatient, NewPatient } from '../patient.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { patientId: unknown }> = Partial<Omit<T, 'patientId'>> & { patientId: T['patientId'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPatient for edit and NewPatientFormGroupInput for create.
 */
type PatientFormGroupInput = IPatient | PartialWithRequiredKeyOf<NewPatient>;

type PatientFormDefaults = Pick<NewPatient, 'patientId'>;

type PatientFormGroupContent = {
  patientId: FormControl<IPatient['patientId'] | NewPatient['patientId']>;
  firstName: FormControl<IPatient['firstName']>;
  lastName: FormControl<IPatient['lastName']>;
  dob: FormControl<IPatient['dob']>;
  age: FormControl<IPatient['age']>;
  gender: FormControl<IPatient['gender']>;
  primaryReferrer: FormControl<IPatient['primaryReferrer']>;
  clientPatientId: FormControl<IPatient['clientPatientId']>;
};

export type PatientFormGroup = FormGroup<PatientFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PatientFormService {
  createPatientFormGroup(patient: PatientFormGroupInput = { patientId: null }): PatientFormGroup {
    const patientRawValue = {
      ...this.getFormDefaults(),
      ...patient,
    };
    return new FormGroup<PatientFormGroupContent>({
      patientId: new FormControl(
        { value: patientRawValue.patientId, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      firstName: new FormControl(patientRawValue.firstName),
      lastName: new FormControl(patientRawValue.lastName),
      dob: new FormControl(patientRawValue.dob),
      age: new FormControl(patientRawValue.age),
      gender: new FormControl(patientRawValue.gender),
      primaryReferrer: new FormControl(patientRawValue.primaryReferrer),
      clientPatientId: new FormControl(patientRawValue.clientPatientId),
    });
  }

  getPatient(form: PatientFormGroup): IPatient | NewPatient {
    return form.getRawValue() as IPatient | NewPatient;
  }

  resetForm(form: PatientFormGroup, patient: PatientFormGroupInput): void {
    const patientRawValue = { ...this.getFormDefaults(), ...patient };
    form.reset(
      {
        ...patientRawValue,
        patientId: { value: patientRawValue.patientId, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): PatientFormDefaults {
    return {
      patientId: null,
    };
  }
}
