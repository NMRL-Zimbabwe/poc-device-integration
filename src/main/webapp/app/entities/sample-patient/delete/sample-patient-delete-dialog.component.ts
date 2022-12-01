import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISamplePatient } from '../sample-patient.model';
import { SamplePatientService } from '../service/sample-patient.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sample-patient-delete-dialog.component.html',
})
export class SamplePatientDeleteDialogComponent {
  samplePatient?: ISamplePatient;

  constructor(protected samplePatientService: SamplePatientService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.samplePatientService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
