package Array;


import java.util.*;

public class Medium200T600 {
    public static void main(String[] args) {
        int[] param = {4,1,-1,2,-1,2,3};
        List<List<String>> equations = new ArrayList<>();

        List<String> e1=new ArrayList<>();
        e1.add("a");
        e1.add("b");
        equations.add(e1);

        List<String> e2=new ArrayList<>();
        e2.add("b");
        e2.add("c");
        equations.add(e2);

        List<String> e3=new ArrayList<>();
        e3.add("bc");
        e3.add("cd");
        equations.add(e3);

        double[] values = {1.5,2.5,5.0};
        List<List<String>> queries = new ArrayList<>();

        List<String> q1=new ArrayList<>();
        q1.add("a");
        q1.add("c");
        queries.add(q1);

        List<String> q2=new ArrayList<>();
        q2.add("c");
        q2.add("b");
        queries.add(q2);

        List<String> q3=new ArrayList<>();
        q3.add("bc");
        q3.add("cd");
        queries.add(q3);

        List<String> q4=new ArrayList<>();
        q4.add("cd");
        q4.add("bc");
        queries.add(q4);

        int[] parameter={4,6,7,7};
        //findSubsequences(parameter);
        List<String> timePoints = new ArrayList<String>();
        timePoints.add("00:00");
        timePoints.add("23:59");
        timePoints.add("00:00");
        //findMinDifference(timePoints);
        int[] par={1,1,2,2,3};
        singleNonDuplicate(par);
        //calcEquation(equations,values,queries);
        //System.out.println(res);
    }
    //540. Single Element in a Sorted Array
    public static int singleNonDuplicate(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        int left=0,right=nums.length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==nums[mid+1]){
                mid=mid-1;
            }
            if((mid-left+1)%2!=0){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return nums[left];
        /* first thought,did not pass the test
        if(nums.length==1){
            return nums[0];
        }
        int start=0;
        if(nums[0]==0){
            if(nums[0]!=nums[1]){
                return 0;
            }else{
                start=1;
            }
        }else{
            for(int i=start;i<nums.length-1;i++){
                if(Math.abs(nums[i])==Math.abs(nums[i+1])){
                    nums[i]*=-1;
                    nums[i+1]*=-1;
                }
            }
            for(int i=start;i<nums.length;i++){
                if(nums[i]>=0){
                    return nums[i];
                }
            }
        }
        return nums[0];*/
    }


    //539. Minimum Time Difference
    public static int findMinDifference(List<String> timePoints) {
        //without Arrays.sort
        boolean[] temp=new boolean[60*24];
        for(int i=0;i<timePoints.size();i++){
            String[] hourMin = timePoints.get(i).split(":");
            int minValue= Integer.parseInt(hourMin[0])*60+Integer.parseInt(hourMin[1]);
            if(temp[minValue]){
                return 0;
            }
            temp[minValue]=true;
        }

        int min = Integer.MAX_VALUE;
        int previous = 0,first=-1;

        for(int i=0;i<temp.length;i++){
            if(temp[i]){
                if(first==-1){
                    first=i;
                }else {
                    min = Math.min(min, i - previous);
                }
                previous=i;
            }
        }
        min=Math.min(min,first-previous+60*24);
        return min;

/*        int[] temp = new int[timePoints.size()];
        int i=0;
        for(String timePoint:timePoints){
            String[] hourMin = timePoint.split(":");
            int minValue= Integer.parseInt(hourMin[0])*60+Integer.parseInt(hourMin[1]);
            temp[i++]=minValue;
        }
        Arrays.sort(temp);
        int min=Integer.MAX_VALUE;
        for(int j=1;j<temp.length;j++){
            min=Math.min(min,temp[j]-temp[j-1]);
        }
        min=Math.min(min,temp[0]-temp[temp.length-1]+24*60);
        return min;*/
    }


    //529. Minesweeper
    public char[][] updateBoard(char[][] board, int[] click) {
        int row=click[0],col=click[1];
        if(board[row][col]=='M'){
            board[row][col]='X';
            return board;
        }
        backTrack(board,row,col);
        return board;
    }
    public void backTrack(char[][] board, int row,int col){
        if(row<0||col<0||row>=board.length||col>=board[0].length||board[row][col]!='E'){
            return;
        }
        int[][] neighbors = {{row-1,col-1},{row-1,col},{row-1,col+1},{row,col-1},{row,col+1},{row+1,col-1},{row+1,col},{row+1,col+1}};
        board[row][col]='0';
        for(int[] neighbor:neighbors){
            if(neighbor[0]<0||neighbor[0]>=board.length||neighbor[1]<0||neighbor[1]>=board[0].length){
                continue;
            }
            if(board[neighbor[0]][neighbor[1]]=='M'){
                board[row][col]++;
            }
        }
        if(board[row][col]!='0'){
            return;
        }
        board[row][col]='B';
        for(int[] neighbor:neighbors){
            backTrack(board,neighbor[0],neighbor[1]);
        }
    }

