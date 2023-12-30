package Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Easy40T60 {
    public static void main(String[] args) {

    }

    //1207. Unique Number of Occurrences
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map_arr = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            map_arr.merge(arr[i],1,Integer::sum);
        }
        Set<Integer> set_num = new HashSet<>(map_arr.values());
        if(map_arr.size()==set_num.size()){
            return true;
        }
        return false;
    }
}
