import { IMovie } from "./IMovie";

export class Movie implements IMovie{
    id: number = 0;
    title: String = "";
    duration: number = 0;
    synopsis: String = "";

    constructor(title : String, duration : number, synopsis : String){
        this.title = title;
        this.duration = duration;
        this.synopsis = synopsis;
    }
}