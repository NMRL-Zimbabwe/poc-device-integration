import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISamplePatient } from '../sample-patient.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sample-patient.test-samples';

import { SamplePatientService, RestSamplePatient } from './sample-patient.service';

const requireRestSample: RestSamplePatient = {
  ...sampleWithRequiredData,
  dob: sampleWithRequiredData.dob?.format(DATE_FORMAT),
  dateCollected: sampleWithRequiredData.dateCollected?.format(DATE_FORMAT),
  dateRegistered: sampleWithRequiredData.dateRegistered?.format(DATE_FORMAT),
  dateTested: sampleWithRequiredData.dateTested?.format(DATE_FORMAT),
  datePublished: sampleWithRequiredData.datePublished?.format(DATE_FORMAT),
};

describe('SamplePatient Service', () => {
  let service: SamplePatientService;
  let httpMock: HttpTestingController;
  let expectedResult: ISamplePatient | ISamplePatient[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SamplePatientService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a SamplePatient', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const samplePatient = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(samplePatient).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SamplePatient', () => {
      const samplePatient = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(samplePatient).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SamplePatient', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SamplePatient', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SamplePatient', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSamplePatientToCollectionIfMissing', () => {
      it('should add a SamplePatient to an empty array', () => {
        const samplePatient: ISamplePatient = sampleWithRequiredData;
        expectedResult = service.addSamplePatientToCollectionIfMissing([], samplePatient);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(samplePatient);
      });

      it('should not add a SamplePatient to an array that contains it', () => {
        const samplePatient: ISamplePatient = sampleWithRequiredData;
        const samplePatientCollection: ISamplePatient[] = [
          {
            ...samplePatient,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSamplePatientToCollectionIfMissing(samplePatientCollection, samplePatient);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SamplePatient to an array that doesn't contain it", () => {
        const samplePatient: ISamplePatient = sampleWithRequiredData;
        const samplePatientCollection: ISamplePatient[] = [sampleWithPartialData];
        expectedResult = service.addSamplePatientToCollectionIfMissing(samplePatientCollection, samplePatient);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(samplePatient);
      });

      it('should add only unique SamplePatient to an array', () => {
        const samplePatientArray: ISamplePatient[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const samplePatientCollection: ISamplePatient[] = [sampleWithRequiredData];
        expectedResult = service.addSamplePatientToCollectionIfMissing(samplePatientCollection, ...samplePatientArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const samplePatient: ISamplePatient = sampleWithRequiredData;
        const samplePatient2: ISamplePatient = sampleWithPartialData;
        expectedResult = service.addSamplePatientToCollectionIfMissing([], samplePatient, samplePatient2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(samplePatient);
        expect(expectedResult).toContain(samplePatient2);
      });

      it('should accept null and undefined values', () => {
        const samplePatient: ISamplePatient = sampleWithRequiredData;
        expectedResult = service.addSamplePatientToCollectionIfMissing([], null, samplePatient, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(samplePatient);
      });

      it('should return initial array if no SamplePatient is added', () => {
        const samplePatientCollection: ISamplePatient[] = [sampleWithRequiredData];
        expectedResult = service.addSamplePatientToCollectionIfMissing(samplePatientCollection, undefined, null);
        expect(expectedResult).toEqual(samplePatientCollection);
      });
    });

    describe('compareSamplePatient', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSamplePatient(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareSamplePatient(entity1, entity2);
        const compareResult2 = service.compareSamplePatient(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareSamplePatient(entity1, entity2);
        const compareResult2 = service.compareSamplePatient(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareSamplePatient(entity1, entity2);
        const compareResult2 = service.compareSamplePatient(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
