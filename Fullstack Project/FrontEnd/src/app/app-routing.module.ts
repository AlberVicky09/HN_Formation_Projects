import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DefaultComponent } from './component/default/default.component';
import { TypeFormComponent } from './component/type-form/type-form.component';
import { TypeListComponent } from './component/type-list/type-list.component';
import { UserFormComponent } from './component/user-form/user-form.component';
import { UserListComponent } from './component/user-list/user-list.component';

const routes: Routes = [
  {path:'', component:DefaultComponent},
  {path:'userList', component:UserListComponent},
  {path:'typeList', component:TypeListComponent},
  {path:'modifyUser/:editMode/:userId', component:UserFormComponent},
  {path:'modifyType/:editMode/:typeId', component:TypeFormComponent},
  {path:'**', redirectTo:''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
