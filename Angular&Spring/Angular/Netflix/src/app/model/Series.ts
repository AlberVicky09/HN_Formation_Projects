import { ISeries } from "./ISeries";

export class Series implements ISeries{
    id: number = 0;
    title: String = "";
    numSeasons: number = 0;
    numChapters: number[] = Array.from({length: 0});
    synopsis: String = "";
    releaseDate: number = 0;

    constructor(title : String, numSeasons : number, numChapters : number[], synopsis : String, releaseDate : number){
        this.title = title;
        this.numSeasons = numSeasons;
        this.numChapters = numChapters;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
    }
}