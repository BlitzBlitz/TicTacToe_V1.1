package sample;
// this is an autoTesting class for the Game class
// makes random choices and plays the game

public class GameTest {
    public static void main(String[] args) {

        for(int i = 0; i < 10; i++){                        // play 10 games

            Game game = new Game(4);
            int allchoices = game.getDimension() * game.getDimension();

            while ((game.getWinner() == 0) && (game.getChoicePlaced() < allchoices)){
                int choice = (int) (Math.random() * allchoices) + 1;
                while (!game.playTurn(choice)){
                    choice = (int) (Math.random() * allchoices) + 1;
                }
                if(game.checkResult()){
                    System.out.println("________________________________");
                    game.printField();
                    System.out.println("the winner is: " + game.getWinner());
                    break;
                }
            }

        }
    }
}
