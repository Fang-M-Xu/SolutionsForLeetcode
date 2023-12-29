package Array;

import java.util.HashMap;
import java.util.Map;

public class Easy20T40 {

    public static void main(String[] args) {
        //724. Find Pivot Index
        int[] param1 = {1,7,3,6,5,6};
        int result = pivotIndex(param1);
        //697. Degree of an Array
        //int[] param1 = {1,2,2,3,1,4,2};
        //int result = findShortestSubArray(param1);
        System.out.println(result);
    }

    //697. Degree of an Array
    public static int findShortestSubArray(int[] nums) {
        Map<Integer,int[]> map_nums = new HashMap<>();
        int max_degree=0;
        int min_len = 0;
        for(int i=0;i<nums.length;i++){
            int item = nums[i];
            if(!map_nums.containsKey(item)){
                map_nums.put(item,new int[]{i,i,1});
                if(max_degree==0){
                    max_degree = 1;
                }
                if(min_len==0){
                    min_len=1;
                }
            }else if(map_nums.containsKey(item)){
                int[] map_item = map_nums.get(item);
                map_item[1]=i;
                map_item[2]++;
                if(map_item[2]>max_degree){
                    max_degree=map_item[2];
                    min_len = i-map_item[0]+1;
                }else if(map_item[2]==max_degree){
                    min_len = Math.min(min_len,i-map_item[0]+1);
                }
            }
        }
        return min_len;
    }

    //724. Find Pivot Index
    public static int pivotIndex(int[] nums) {
        int left_sum = 0;
        int right_sum = 0;
        for(int num:nums){
            right_sum += num;
        }

        for(int i  = 0; i< nums.length;i++){
            right_sum -= nums[i];
            if(left_sum == right_sum){
                return i;
            }
            left_sum += nums[i];
        }
        return -1;
    }
}
