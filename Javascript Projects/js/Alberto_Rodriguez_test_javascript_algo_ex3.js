function checkSudoku(){
    //Initialize variables
    let tableRow, tableCol;
    //Get errors table
    const table = document.querySelector('#resultTable');
    //Clear the table  
    while(table.firstChild)
        table.removeChild(table.lastChild);

    //Check rows
    for(let i = 0; i < 9; i++){
        //If row has error
        if(!checkLine(sudoku[i])){
            //Add error row
            tableRow = table.insertRow(-1);

            //Add description
            tableCol = tableRow.insertCell(0);
            tableCol.innerHTML = `Line ${i} incorrect`;

            //Add numbers in row
            for(let j = 0; j < 9; j++){
                tableCol = tableRow.insertCell(j+1);
                tableCol.innerHTML = ` ${sudoku[i][j]} `;
            }
        }
    }

    //Check columns
    let col = [];
    for(let i = 0; i < 9; i++){
        //Store column in an array
        for(let j = 0; j < 9; j++){
            col[j] = sudoku[i][j];
        }

        //If column has error
        if(!checkLine(col)){
            //Add error row
            tableRow = table.insertRow(-1);

            //Add description
            tableCol = tableRow.insertCell(0);
            tableCol.innerHTML = `Row ${i} incorrect`;

            //Add numbers in row
            for(let j = 0; j < 9; j++){
                tableCol = tableRow.insertCell(j+1);
                tableCol.innerHTML = ` ${col[j]} `;
            }
        }
    }   

    //Check regions
    let reg = [];
    for(let i = 0; i < 9; i++){
        //Store region in an array
        for(let j = 0; j < 3; j++){
            for(let k = 0; k < 3; k++){
                //reg[k+3*j] = sudoku[j + (i % 3) * 3][k + Math.floor(i / 3) * 3];
                reg[k+3*j] = sudoku[k + Math.floor(i / 3) * 3][j + (i % 3) * 3];
            }
        }

        if(!checkLine(reg)){
            //Add error row
            tableRow = table.insertRow(-1);

            //Add description
            tableCol = tableRow.insertCell(0);
            tableCol.innerHTML = `Region ${i+1} incorrect`;

            //Add numbers in row
            for(let j = 0; j < 9; j++){
                tableCol = tableRow.insertCell(j+1);
                tableCol.innerHTML = ` ${reg[j]} `;
            }
        }
    }

    //If no errors, display all ok
    if(!table.firstChild){
        tableRow = table.insertRow(-1);
        tableCol = tableRow.insertCell(0);
        tableCol.innerHTML = 'No errors. Congratulations!'
    }
}