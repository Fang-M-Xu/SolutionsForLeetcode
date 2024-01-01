package Array;

public class Medium10T60 {
    public static void main(String[] args) {

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
