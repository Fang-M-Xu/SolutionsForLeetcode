package Array;

import java.util.*;

public class Medium600T1k {

    public static void main(String[] args) {
        int[] para = {5,2,3,1};
        sortArray(para);
    }
    //912. Sort an Array
    //7 methods

    //2.

    //1.quick sort-took too long time,so pass this method
    public static int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;

    }
    public static void quickSort(int[] nums,int left,int right){
        if(left<right){
            int middle=partition(nums,left,right);
            quickSort(nums,left,middle-1);
            quickSort(nums,middle+1,right);
        }
    }
    public static int partition(int[] nums,int left,int right){
/*      int pivot = nums[left];
        while(left<right){
            while(left<right&&pivot<=nums[right]){
                right--;
            }
            nums[left]=nums[right];
            while(left<right&&pivot>=nums[left]){
                left++;
            }
            nums[right]=nums[left];
        }
        nums[left]=pivot;
        return left;*/


        int pivot=nums[right];
        int savePin=left-1;
        for(int i=left;i<right;i++){
            if(nums[i]<pivot){
                savePin++;
                swap(nums,i,savePin);
            }
        }
        swap(nums,savePin+1,right);
        return savePin+1;
    }
    public static void swap(int[] nums, int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    //909. Snakes and Ladders-maybe later
    public int snakesAndLadders(int[][] board) {
        return 1;
    }

    //904. Fruit Into Baskets
    public static int totalFruit(int[] fruits) {
        //faster
        int pre1=-1,pre2=-1;
        int preCount=0;
        int currMax=0;
        int max=0;
        for(int fruit:fruits){
            if(pre1==fruit||pre2==fruit){
                currMax++;
            }else{
                max=Math.max(max,currMax);
                currMax=preCount+1;
            }
            if(pre1!=fruit){
                pre2=pre1;
                pre1=fruit;
                preCount=1;
            }else{
                preCount++;
            }
        }
        max=Math.max(max,currMax);
        return max;

/*        Map<Integer,Integer> fruitsType=new HashMap<>();
        int left=0;
        int len=0;
        for(int i=0;i<fruits.length;i++){
            fruitsType.put(fruits[i],fruitsType.getOrDefault(fruits[i],0)+1);
            while(fruitsType.size()>2){
                fruitsType.put(fruits[left],fruitsType.getOrDefault(fruits[left],0)-1);
                fruitsType.remove(fruits[left],0);
                left++;
            }
            len=Math.max(len,i-left+1);
        }
        return len;*/
    }

    //881. Boats to Save People
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left=0,right=people.length-1;
        int account=0;
        while(left<=right){
            if(people[left]+people[right]<=limit){
                left++;
            }
            right--;
            account++;
        }
        return account;
    }


    //875. Koko Eating Bananas
    //this should has more than one answers
    public static int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int left=1,right=piles[piles.length-1];
        while(left<right){
            int sumK=0;
            int middle=left+(right-left)/2;
            for(int i=0;i<piles.length;i++){
                sumK+=Math.ceil(1.0*piles[i]/middle);
            }
            if(sumK>h){
                left=middle+1;
            }else if(sumK<=h){
                right=middle;
            }
        }
        return left;
    }


    //852. Peak Index in a Mountain Array
    public int peakIndexInMountainArray(int[] arr) {
        int left=0,right=arr.length;
        while(left<right){
            int middle=left+(right-left)/2;
            if(arr[middle-1]<arr[middle]&&arr[middle+1]<arr[middle]){
                return middle;
            }
            if(arr[middle-1]>arr[middle]){
                right=middle;
            }else if(arr[middle+1]>arr[middle]){
                left=middle+1;
            }
        }
        return left;
    }

    //849. Maximize Distance to Closest Person
    public static int maxDistToClosest(int[] seats) {
        //even - get smaller one,odd-get middle one
        int maxDistance=0;
        int prePosition=-1;
        for(int i=0;i<seats.length;i++) {
            if(seats[i]==1){
                if(prePosition==-1){
                    maxDistance=i;
                }else{
                    maxDistance=Math.max(maxDistance,(i-prePosition)/2);
                }
                prePosition=i;
            }
        }
        maxDistance=Math.max(maxDistance,seats.length-1-prePosition);
        return maxDistance;

/*   first thought
        int maxDistance=0;
        int prePosition=-1;
        for(int i=0;i<seats.length;i++){
            int distance = 0;
            if(prePosition==-1){
                prePosition=i;
            }else if(seats[i]==1&&prePosition!=-1){
                if(seats[prePosition]==0){
                    distance=i-prePosition;
                }else{
                    distance=(i-prePosition)/2;
                }
                prePosition=i;
            }else if(i==seats.length-1&&seats[i]==0){
                distance=i-prePosition;
            }
            maxDistance= Math.max(maxDistance,distance);
        }
        if(maxDistance==0){
            return seats.length-1;
        }
        return maxDistance;*/
    }

    //845. Longest Mountain in Array
    public static int longestMountain(int[] arr) {
        if(arr.length<3){
            return 0;
        }
        int len=0;
        for(int i=1;i<arr.length-1;i++){
            if(arr[i]>arr[i-1]&&arr[i]>arr[i+1]){
                int left = leftMountain(arr, i-1);
                int right = rightMountain(arr, i+1);
                len = Math.max(len, right - left + 1);
            }
        }
        return len;
    }
    public static int leftMountain(int[] arr, int right){
        int count=right;
        for(int i=right;i>0;i--){
            if(arr[i]>arr[i-1]){
                return i-1;
            }else{
                break;
            }
        }
        return count;
    }
    public static int rightMountain(int[] arr, int left){
        int count=left;
        for(int i=left;i<arr.length-1;i++){
            if(arr[i]>arr[i+1]){
                count=i+1;
            }else{
                break;
            }
        }
        return count;
    }

    //835. Image Overlap
    public int largestOverlap(int[][] img1, int[][] img2) {
        //Method2-faster
        int N=img1.length;
        int[][] account = new int[2*N][2*N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(img1[i][j]==1){
                    for(int m=0;m<N;m++){
                        for(int n=0;n<N;n++){
                            if(img2[m][n]==1){
                                account[i-m][j-n]++;
                            }
                        }
                    }
                }
            }
        }
        int max=0;
        for(int i=0;i<2*N;i++){
            for(int j=0;j<2*N;j++){
                max = Math.max(max,account[i][j]);
            }
        }
        return max;

        /*    method1-time lower
        int result =0;
        int K = img1.length;
        for(int m=1-K;m<K;m++){
            for(int n=1-K;n<K;n++){
                result=Math.max(result,overlapAccount(img1,img2,m,n,K));
            }
        }
        return result;*/
    }
    //method1-time lower
