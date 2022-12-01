import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SamplePatientComponent } from '../list/sample-patient.component';
import { SamplePatientDetailComponent } from '../detail/sample-patient-detail.component';
import { SamplePatientUpdateComponent } from '../update/sample-patient-update.component';
import { SamplePatientRoutingResolveService } from './sample-patient-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const samplePatientRoute: Routes = [
  {
    path: '',
    component: SamplePatientComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SamplePatientDetailComponent,
    resolve: {
      samplePatient: SamplePatientRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SamplePatientUpdateComponent,
    resolve: {
      samplePatient: SamplePatientRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SamplePatientUpdateComponent,
    resolve: {
      samplePatient: SamplePatientRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(samplePatientRoute)],
  exports: [RouterModule],
})
export class SamplePatientRoutingModule {}
