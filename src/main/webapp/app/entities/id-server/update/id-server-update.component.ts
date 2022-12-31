import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IdServerFormService, SampleTypeFormGroup } from './id-server-form.service';
import { IIdServer } from '../id-server.model';
import { IdServerService } from '../service/id-server.service';

@Component({
  selector: 'jhi-id-server-update',
  templateUrl: './id-server-update.component.html',
})
export class IdServerUpdateComponent implements OnInit {
  isSaving = false;
  sampleType: IIdServer | null = null;

  editForm: SampleTypeFormGroup = this.sampleTypeFormService.createSampleTypeFormGroup();

  constructor(
    protected sampleTypeService: IdServerService,
    protected sampleTypeFormService: IdServerFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sampleType }) => {
      this.sampleType = sampleType;
      if (sampleType) {
        this.updateForm(sampleType);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sampleType = this.sampleTypeFormService.getSampleType(this.editForm);
    if (sampleType.id !== null) {
      this.subscribeToSaveResponse(this.sampleTypeService.update(sampleType));
    } else {
      this.subscribeToSaveResponse(this.sampleTypeService.create(sampleType));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIdServer>>): void {
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

  protected updateForm(sampleType: IIdServer): void {
    this.sampleType = sampleType;
    this.sampleTypeFormService.resetForm(this.editForm, sampleType);
  }
}
