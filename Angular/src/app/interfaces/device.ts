import {Consumption} from "./consumption";

export interface Device{
  id : number;
  description: string;
  address: string;
  max_hourly_energy_consumption: number;
  user: number;
  consumptions: Consumption[];
}
