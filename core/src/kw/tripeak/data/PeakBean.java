package kw.tripeak.data;

import java.util.ArrayList;

public class PeakBean {
    private int handRank;
    private int handSuit;
    private ArrayList<Board> boards;

    public int getHandRank() {
        return handRank;
    }

    public void setHandRank(int handRank) {
        this.handRank = handRank;
    }

    public int getHandSuit() {
        return handSuit;
    }

    public void setHandSuit(int handSuit) {
        this.handSuit = handSuit;
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }

    public void setBoards(ArrayList<Board> boards) {
        this.boards = boards;
    }
}
