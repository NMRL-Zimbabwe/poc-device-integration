import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SamplePatientComponent } from './list/sample-patient.component';
import { SamplePatientDetailComponent } from './detail/sample-patient-detail.component';
import { SamplePatientUpdateComponent } from './update/sample-patient-update.component';
import { SamplePatientDeleteDialogComponent } from './delete/sample-patient-delete-dialog.component';
import { SamplePatientRoutingModule } from './route/sample-patient-routing.module';

@NgModule({
  imports: [SharedModule, SamplePatientRoutingModule],
  declarations: [SamplePatientComponent, SamplePatientDetailComponent, SamplePatientUpdateComponent, SamplePatientDeleteDialogComponent],
})
export class SamplePatientModule {}
