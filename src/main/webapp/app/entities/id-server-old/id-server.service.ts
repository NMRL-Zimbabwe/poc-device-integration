import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IIdServer } from 'app/shared/model/id-server.model';

type EntityResponseType = HttpResponse<IIdServer>;
type EntityArrayResponseType = HttpResponse<IIdServer[]>;

@Injectable({ providedIn: 'root' })
export class IdServerService {
  public resourceUrl = SERVER_API_URL + 'api/id-servers';

  constructor(protected http: HttpClient) {}

  create(idServer: IIdServer): Observable<EntityResponseType> {
    return this.http.post<IIdServer>(this.resourceUrl, idServer, { observe: 'response' });
  }

  update(idServer: IIdServer): Observable<EntityResponseType> {
    return this.http.put<IIdServer>(this.resourceUrl, idServer, { observe: 'response' });
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
}
