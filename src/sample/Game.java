package sample;
// This is the Game class
// It can handle TicTacToe games whatever dimensions they are: 3,4,5...
public class Game {
    private int turn;
    private int choicePlaced = 0;
    private int winner;
    private int[][] field;
    private int dimension;

    public Game(int dimension) {
        turn = (int)((Math.random() * 2) + 1);          //generate a random turn to start the game

        if(dimension < 3){                              // valedating dimensions
            dimension = 3;
        }
        this.dimension = dimension;
        field = new int[dimension][dimension];         // this is used to keep the game results while played
    }


    public boolean playTurn(int choice){                        //return true if choice proceeded
        if(choice < 1 || choice > dimension * dimension){      // if the choice is out of the available space
            return false;
        }
        if(!placeChoice(choice)){
            return false;
        }
        return true;
    }

    private boolean placeChoice(int choice){                // user chooses  1 - dimension^2
        int row = (choice - 1) / dimension;                 //ex: if 3D 1-9, if 4D 1-16 and so on
        int column = (choice - 1)  % dimension;
        if(field[row][column] != 0){
            return false;
        }
        field[row][column] = turn;
        choicePlaced++;
        return true;
    }

    public boolean checkResult(){
        if(checkDiagonal()){                //checking both diagonals
            winner = turn;
            return true;
        }

        for (int i = 0; i< dimension; i++){
            if(checkRow(i)){                        // checking every row
                winner = turn;
                return true;
            }
            if(checkColumn(i)){                     // checking every column
                winner = turn;
                return true;
            }
        }

        if(choicePlaced == dimension*dimension){    //game is over and we have a draw
            return true;
        }
        turn = (turn % 2) + 1;                      //change turn
        return false;
    }

    private boolean checkRow(int row){
        for (int i = 0; i < dimension - 1; i++){                 // we have to stop before the last element
            if((field[row][i] != field[row][i+1])               // so we don`t get out of bound with [i+1]
                    || (field[row][i] == 0) || ( field[row][i+1] == 0)){    //we want the values to be different
                return false;                                               // from 0 because 0 is the default value
            }                                                               // not the players` choice
        }
        return true;
    }

    private boolean checkColumn(int column){
        for (int i = 0; i < dimension - 1; i++){
            if((field[i][column] != field[i+1][column])
                    || (field[i][column] == 0) || (field[i+1][column] == 0)){     // we have to stop before the last element
                return false;                                                     // in order not to go out of bound with [i+1]
            }
        }
        return true;
    }

    private boolean checkDiagonal(){
        boolean result = true;
        for(int i = 0; i < dimension - 1; i++){                             //we want to stop before the last element of the diagonal
            if((field[i][i] != field[i+1][i+1])
                    || (field[i][i] == 0) || (field[i+1][i+1] == 0)){             // the first diagonal
                result = false;
            }
        }

        if(!result){                                                                //check for second diagonal
            result = true;
            int diagonal2Row = dimension - 1;
            for(int i = 0; i < dimension - 1; i++){
                if((field[diagonal2Row][i] != field[diagonal2Row - 1][i+1])
                        || (field[diagonal2Row][i] == 0) || (field[diagonal2Row - 1][i+1] == 0)){
                    result = false;
                }
                diagonal2Row--;
            }
        }

        return result;
    }

    public void printField(){                                                               //for testing
        for(int i = 0; i<field.length; i++){
            for(int j = 0; j<field.length; j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getChoicePlaced() {
        return choicePlaced;
    }

    public int getTurn() {
        return turn;
    }

    public int getWinner() {
        return winner;
    }

    public int getDimension() {
        return dimension;
    }
}
