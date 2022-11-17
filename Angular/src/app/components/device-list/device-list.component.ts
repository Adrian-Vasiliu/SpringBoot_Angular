import {Component, OnInit} from '@angular/core';
import {Device} from "../../interfaces/device";
import {DeviceService} from "../../services/device.service";
import {AuthService} from "../../services/auth.service";
import {UserService} from "../../services/user.service";
import {ActivatedRoute} from "@angular/router";
import {UserDetails} from "../../interfaces/user-details";

@Component({
  selector: 'app-device-list',
  templateUrl: './device-list.component.html',
  styleUrls: ['./device-list.component.css']
})
export class DeviceListComponent implements OnInit {

  device: Device = {
    id: 0,
    description: '',
    address: '',
    max_hourly_energy_consumption: 0,
    user: 0,
    consumptions: []
  };
  createForm = false;
  isAdmin = false;
  devicesColumns = ['description', 'address', 'max_hourly_energy_consumption', 'edit', 'delete'];
  devices: Device[] = [];
  totalItems = 0;
  pageNumber = 1;
  pageSize = 3;
  pageSizes = [3, 6, 9];

  constructor(private route: ActivatedRoute, private deviceService: DeviceService, private authService: AuthService,
              private userService: UserService) {
  }

  showCreateForm(): void {
    this.createForm = true;
    let addButton = document.getElementById('add-button')
    if (addButton) {
      addButton.hidden = true;
    }
  }

  create(device: Device): void {
    this.deviceService.add(device).subscribe(d => this.devices.push(d));
    this.createForm = false;
    let addButton = document.getElementById('add-button')
    if (addButton) {
      addButton.hidden = false;
    }
  }


  removeReadonly(element: any) {
    if (element) {
      element.removeAttribute('readonly');
      element.classList.remove("inputReadOnly");
      element.type = 'text';
    }
  }

  showEditForm(deviceId: number): void {
    this.removeReadonly(document.getElementById('device' + deviceId + '-description'));
    this.removeReadonly(document.getElementById('device' + deviceId + '-description'));
    this.removeReadonly(document.getElementById('device' + deviceId + '-address'));
    this.removeReadonly(document.getElementById('device' + deviceId + '-consumption'));
    let editDeviceButton = document.getElementById('edit-device' + deviceId)
    if (editDeviceButton) {
      editDeviceButton.hidden = true;
    }
    let updateDeviceButton = document.getElementById('update-device' + deviceId)
    if (updateDeviceButton) {
      updateDeviceButton.hidden = false;
    }
  }

  updateDevice(device: Device) {
    this.deviceService.update(device.id, device).subscribe(() => {
      this.ngOnInit();
    });
  }

  deleteDevice(deviceId: number): void {
    this.deviceService.delete(deviceId).subscribe(() => {
      this.ngOnInit();
    });
  }

  handlePageChange(event: number): void {
    this.pageNumber = event;
    this.getDevices();
  }

  handlePageSizeChange(event: any) {
    this.pageSize = event.target.value;
    this.pageNumber = 1;
    this.getDevices();
  }

  getParams(pageNumber: number, pageSize: number): any {
    let params: any = {};
    if (pageNumber) {
      params[`pageNumber`] = pageNumber - 1;
    }
    if (pageSize) {
      params[`pageSize`] = pageSize;
    }
    return params;
  }

  getDevices(): void {
    const params = this.getParams(this.pageNumber, this.pageSize);
    this.deviceService.get(params).subscribe(page => {
        // const {items, totalItems} = data;
        // this.devices = items;
        // this.totalItems = totalItems;
        // console.log(data);
        this.devices = page.items;
        this.totalItems = page.totalItems;
      }
      // , error => {
      //   console.log(error);
      // }
    )
  }

  // showByUserId(userId: number): void {
  //   this.userService.getById(userId).subscribe(user => {
  //     this.devices = user.devices;
  //   });
  // }

  ngOnInit(): void {
    // var buttons = document.getElementsByTagName('button');
    // for (let i = 0; i < buttons.length; i++) {
    //   buttons[i].setAttribute("mat");
    //
    // }

    let loggedUser: UserDetails = this.authService.getUser();
    if (Object.keys(loggedUser).length) {
      if (loggedUser.roles.includes('ROLE_ADMIN')) {
        this.isAdmin = true;
        let userId;
        this.route.queryParams.subscribe(params => {
          userId = params['userId'];
        });
        if (userId) {
          // this.showByUserId(userId);
        } else {
          this.getDevices();
        }
      } else if (loggedUser.roles.includes('client')) {
        // this.showByUserId(loggedUser.id);
      }
    }
  }
}
