import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IIdServer, IdServer } from 'app/shared/model/id-server.model';
import { IdServerService } from './id-server.service';
import { IdServerComponent } from './id-server.component';
import { IdServerDetailComponent } from './id-server-detail.component';
import { IdServerUpdateComponent } from './id-server-update.component';

@Injectable({ providedIn: 'root' })
export class IdServerResolve implements Resolve<IIdServer> {
  constructor(private service: IdServerService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IIdServer> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((idServer: HttpResponse<IdServer>) => {
          if (idServer.body) {
            return of(idServer.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new IdServer());
  }
}

export const idServerRoute: Routes = [
  {
    path: '',
    component: IdServerComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'centralRepositoryApp.idServer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: IdServerDetailComponent,
    resolve: {
      idServer: IdServerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'centralRepositoryApp.idServer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: IdServerUpdateComponent,
    resolve: {
      idServer: IdServerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'centralRepositoryApp.idServer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: IdServerUpdateComponent,
    resolve: {
      idServer: IdServerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'centralRepositoryApp.idServer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
