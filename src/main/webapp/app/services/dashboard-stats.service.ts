import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { IDashBoardSync, IDataIDashBoardSync } from 'app/models/dashboard.model';
import { createRequestOption } from 'app/core/request/request-util';

@Injectable({ providedIn: 'root' })
export class DashBoardStatsService {
  monthNames = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  initial = {
    fetching: false,
    data: [],
    months: [],
    error: false,
  };

  aggregateData$ = new BehaviorSubject<IDataIDashBoardSync>(this.initial);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/dashboard-statistics');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
    this.initData();
  }

  initData(): void {
    this.aggregateData$.next({ ...this.initial, fetching: true });
    this.getAll().subscribe({
      next: (res: HttpResponse<IDashBoardSync[]>) => {
        this.onSuccess(res.body);
      },
      error: e => console.error(e),
    });
  }

  onSuccess(payload: IDashBoardSync[] | null): void {
    const months: number[] = [];

    payload?.forEach(datum => {
      months.push(+datum.month);
    });

    this.aggregateData$.next({
      fetching: false,
      data: payload?.map(datum => ({ ...datum, monthName: this.monthNames[+datum.month - 1] })) ?? [],
      months: [...new Set(months)].sort((a, b) => a - b).map(month => this.monthNames[month - 1]),
      error: false,
    });
  }

  find(id: number): Observable<HttpResponse<IDashBoardSync[]>> {
    return this.http.get<IDashBoardSync[]>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAll(): Observable<HttpResponse<IDashBoardSync[]>> {
    return this.http.get<IDashBoardSync[]>(this.resourceUrl, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<IDashBoardSync[]>> {
    const options = createRequestOption(req);
    return this.http.get<IDashBoardSync[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
