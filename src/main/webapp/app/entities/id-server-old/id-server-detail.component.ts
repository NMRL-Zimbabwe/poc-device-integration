import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIdServer } from 'app/shared/model/id-server.model';

@Component({
  selector: 'jhi-id-server-detail',
  templateUrl: './id-server-detail.component.html',
})
export class IdServerDetailComponent implements OnInit {
  idServer: IIdServer | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ idServer }) => (this.idServer = idServer));
  }

  previousState(): void {
    window.history.back();
  }
}
