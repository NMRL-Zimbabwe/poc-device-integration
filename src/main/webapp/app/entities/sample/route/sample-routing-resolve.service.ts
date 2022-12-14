import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISample } from '../sample.model';
import { SampleService } from '../service/sample.service';

@Injectable({ providedIn: 'root' })
export class SampleRoutingResolveService implements Resolve<ISample | null> {
  constructor(protected service: SampleService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISample | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sample: HttpResponse<ISample>) => {
          if (sample.body) {
            return of(sample.body);
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
