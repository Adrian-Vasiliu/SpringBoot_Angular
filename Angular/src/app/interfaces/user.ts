import {Device} from "./device";

export interface User{
  id : number;
  name: string;
  username: string;
  role: string;
  devices: Device[];
}
