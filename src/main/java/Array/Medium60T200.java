package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medium60T200 {
    public static void main(String[] args) {

        //int[][] duparam = {{1,3,1}, {1,5,1}, {4,2,1}};
        int[][] duparam = {{1,2,3}, {4,5,6}};
        int[] param={1,2,3};
        subsets(param);
        //minPathSum(duparam);
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
