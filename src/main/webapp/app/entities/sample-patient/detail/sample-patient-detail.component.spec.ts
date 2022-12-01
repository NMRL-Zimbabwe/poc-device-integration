import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SamplePatientDetailComponent } from './sample-patient-detail.component';

describe('SamplePatient Management Detail Component', () => {
  let comp: SamplePatientDetailComponent;
  let fixture: ComponentFixture<SamplePatientDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SamplePatientDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ samplePatient: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(SamplePatientDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SamplePatientDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load samplePatient on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.samplePatient).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
