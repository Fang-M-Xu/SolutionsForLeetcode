package Array;

import java.util.*;

public class Medium60T200 {
    public static void main(String[] args) {

        int[] param={0,0};
        largestNumber(param);
        //int[][] duparam = {{1,3,1}, {1,5,1}, {4,2,1}};
        //char[][] duparam = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        //int[] param={2,3,-2,4};
        //int[] gas={1,2,3,4,5},cost={3,4,5,1,2};
        //maxProduct(param);
        //canCompleteCircuit(gas,cost);
        //exist(duparam,"ABCESEEEFS");
        //int res=longestConsecutive(param);
        //System.out.println(res);
    }
    //189. Rotate Array
    public void rotate(int[] nums, int k) {
        k=k%nums.length;
        reverse(nums,0,nums.length-k-1);
        reverse(nums,nums.length-k,nums.length-1);
        reverse(nums,0,nums.length-1);
    }
    private void reverse(int[] nums, int start,int end){
        int i = start,j=end;
        while(i<j){
            int temp = nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
            i++;
            j--;
        }
    }

    //179. Largest Number
    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            strs[i]=nums[i]+"";
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String i1 = o1+o2;
                String i2 = o2+o1;
                return i1.compareTo(i2);
            }
        });
        if (strs[strs.length-1].charAt(0) == '0') return "0";
        String result = new String();
        for(String num:strs){
            result=num+result;
        }
        return result;

