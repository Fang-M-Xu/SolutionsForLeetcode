package Array;

import java.math.BigDecimal;
import java.util.*;

public class Medium10T60 {
    public static void main(String[] args) {

        int[][] duparam = {{2,5},{4,6},{8,10},{15,18}};
        int[][] duparam1 = {{1,4},{0,4}};
        merge(duparam);
        int[] params={7,0,9,6,9,6,1,7,9,0,1,2,9,0,3};
        //rotate(duparam);
        //39. Combination Sum
        //List<List<Integer>> res = combinationSum(params,8);
       /* String[][] param1= {{"5","3",".",".","7",".",".",".","."}
                            ,{"6",".",".","1","9","5",".",".","."}
                            ,{".","9","8",".",".",".",".","6","."}
                            ,{"8",".",".",".","6",".",".",".","3"}
                            ,{"4",".",".","8",".","3",".",".","1"}
                            ,{"7",".",".",".","2",".",".",".","6"}
                            ,{".","6",".",".",".",".","2","8","."}
                            ,{".",".",".","4","1","9",".",".","5"}
                            ,{".",".",".",".","8",".",".","7","9"}};
        isValidSudoku(param1);*/
        //34. Find First and Last Position of Element in Sorted Array
        //searchRange(param1,8);
        //33. Search in Rotated Sorted Array
        //int res = search(param1,3);
        //31. Next Permutation
        //nextPermutation(param1);
        //18. 4Sum
        //int[] param1={1,0,-1,0,-2,2};
        //fourSum(param1,0);
        System.out.println("res");
    }
    //59. Spiral Matrix II
    public int[][] generateMatrix(int n) {
        int[][] new_list = new int[n][n];
        int top=0,bottom=n-1,left=0,right=n-1;
        int k=1;
        while(top<=bottom&&left<=right){
            if(top<=bottom){
                for(int i=left;i<=right;i++){
                    new_list[top][i]=k++;
                }
                top++;
            }
            if(left<=right){
                for(int i=top;i<=bottom;i++){
                    new_list[i][right]=k++;
                }
                right--;
            }
            if(top<=bottom){
                for(int i=right;i>=left;i--){
                    new_list[bottom][i]=k++;
                }
                bottom--;
            }
            if(left<=right){
                for(int i=bottom;i>=top;i--){
                    new_list[i][left]=k++;
                }
                left++;
            }
        }
        return new_list;
    }

    //57. Insert Interval
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList();
        if(intervals.length==0&&newInterval.length!=0){
            int[][] intervals_new = new int[1][2];
            intervals_new[0]=newInterval;
            return intervals_new;
        }
        List<int[]> intervals_list = new ArrayList<>();
        intervals_list.add(newInterval);
        for(int[] interval:intervals){
            intervals_list.add(interval);
        }

        int[][] intervals_new = intervals_list.toArray(new int[intervals_list.size()][]);

        int max_index = Integer.MIN_VALUE;
        int min_index = Integer.MAX_VALUE;
        for(int i=0; i< intervals_list.size();i++){
            max_index=Math.max(max_index,intervals_new[i][0]);
            min_index=Math.min(min_index,intervals_new[i][0]);
        }

        int[] range_array = new int[max_index+1];
        for(int i=0; i< intervals_new.length;i++){
            range_array[intervals_new[i][0]]=Math.max(range_array[intervals_new[i][0]],intervals_new[i][1]);
        }
        int start=min_index,end=min_index;
        for(int i=0;i<range_array.length;i++){
            if(range_array[i]==0){
                continue;
            }
            if(i<=end){
                end=Math.max(end,range_array[i]);
            }else{
                result.add(new int[]{start,end});
                start=i;
                end=range_array[i];
            }
        }
        result.add(new int[]{start,end});
        return result.toArray(new int[result.size()][]);
    }

    //56. Merge Intervals
