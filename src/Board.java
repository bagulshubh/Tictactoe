import java.util.ArrayList;

public class Board{
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
