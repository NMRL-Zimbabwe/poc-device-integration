import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SamplePatientFormService } from './sample-patient-form.service';
import { SamplePatientService } from '../service/sample-patient.service';
import { ISamplePatient } from '../sample-patient.model';

import { SamplePatientUpdateComponent } from './sample-patient-update.component';

describe('SamplePatient Management Update Component', () => {
  let comp: SamplePatientUpdateComponent;
  let fixture: ComponentFixture<SamplePatientUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let samplePatientFormService: SamplePatientFormService;
  let samplePatientService: SamplePatientService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SamplePatientUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(SamplePatientUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SamplePatientUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    samplePatientFormService = TestBed.inject(SamplePatientFormService);
    samplePatientService = TestBed.inject(SamplePatientService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const samplePatient: ISamplePatient = { id: 456 };

      activatedRoute.data = of({ samplePatient });
      comp.ngOnInit();

      expect(comp.samplePatient).toEqual(samplePatient);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISamplePatient>>();
      const samplePatient = { id: 123 };
      jest.spyOn(samplePatientFormService, 'getSamplePatient').mockReturnValue(samplePatient);
      jest.spyOn(samplePatientService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ samplePatient });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: samplePatient }));
      saveSubject.complete();

      // THEN
      expect(samplePatientFormService.getSamplePatient).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(samplePatientService.update).toHaveBeenCalledWith(expect.objectContaining(samplePatient));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISamplePatient>>();
      const samplePatient = { id: 123 };
      jest.spyOn(samplePatientFormService, 'getSamplePatient').mockReturnValue({ id: null });
      jest.spyOn(samplePatientService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ samplePatient: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: samplePatient }));
      saveSubject.complete();

      // THEN
      expect(samplePatientFormService.getSamplePatient).toHaveBeenCalled();
      expect(samplePatientService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISamplePatient>>();
      const samplePatient = { id: 123 };
      jest.spyOn(samplePatientService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ samplePatient });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(samplePatientService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
