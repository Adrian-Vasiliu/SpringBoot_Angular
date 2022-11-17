import {UserDetails} from "./user-details";

export interface JwtResponse {
  type: number;
  token: string;
  user: UserDetails;
}
