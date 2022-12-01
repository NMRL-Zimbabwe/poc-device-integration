import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISamplePatient } from '../sample-patient.model';
import { SamplePatientService } from '../service/sample-patient.service';

@Injectable({ providedIn: 'root' })
export class SamplePatientRoutingResolveService implements Resolve<ISamplePatient | null> {
  constructor(protected service: SamplePatientService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISamplePatient | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((samplePatient: HttpResponse<ISamplePatient>) => {
          if (samplePatient.body) {
            return of(samplePatient.body);
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