/*        Map<Integer,Set> bitGroup = new HashMap();
        for(int num:nums){
            int len = String.valueOf(num).length();
            Set<Integer> item=bitGroup.getOrDefault(len,new HashSet<Integer>());
            item.add(num);
            bitGroup.put(len,item);
        }
        return "";*/
    }

    //152. Maximum Product Subarray
    public static int maxProduct(int[] nums) {
        int max=nums[0],min=nums[0],res=nums[0];
        for(int i=0;i<nums.length;i++){
            if(nums[i]<0){
                int temp=max;
                max=min;
                min=temp;
            }
            max=Math.max(max,max*nums[i]);
            min=Math.max(min,min*nums[i]);

            res=Math.max(res,max);
        }
        return res;
    }

    //134. Gas Station
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas=0,totalCost=0;
        for(int i=0;i<gas.length;i++){
            totalGas+=gas[i];
            totalCost+=cost[i];
        }
        if(totalGas<totalCost){
            return -1;
        }
        int remain=0,station=0;
        for(int i=0;i<gas.length;i++){
            remain+=gas[i]-cost[i];
            if(remain<0){
                remain=0;
                station=i+1;
            }
        }
        return station;
    }

    //128. Longest Consecutive Sequence
    public static int longestConsecutive(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        Set dup_nums=new HashSet();
        for(int num:nums){
            dup_nums.add(num);
        }

        int max=0;
        for(int i=0; i<nums.length;i++){
            if(dup_nums.remove(nums[i])){
                int start=nums[i];
                int end=start;
                int sum=1;
                while(dup_nums.remove(end+1)){
                    end++;
                }
                sum+=end-start;
                end=start;
                while(dup_nums.remove(end-1)){
                    end--;
                }
                sum+=start-end;
                max=Math.max(max,sum);
            }
        }
        return max;
    }


    //79. Word Search
    public static boolean exist(char[][] board, String word) {
        int m=board.length,n=board[0].length;
        if(m*n<word.length()){
            return false;
        }
        if(!checkWordInborad(board,word)){
            return false;
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]==word.charAt(0)){
                    boolean isContain = backtrack79(board,word,i,j,0,new boolean[m][n]);
                    if(isContain){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private static boolean checkWordInborad(char[][] board, String word){
        List<Character> word_array = new ArrayList<>();
        for(char letter:word.toCharArray()){
            word_array.add(letter);
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(word_array.size()==0){
                    return true;
                }
                if(word_array.contains(board[i][j])){
                    word_array.remove((Character)board[i][j]);
                }
            }
        }
        if(word_array.size()==0){
            return true;
        }
        return false;
    }

    private static boolean backtrack79(char[][] board, String word, int row,int col,int wordIndex,boolean[][] flagBoard){
        if(row<0||row>=board.length||col<0||col>=board[0].length||board[row][col]!=word.charAt(wordIndex)){
            return false;
        }
        if(flagBoard[row][col]){
            return false;
        }
        if(wordIndex==word.length()-1){
            return true;
        }
        flagBoard[row][col] = true;
        if(backtrack79(board,word,row,col+1,wordIndex+1,flagBoard)){
            return true;
        }
        if(backtrack79(board,word,row,col-1,wordIndex+1,flagBoard)){
            return true;
        }
        if(backtrack79(board,word,row+1,col,wordIndex+1,flagBoard)){
            return true;
        }
        if(backtrack79(board,word,row-1,col,wordIndex+1,flagBoard)){
            return true;
        }
        flagBoard[row][col] = false;// if the four directions don't fit the condition, reset the flag to false.
        return false;
    }

    //78. Subsets
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<Integer>(),nums,0);
        return result;
    }
    private static void backtrack(List<List<Integer>> result,List<Integer> item , int[] nums, int start){
        result.add(new ArrayList<>(item));
        for(int i=start;i<nums.length;i++){
            item.add(nums[i]);
            backtrack(result,item,nums,i+1);
            item.remove(item.size()-1);
        }
    }


    //75. Sort Colors
    public static void sortColors(int[] nums) {
        int left=0,right=nums.length-1;
        for(int i=0; i<=right;i++){
            if(nums[i]==0){
                //no need to check if the num is 2 after swap,because no 2 before 0
                swap(nums,i,left++);
            }else if(nums[i]==1){
                continue;
            }else if(nums[i]==2){
                //need to check because the new num may be 0
                swap(nums,i--,right--);
            }
        }
    }
    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    //73. Set Matrix Zeroes
    public void setZeroes(int[][] matrix) {
        boolean row0=false, col0=false;
        int m=matrix.length,n=matrix[0].length;
        for(int i=0;i<n;i++){
            if(matrix[0][i]==0) row0=true;
        }
        for(int i=0;i<m;i++){
            if(matrix[i][0]==0) col0=true;
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][j]==0){
                    matrix[0][j]=0;
                    matrix[i][0]=0;
                }
            }
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[0][j]==0||matrix[i][0]==0){
                    matrix[i][j]=0;
                }
            }
        }
        if(row0==true){
            for(int i=0;i<n;i++){
                matrix[0][i]=0;
            }
        }
        if(col0==true){
            for(int i=0;i<m;i++){
                matrix[i][0]=0;
            }
        }
    }
    //64. Minimum Path Sum
    public static int minPathSum(int[][] grid) {
        int[][] path_grid = new int[grid.length][grid[0].length];
        path_grid[0][0] = grid[0][0];
        for(int i=0; i<grid.length-1; i++){
            path_grid[i+1][0]=path_grid[i][0]+grid[i+1][0];
        }
        for(int i=0; i<grid[0].length-1; i++){
            path_grid[0][i+1]=path_grid[0][i]+grid[0][i+1];
        }
        for(int i=1; i<grid.length; i++){
            for(int j=1; j<grid[0].length; j++){
                path_grid[i][j]=Math.min(path_grid[i-1][j],path_grid[i][j-1])+grid[i][j];
            }
        }
        return path_grid[grid.length-1][grid[0].length-1];
    }
/*  less runtime
    private int[][] dp;
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        dp = new int[n][m];
        for(int[] arr : dp)
            Arrays.fill(arr, -1);
        return helper(grid, n-1, m-1);
    }

    private int helper(int[][] grid, int i, int j) {
        if(dp[i][j] != -1)
            return dp[i][j];
        if(i == 0 && j == 0)
            return grid[i][j];
        if(i == 0)
            return dp[i][j] = grid[i][j] + helper(grid, i, j-1);
        if(j == 0)
            return dp[i][j] = grid[i][j] + helper(grid, i-1, j);
        return dp[i][j] = grid[i][j] + Math.min(helper(grid, i-1, j), helper(grid, i, j-1));
    }*/
}
