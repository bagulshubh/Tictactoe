import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Working..");

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Player1 Name: ");
        String name1 = sc.nextLine();
        System.out.println("Enter Player2 Name: ");
        String name2 = sc.nextLine();

        Board board = new Board();
        Player p1 = new Player(name1);
        Player p2 = new Player(name2);

        System.out.println();
        System.out.println("Select who wants to play first with X: ");
        System.out.println("Enter 1 for player1 and 2 for player2: ");
        int decision = sc.nextInt();
        if(decision == 1){
            p1.setTurn(true);
            p1.setSymbol("X");
            System.out.println(p1.getName() + " is playing first with X");
        }
        else {
            p2.setTurn(true);
            p2.setSymbol("X");
            System.out.println(p2.getName() + " is playing first with X");
        }
        System.out.println();

        while (!board.isGameEnded()){
            System.out.println("Enter co-ordinates");
            int x = sc.nextInt();
            int y = sc.nextInt();

            if(board.takeInput(p1,p2,x,y)){
                board.print();
                if(board.isGameWon(p1,p2)){
                    break;
                }
            }
            else{
                System.out.println("Enter valid co-ordinates");
            }

        }
        sc.close();
        System.out.println("Exiting..");
    }


}