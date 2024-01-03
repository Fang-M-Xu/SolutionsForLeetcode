package Array;

import java.math.BigDecimal;
import java.util.*;

public class Medium10T60 {
    public static void main(String[] args) {

        String[][] param1= {{"5","3",".",".","7",".",".",".","."}
                            ,{"6",".",".","1","9","5",".",".","."}
                            ,{".","9","8",".",".",".",".","6","."}
                            ,{"8",".",".",".","6",".",".",".","3"}
                            ,{"4",".",".","8",".","3",".",".","1"}
                            ,{"7",".",".",".","2",".",".",".","6"}
                            ,{".","6",".",".",".",".","2","8","."}
                            ,{".",".",".","4","1","9",".",".","5"}
                            ,{".",".",".",".","8",".",".","7","9"}};
        isValidSudoku(param1);
        //34. Find First and Last Position of Element in Sorted Array
        //searchRange(param1,8);
        //33. Search in Rotated Sorted Array
        //int res = search(param1,3);
        //31. Next Permutation
        //nextPermutation(param1);
        //18. 4Sum
        //int[] param1={1,0,-1,0,-2,2};
        //fourSum(param1,0);
        System.out.println("res");
    }

    //36. Valid Sudoku
    public static boolean isValidSudoku(String[][] board) {
        Set char_set = new HashSet();
        for(int i=0;i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j]!="."){
                    String lab="c"+board[i][j]+")";
                    if(!char_set.add(i+lab)||!char_set.add(lab+j)||!char_set.add(i/3+lab+j/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //34. Find First and Last Position of Element in Sorted Array
    public static int[] searchRange(int[] nums, int target) {
        int first_index=findIndex(nums,target,0);
        if(first_index>=nums.length||nums[first_index] != target){
            return new int[] {-1,-1};
        }
        int last_index = findIndex(nums,target+1,first_index);
        return new int[] {first_index,last_index-1};
    }
    private static int findIndex(int[] nums, int target, int start){
        int end=nums.length-1;
        while(start<=end){
            int middle=start+(end-start)/2;
            if(nums[middle]<target){
                start=middle+1;
            }else{
                end=middle-1;
            }
        }
        return start;
    }

    //33. Search in Rotated Sorted Array
    public static int search(int[] nums, int target) {
        int left=0;
        int right = nums.length-1;
        while(left<=right){
            int middle = left+(right-left)/2;
            if(nums[middle]==target) return middle;
            if(nums[left]<=nums[middle]){
                if(nums[left]<=target && target<nums[middle]){
                    right=middle-1;
                }else{
                    left=middle+1;
                }
            }else{
                if(nums[middle]<target && target<=nums[right]){
                    left=middle+1;
                }else{
                    right=middle-1;
                }
            }
        }
        return -1;
    }


    //31. Next Permutation
    public static void nextPermutation(int[] nums) {
        int decrease_index = getBreakPoint(nums);
        if(decrease_index!=-1) {
            int greater_index = getGreatValue(nums, decrease_index);
            if(greater_index!=-1){
                swap_value(nums, decrease_index, greater_index);
            }
        }
        reverse(nums,decrease_index);
    }
    private static int getBreakPoint(int[] nums){
        for(int i=nums.length-1;i>0;i--){
            //23543,so find 3
            if(nums[i]>nums[i-1]){
                return i-1;
            }
        }
        return -1;
    }
    private static int getGreatValue(int[] nums,int decrease_index){
        for(int i=nums.length-1;i>0; i--){
            if(nums[i]>nums[decrease_index]){
                return i;
            }
        }
        return -1;
    }

    private static void swap_value(int[] nums,int left_index,int right_index){
        int temp = nums[left_index];
        nums[left_index]=nums[right_index];
        nums[right_index]=temp;
    }

    private static void reverse(int[] nums,int decrease_index){
        int left_index=decrease_index+1;
        int right_index = nums.length-1;
        while(left_index<right_index){
            swap_value(nums,left_index,right_index);
            left_index++;
            right_index--;
        }
    }
    //18. 4Sum
    // this is not perfect,if it changed to 8sum,then we need create 6 lever loop,
    // it's inefficiency
/*    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        Set<Integer> unique_nums = new HashSet<>();
        for(int num:nums){
            unique_nums.add(num);
        }
        for(int i=0; i<unique_nums.size(); i++){
            for(int j=i+1; j<unique_nums.size();j++){
                int left = j+1;
                int right = unique_nums.size()-1;
                while(left<right){
                    long sum = 0;
                    sum+=nums[i];
                    sum+=nums[j];
                    sum+=nums[left];
                    sum+=nums[right];
                    if(left<right&&sum==target){
                        List<Integer> temp =Arrays.asList(nums[i],nums[j],nums[left],nums[right]);
                        result.add(temp);
                        left++;
                        right--;
                    }else if(left<right&&sum>target){
                        right--;
                    }else if(left<right&&sum==target){
                        left++
                    }
                }
            }
        }
        return result;
    }*/
    int len = 0;
    public List<List<Integer>> fourSum(int[] nums, int target) {
        len = nums.length;
        Arrays.sort(nums);
        BigDecimal target_big = new BigDecimal(target);
        List<List<Integer>> result = KSum(nums,target_big,4,0);
        return result;

    }

    private List<List<Integer>> KSum(int[] nums, BigDecimal target, int k,int sub_index){
        List<List<Integer>> result = new ArrayList<>();
        if(len<4||sub_index>len-2){
            return result;
        }
        if(k==2){
            int left = sub_index;
            int right = len-1;
            while(left<right){
                int compare = target.subtract(new BigDecimal(nums[left])).compareTo(new BigDecimal(nums[right]));
                if(compare==0){
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(nums[left]);
                    tempList.add(nums[right]);
                    result.add(tempList);
                    while (left<right&&nums[left]==nums[left+1]) left++;
                    while(left<right&&nums[right]==nums[right-1]) right--;
                    left++;
                    right--;
                }else if(compare==1){
                    left++;
                }else if(compare==-1){
                    right--;
                }
            }
        }else{
            for(int i=sub_index; i<len-k+1; i++){
                BigDecimal nums_big =target.subtract(new BigDecimal(nums[i]));
                List<List<Integer>> attempResult = KSum(nums,nums_big,k-1,i+1);
                for(List<Integer> item:attempResult){
                    item.add(0,nums[i]);
                }
                result.addAll(attempResult);
                while (i<len-1&&nums[i]==nums[i+1]) i++;
            }
        }
        return result;
    }


    //15. 3Sum
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> triplets = new HashSet<>();
        for(int i=0;i<nums.length-2; i++){
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                int sum = nums[i]+nums[j]+nums[k];
                if(sum==0){
                    triplets.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                }else if(sum<0){
                    j++;
                }else if(sum>0){
                    k--;
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>(triplets);
        return result;
    }

    //11. Container With Most Water
    public int maxArea(int[] height) {
        int max_area = 0;
        int start_index=0;
        int end_index=height.length-1;
        while(start_index<end_index){
            int min_heigh=Math.min(height[start_index],height[end_index]);
            int area = Math.max(max_area,min_heigh*(end_index-start_index));
            max_area=Math.max(max_area,area);
            if(height[start_index]<height[end_index]){
                start_index++;
            }else{
                end_index--;
            }
        }
        return max_area;
    }
}