/*    public int overlapAccount(int[][] img1, int[][] img2,int m,int n,int K){
            int account=0;
            for(int i=0;i<K;i++){
                for(int j=0;j<K;j++){
                    if(i-m>=0&&i-m<K&&j-n>=0&&j-n<K){
                        if(img1[i][j]==img2[i-m][j-n]&&img1[i][j]==1){
                            account++;
                        }
                    }
                }
            }
            return account;
    }*/


    //823. Binary Trees With Factors
    //public static int numFactoredBinaryTrees(int[] arr) {
/*  study later
      Map<Integer,Long> account = new HashMap<>();
        Arrays.sort(arr);
        return 1;
        Arrays.sort(arr);
        int ans =0;
        HashMap<Integer, Long> map = new HashMap<>();

        for(int x:arr)
        {
            long ways =1;
            int max = (int)Math.sqrt(x);

            for(int j = 0,left=arr[0];left<=max;left=arr[++j])
            {
                if(x%left!=0)   continue;
                int right =x/left;
                if(map.containsKey(right))
                {
                    ways = (ways+map.get(left)*map.get(right)*(left==right?1:2))%mod;
                }
            }
            map.put(x,ways);
            ans=(int)(ans+ways)%mod;
        }
        return ans;*/

        /*Lower
        Map<Integer,Long> account = new HashMap<>();
        Arrays.sort(arr);
        long len = 0L;

        for(int i=0;i<arr.length;i++){
            account.put(arr[i],1L);
            for(int j=0;j<arr.length;j++){
                if(arr[j]>=arr[i]) break;
                if(arr[i]%arr[j]==0&&account.containsKey(arr[i]/arr[j])){
                    account.put(arr[i],account.get(arr[j])*account.get(arr[i]/arr[j])+account.get(arr[i]));
                }
            }
        }
        for(Map.Entry<Integer,Long> entry:account.entrySet()){
            len=(len+entry.getValue())%1000000007;
        }
        return (int)len;*/
    //}


    //811. Subdomain Visit Count
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> result = new ArrayList<>();
        Map<String,Integer> cpdomains_map = new HashMap<>();
        for(String cpdomain:cpdomains){
            String[] cpdomain_kv = cpdomain.split(" ");
            int account = Integer.valueOf(cpdomain_kv[0]);
            cpdomains_map.put(cpdomain_kv[1],cpdomains_map.getOrDefault(cpdomain_kv[1],0)+account);
            String second_dom = cpdomain_kv[1].substring(cpdomain_kv[1].indexOf(".")+1);
            cpdomains_map.put(second_dom,cpdomains_map.getOrDefault(second_dom,0)+account);

            if(second_dom.contains(".")){
                String last = second_dom.substring(second_dom.indexOf(".")+1);
                cpdomains_map.put(last,cpdomains_map.getOrDefault(last,0)+account);
            }

        }
        for(Map.Entry<String,Integer> entry:cpdomains_map.entrySet()){
            StringBuilder res_item = new StringBuilder();
            res_item.append(entry.getValue()+" "+entry.getKey());
            result.add(res_item.toString());
        }

        return result;
    }



    //658. Find K Closest Elements
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        //Method3-Heap-bad speed
        List<Integer> result=new ArrayList<>();
        PriorityQueue<Integer> temp_result = new PriorityQueue<>();
        for(int i=0;i<k;i++){
            temp_result.offer(arr[i]);
        }
        if(k<arr.length){
        for(int i=k;i<arr.length;i++){
            if(x-temp_result.peek()>arr[i]-x){
                temp_result.poll();
                temp_result.offer(arr[i]);
            }
        }
        }
        while(!temp_result.isEmpty()){
            result.add(temp_result.poll());
        }
        return result;



        //Method2-Without Math,create a sliding window
/*        List<Integer> result=new ArrayList<>();
        int start = 0, end = arr.length-k;
        while(start<end){
            int mid = start+(end-start)/2;
            if(x-arr[mid]>arr[mid+k]-x){
                start=mid+1;
            }else{
                end=mid;
            }
        }
        for(int i=start;i<end+k;i++){
            result.add(arr[i]);
        }
        return result;*/
        //Method1-Using Math
       /* List<Integer> result=new ArrayList<>();
        int start=0,end=arr.length-1;
        while(end-start>=k){
            if(Math.abs(arr[start]-x)<=Math.abs(arr[end]-x)){
                end--;
            }else{
                start++;
            }
        }
        for(int i=start;i<=end;i++){
            result.add(arr[i]);
        }
        return result;*/
    }
}
