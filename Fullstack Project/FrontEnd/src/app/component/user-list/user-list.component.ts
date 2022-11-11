import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  //List of users
  users : User[] = new Array();

  constructor(private userService : UserService, private router : Router) { }

  ngOnInit(): void {
    //If users are cached, get them
    if(this.userService.userLoaded)
      this.users = this.userService.getUserStored();
    else{
      //Else, get them from server
      this.userService.getUsers()
        .subscribe(users => {
          this.users = users;
          //Cache the users
          this.userService.userLoaded = true;
          this.userService.setUserStored(users);
        });
    }
  }

  checkUser(user : User){

  }

  enterEditUser(index : number){
    this.router.navigate(['/modifyUser', true, index]);
  }

  removeUser(name : string, index : number){
    if (confirm('Are you sure you want to delete ' + name + '?')){
      this.userService.removeUser(index)
        .subscribe(removed => {
          if(removed){
            this.userService.getUsers()
              .subscribe(users => {
                this.users = users;
                this.userService.userLoaded = true;
                this.userService.setUserStored(users);
              })
          }
        })
    }
  }
}
