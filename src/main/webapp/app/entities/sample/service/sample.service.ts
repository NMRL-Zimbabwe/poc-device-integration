import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISample, NewSample } from '../sample.model';

export type PartialUpdateSample = Partial<ISample> & Pick<ISample, 'id'>;

type RestOf<T extends ISample | NewSample> = Omit<T, 'dateCollected' | 'dateRegistered' | 'dateTested' | 'datePublished'> & {
  dateCollected?: string | null;
  dateRegistered?: string | null;
  dateTested?: string | null;
  datePublished?: string | null;
};

export type RestSample = RestOf<ISample>;

export type NewRestSample = RestOf<NewSample>;

export type PartialUpdateRestSample = RestOf<PartialUpdateSample>;

export type EntityResponseType = HttpResponse<ISample>;
export type EntityArrayResponseType = HttpResponse<ISample[]>;

@Injectable({ providedIn: 'root' })
export class SampleService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/samples');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sample: NewSample): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sample);
    return this.http
      .post<RestSample>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sample: ISample): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sample);
    return this.http
      .put<RestSample>(`${this.resourceUrl}/${this.getSampleIdentifier(sample)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sample: PartialUpdateSample): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sample);
    return this.http
      .patch<RestSample>(`${this.resourceUrl}/${this.getSampleIdentifier(sample)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestSample>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSample[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSampleIdentifier(sample: Pick<ISample, 'id'>): number {
    return sample.id;
  }

  compareSample(o1: Pick<ISample, 'id'> | null, o2: Pick<ISample, 'id'> | null): boolean {
    return o1 && o2 ? this.getSampleIdentifier(o1) === this.getSampleIdentifier(o2) : o1 === o2;
  }

  addSampleToCollectionIfMissing<Type extends Pick<ISample, 'id'>>(
    sampleCollection: Type[],
    ...samplesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const samples: Type[] = samplesToCheck.filter(isPresent);
    if (samples.length > 0) {
      const sampleCollectionIdentifiers = sampleCollection.map(sampleItem => this.getSampleIdentifier(sampleItem)!);
      const samplesToAdd = samples.filter(sampleItem => {
        const sampleIdentifier = this.getSampleIdentifier(sampleItem);
        if (sampleCollectionIdentifiers.includes(sampleIdentifier)) {
          return false;
        }
        sampleCollectionIdentifiers.push(sampleIdentifier);
        return true;
      });
      return [...samplesToAdd, ...sampleCollection];
    }
    return sampleCollection;
  }

  protected convertDateFromClient<T extends ISample | NewSample | PartialUpdateSample>(sample: T): RestOf<T> {
    return {
      ...sample,
      dateCollected: sample.dateCollected?.format(DATE_FORMAT) ?? null,
      dateRegistered: sample.dateRegistered?.format(DATE_FORMAT) ?? null,
      dateTested: sample.dateTested?.format(DATE_FORMAT) ?? null,
      datePublished: sample.datePublished?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSample: RestSample): ISample {
    return {
      ...restSample,
      dateCollected: restSample.dateCollected ? dayjs(restSample.dateCollected) : undefined,
      dateRegistered: restSample.dateRegistered ? dayjs(restSample.dateRegistered) : undefined,
      dateTested: restSample.dateTested ? dayjs(restSample.dateTested) : undefined,
      datePublished: restSample.datePublished ? dayjs(restSample.datePublished) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSample>): HttpResponse<ISample> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSample[]>): HttpResponse<ISample[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
