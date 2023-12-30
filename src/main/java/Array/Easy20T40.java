package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Easy20T40 {

    public static void main(String[] args) {

        //977. Squares of a Sorted Array
        int[] param1 = {-7,-3,2,3,11};
        int[] result=sortedSquares(param1);
        //953. Verifying an Alien Dictionary
//        String[] param1 = {"hello","leetcode"};
//        String order = "hlabcdefgijkmnopqrstuvwxyz";
//        boolean result =isAlienSorted(param1,order);

        //944. Delete Columns to Make Sorted
        //String[] param1 = {"a","b"};
        //int result = minDeletionSize(param1);
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

    //997. Find the Town Judge
    public static int findJudge(int n, int[][] trust) {
        Map<Integer,Integer> trusted_person = new HashMap();
        Map<Integer,Integer> betrust_person = new HashMap();
        for(int[] pair: trust){
            trusted_person.put(pair[0],trusted_person.getOrDefault(pair[0],0)+1);
            betrust_person.put(pair[1],betrust_person.getOrDefault(pair[1],0)+1);
        }
        for(int person = 1; person<=n;person++){
            int num_trusted=trusted_person.getOrDefault(person,0);
            int num_betrust=betrust_person.getOrDefault(person,0);
            if(num_trusted == 0 && num_betrust == n-1){
                return person;
            }
        }
        return -1;
    }
    //977. Squares of a Sorted Array
    public static int[] sortedSquares(int[] nums) {
        int[] newNums = new int[nums.length];
        int left = 0;
        int right = nums.length-1;
        for(int i=nums.length-1; i>= 0; i--){
            if(Math.abs(nums[left])>Math.abs(nums[right])){
                newNums[i] = nums[left]*nums[left];
                left++;
            }else{
                newNums[i] = nums[right]*nums[right];
                right--;
            }
        }
        return newNums;
    }

    //953. Verifying an Alien Dictionary
    public static boolean isAlienSorted(String[] words, String order) {
        Map<Character,Integer> char_order = new HashMap<>();
        for(int i = 0; i < order.length(); i++){
            char_order.put(order.charAt(i),i);
        }

        for(int i = 0; i<words.length-1;i++){
            for(int j = 0; j<words[i].length();j++){
                if(j==words[i+1].length()){
                    return false;
                }
                char first_word = words[i].charAt(j);
                char second_word = words[i+1].charAt(j);

                if(char_order.get(first_word)>char_order.get(second_word)){
                    return false;
                }else if(char_order.get(first_word)<char_order.get(second_word)){
                    break;
                }
            }
        }
        return true;
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
