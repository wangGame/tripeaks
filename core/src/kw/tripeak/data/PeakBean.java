package kw.tripeak.data;

import java.util.ArrayList;

public class PeakBean {
    private int num;
    private ArrayList<Board> boards;

    public int getNum() {
        System.out.println(num);
        if (num == 1){
            num = 13;
        }
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }

    public void setBoards(ArrayList<Board> boards) {
        this.boards = boards;
    }
}
