public class Player{
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