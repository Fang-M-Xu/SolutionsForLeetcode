package Array;

public class Easy1To10 {
    public static void main(String[] args) {
        int tes1 = 1+(3-1)>>1;
        int tes2 = 1+((3-1)>>1);
        System.out.println(tes1+","+tes2);
        int[] test1 = {1,3};
        System.out.print(searchInsert(test1,2));
    }

    public static int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        while(start <= end){
            int middle = start+((end-start)>>1);
            if(nums[middle] == target){
                return middle;
            }else if(nums[middle] > target){
                end = middle-1;
            }else {
                start = middle+1;
            }
        }
        return start;
    }
}
