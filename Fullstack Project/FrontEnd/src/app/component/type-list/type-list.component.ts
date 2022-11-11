import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TypeUser } from 'src/app/model/TypeUser';
import { TypeService } from 'src/app/service/type.service';

@Component({
  selector: 'app-type-list',
  templateUrl: './type-list.component.html',
  styleUrls: ['./type-list.component.css']
})
export class TypeListComponent implements OnInit {
  //List of typeUsers
  typeUsers : TypeUser[] = new Array();

  constructor(private typeService : TypeService, private router : Router) { }

  ngOnInit(): void {
    //If users are cached, get them
    if(this.typeService.typeLoaded){
      this.typeUsers = this.typeService.getTypesStored();
    }else{
      //Else, get them from server
      this.typeService.getTypes()
        .subscribe(types => {
          this.typeUsers = types;
          //Cache the users
          this.typeService.typeLoaded = true;
          this.typeService.setTypesStored(types);
        });
    }
  }

  checkType(type : TypeUser){

  }

  enterEditType(index : number){
    this.router.navigate(['/modifyType', true, index]);
  }

  removeType(typeName : string, index : number){
    if (confirm('Are you sure you want to delete ' + typeName + '?')){
      this.typeService.removeType(index)
        .subscribe(removed => {
          if(removed){
            this.typeService.getTypes()
              .subscribe(types => {
                this.typeUsers = types;
                this.typeService.typeLoaded = true;
                this.typeService.setTypesStored(types);
              })
          }else
            alert('Can´t delete type referenced by an User!');
      })
    }
  }
}