     //491. Non-decreasing Subsequences
    public static List<List<Integer>> findSubsequences(int[] nums) {
        if(nums.length<=1){
            return new ArrayList<>();
        }
        if(nums.length==2&&nums[0]>nums[1]){
            return new ArrayList<>();
        }
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> item = new ArrayList<>();
        buildSequences(nums,0, result,item,nums.length);
        return new ArrayList<>(result);
    }
    public static void buildSequences(int[] nums,int start,Set<List<Integer>> result,List<Integer> items,int len){
        if(items.size()>=2){
            result.add(new ArrayList<>(items));
        }
        for(int i=start;i<len;i++) {
            if (items.size()==0||nums[i] >= items.get(items.size()-1)) {
                items.add(nums[i]);
                buildSequences(nums, i + 1, result, items, len);
                items.remove(items.size()-1);
            }
        }
    }

    //452. Minimum Number of Arrows to Burst Balloons
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points,(a,b)->Integer.compare(a[1],b[1]));
        int account=1;
        int arrow=points[0][1];
        for(int i=1;i<points.length;i++){
            if(points[i][0]<=arrow){
                continue;
            }
            account++;
            arrow=points[i][1];
        }
        return account;
    }


    //442. Find All Duplicates in an Array
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int index = Math.abs(nums[i])-1;
            int value = nums[index];
            if(value<0){
                result.add(index+1);
            }else{
                nums[index]*=-1;
            }
        }
        return result;
    }


    //399. Evaluate Division
    //a/d = a/b*b/c*c/d
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String,Map<String,Double>> matrix = buildMatrix(equations,values);
        double[] result = new double[queries.size()];
        int count = 0;
        for(List<String> query:queries){
            String dividend = query.get(0);
            String divisor = query.get(1);

            if(!matrix.containsKey(dividend)||!matrix.containsKey(divisor)){
                result[count++] = -1.0;
            }else{
                double[] ans = {-1.0};
                List<String> visited = new ArrayList<>();
                dfs(dividend,divisor,visited,1.0,matrix,ans);
                result[count++] = ans[0];
            }
        }
        return result;
    }
    public static Map<String,Map<String,Double>> buildMatrix(List<List<String>> equations, double[] values){
        Map<String,Map<String,Double>> result = new HashMap<>();
        for(int i=0;i<values.length;i++){
            String dividend = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);

            Map<String,Double> item_dividend=result.getOrDefault(dividend, new HashMap<>());
            Map<String,Double> item_divisor=result.getOrDefault(divisor, new HashMap<>());
            item_dividend.put(divisor,values[i]);
            item_divisor.put(dividend,1.0/values[i]);
            result.put(dividend,item_dividend);
            result.put(divisor,item_divisor);
        }
        return result;
    }

    public static void dfs(String middle_item,String divisor,List<String> visited,double temp_diff, Map<String,Map<String,Double>> matrix,double[] temp_res ){
        if(visited.contains(middle_item)){
            return;
        }
        visited.add(middle_item);
        if(middle_item.equals(divisor)){
            temp_res[0]=temp_diff;
            return;
        }

        for(Map.Entry<String, Double> entry: matrix.get(middle_item).entrySet()){
            String temp_divisor = entry.getKey();
            double diff = entry.getValue();
            dfs(temp_divisor,divisor,visited,temp_diff*diff,matrix, temp_res);
        }
    }


    //396. Rotate Function
    public int maxRotateFunction(int[] nums) {
        if(nums.length == 1 || nums.length == 0){
            return 0;
        }
        int max=0,sum=0,len=nums.length;
        int tempF = 0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            tempF+=i*nums[i];
        }

        max=tempF;
        for(int i=1;i<nums.length;i++){
            tempF=tempF+sum-len*nums[len-i];
            max=Math.max(max,tempF);
        }
        return max;
    }

    //347. Top K Frequent Elements
    public static int[] topKFrequent(int[] nums, int k) {
        if(nums.length==0||nums.length==k){
            return nums;
        }
        Map<Integer,Integer> freqs = new HashMap<>();
        for(int num:nums){
            freqs.put(num,freqs.getOrDefault(num,0)+1);
        }
        TreeMap<Integer,List<Integer>> sort_freq = new TreeMap<>();
        for(int num:freqs.keySet()){
            int freq = freqs.get(num);
            if(!sort_freq.containsKey(freq)){
                sort_freq.put(freq,new ArrayList<>());
            }
            sort_freq.get(freq).add(num);
        }
        List<Integer> result = new ArrayList<>();
        while(result.size()<k){
            Map.Entry<Integer, List<Integer>> entry =sort_freq.pollLastEntry();
            if(entry!=null){
                result.addAll(entry.getValue());
            }
        }

        return result.stream().mapToInt(i->i).toArray();
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
