package Array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class Medium200T600 {
    public static void main(String[] args) {
        int[] param = {1,2,5};
        coinChange(param,11);
        //System.out.println(res);
    }
    //322. Coin Change
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for(int i=0;i<dp.length;i++){
            dp[i]=amount+1;
        }
        dp[0]=0;
        for(int i=1;i<=amount;i++){
            for(int coin:coins){
                if(i>=coin){
                    dp[i]=Math.min(dp[i],1+dp[i-coin]);
                }
            }
        }
        if(dp[amount]!=amount+1){
            return dp[amount];
        }else{
            return -1;
        }
    }

    //287. Find the Duplicate Number
    public int findDuplicate(int[] nums) {
        int slow =nums[0];
        int fast =nums[0];
        while(slow!=fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        int slow2=nums[0];
        while(slow!=slow2){
            slow=nums[slow];
            slow2=nums[slow2];
        }
        return slow2;
    }

    //238. Product of Array Except Self
    public static int[] productExceptSelf1(int[] nums) {
        int len = nums.length;
        int[] preArray = new int[len];
        int[] suffArray = new int[len];
        int[] result =  new int[len];
        preArray[0]=1;
        suffArray[len-1]=1;
        for(int i =1;i<len;i++){
            preArray[i]=nums[i-1]*preArray[i-1];
        }
        for(int i =len-2;i>=0;i--){
            suffArray[i]=nums[i+1]*suffArray[i+1];
        }
        for(int i =0;i<len;i++){
            result[i]=preArray[i]*suffArray[i];
        }
        return result;
    }
    //extra space complexity-O(1)
    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result =  new int[len];
        Arrays.fill(result,1);
        for(int i =1;i<len;i++){
            result[i]=nums[i-1]*result[i-1];
        }
        int lastNum = 1;
        for(int i =len-1;i>=0;i--){
            result[i] = lastNum*result[i];
            lastNum =  nums[i]*lastNum;
        }
        return result;
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
