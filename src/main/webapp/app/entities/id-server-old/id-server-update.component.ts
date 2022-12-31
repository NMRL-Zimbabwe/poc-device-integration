import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IIdServer, IdServer } from 'app/shared/model/id-server.model';
import { IdServerService } from './id-server.service';

@Component({
  selector: 'jhi-id-server-update',
  templateUrl: './id-server-update.component.html',
})
export class IdServerUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    number: [],
    description: [],
    prefix: [],
  });

  constructor(protected idServerService: IdServerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ idServer }) => {
      this.updateForm(idServer);
    });
  }

  updateForm(idServer: IIdServer): void {
    this.editForm.patchValue({
      id: idServer.id,
      number: idServer.number,
      description: idServer.description,
      prefix: idServer.prefix,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const idServer = this.createFromForm();
    if (idServer.id !== undefined) {
      this.subscribeToSaveResponse(this.idServerService.update(idServer));
    } else {
      this.subscribeToSaveResponse(this.idServerService.create(idServer));
    }
  }

  private createFromForm(): IIdServer {
    return {
      ...new IdServer(),
      id: this.editForm.get(['id'])!.value,
      number: this.editForm.get(['number'])!.value,
      description: this.editForm.get(['description'])!.value,
      prefix: this.editForm.get(['prefix'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIdServer>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
