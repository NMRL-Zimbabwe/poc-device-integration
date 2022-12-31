import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';

import * as echarts from 'echarts';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  apiSyncing = false;
  centralRepoSyncing = false;

  private readonly destroy$ = new Subject<void>();

  constructor(private accountService: AccountService, private router: Router) {}

  ngOnInit(): void {
    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => (this.account = account));

    this.plotTotalReceivedVsTestedChart(
      ['jan', 'feb', 'march', 'april', 'may'],
      [20, 120, 70, 130, 56],
      [20, 120, 70, 130, 0],
      'receivedVssynced'
    );
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  apiSync(start: boolean): void {
    this.apiSyncing = start;
    if (start) {
      //
    } else {
      //
    }
  }

  centralRepoSync(start: boolean): void {
    this.centralRepoSyncing = start;
    if (start) {
      //
    } else {
      //
    }
  }

  // this.labData = this.sampleTotalsData.filter(d => d.reportingSite === this.lab);

  // // data setup
  // let _tests: string[] = [];
  // let _years: string[] = [];
  // let _months: string[] = [];

  // this.labData.forEach(datum => {
  //   _tests.push(datum.testName);
  //   _years.push(datum.year);
  //   _months.push(datum.monthName);
  // });

  // this.tests = [...new Set(_tests)].sort();
  // this.test = this.tests[0];
  // this.years = [...new Set(_years)];
  // this.year = this.years[0];
  // this.months = [...new Set(_months)];

  // let totalTested: number[] = [];
  // let totalRegistered: number[] = [];

  // this.years.forEach(year => {
  //   let datums = this.labData.filter(t => +t.year === +year);
  //   const totalR = datums.reduce((acc, val): number => (acc = acc + +val.totalRegistered), 0);
  //   totalRegistered.push(totalR);
  //   const totalT = datums.reduce((acc, val): number => (acc = acc + +val.totalTested), 0);
  //   totalTested.push(totalT);
  // });
  // this.plotTotalReceivedVsTestedChart(this.years, totalRegistered, totalTested, 'sample-totals');

  plotTotalReceivedVsTestedChart(categories: string[], totalRegistered: number[], totalTested: number[], chartId: string): void {
    const chartDom = document.getElementById(chartId);
    const myChart = echarts.init(chartDom!);
    const option = {
      title: {
        text: 'Received Vs Synced',
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          label: {
            backgroundColor: '#283b56',
          },
        },
      },
      legend: {},
      toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'top',
        feature: {
          mark: { show: true },
          magicType: { show: true, type: ['line', 'stack'] },
          restore: { show: true },
          saveAsImage: { show: true },
        },
      },
      dataZoom: {
        show: false,
        start: 0,
        end: 100,
      },
      xAxis: [
        {
          type: 'category',
          boundaryGap: true,
          data: categories,
        },
      ],
      yAxis: [
        {
          type: 'value',
          scale: true,
          name: 'Total',
          max: Math.max(...[...totalRegistered, ...totalTested]),
          min: 0,
          boundaryGap: [0.2, 0.2],
        },
      ],
      series: [
        {
          name: 'Total Received',
          type: 'bar',
          data: totalRegistered,
        },
        {
          name: 'Total Synced',
          type: 'line',
          data: totalTested,
        },
      ],
    };

    myChart.setOption(option);
  }
}
