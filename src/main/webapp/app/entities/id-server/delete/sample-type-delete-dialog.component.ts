import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IIdServer } from '../id-server.model';
import { IdServerService } from '../service/id-server.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './id-server-delete-dialog.component.html',
})
export class IdServerDeleteDialogComponent {
  sampleType?: IIdServer;

  constructor(protected sampleTypeService: IdServerService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sampleTypeService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
