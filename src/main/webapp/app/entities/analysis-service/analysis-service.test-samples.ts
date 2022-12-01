import { IAnalysisService, NewAnalysisService } from './analysis-service.model';

export const sampleWithRequiredData: IAnalysisService = {
  id: 11428,
};

export const sampleWithPartialData: IAnalysisService = {
  id: 45843,
  unit: 'bypassing',
};

export const sampleWithFullData: IAnalysisService = {
  id: 14384,
  name: 'Mexican Rustic',
  unit: 'Crescent',
};

export const sampleWithNewData: NewAnalysisService = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
