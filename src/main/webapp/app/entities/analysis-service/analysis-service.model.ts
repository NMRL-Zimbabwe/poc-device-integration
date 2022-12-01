export interface IAnalysisService {
  id: number;
  name?: string | null;
  unit?: string | null;
}

export type NewAnalysisService = Omit<IAnalysisService, 'id'> & { id: null };
