import {Component, OnInit} from '@angular/core';
import {TestService} from "../../services/test.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  content?: string;

  constructor(private testService: TestService) {
  }

  ngOnInit(): void {
    this.testService.getAdminBoard().subscribe(data => this.content = data);
  }
}
