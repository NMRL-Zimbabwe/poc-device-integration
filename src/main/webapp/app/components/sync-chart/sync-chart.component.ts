import { Component, OnInit } from '@angular/core';
import * as echarts from 'echarts';

@Component({
  selector: 'jhi-sync-chart',
  templateUrl: './sync-chart.component.html',
  styleUrls: ['./sync-chart.component.scss'],
})
export class SyncChartComponent implements OnInit {
  constructor() {
    const a = 1 + 1;
  }

  ngOnInit(): void {
    this.plotTotalReceivedVsTestedChart(
      ['jan', 'feb', 'march', 'april', 'may'],
      [20, 120, 70, 130, 56],
      [20, 120, 70, 130, 0],
      'receivedVssynced'
    );
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
