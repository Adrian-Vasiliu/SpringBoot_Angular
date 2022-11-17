import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Subscription} from "rxjs";
import {UserSession} from "../../model/UserSession";

@Component({
  selector: 'app-navigation-menu',
  templateUrl: './navigation-menu.component.html',
  styleUrls: ['./navigation-menu.component.css']
})
export class NavigationMenuComponent implements OnInit {

  //loggedUser?: UserDetails;
  loggedUser?: UserSession;
  userRoles: string[] = [];

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
    // console.log(this.loggedUser);
    // if (!this.loggedUser)
    //   console.log(true);
    // else console.log(false);

    //this.authService.loginsubjext.next(this.authService.getUser());
    this.authService.loginSubject.subscribe(x => {
      this.loggedUser = x;
      this.userRoles = this.loggedUser ? this.loggedUser.roles : [];
    })
  }

  logout(): void {
    this.authService.logOut();

  }

}
