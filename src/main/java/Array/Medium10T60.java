package Array;

import java.util.*;

public class Medium10T60 {
    public static void main(String[] args) {

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
