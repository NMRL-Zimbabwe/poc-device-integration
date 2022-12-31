import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIdServer } from '../id-server.model';

@Component({
  selector: 'jhi-id-server-detail',
  templateUrl: './id-server-detail.component.html',
})
export class IdServerDetailComponent implements OnInit {
  sampleType: IIdServer | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sampleType }) => {
      this.sampleType = sampleType;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
