import java.util.ArrayList;
import java.util.Scanner;

class Board{
    private ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>(3);
    private  final int n  = 3;
    public Board(){
        for(int i = 0;i<3;i++){
            ArrayList<String> temp = new ArrayList<>(3);
            for(int j = 0;j<3;j++){
                temp.add(j,"-");
            }
            this.board.add(temp);
        }
        System.out.println("Board Initialized: ");
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                System.out.print(this.board.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public Board(ArrayList<ArrayList<String>> initialBoard){
        this.board = initialBoard;
        System.out.println("Board Initialized with starting configuration");
    }

    public void setBoard(ArrayList<ArrayList<String>> board) {
        this.board = board;
    }

    public ArrayList<ArrayList<String>> getBoard() {
        return board;
    }

    public boolean takeInput(Player p1 , Player p2, int x ,  int y){
        if(x>=3 || y>=3) return false;

        String exe = this.board.get(x).get(y);
        if(!exe.equalsIgnoreCase("-")) return false;

        if(p1.getTurn()){
            ArrayList<String> temp = new ArrayList<>(3);
            for(int i = 0;i<n;i++){
                if(i == y) temp.add(p1.getSymbol());
                else temp.add(this.board.get(x).get(i));
            }
            this.board.set(x,temp);
            p1.setTurn(false);
            p2.setTurn(true);
        }
        else{
            ArrayList<String> temp = new ArrayList<>(3);
            for(int i = 0;i<n;i++){
                if(i == y) temp.add(p2.getSymbol());
                else temp.add(this.board.get(x).get(i));
            }
            this.board.set(x,temp);
            p1.setTurn(true);
            p2.setTurn(false);
        }


        return true;
    }

    public void print(){
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                System.out.print(this.board.get(i).get(j)+"  ");
            }
            System.out.println();
        }
    }

    public boolean isGameWon( Player p1 , Player p2){
        String symbol = "";
        String playerName = "";
        if(p1.getTurn()) {
            symbol = p2.getSymbol();
            playerName = p2.getName();
        }
        else{
            symbol = p1.getSymbol();
            playerName = p1.getName();
        }
        //checking diagonals
        if(this.board.get(0).get(0).equalsIgnoreCase(symbol) && this.board.get(1).get(1).equalsIgnoreCase(symbol) && this.board.get(2).get(2).equalsIgnoreCase(symbol))
        {
            System.out.println(playerName+" Won");
            return true;
        }
        else if(this.board.get(0).get(2).equalsIgnoreCase(symbol) && this.board.get(1).get(1).equalsIgnoreCase(symbol) && this.board.get(2).get(0).equalsIgnoreCase(symbol))
        {
            System.out.println(playerName+" Won");
            return true;
        }

        //check rows and col
        for(int i = 0;i<n;i++){
            boolean flag = true;
            for(int j = 0;j<n;j++){
                if(!this.board.get(i).get(j).equalsIgnoreCase(symbol)) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.println(playerName+" Won");
                return true;
            }
            flag = true;
            for(int j = 0;j<n;j++){
                if(!this.board.get(j).get(i).equalsIgnoreCase(symbol)) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                System.out.println(playerName+ "Won");
                return true;
            }
        }

        return false;
    }

    public boolean isGameEnded(){

        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                if(this.board.get(i).get(j).equalsIgnoreCase("-")) return false;
            }
        }
        System.out.println("Game ended in draw");
        return true;

    }

}

class Player{
    private final String name;
    private boolean turn;
    private String symbol;

    public Player(String name ){
        this.name = name;
        this.turn = false;
        this.symbol = "O";
        System.out.println("Player Initialized with name: "+this.name);
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public String getName() {
        return this.name;
    }
    public boolean getTurn(){
        return this.turn;
    }
    public String getSymbol(){
        return this.symbol;
    }
}

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

        System.out.println("Exiting..");
    }


}