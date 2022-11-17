import { Component, OnInit } from '@angular/core';
import {TestService} from "../../services/test.service";

@Component({
  selector: 'app-moderator',
  templateUrl: './moderator.component.html',
  styleUrls: ['./moderator.component.css']
})
export class ModeratorComponent implements OnInit {
  content?: string;

  constructor(private testService: TestService) {
  }

  ngOnInit(): void {
    this.testService.getModeratorBoard().subscribe(data => this.content = data);
  }
}
