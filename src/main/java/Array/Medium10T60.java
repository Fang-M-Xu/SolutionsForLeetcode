package Array;

import java.math.BigDecimal;
import java.util.*;

public class Medium10T60 {
    public static void main(String[] args) {
        //int[] param1={1,0,-1,0,-2,2};
        //fourSum(param1,0);
    }

    //31. Next Permutation
    public void nextPermutation(int[] nums) {

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
