import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sample-patient.test-samples';

import { SamplePatientFormService } from './sample-patient-form.service';

describe('SamplePatient Form Service', () => {
  let service: SamplePatientFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SamplePatientFormService);
  });

  describe('Service methods', () => {
    describe('createSamplePatientFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSamplePatientFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            firstName: expect.any(Object),
            lastName: expect.any(Object),
            dob: expect.any(Object),
            age: expect.any(Object),
            gender: expect.any(Object),
            primaryReferrer: expect.any(Object),
            clientPatientId: expect.any(Object),
            clientSampleId: expect.any(Object),
            clientContact: expect.any(Object),
            sampleTypeId: expect.any(Object),
            sampleTypeName: expect.any(Object),
            analysisServiceId: expect.any(Object),
            analysisServiceName: expect.any(Object),
            dateCollected: expect.any(Object),
            dateRegistered: expect.any(Object),
            dateTested: expect.any(Object),
            result: expect.any(Object),
            unit: expect.any(Object),
            datePublished: expect.any(Object),
            state: expect.any(Object),
          })
        );
      });

      it('passing ISamplePatient should create a new form with FormGroup', () => {
        const formGroup = service.createSamplePatientFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            firstName: expect.any(Object),
            lastName: expect.any(Object),
            dob: expect.any(Object),
            age: expect.any(Object),
            gender: expect.any(Object),
            primaryReferrer: expect.any(Object),
            clientPatientId: expect.any(Object),
            clientSampleId: expect.any(Object),
            clientContact: expect.any(Object),
            sampleTypeId: expect.any(Object),
            sampleTypeName: expect.any(Object),
            analysisServiceId: expect.any(Object),
            analysisServiceName: expect.any(Object),
            dateCollected: expect.any(Object),
            dateRegistered: expect.any(Object),
            dateTested: expect.any(Object),
            result: expect.any(Object),
            unit: expect.any(Object),
            datePublished: expect.any(Object),
            state: expect.any(Object),
          })
        );
      });
    });

    describe('getSamplePatient', () => {
      it('should return NewSamplePatient for default SamplePatient initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSamplePatientFormGroup(sampleWithNewData);

        const samplePatient = service.getSamplePatient(formGroup) as any;

        expect(samplePatient).toMatchObject(sampleWithNewData);
      });

      it('should return NewSamplePatient for empty SamplePatient initial value', () => {
        const formGroup = service.createSamplePatientFormGroup();

        const samplePatient = service.getSamplePatient(formGroup) as any;

        expect(samplePatient).toMatchObject({});
      });

      it('should return ISamplePatient', () => {
        const formGroup = service.createSamplePatientFormGroup(sampleWithRequiredData);

        const samplePatient = service.getSamplePatient(formGroup) as any;

        expect(samplePatient).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISamplePatient should not enable id FormControl', () => {
        const formGroup = service.createSamplePatientFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSamplePatient should disable id FormControl', () => {
        const formGroup = service.createSamplePatientFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
