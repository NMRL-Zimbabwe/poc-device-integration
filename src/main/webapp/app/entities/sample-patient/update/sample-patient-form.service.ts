import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISamplePatient, NewSamplePatient } from '../sample-patient.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISamplePatient for edit and NewSamplePatientFormGroupInput for create.
 */
type SamplePatientFormGroupInput = ISamplePatient | PartialWithRequiredKeyOf<NewSamplePatient>;

type SamplePatientFormDefaults = Pick<NewSamplePatient, 'id'>;

type SamplePatientFormGroupContent = {
  id: FormControl<ISamplePatient['id'] | NewSamplePatient['id']>;
  firstName: FormControl<ISamplePatient['firstName']>;
  lastName: FormControl<ISamplePatient['lastName']>;
  dob: FormControl<ISamplePatient['dob']>;
  age: FormControl<ISamplePatient['age']>;
  gender: FormControl<ISamplePatient['gender']>;
  primaryReferrer: FormControl<ISamplePatient['primaryReferrer']>;
  clientPatientId: FormControl<ISamplePatient['clientPatientId']>;
  clientSampleId: FormControl<ISamplePatient['clientSampleId']>;
  clientContact: FormControl<ISamplePatient['clientContact']>;
  sampleTypeId: FormControl<ISamplePatient['sampleTypeId']>;
  sampleTypeName: FormControl<ISamplePatient['sampleTypeName']>;
  analysisServiceId: FormControl<ISamplePatient['analysisServiceId']>;
  analysisServiceName: FormControl<ISamplePatient['analysisServiceName']>;
  dateCollected: FormControl<ISamplePatient['dateCollected']>;
  dateRegistered: FormControl<ISamplePatient['dateRegistered']>;
  dateTested: FormControl<ISamplePatient['dateTested']>;
  result: FormControl<ISamplePatient['result']>;
  unit: FormControl<ISamplePatient['unit']>;
  datePublished: FormControl<ISamplePatient['datePublished']>;
  state: FormControl<ISamplePatient['state']>;
};

export type SamplePatientFormGroup = FormGroup<SamplePatientFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SamplePatientFormService {
  createSamplePatientFormGroup(samplePatient: SamplePatientFormGroupInput = { id: null }): SamplePatientFormGroup {
    const samplePatientRawValue = {
      ...this.getFormDefaults(),
      ...samplePatient,
    };
    return new FormGroup<SamplePatientFormGroupContent>({
      id: new FormControl(
        { value: samplePatientRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      firstName: new FormControl(samplePatientRawValue.firstName),
      lastName: new FormControl(samplePatientRawValue.lastName),
      dob: new FormControl(samplePatientRawValue.dob),
      age: new FormControl(samplePatientRawValue.age),
      gender: new FormControl(samplePatientRawValue.gender),
      primaryReferrer: new FormControl(samplePatientRawValue.primaryReferrer),
      clientPatientId: new FormControl(samplePatientRawValue.clientPatientId),
      clientSampleId: new FormControl(samplePatientRawValue.clientSampleId),
      clientContact: new FormControl(samplePatientRawValue.clientContact),
      sampleTypeId: new FormControl(samplePatientRawValue.sampleTypeId),
      sampleTypeName: new FormControl(samplePatientRawValue.sampleTypeName),
      analysisServiceId: new FormControl(samplePatientRawValue.analysisServiceId),
      analysisServiceName: new FormControl(samplePatientRawValue.analysisServiceName),
      dateCollected: new FormControl(samplePatientRawValue.dateCollected),
      dateRegistered: new FormControl(samplePatientRawValue.dateRegistered),
      dateTested: new FormControl(samplePatientRawValue.dateTested),
      result: new FormControl(samplePatientRawValue.result),
      unit: new FormControl(samplePatientRawValue.unit),
      datePublished: new FormControl(samplePatientRawValue.datePublished),
      state: new FormControl(samplePatientRawValue.state),
    });
  }

  getSamplePatient(form: SamplePatientFormGroup): ISamplePatient | NewSamplePatient {
    return form.getRawValue() as ISamplePatient | NewSamplePatient;
  }

  resetForm(form: SamplePatientFormGroup, samplePatient: SamplePatientFormGroupInput): void {
    const samplePatientRawValue = { ...this.getFormDefaults(), ...samplePatient };
    form.reset(
      {
        ...samplePatientRawValue,
        id: { value: samplePatientRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SamplePatientFormDefaults {
    return {
      id: null,
    };
  }
}
