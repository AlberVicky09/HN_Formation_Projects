import { TypeUser } from "./TypeUser";

export class User {
    id : number = 0;
    name : string ;
    firstName : string;
    email : string;
    typeUser : TypeUser;

    constructor(name : string, firstName : string, email : string, typeUser : TypeUser){
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.typeUser = typeUser;
    }
}