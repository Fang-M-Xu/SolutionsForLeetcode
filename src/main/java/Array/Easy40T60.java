package Array;

import java.util.*;

public class Easy40T60 {
    public static void main(String[] args) {
        //1470. Shuffle the Array
        int[] param1 = {2,5,1,3,4,7};
        shuffle(param1,3);
        //1356. Sort Integers by The Number of 1 Bits
        //int[] param1 = {0,1,2,3,4,5,6,7,8};
        //sortByBits(param1);
    }

    //1470. Shuffle the Array
    public static int[] shuffle(int[] nums, int n) {
        int[] arr_first = Arrays.copyOfRange(nums,0,n);
        int[] arr_last = Arrays.copyOfRange(nums,n,2*n);
        int[] result = new int[nums.length];
        int merge_index=0;
        for(int i = 0;i<n; i++){
            result[merge_index] = arr_first[i];
            result[++merge_index] = arr_last[i];
            merge_index++;
        }
        return result;
    }

    //1356. Sort Integers by The Number of 1 Bits
    public static int[] sortByBits(int[] arr) {
        for(int i=0;i<arr.length;i++){
            int count = Integer.bitCount(arr[i]);
            arr[i] = arr[i]+count*100000;
        }
        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++) {
            arr[i] = arr[i]%100000;
        }
        return arr;
/*        Integer[] box_arr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(box_arr,(a,b)->{
            int binary1 = Integer.bitCount(a);
            int binary2 = Integer.bitCount(b);
            int result = binary1==binary2?a-b:binary1-binary2;
            return result;
        });
        int[] unbox_arr = Arrays.stream(box_arr).mapToInt(i->i).toArray();
        return unbox_arr;*/
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
