package Tets;

import java.util.*;

public class TestForArray {
    public static void main(String[] args) {
        System.out.println(3/2);
        System.out.println("doucms.coam.casd".indexOf("."));
        System.out.println("doucms.coam.casd".substring(6));

        Random rand=new Random();
        int[] nums = {1,3};
        // no numbers to pick!
        int n = rand.nextInt(4);
        for (int i = 0; i < nums.length; i++) {
            if (n < nums[i])
                System.out.println(i);
        }

        System.out.println("fine");



        Set<int[]> temp = new TreeSet();
/*        temp.add("1");
        temp.add("1");
        temp.add("2");*/
        temp.add(new int[]{1,4});
        temp.add(new int[]{1,4});
        temp.add(new int[]{1,2});


        for(int i=0;i<3;i++){
            if(i==2){
                break;
            }
            System.out.println(i);
        }
/*        Set seen = new HashSet();
        List ter = new ArrayList();
        ter.add(1);
        seen.add(1);
        boolean ifsuc = ter.add(1);
        boolean ifsuc2 = seen.add(1);
        seen.add("as");*/

       /*int tem= 4+(5-4)/2;
        System.out.println(tem);
        int res = -294967296-1000000000;
        res = res - 1000000000;
        System.out.println(res);
        */
    }
}
