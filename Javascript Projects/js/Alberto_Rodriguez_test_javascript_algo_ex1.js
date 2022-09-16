//Initialize variables
let list;
let sudoku;

function to_verify(){
    //Create first dimension of array
    sudoku = new Array(9);

    //Create second dimension of array
    for (var i = 0; i < sudoku.length; i++) {
        sudoku[i] = new Array(9);
    }
}

function read_table(){
    //Read table from other js and store it in a variable
    for(let i = 0; i < 9; i++){
        for(let j = 0; j < 9; j++){
            sudoku[i][j] = array_number[i][j+j];
        }
    }
}

function write_table(){
    //Get the reference to the table
    list = document.querySelector('#sudokuTable');
    //Clear the table  
    while(list.firstChild)
        list.removeChild(list.lastChild);

    //Write the numbers in the table
    let row, col;
    for(let i = 0; i < 9; i++){
        //Pick current row
        row = list.insertRow(i);
        for(let j = 0; j < 9; j++){
            //Pick current column
            col = row.insertCell(j);
            col.innerHTML = ` ${sudoku[i][j]} `;
        }
    }
}

//This function joins the 3 previous in a more efficient way
function all_in_one(){
    //Get the reference to the table
    list = document.querySelector('#sudokuTable');
    //Clear the table  
    while(list.firstChild)
        list.removeChild(list.lastChild);

    //Create a table and fill it
    let row, col;
    for(let i = 0; i < 9; i++){
        row = list.insertRow(i);
        for(let j = 0; j < 9; j++){
            col = row.insertCell(j);
            col.innerHTML = ` ${array_number[i][j+j]} `;
        }
    }
}