import { Component, OnInit } from '@angular/core';
import { IDashBoardSync } from 'app/models/dashboard.model';
import { DashBoardStatsService } from 'app/services/dashboard-stats.service';
import * as echarts from 'echarts';

@Component({
  selector: 'jhi-sync-chart',
  templateUrl: './sync-chart.component.html',
  styleUrls: ['./sync-chart.component.scss'],
})
export class SyncChartComponent implements OnInit {
  months: string[] = [];
  data: IDashBoardSync[] = [];
  loading = false;

  constructor(private dashBoardStatsService: DashBoardStatsService) {}

  ngOnInit(): void {
    this.dashBoardStatsService.aggregateData$.subscribe(data => {
      this.months = data.months;
      this.loading = data.fetching;
      this.data = data.data;
      this.plotChart();
    });
  }

  plotChart(): void {
    const received: number[] = [];
    const synced: number[] = [];

    this.months.forEach(month => {
      const monthData = this.data.filter(d => d.monthName === month);
      const totRec = monthData.reduce((acc, d): number => acc + d.totalReceived, 0);
      received.push(totRec);
      const totSync = monthData.reduce((acc, d): number => acc + d.totalSynced, 0);
      synced.push(totSync);
    });

    this.charter(this.months, received, synced, 'receivedVssynced');
  }

  charter(categories: string[], totalRegistered: number[], totalTested: number[], chartId: string): void {
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
          barMaxWidth: 50,
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
