package Array;

import java.util.*;

public class Medium600T1k {

    public static void main(String[] args) {
        int[] para = {1,2,3,4,5};
        findClosestElements(para,4,3);
    }
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
