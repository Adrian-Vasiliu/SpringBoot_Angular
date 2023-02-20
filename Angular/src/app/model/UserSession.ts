export class UserSession {
  id: number;
  username: string;
  email: string;
  roles: string[];


   constructor() {
     this.id = 0;
     this.username = '';
     this.email = '';
     this.roles = [];
   }
}
