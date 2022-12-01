import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISamplePatient, NewSamplePatient } from '../sample-patient.model';

export type PartialUpdateSamplePatient = Partial<ISamplePatient> & Pick<ISamplePatient, 'id'>;

type RestOf<T extends ISamplePatient | NewSamplePatient> = Omit<
  T,
  'dob' | 'dateCollected' | 'dateRegistered' | 'dateTested' | 'datePublished'
> & {
  dob?: string | null;
  dateCollected?: string | null;
  dateRegistered?: string | null;
  dateTested?: string | null;
  datePublished?: string | null;
};

export type RestSamplePatient = RestOf<ISamplePatient>;

export type NewRestSamplePatient = RestOf<NewSamplePatient>;

export type PartialUpdateRestSamplePatient = RestOf<PartialUpdateSamplePatient>;

export type EntityResponseType = HttpResponse<ISamplePatient>;
export type EntityArrayResponseType = HttpResponse<ISamplePatient[]>;

@Injectable({ providedIn: 'root' })
export class SamplePatientService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sample-patients');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(samplePatient: NewSamplePatient): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(samplePatient);
    return this.http
      .post<RestSamplePatient>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(samplePatient: ISamplePatient): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(samplePatient);
    return this.http
      .put<RestSamplePatient>(`${this.resourceUrl}/${this.getSamplePatientIdentifier(samplePatient)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(samplePatient: PartialUpdateSamplePatient): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(samplePatient);
    return this.http
      .patch<RestSamplePatient>(`${this.resourceUrl}/${this.getSamplePatientIdentifier(samplePatient)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestSamplePatient>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSamplePatient[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSamplePatientIdentifier(samplePatient: Pick<ISamplePatient, 'id'>): number {
    return samplePatient.id;
  }

  compareSamplePatient(o1: Pick<ISamplePatient, 'id'> | null, o2: Pick<ISamplePatient, 'id'> | null): boolean {
    return o1 && o2 ? this.getSamplePatientIdentifier(o1) === this.getSamplePatientIdentifier(o2) : o1 === o2;
  }

  addSamplePatientToCollectionIfMissing<Type extends Pick<ISamplePatient, 'id'>>(
    samplePatientCollection: Type[],
    ...samplePatientsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const samplePatients: Type[] = samplePatientsToCheck.filter(isPresent);
    if (samplePatients.length > 0) {
      const samplePatientCollectionIdentifiers = samplePatientCollection.map(
        samplePatientItem => this.getSamplePatientIdentifier(samplePatientItem)!
      );
      const samplePatientsToAdd = samplePatients.filter(samplePatientItem => {
        const samplePatientIdentifier = this.getSamplePatientIdentifier(samplePatientItem);
        if (samplePatientCollectionIdentifiers.includes(samplePatientIdentifier)) {
          return false;
        }
        samplePatientCollectionIdentifiers.push(samplePatientIdentifier);
        return true;
      });
      return [...samplePatientsToAdd, ...samplePatientCollection];
    }
    return samplePatientCollection;
  }

  protected convertDateFromClient<T extends ISamplePatient | NewSamplePatient | PartialUpdateSamplePatient>(samplePatient: T): RestOf<T> {
    return {
      ...samplePatient,
      dob: samplePatient.dob?.format(DATE_FORMAT) ?? null,
      dateCollected: samplePatient.dateCollected?.format(DATE_FORMAT) ?? null,
      dateRegistered: samplePatient.dateRegistered?.format(DATE_FORMAT) ?? null,
      dateTested: samplePatient.dateTested?.format(DATE_FORMAT) ?? null,
      datePublished: samplePatient.datePublished?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSamplePatient: RestSamplePatient): ISamplePatient {
    return {
      ...restSamplePatient,
      dob: restSamplePatient.dob ? dayjs(restSamplePatient.dob) : undefined,
      dateCollected: restSamplePatient.dateCollected ? dayjs(restSamplePatient.dateCollected) : undefined,
      dateRegistered: restSamplePatient.dateRegistered ? dayjs(restSamplePatient.dateRegistered) : undefined,
      dateTested: restSamplePatient.dateTested ? dayjs(restSamplePatient.dateTested) : undefined,
      datePublished: restSamplePatient.datePublished ? dayjs(restSamplePatient.datePublished) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSamplePatient>): HttpResponse<ISamplePatient> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSamplePatient[]>): HttpResponse<ISamplePatient[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
