package Array;

import java.util.PriorityQueue;
import java.util.Random;

public class Medium200T600 {
    public static void main(String[] args) {
        int[] param = {3,2,3,1,2,4,5,5,6};
        int res=findKthLargest(param,4 );
        System.out.println(res);
    }
    //215. Kth Largest Element in an Array
    //QuickSelect Algorithm-more runtime
    public static int findKthLargest2(int[] nums, int k) {
        int left=0, right=nums.length-1;
        while(true){
            int pivot_index = left;
            int new_pivot_index = QuickSearch(nums,pivot_index,left,right);
            if(new_pivot_index==nums.length-k){
                return nums[new_pivot_index];
            }else if(new_pivot_index<nums.length-k){
                left=new_pivot_index+1;
            }else{
                right=new_pivot_index-1;
            }
        }
    }
    private static int QuickSearch(int[] nums, int pivot_index, int left, int right){
        int pivot= nums[pivot_index];
        swap(nums,pivot_index,right);
        int fix_pivot_index=left;
        for(int i=left;i<right;i++){
            if(nums[i]<pivot){
                swap(nums,i,fix_pivot_index);
                fix_pivot_index++;
            }
        }
        swap(nums,right,fix_pivot_index);
        return fix_pivot_index;
    }

    private static void swap(int[] nums, int indx_left, int indx_right){
        int temp = nums[indx_left];
        nums[indx_left] = nums[indx_right];
        nums[indx_right] = temp;
    }

    //Min-Heap
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> tempQueue = new PriorityQueue(k);
        for(int i=0; i<k;i++){
            tempQueue.offer(nums[i]);
        }
        for(int i=k;i<nums.length;i++){
            if(nums[i]>tempQueue.peek()){
                tempQueue.poll();
                tempQueue.offer(nums[i]);
            }
        }
        return tempQueue.peek();
    }




    //204. Count Primes
    public int countPrimes(int n) {
        boolean[] flag = new boolean[n];
        int number=0;
        for(int i=2;i<=n;i++){
            if(flag[i]){
                continue;
            }
            number++;
            for(long item=(long)i+i;item<=n;item+=i){
                flag[(int)item]=true;
            }
        }
        return number;
    }
}
