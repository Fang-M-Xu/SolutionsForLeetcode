package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Easy1To10 {
    public static void main(String[] args) {
        //228. Summary Ranges
        int[] test228={};
        List<String> result = summaryRanges(test228);
        System.out.println(result);
        //121.Best Time to Buy and Sell Stock
        //int[] tes121 = {7,3,9,1,6,4};
        //System.out.println(maxProfit(tes121));
        //118. Pascal's Triangle
        //generate(5);
        //66. Plus One
        /*int[] orig = {8,6,9,9};
        orig = new int[orig.length+1];
        System.out.println(orig);
        plusOne(orig);
        System.out.println(orig);*/
        //35. Search Insert Position
        /*int tes1 = 1+(3-1)>>1;
        int tes2 = 1+((3-1)>>1);
        System.out.println(tes1+","+tes2);
        int[] test1 = {1,3};
        System.out.print(searchInsert(test1,2));*/
    }
    //228. Summary Ranges
    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        if(nums == null || nums.length==0){
            return result;
        }
        int start = nums[0];
        int end = 0;
        for(int i=0; i<nums.length-1; i++){
            end = nums[i];
            if(nums[i]+1!=nums[i+1]){
                String item = "";
                if(start == end){
                    item = String.valueOf(start);
                }else {
                    item = String.valueOf(start) + "->" + String.valueOf(end);
                }
                result.add(item);
                start = nums[i + 1];
            }
        }
        if(start == nums[nums.length-1]){
            String item = String.valueOf(start);
            result.add(item);
        }else{
            String item = String.valueOf(start)+"->"+String.valueOf(nums[nums.length-1]);
            result.add(item);
        }
        return result;
    }
    //136. Single Number
    //121.Best Time to Buy and Sell Stock
    public static int maxProfit(int[] prices) {
        if(prices == null){
            return 0;
        }

        int leastItem = Integer.MAX_VALUE;
        int profit = 0;
        int difference = 0;

        for(int i = 0;i<prices.length;i++){
            if(prices[i]<leastItem){
                leastItem = prices[i];
            }
            difference = prices[i] - leastItem;
            if(profit<difference){
                profit = difference;
            }
        }
        return profit;
    }


    //118. Pascal's Triangle
    public static List<List<Integer>> generate(int numRows) {
       List<List<Integer>> result = new ArrayList<List<Integer>>();
       if(numRows == 0){
           return result;
       }
        if(numRows == 1){
            List<Integer> row = new ArrayList<Integer>();
            row.add(1);
            result.add(row);
            return result;
        }
        result = generate(numRows-1);
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for(int i =1;i< numRows-1;i++){
            row.add(result.get(numRows-2).get(i-1)+result.get(numRows-2).get(i));
        }
        row.add(1);
        result.add(row);
        return result;

     /*   if (numRows == 0) return new ArrayList<List<Integer>>();
        if (numRows == 1) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            result.add(Arrays.asList(1));
            return result;
        }

        List<List<Integer>> prevRows = generate(numRows - 1);
        List<Integer> newRow = new ArrayList<Integer>();

        for (int i = 0; i < numRows; i++) {
            newRow.add(1);
        }

        for (int i = 1; i < numRows - 1; i++) {
            newRow.set(i, prevRows.get(numRows - 2).get(i - 1) + prevRows.get(numRows - 2).get(i));
        }

        prevRows.add(newRow);
        return prevRows;*/
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
