import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';

import { IdServerService } from 'app/entities/id-server/service/id-server.service';
import { IIdServer } from 'app/entities/id-server/id-server.model';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;

  idServers: IIdServer[] = [];
  apiSyncing = false;
  centralRepoSyncing = false;

  private readonly destroy$ = new Subject<void>();

  constructor(private accountService: AccountService, private idServerService: IdServerService, private router: Router) {}

  ngOnInit(): void {
    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => (this.account = account));

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
