package Array;

import java.util.HashMap;
import java.util.Map;

public class Easy20T40 {

    public static void main(String[] args) {
        //944. Delete Columns to Make Sorted
        String[] param1 = {"a","b"};
        int result = minDeletionSize(param1);
        //733. Flood Fill
        //int[][] param1 = {{1,1,1},{1,1,0},{1,0,1}};
        //int[][] result = floodFill(param1,1,1,2);
        //704. Binary Search
        //int[] param1 = {1,3,6,8,16,20};
        //int result = search(param1,18);
        //724. Find Pivot Index
        //int result = pivotIndex(param1);
        //697. Degree of an Array{1,2,2,3,1,4,2};
        //int result = findShortestSubArray(param1);
        System.out.println(result);
    }


    //944. Delete Columns to Make Sorted
    public static int minDeletionSize(String[] strs) {
        int index_num = 0;
        for(int i=0;i<strs[0].length();i++){
            for(int j=0;j<strs.length-1;j++){
                char first_char = strs[j].charAt(i);
                char second_char = strs[j+1].charAt(i);
                if(first_char>second_char){
                    index_num++;
                    break;
                }
            }
        }
        return index_num;
    }

    //733. Flood Fill
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc]==color){
            return image;
        }
        fill(image,sr, sc, color, image[sr][sc]);
        return image;
    }

    public static void fill(int[][] image, int sr, int sc, int color, int original) {
        if(sr<0||sc<0||image.length<=sr||image[0].length<=sc){
            return ;
        }

        if(original != image[sr][sc]) {
            return;
        }

        image[sr][sc] = color;
        fill(image,sr+1,sc,color,original);
        fill(image,sr-1,sc,color,original);
        fill(image,sr,sc+1,color,original);
        fill(image,sr,sc-1,color,original);
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

    //704.Binary Search
    public static int search(int[] nums, int target) {
        int left_index = 0;
        int right_index = nums.length - 1;

        while(left_index<=right_index){
            int middle_index = left_index+(right_index-left_index)/2;
            if(target == nums[middle_index]){
                return middle_index;
            }else if(target > nums[middle_index]){
                left_index = middle_index+1;
            }else if(target < nums[middle_index]){
                right_index = middle_index-1;
            }
        }
        return -1;
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
}
