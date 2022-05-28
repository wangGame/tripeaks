package ogz.tripeaks.data;

import com.kw.gdx.log.NLog;

import java.util.ArrayList;
import java.util.Collections;

public class GameData {
    public int[][] parkData(){
        int arr[][] = {
                {0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0},
                {0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0},
                {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
        };
        return arr;
    }

    public ArrayList<Integer> random(){
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            data.add(i);
        }
        NLog.e("shuffle before");
        print(data);
        Collections.shuffle(data);
        NLog.e("shuffle after");
        print(data);
        return data;
    }

    public void print(ArrayList<Integer> data){
        System.out.println("------------------------");
        for (Integer datum : data) {
            System.out.print(datum+"  ");
        }
    }
    public ArrayList<Bean> getData() {
        int index = 1;
        int dd = 1;
        int table = 3;
        ArrayList<Bean> arrayList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int i1 = 0; i1 < table; i1++) {
                System.out.print(" ");
            }
            for (int i1 = 0; i1 < index; i1++) {
                Bean bean = new Bean();
                bean.index = dd;
                bean.line = table+i1*2;
                bean.row = i;
                arrayList.add(bean);
            }
            table --;
            index++;
        }
//        for (Bean bean : arrayList) {
//            System.out.println(bean);
//        }
        return arrayList;
    }

    public static class Bean{
        public Bean(){}
        public int index;
        public int cardIndex;
        public int line;
        public int row;

        @Override
        public String toString() {
            return "Bean{" +
                    "index=" + index +
                    ", cardIndex=" + cardIndex +
                    ", line=" + line +
                    ", row=" + row +
                    '}';
        }
    }
}
