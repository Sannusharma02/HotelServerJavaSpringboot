import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterLink, RouterOutlet} from '@angular/router';
import {NzContentComponent, NzFooterComponent, NzLayoutComponent, NzSiderComponent} from 'ng-zorro-antd/layout';
import {NzMenuDirective, NzMenuItemComponent} from 'ng-zorro-antd/menu';
import {NzIconDirective} from 'ng-zorro-antd/icon';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, RouterLink, NzLayoutComponent, NzSiderComponent, NzMenuDirective, NzMenuItemComponent, NzIconDirective, NzContentComponent, RouterOutlet, NzFooterComponent],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent {
  title = 'Admin Dashboard';

}
