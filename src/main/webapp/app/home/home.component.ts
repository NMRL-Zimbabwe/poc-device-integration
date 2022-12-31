import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';

import { IdServerService } from 'app/entities/id-server/service/id-server.service';
import { IIdServer } from 'app/entities/id-server/id-server.model';
import { DashBoardStatsService } from 'app/services/dashboard-stats.service';
import { IDashBoardSync } from 'app/models/dashboard.model';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;

  // Stats
  statsData: IDashBoardSync[] = [];
  fetchingStats = false;
  totalReceived = 1;
  totalSynced = 1;
  totalError = 1;
  totalSubscribers = 1;

  idServers: IIdServer[] = [];
  apiSyncing = false;
  centralRepoSyncing = false;

  private readonly destroy$ = new Subject<void>();

  constructor(
    private accountService: AccountService,
    private idServerService: IdServerService,
    private dashBoardStatsService: DashBoardStatsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => (this.account = account));

    this.dashBoardStatsService.aggregateData$.subscribe(data => {
      this.fetchingStats = data.fetching;
      this.statsData = data.data;
      //
      this.totalReceived = data.data.reduce((acc, d): number => acc + d.totalReceived, 0);
      this.totalSynced = data.data.reduce((acc, d): number => acc + d.totalSynced, 0);
      this.totalSubscribers = data.data.reduce((acc, d): number => acc + d.subscriber, 0);
      this.totalError = data.data.reduce((acc, d): number => acc + d.error, 0);
    });

    this.idServerService.query({ size: 10, sort: ['prefix'] }).subscribe(res => {
      this.idServers = res.body ?? [];
      this.idServers.forEach(server => {
        if (server.prefix === 'ScheduledPOC') {
          this.apiSyncing = server.number! !== 0;
        }
        if (server.prefix === 'ScheduledCentralRepo') {
          this.centralRepoSyncing = server.number! !== 0;
        }
      });
    });
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  getIdServer(name: string): IIdServer {
    const index = this.idServers.findIndex(is => is.prefix === name);
    return this.idServers[index];
  }

  apiSync(number: number): void {
    const idServer = this.getIdServer('ScheduledPOC');
    if (number) {
      this.idServerService.update({ ...idServer, number }).subscribe(res => {
        if (res.body) {
          this.apiSyncing = res.body.number !== 0;
        }
      });
    } else {
      this.idServerService.update({ ...idServer, number }).subscribe(res => {
        if (res.body) {
          this.apiSyncing = res.body.number !== 0;
        }
      });
    }
  }

  centralRepoSync(number: number): void {
    const idServer = this.getIdServer('ScheduledCentralRepo');
    if (number) {
      this.idServerService.update({ ...idServer, number }).subscribe(res => {
        if (res.body) {
          this.centralRepoSyncing = res.body.number !== 0;
        }
      });
    } else {
      this.idServerService.update({ ...idServer, number }).subscribe(res => {
        if (res.body) {
          this.centralRepoSyncing = res.body.number !== 0;
        }
      });
    }
  }
}
