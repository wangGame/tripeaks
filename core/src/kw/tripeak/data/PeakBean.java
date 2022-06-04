package kw.tripeak.data;

import java.util.ArrayList;

public class PeakBean {
    private Board board;
    private ArrayList<Board> boards;

    public PeakBean(){
        board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public int getHandRank() {
        return board.getRank();
    }

    public void setHandRank(int handRank) {
        this.board.setRank(handRank);
    }

    public int getHandSuit() {
        return board.getSuit();
    }

    public void setHandSuit(int handSuit) {
        this.board.setSuit(handSuit);
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }

    public void setBoards(ArrayList<Board> boards) {
        this.boards = boards;
    }
}