/*  sort() method is lower
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,Comparator.comparingInt(X->X[0]));
        List<int[]> result = new ArrayList<>();
        for(int i=0;i<intervals.length;i++){
            if(result.size()==0||intervals[i][0]>result.get(result.size()-1)[1]){
                result.add(intervals[i]);
            }else{
                result.get(result.size()-1)[1] = Math.max(result.get(result.size()-1)[1],intervals[i][1]);
            }
        }
        return result.toArray(new int[result.size()][2]);
    }*/
    public static int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList();
        int max_index = Integer.MIN_VALUE;
        int min_index = Integer.MAX_VALUE;
        for(int i=0; i< intervals.length;i++){
            max_index=Math.max(max_index,intervals[i][0]);
            min_index=Math.min(min_index,intervals[i][0]);
        }
        int[]  range_list = new int[max_index+1];
        for(int i=0; i< intervals.length;i++){
            range_list[intervals[i][0]]=Math.max(range_list[intervals[i][0]],intervals[i][1]);
        }
        int start=min_index,end=min_index;
        for(int i=0;i<range_list.length;i++){
            if(range_list[i]==0){
                continue;
            }
            if(i<=end){
                end=Math.max(end,range_list[i]);
            }else{
                result.add(new int[]{start,end});
                start=i;
                end=range_list[i];
            }
        }
        result.add(new int[]{start,end});
        return result.toArray(new int[result.size()][]);
    }

    //55. Jump Game
    public boolean canJump(int[] nums) {
        int maxJump = 0;
        int furtherIndex = 0;
        for(int i=0;i<nums.length;i++){
            maxJump=Math.max(maxJump,nums[i]+i);
            if(i==furtherIndex){
                furtherIndex=maxJump;
            }
            if(furtherIndex>=nums.length-1){
                return true;
            }
        }
        return false;
    }


    //54. Spiral Matrix-judge the edge position
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top =0,bottom=matrix.length-1, left=0, right=matrix[0].length-1;
        while(top<=bottom&&left<=right){
            if(top<=bottom){
                for(int i=left; i<=right; i++){
                    result.add(matrix[top][i]);
                }
                top++;
            }
            if(left<=right){
                for(int i=top; i<=bottom; i++){
                    result.add(matrix[i][right]);
                }
                right--;
            }
            if(top<=bottom){
                for(int i=right; i>=left;i--){
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if(left<=right){
                for(int i=bottom; i>=top; i--){
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;
    }

    //49. Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> group = new HashMap<>();
        for(String str:strs){
            char[] str_list = str.toCharArray();
            Arrays.sort(str_list);
            String sorted=new String(str_list);
            if(!group.containsKey(sorted)){
                group.put(sorted,new ArrayList<>());
            }
            group.get(sorted).add(str);
        }
        return new ArrayList<>(group.values());
    }
/* Another method
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> list = new AbstractList<List<String>>() {

            List<List<String>> resultList = null;

            public int size() {
                if(resultList == null) {
                    init();
                }
                return resultList.size();
            }

            public List<String> get(int index) {
                if(resultList == null) {
                    init();
                }
                return resultList.get(index);
            }

            private void init() {
                Map<String, List<String>> resMap = new HashMap<>();

                for(String s : strs) {
                    char[] arr = new char[26];
                    for(char ch : s.toCharArray()) {
                        arr[ch - 97]++;
                    }

                    String strKey = String.valueOf(arr);
                    resMap.computeIfAbsent(strKey, k -> new ArrayList<>());
                    resMap.get(strKey).add(s);
                }

                resultList = new ArrayList<>(resMap.values().size());

                for(Map.Entry<String, List<String>> mapToList : resMap.entrySet()) {
                    resultList.add(mapToList.getValue());
                }

            }

        };
        return list;
    }*/
    //48. Rotate Image
    public static void rotate(int[][] matrix) {
        int len = matrix.length;
        int i=0;
        for(int j=len-1; j>0;j--){
            int temp = matrix[i][j];
            matrix[i][j] = matrix[len-1-j][i];
            matrix[len-1-j][i]=matrix[len-1-i][len-1-j];
            matrix[len-1-i][len-1-j]= matrix[len-1-(len-1-j)][len-1-i];
            matrix[len-1-(len-1-j)][len-1-i]=temp;
        }
/*   Counterclockwise
     for(int j=len-1; j>0;j--){
            int temp = matrix[i][j];
            matrix[i][j]=matrix[j][len-1-i];
            matrix[j][len-1-i]=matrix[len-1-i][len-1-j];
            matrix[len-1-i][len-1-j]=matrix[len-1-j][len-(len-1-i)-1];
            matrix[len-1-j][len-(len-1-i)-1]=temp;
        }*/
    }

    //45. Jump Game II
    public static int jump(int[] nums) {
        if(nums.length==1){
            return 0;
        }
        int maxJumpIndex=0;
        int edgeIndex=0;
        int JumpTime=0;
        for(int i=0;i<nums.length;i++){
            maxJumpIndex=Math.max(maxJumpIndex,i+nums[i]);
            if(i==edgeIndex){
                JumpTime++;
                edgeIndex=maxJumpIndex;
            }
            if(edgeIndex>=nums.length-1){
                break;
            }
        }
        return JumpTime;
    }

    //39. Combination Sum
    static List<Integer> tempItem = new ArrayList<>();
    static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] c, int target) {
        comb(c,target,0);
        return result;
    }
    private static void comb(int[] c, int target, int start_index){
        if(target == 0){
            result.add(new ArrayList<>(tempItem));
            return ;
        }
        for(int i=start_index; i<c.length; i++){
            if(c[i]<=target){
                tempItem.add(c[i]);
                comb(c, target-c[i], i);
                tempItem.remove(tempItem.size()-1);
            }
        }
    }


    //36. Valid Sudoku
    public static boolean isValidSudoku(String[][] board) {
        Set char_set = new HashSet();
        for(int i=0;i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j]!="."){
                    String lab="c"+board[i][j]+")";
                    if(!char_set.add(i+lab)||!char_set.add(lab+j)||!char_set.add(i/3+lab+j/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //34. Find First and Last Position of Element in Sorted Array
    public static int[] searchRange(int[] nums, int target) {
        int first_index=findIndex(nums,target,0);
        if(first_index>=nums.length||nums[first_index] != target){
            return new int[] {-1,-1};
        }
        int last_index = findIndex(nums,target+1,first_index);
        return new int[] {first_index,last_index-1};
    }
    private static int findIndex(int[] nums, int target, int start){
        int end=nums.length-1;
        while(start<=end){
            int middle=start+(end-start)/2;
            if(nums[middle]<target){
                start=middle+1;
            }else{
                end=middle-1;
            }
        }
        return start;
    }

    //33. Search in Rotated Sorted Array
    public static int search(int[] nums, int target) {
        int left=0;
        int right = nums.length-1;
        while(left<=right){
            int middle = left+(right-left)/2;
            if(nums[middle]==target) return middle;
            if(nums[left]<=nums[middle]){
                if(nums[left]<=target && target<nums[middle]){
                    right=middle-1;
                }else{
                    left=middle+1;
                }
            }else{
                if(nums[middle]<target && target<=nums[right]){
                    left=middle+1;
                }else{
                    right=middle-1;
                }
            }
        }
        return -1;
    }


    //31. Next Permutation
    public static void nextPermutation(int[] nums) {
        int decrease_index = getBreakPoint(nums);
        if(decrease_index!=-1) {
            int greater_index = getGreatValue(nums, decrease_index);
            if(greater_index!=-1){
                swap_value(nums, decrease_index, greater_index);
            }
        }
        reverse(nums,decrease_index);
    }
    private static int getBreakPoint(int[] nums){
        for(int i=nums.length-1;i>0;i--){
            //23543,so find 3
            if(nums[i]>nums[i-1]){
                return i-1;
            }
        }
        return -1;
    }
    private static int getGreatValue(int[] nums,int decrease_index){
        for(int i=nums.length-1;i>0; i--){
            if(nums[i]>nums[decrease_index]){
                return i;
            }
        }
        return -1;
    }

    private static void swap_value(int[] nums,int left_index,int right_index){
        int temp = nums[left_index];
        nums[left_index]=nums[right_index];
        nums[right_index]=temp;
    }

    private static void reverse(int[] nums,int decrease_index){
        int left_index=decrease_index+1;
        int right_index = nums.length-1;
        while(left_index<right_index){
            swap_value(nums,left_index,right_index);
            left_index++;
            right_index--;
        }
    }
    //18. 4Sum
    // this is not perfect,if it changed to 8sum,then we need create 6 lever loop,
    // it's inefficiency
/*    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        Set<Integer> unique_nums = new HashSet<>();
        for(int num:nums){
            unique_nums.add(num);
        }
        for(int i=0; i<unique_nums.size(); i++){
            for(int j=i+1; j<unique_nums.size();j++){
                int left = j+1;
                int right = unique_nums.size()-1;
                while(left<right){
                    long sum = 0;
                    sum+=nums[i];
                    sum+=nums[j];
                    sum+=nums[left];
                    sum+=nums[right];
                    if(left<right&&sum==target){
                        List<Integer> temp =Arrays.asList(nums[i],nums[j],nums[left],nums[right]);
                        result.add(temp);
                        left++;
                        right--;
                    }else if(left<right&&sum>target){
                        right--;
                    }else if(left<right&&sum==target){
                        left++
                    }
                }
            }
        }
        return result;
    }*/
    int len = 0;
    public List<List<Integer>> fourSum(int[] nums, int target) {
        len = nums.length;
        Arrays.sort(nums);
        BigDecimal target_big = new BigDecimal(target);
        List<List<Integer>> result = KSum(nums,target_big,4,0);
        return result;

    }

    private List<List<Integer>> KSum(int[] nums, BigDecimal target, int k,int sub_index){
        List<List<Integer>> result = new ArrayList<>();
        if(len<4||sub_index>len-2){
            return result;
        }
        if(k==2){
            int left = sub_index;
            int right = len-1;
            while(left<right){
                int compare = target.subtract(new BigDecimal(nums[left])).compareTo(new BigDecimal(nums[right]));
                if(compare==0){
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(nums[left]);
                    tempList.add(nums[right]);
                    result.add(tempList);
                    while (left<right&&nums[left]==nums[left+1]) left++;
                    while(left<right&&nums[right]==nums[right-1]) right--;
                    left++;
                    right--;
                }else if(compare==1){
                    left++;
                }else if(compare==-1){
                    right--;
                }
            }
        }else{
            for(int i=sub_index; i<len-k+1; i++){
                BigDecimal nums_big =target.subtract(new BigDecimal(nums[i]));
                List<List<Integer>> attempResult = KSum(nums,nums_big,k-1,i+1);
                for(List<Integer> item:attempResult){
                    item.add(0,nums[i]);
                }
                result.addAll(attempResult);
                while (i<len-1&&nums[i]==nums[i+1]) i++;
            }
        }
        return result;
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
