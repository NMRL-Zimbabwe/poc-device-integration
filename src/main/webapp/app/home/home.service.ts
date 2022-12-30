import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { IAggregateYearMonthSamples, IDataAggregateYearMonthSamples } from 'app/shared/models/AggregateYearlyMontlySamples';

@Injectable({ providedIn: 'root' })
export class HomeSyncService {
  monthNames = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  initial = {
    fetching: false,
    data: [],
    labs: [],
    years: [],
    months: [],
    tests: [],
    error: false,
  };

  aggregateData$ = new BehaviorSubject<IDataAggregateYearMonthSamples>(this.initial);

  private resourceUrl = this.applicationConfigService.getEndpointFor('api/aggregates/samples-yearly-monthly');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {
    this.initData();
  }

  getAggregated(): Observable<HttpResponse<IAggregateYearMonthSamples[]>> {
    return this.http.get<IAggregateYearMonthSamples[]>(this.resourceUrl, { observe: 'response' });
  }

  initData(): void {
    this.aggregateData$.next({ ...this.initial, fetching: true });
    this.getAggregated().subscribe({
      next: (res: HttpResponse<IAggregateYearMonthSamples[]>) => {
        this.onSuccess(res.body);
      },
      error: e => console.error(e),
    });
  }

  onSuccess(payload: IAggregateYearMonthSamples[] | null): void {
    const data = payload?.filter(d => !['', null, undefined].includes(d.year))?.sort((a, b) => (a.year > b.year ? -1 : 1)) ?? [];

    const labs: string[] = [];
    const years: string[] = [];
    const tests: string[] = [];
    const months: number[] = [];

    data.forEach(datum => {
      labs.push(datum.reportingSite);
      years.push(datum.year);
      tests.push(datum.testName);
      months.push(+datum.month);
    });

    this.aggregateData$.next({
      fetching: false,
      data: data.map(datum => ({ ...datum, monthName: this.monthNames[+datum.month - 1] })),
      labs: [...new Set(labs)].sort(),
      years: [...new Set(years)],
      tests: [...new Set(tests)].sort(),
      months: [...new Set(months)].sort((a, b) => a - b).map(month => this.monthNames[month - 1]),
      error: false,
    });
  }
}
