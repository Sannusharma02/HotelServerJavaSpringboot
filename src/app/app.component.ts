import {Component, OnInit} from '@angular/core';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import {NzSiderComponent} from 'ng-zorro-antd/layout';
import {NzMenuDirective, NzMenuItemComponent} from 'ng-zorro-antd/menu';
import {NzIconDirective} from 'ng-zorro-antd/icon';
import {ReactiveFormsModule} from '@angular/forms';
import {UserStorageService} from './auth/components/storage/user-storage.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NzLayoutModule, NzSiderComponent, NzMenuDirective, NzMenuItemComponent, RouterLink, NzIconDirective, RouterOutlet, ReactiveFormsModule, NgIf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'HotelWeb';

  isCustomerLoggedIn: boolean = UserStorageService.isCustomerLoggedIn();
  isAdminLoggedIn: boolean = UserStorageService.isCustomerLoggedIn();

  constructor(private router:Router){}

  ngOnInit() {
    this.router.events.subscribe((event:any)=>{
      if(event.constructor.name === 'NavigationEnd'){
        if(event.constructor.name === 'NavigationEnd'){
          this.isCustomerLoggedIn = UserStorageService.isCustomerLoggedIn();
          this.isAdminLoggedIn = UserStorageService.isAdminLoggedIn();
        }
      }
    })
  }

  logout(){
    UserStorageService.signOut();
    this.router.navigateByUrl('/');
  }
}

