package Array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//528. Random Pick with Weight
//need to study more
public class Solution528 {

    int total=0;
    Random random;
    List<Integer> sumList = new ArrayList<Integer>();
    public Solution528(int[] w) {
        this.random=new Random();
        for(int i=0;i<w.length;i++){
            total+=w[i];
            sumList.add(total);
        }
    }

    public int pickIndex() {
        if(this.total==0){
            return -1;
        }
        int flag = this.random.nextInt(this.total);
        for(int i=0;i<this.sumList.size();i++) {
            if (flag < this.sumList.get(i)){
                return i;
            }
        }
        return -1;
    }
}
