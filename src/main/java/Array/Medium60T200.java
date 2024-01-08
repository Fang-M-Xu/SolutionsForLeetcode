package Array;

public class Medium60T200 {
    public static void main(String[] args) {

        //int[][] duparam = {{1,3,1}, {1,5,1}, {4,2,1}};
        int[][] duparam = {{1,2,3}, {4,5,6}};
        minPathSum(duparam);
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
