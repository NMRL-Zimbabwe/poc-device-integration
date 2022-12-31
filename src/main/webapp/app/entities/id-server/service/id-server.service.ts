import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IIdServer, NewIdServer } from '../id-server.model';

export type PartialUpdateIdServer = Partial<IIdServer> & Pick<IIdServer, 'id'>;
export type EntityResponseType = HttpResponse<IIdServer>;
export type EntityArrayResponseType = HttpResponse<IIdServer[]>;

@Injectable({ providedIn: 'root' })
export class IdServerService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/id-servers');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(idServer: NewIdServer): Observable<EntityResponseType> {
    return this.http.post<IIdServer>(this.resourceUrl, idServer, { observe: 'response' });
  }

  update(idServer: IIdServer): Observable<EntityResponseType> {
    return this.http.put<IIdServer>(`${this.resourceUrl}`, idServer, {
      observe: 'response',
    });
  }

  partialUpdate(idServer: PartialUpdateIdServer): Observable<EntityResponseType> {
    return this.http.patch<IIdServer>(`${this.resourceUrl}/${this.getIdServerIdentifier(idServer)}`, idServer, {
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

  getIdServerIdentifier(idServer: Pick<IIdServer, 'id'>): number {
    return idServer.id;
  }

  compareIdServer(o1: Pick<IIdServer, 'id'> | null, o2: Pick<IIdServer, 'id'> | null): boolean {
    return o1 && o2 ? this.getIdServerIdentifier(o1) === this.getIdServerIdentifier(o2) : o1 === o2;
  }

  addIdServerToCollectionIfMissing<Type extends Pick<IIdServer, 'id'>>(
    idServerCollection: Type[],
    ...idServersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const idServers: Type[] = idServersToCheck.filter(isPresent);
    if (idServers.length > 0) {
      const idServerCollectionIdentifiers = idServerCollection.map(idServerItem => this.getIdServerIdentifier(idServerItem)!);
      const idServersToAdd = idServers.filter(idServerItem => {
        const idServerIdentifier = this.getIdServerIdentifier(idServerItem);
        if (idServerCollectionIdentifiers.includes(idServerIdentifier)) {
          return false;
        }
        idServerCollectionIdentifiers.push(idServerIdentifier);
        return true;
      });
      return [...idServersToAdd, ...idServerCollection];
    }
    return idServerCollection;
  }
}
