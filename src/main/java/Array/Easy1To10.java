package Array;

public class Easy1To10 {
    public static void main(String[] args) {

    }

    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length;

        while(low < high){
            int middle = (low+high)/2;
            if(nums[middle] == target){
                return middle;
            }else if(nums[middle] > target){
                high = middle;
            }else  if(nums[middle] < target){
                low = middle;
            }
        }

        return high;
    }
}
