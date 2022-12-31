import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IIdServer } from '../id-server.model';
import { IdServerService } from '../service/id-server.service';

@Injectable({ providedIn: 'root' })
export class IdServerRoutingResolveService implements Resolve<IIdServer | null> {
  constructor(protected service: IdServerService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IIdServer | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sampleType: HttpResponse<IIdServer>) => {
          if (sampleType.body) {
            return of(sampleType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
