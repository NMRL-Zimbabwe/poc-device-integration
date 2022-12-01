import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISamplePatient } from '../sample-patient.model';

@Component({
  selector: 'jhi-sample-patient-detail',
  templateUrl: './sample-patient-detail.component.html',
})
export class SamplePatientDetailComponent implements OnInit {
  samplePatient: ISamplePatient | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ samplePatient }) => {
      this.samplePatient = samplePatient;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
