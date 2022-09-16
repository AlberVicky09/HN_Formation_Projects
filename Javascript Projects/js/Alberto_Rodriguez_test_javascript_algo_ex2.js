function checkLine(line){
    //Create array of checked numbers
    let checker = [];
    for(let i = 0; i < 9; i++)
        checker[i] = false;

    //Check numbers
    let num;
    for(let i = 0; i < 9; i++){
        num = Number(line[i]);

        //Check if input is number
        if(num === NaN){
            console.log(`${num} not a number`);
            return false;
        }

        //If number already appeared, return false
        if(checker[num]){
            return false;
        }else{
            //Else, mark as appeared
            checker[num] = true;
        }
    }
    return true;
}