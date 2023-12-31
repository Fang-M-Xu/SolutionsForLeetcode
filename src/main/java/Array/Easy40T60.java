package Array;

import java.util.*;

public class Easy40T60 {
    public static void main(String[] args) {
        //1572. Matrix Diagonal Sum
        int[] param1={0,2,1,5,3,4};
        int[] result = buildArray(param1);
        //int[][] param1= {{7,3,1,9},{3,4,6,9},{6,9,6,6},{9,5,8,5}};
        //int result = diagonalSum(param1);
        //1470. Shuffle the Array
        //int[] param1 = {1,3,5,6,7,8,11,13,14,16,17,18,19,20,21,23,24,25,26,28,29,30,31,34,35,36,37,38,41,43,44,47,50,51,53,54,56,57,58,59,60,62,63,65,67,68,69,70,71,72,73,74,76,78,80,81,83,84,85,88,89,90,91,92,93,95,97,98,102,103,104,105,108,109,110,111,112,113,114,117,120,123,124,125,127,128,129,130,131,132,133,135,136,137,138,139,141,142,145,146,148,149,150,151,153,154,155,156,161,162,164,167,168,169,170,171,172,175,176,178,179,181,182,184,191,193,195,196,199,201,202,204,205,208,210,214,215,217,219,221,222,224,226,228,229,230,231,232,234,235,236,240,242,246,248,249,251,252,253,254,255,256,257,258,259,260,261,262,265,267,269,272,273,275,278,279,280,281,282,283,284,285,286,287,289,291,292,293,296,297,298,299,303,305,306,308,312,313,315,316,318,320,323,324,327,330,332,335,337,340,342,343,344,346,349,350,352,353,354,356,357,359,360,362,366,367,369,370,374,375,376,377,378,379,382,384,386,390,392,393,394,395,396,399,400,401,403,406,411,413,415,416,420,424,425,426,427,429,430,432,434,435,436,437,438,439,440,441,442,443,444,446,447,448,449,452,455,456,458,459,460,461,462,463,464,465,466,467,469,470,471,472,477,479,480,483,484,486,488,489,490,491,492,493,494,495,500,501,503,504,506,508,510,513,514,515,516,517,527,531,533,534,535,536,542,543,546,547,548,549,550,553,556,559,561,562,563,566,567,569,571,572,576,578,579,581,582,583,584,586,589,591,592,593,594,595,598,600,601,602,603,605,606,607,609,611,612,613,614,616,617,621,622,624,625,626,627,630,631,633,635,636,637,639,640,643,644,646,647,648,649,650,651,652,654,658,660,661,662,663,664,665,667,668,669,672,673,678,679,683,685,686,687,689,690,691,692,693,695,696,697,701,702,703,704,707,709,711,714,717,718,719,720,721,723,724,725,726,728,729,730,733,735,736,737,738,740,742,745,746,747,750,754,755,757,759,761,763,765,768,771,773,774,775,776,779,780,781,782,783,784,787,788,789,790,791,792,794,795,797,798,800,801,805,806,808,810,811,812,814,816,819,822,824,825,826,828,831,833,835,838,841,842,844,845,846,847,849,853,854,855,857,858,861,862,866,868,869,870,874,878,882,884,885,888,889,890,892,893,897,900,903,905,906,907,908,911,913,916,918,920,921,922,924,925,926,928,929,930,932,933,934,936,937,938,940,942,944,946,949,953,954,956,957,958,961,962,964,965,966,969,972,973,974,976,977,978,979,980,981,982,984,985,986,988,993,996,997,999};
        //int result = findKthPositive(param1,724);
        //double result = average(param1);
        //shuffle(param1,3);
        //1356. Sort Integers by The Number of 1 Bits
        //int[] param1 = {0,1,2,3,4,5,6,7,8};
        //sortByBits(param1);
        System.out.println(result);
    }
    //1929. Concatenation of Array
    public int[] getConcatenation(int[] nums) {

        int nums_len = nums.length;
        int[] new_arr = new int[nums.length*2];
        System.arraycopy(nums,0,new_arr,0,nums_len);
        System.arraycopy(nums,0,new_arr,nums_len,nums_len);
        return new_arr;
        /*int[] new_arr = new int[nums.length*2];
        for(int i=0; i<new_arr.length; i++){
            if(i>=nums.length){
                new_arr[i] = nums[i-nums.length];
            }else {
                new_arr[i] = nums[i];
            }
        }
        return new_arr;*/
    }

