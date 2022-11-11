import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { TypeUser } from 'src/app/model/TypeUser';
import { User } from 'src/app/model/User';
import { TypeService } from 'src/app/service/type.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  editMode : boolean = false;
  editId : number = -1;
  user : User = new User("", "", "", new TypeUser(""));
  types : TypeUser[] = new Array();

  constructor(private userService : UserService,
    private typeUserService : TypeService,
    private activatedRouter : ActivatedRoute,
    private router : Router) { }

  ngOnInit(): void {
    //Store all types
    if(this.typeUserService.typeLoaded === true){
      this.types = new Array();
      this.types = Array.from(this.typeUserService.getTypesStored());
      this.types.unshift(new TypeUser(""));
    }else{
      this.typeUserService.getTypes().subscribe(types => {
        this.types = new Array();
        this.types = Array.from(types);
        this.types.unshift(new TypeUser(""));
      });
    }
    console.log(this.types);

    //Check if its create or edit
    this.activatedRouter.paramMap.subscribe(
      //Get parameter to enter creation or edit mode
      (parameters : ParamMap) => {
        this.editMode = parameters.get("editMode") == "true";
        //If edit mode, get the user
        if(this.editMode){
          this.editId = parseInt(parameters.get("userId") || "");
          this.userService.getUserById(this.editId)
            .subscribe(user => {
              this.user = user;
              //Write user values in the form
              this.userCreationForm.setValue({
                name : user.name,
                firstName : user.firstName,
                email : user.email,
                typeUser : user.typeUser.typeName
              });
            })
        }else{
          this.userCreationForm.setValue({
            name : "",
            firstName : "",
            email : "",
            typeUser : ""
          })
        }
      }
    )
  }

  userCreationForm : FormGroup = new FormGroup({
    name : new FormControl('', Validators.required),
    firstName : new FormControl('', Validators.required),
    email : new FormControl('', Validators.compose([
      Validators.required, Validators.email
    ])),
    typeUser : new FormControl('', Validators.required)
  });

  userCreation(){
    if(this.userCreationForm.valid && !this.editMode){
      this.userService.addUser(new User(
        this.userCreationForm.get('name')?.value,
        this.userCreationForm.get('firstName')?.value,
        this.userCreationForm.get('email')?.value,
        new TypeUser(
          this.userCreationForm.get('typeUser')?.value
        )
      )).subscribe(result => {
        alert("User succesfully saved!");
        this.userService.userLoaded = false;
        this.router.navigate([''])
      });
    }
  }

  userModification(){
    if(this.userCreationForm.valid){
      this.userService.editUser(this.editId, new User(
        this.userCreationForm.get('name')?.value,
        this.userCreationForm.get('firstName')?.value,
        this.userCreationForm.get('email')?.value,
        new TypeUser(
          this.userCreationForm.get('typeUser')?.value
        )
      )).subscribe(() => {
        alert("User succesfully updated!");
        this.userService.userLoaded = false;
        this.router.navigate(['']);
      })
    }
  }
}
