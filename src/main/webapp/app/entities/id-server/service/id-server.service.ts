import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IIdServer, NewIdServer } from '../id-server.model';

export type PartialUpdateSampleType = Partial<IIdServer> & Pick<IIdServer, 'id'>;

export type EntityResponseType = HttpResponse<IIdServer>;
export type EntityArrayResponseType = HttpResponse<IIdServer[]>;

@Injectable({ providedIn: 'root' })
export class IdServerService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sample-types');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sampleType: NewIdServer): Observable<EntityResponseType> {
    return this.http.post<IIdServer>(this.resourceUrl, sampleType, { observe: 'response' });
  }

  update(sampleType: IIdServer): Observable<EntityResponseType> {
    return this.http.put<IIdServer>(`${this.resourceUrl}/${this.getSampleTypeIdentifier(sampleType)}`, sampleType, {
      observe: 'response',
    });
  }

  partialUpdate(sampleType: PartialUpdateSampleType): Observable<EntityResponseType> {
    return this.http.patch<IIdServer>(`${this.resourceUrl}/${this.getSampleTypeIdentifier(sampleType)}`, sampleType, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IIdServer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IIdServer[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSampleTypeIdentifier(sampleType: Pick<IIdServer, 'id'>): number {
    return sampleType.id;
  }

  compareSampleType(o1: Pick<IIdServer, 'id'> | null, o2: Pick<IIdServer, 'id'> | null): boolean {
    return o1 && o2 ? this.getSampleTypeIdentifier(o1) === this.getSampleTypeIdentifier(o2) : o1 === o2;
  }

  addSampleTypeToCollectionIfMissing<Type extends Pick<IIdServer, 'id'>>(
    sampleTypeCollection: Type[],
    ...sampleTypesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sampleTypes: Type[] = sampleTypesToCheck.filter(isPresent);
    if (sampleTypes.length > 0) {
      const sampleTypeCollectionIdentifiers = sampleTypeCollection.map(sampleTypeItem => this.getSampleTypeIdentifier(sampleTypeItem)!);
      const sampleTypesToAdd = sampleTypes.filter(sampleTypeItem => {
        const sampleTypeIdentifier = this.getSampleTypeIdentifier(sampleTypeItem);
        if (sampleTypeCollectionIdentifiers.includes(sampleTypeIdentifier)) {
          return false;
        }
        sampleTypeCollectionIdentifiers.push(sampleTypeIdentifier);
        return true;
      });
      return [...sampleTypesToAdd, ...sampleTypeCollection];
    }
    return sampleTypeCollection;
  }
}
