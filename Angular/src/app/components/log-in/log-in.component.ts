import {Component, Inject, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
// import {FormControl, FormGroup} from "@angular/forms";
import {FormBuilder, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {NavigationMenuComponent} from "../navigation-menu/navigation-menu.component";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  //Classic method for declaring FormGroup
  // loginForm = new FormGroup({
  //   userName: new FormControl(''),
  //   password: new FormControl(''),
  // });
  loginForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required] //Validators.minLength(8)
  })
  userRoles: string[] = [];
  isLoggedIn = false;
  errorMessage = '';

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
  }

  login() {
    let username = this.loginForm.value.username;
    let password = this.loginForm.value.password;
    if (username && password) {
      this.authService.login(username, password).subscribe(jwtResponse => {
        this.authService.saveData(jwtResponse);
        this.authService.loginSubject.next(jwtResponse.user);
        //window.location.reload();
        //debugger;
        this.router.navigate(['/home']);
      });
    }
  }

  ngOnInit(): void {
    let user = this.authService.getUser();
    if (Object.keys(user).length) {
      this.isLoggedIn = true;
      this.userRoles = user.roles;
    }
  }
}
