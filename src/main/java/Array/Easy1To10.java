package Array;

public class Easy1To10 {
    public static void main(String[] args) {
        //66. Plus One
        int[] orig = {8,6,9,9};
        //orig = new int[orig.length+1];
        System.out.println(orig);
        plusOne(orig);
        System.out.println(orig);
        //35. Search Insert Position
        /*int tes1 = 1+(3-1)>>1;
        int tes2 = 1+((3-1)>>1);
        System.out.println(tes1+","+tes2);
        int[] test1 = {1,3};
        System.out.print(searchInsert(test1,2));*/
    }
    //88. Merge Sorted Array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int end1 = m-1;
        int end2 = n-1;
        int mergeEnd = m+n-1;
        while(end2>=0){
            if(end1>=0 && nums1[end1]>nums2[end2]){
                nums1[mergeEnd] = nums1[end1];
                mergeEnd--;
                end1--;
            }else{
                nums1[mergeEnd] = nums2[end2];
                mergeEnd--;
                end2--;
            }
        }
    }

    //66. Plus One
    public static int[] plusOne(int[] digits) {
        if (digits==null){
            return digits;
        }
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i]<9){
                digits[i]++;
                return digits;
            }else if(digits[i] == 9){
                digits[i]=0;
            }
        }
        digits = new int[digits.length+1];
        digits[0]=1;
        return digits;
    }
    //35. Search Insert Position
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
