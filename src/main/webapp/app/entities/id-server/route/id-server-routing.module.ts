import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { IdServerComponent } from '../list/id-server.component';
import { IdServerDetailComponent } from '../detail/id-server-detail.component';
import { IdServerUpdateComponent } from '../update/id-server-update.component';
import { IdServerRoutingResolveService } from './id-server-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sampleTypeRoute: Routes = [
  {
    path: '',
    component: IdServerComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: IdServerDetailComponent,
    resolve: {
      sampleType: IdServerRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: IdServerUpdateComponent,
    resolve: {
      sampleType: IdServerRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: IdServerUpdateComponent,
    resolve: {
      sampleType: IdServerRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sampleTypeRoute)],
  exports: [RouterModule],
})
export class IdServerRoutingModule {}
