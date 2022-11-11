import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { TypeUser } from 'src/app/model/TypeUser';
import { TypeService } from 'src/app/service/type.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-type-form',
  templateUrl: './type-form.component.html',
  styleUrls: ['./type-form.component.css']
})
export class TypeFormComponent implements OnInit {

  editMode : boolean = false;
  editId : number = -1;
  type : TypeUser = new TypeUser("");

  constructor(private typeService : TypeService,
    private userService : UserService,
    private activatedRouter : ActivatedRoute,
    private router : Router) { }

  ngOnInit(): void {
    this.activatedRouter.paramMap.subscribe(
      //Get parameter to enter creation or edit mode
      (parameters : ParamMap) => {
        this.editMode = parameters.get("editMode") == "true";
        //If edit mode, get the user
        if(this.editMode){
          this.editId = parseInt(parameters.get("typeId") || "");
          this.typeService.getTypeById(this.editId)
            .subscribe(type => {
              this.type = type;
              //Write user values in the form
              this.typeCreationForm.setValue({
                type : type.typeName
              });
            })
        }else{
          this.typeCreationForm.setValue({
            type : ""
          })
        }
      }
    )
  }

  typeCreationForm : FormGroup = new FormGroup({
    type : new FormControl('', Validators.required)
  });

  typeCreation(){
    if(this.typeCreationForm.valid && !this.editMode){
      this.typeService.addType(new TypeUser(
        this.typeCreationForm.get('type')?.value
      )).subscribe(result => {
        if(result){
          alert("Type succesfully saved!");
          this.typeService.typeLoaded = false;
          this.router.navigate(['']);
        }else
          alert("Type is already in database");
      });
    }
  }

  typeModification(){
    if(this.typeCreationForm.valid){
      this.typeService.editType(this.editId, new TypeUser(
        this.typeCreationForm.get('type')?.value
      )).subscribe(worked => {
        alert("Type succesfully updated!");
        this.typeService.typeLoaded = false;
        this.userService.userLoaded = false;
        this.router.navigate(['']);
      })
    }
  }
}
