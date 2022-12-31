export interface IDashBoardSync {
  month: number;
  monthName: string;
  totalReceived: number;
  totalSynced: number;
  subscriber: number;
  error: number;
}

export interface IDataIDashBoardSync {
  fetching: boolean;
  data: IDashBoardSync[];
  months: string[];
  error: boolean;
}
