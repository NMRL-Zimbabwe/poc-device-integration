import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SamplePatientFormService, SamplePatientFormGroup } from './sample-patient-form.service';
import { ISamplePatient } from '../sample-patient.model';
import { SamplePatientService } from '../service/sample-patient.service';

@Component({
  selector: 'jhi-sample-patient-update',
  templateUrl: './sample-patient-update.component.html',
})
export class SamplePatientUpdateComponent implements OnInit {
  isSaving = false;
  samplePatient: ISamplePatient | null = null;

  editForm: SamplePatientFormGroup = this.samplePatientFormService.createSamplePatientFormGroup();

  constructor(
    protected samplePatientService: SamplePatientService,
    protected samplePatientFormService: SamplePatientFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ samplePatient }) => {
      this.samplePatient = samplePatient;
      if (samplePatient) {
        this.updateForm(samplePatient);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const samplePatient = this.samplePatientFormService.getSamplePatient(this.editForm);
    if (samplePatient.id !== null) {
      this.subscribeToSaveResponse(this.samplePatientService.update(samplePatient));
    } else {
      this.subscribeToSaveResponse(this.samplePatientService.create(samplePatient));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISamplePatient>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(samplePatient: ISamplePatient): void {
    this.samplePatient = samplePatient;
    this.samplePatientFormService.resetForm(this.editForm, samplePatient);
  }
}
