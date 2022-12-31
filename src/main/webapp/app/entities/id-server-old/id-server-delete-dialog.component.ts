import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIdServer } from 'app/shared/model/id-server.model';
import { IdServerService } from './id-server.service';

@Component({
  templateUrl: './id-server-delete-dialog.component.html',
})
export class IdServerDeleteDialogComponent {
  idServer?: IIdServer;

  constructor(protected idServerService: IdServerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.idServerService.delete(id).subscribe(() => {
      this.eventManager.broadcast('idServerListModification');
      this.activeModal.close();
    });
  }
}
