package Array;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Medium600T1k {

    public static void main(String[] args) {
        int[] para = {1,2,3,4,5};
        findClosestElements(para,4,3);
    }
    //



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
