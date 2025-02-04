import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth/auth.service';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzButtonComponent} from 'ng-zorro-antd/button';
import {NzFormControlComponent, NzFormDirective, NzFormItemComponent} from 'ng-zorro-antd/form';
import {NzInputDirective, NzInputGroupComponent} from 'ng-zorro-antd/input';
import {UserStorageService} from '../storage/user-storage.service';

@Component({
  selector: 'app-login',
  imports: [
    NzButtonComponent,
    NzFormControlComponent,
    NzFormDirective,
    NzFormItemComponent,
    NzInputDirective,
    NzInputGroupComponent,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authService: AuthService,
              private message: NzMessageService) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['null', [Validators.required, Validators.email]],
      password: ['null', Validators.required],
    })
  }

  submitForm(){
    this.authService.login(this.loginForm.value).subscribe(res=>{
      console.log(res);
      if(res.userId != null){
        const user ={
          id:res.userId,
          role:res.userRole,
        }
        UserStorageService.saveUser(user);
        UserStorageService.saveToken(res.jwt);

        if(UserStorageService.isAdminLoggedIn()){
          this.router.navigateByUrl('/admin/dashboard');
        } else if(UserStorageService.isCustomerLoggedIn()){
          this.router.navigateByUrl('/customers/room');
        }
      }
    },error=>{
      this.message
        .error(
          `Bad credentials`,
          {nzDuration:5000 }
        )
    })
  }
}