    //1920. Build Array from Permutation
    public static int[] buildArray(int[] nums) {
        creatTempArray(nums,0);
        return nums;
/*       int[] new_arr = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            new_arr[i]=nums[nums[i]];
        }
        return new_arr;*/
    }
    static void creatTempArray(int[] nums,int start) {
        if (start < nums.length) {
            int temp = nums[start];
            int result = nums[temp];
            creatTempArray(nums, start + 1);
            nums[start] = result;
        }
    }

    //1672. Richest Customer Wealth
    public static int maximumWealth(int[][] accounts) {
        int max_weight = 0;
        for(int i=0; i<accounts.length; i++){
            int sum = 0;
            for(int j=0; j<accounts[i].length;j++){
                sum+=accounts[i][j];
            }
            if(max_weight<sum){
                max_weight=sum;
            }
        }
        return max_weight;
    }

    //1572. Matrix Diagonal Sum
    public static int diagonalSum(int[][] mat) {
        int mat_len = mat.length;
        int sum = 0;
        for(int i=0;i<mat_len;i++){
            sum = sum+mat[i][i]+mat[i][mat_len-1-i];
            if(mat_len%2!=0&&i==mat_len/2){
                sum-=sum+mat[i][i];
            }
        }
        return sum;
    }

    //1539. Kth Missing Positive Number
    public static int findKthPositive(int[] arr, int k) {
        for(int item:arr){
            if(item<=k){
                k++;
            }else{
                break;
            }
        }
        return k;
        /*List<Integer> missing_array = new ArrayList<>();
        List<Integer> arr_list = new ArrayList<>();
        for(int item:arr){
            arr_list.add(item);
        }
        int whole_arr=1;
        while(true){
            if(missing_array.size()==k){
                return missing_array.get(k-1);
            }
            if(!arr_list.contains(whole_arr)){
                missing_array.add(whole_arr);
            }
            whole_arr++;
        }*/
    }

    //1491. Average Salary Excluding the Minimum and Maximum Salary
    public static double average(int[] salary) {
        int max = 0;
        int min = 1000001;
        double sum = 0;
        for(int person: salary){
            if(person>max){
                max=person;
            }
            if(person<min){
                min=person;
            }
            sum +=person;
        }
        double result=(sum-max-min)/(salary.length-2);
        return Math.round(result*100000)*0.00001d;
    }

    //1470. Shuffle the Array
    public static int[] shuffle(int[] nums, int n) {
        int[] arr_first = Arrays.copyOfRange(nums,0,n);
        int[] arr_last = Arrays.copyOfRange(nums,n,2*n);
        int[] result = new int[nums.length];
        int merge_index=0;
        for(int i = 0;i<n; i++){
            result[merge_index] = arr_first[i];
            result[++merge_index] = arr_last[i];
            merge_index++;
        }
        return result;
    }

    //1356. Sort Integers by The Number of 1 Bits
    public static int[] sortByBits(int[] arr) {
        for(int i=0;i<arr.length;i++){
            int count = Integer.bitCount(arr[i]);
            arr[i] = arr[i]+count*100000;
        }
        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++) {
            arr[i] = arr[i]%100000;
        }
        return arr;
/*        Integer[] box_arr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(box_arr,(a,b)->{
            int binary1 = Integer.bitCount(a);
            int binary2 = Integer.bitCount(b);
            int result = binary1==binary2?a-b:binary1-binary2;
            return result;
        });
        int[] unbox_arr = Arrays.stream(box_arr).mapToInt(i->i).toArray();
        return unbox_arr;*/
    }
    //1207. Unique Number of Occurrences
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map_arr = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            map_arr.merge(arr[i],1,Integer::sum);
        }
        Set<Integer> set_num = new HashSet<>(map_arr.values());
        if(map_arr.size()==set_num.size()){
            return true;
        }
        return false;
    }
}
