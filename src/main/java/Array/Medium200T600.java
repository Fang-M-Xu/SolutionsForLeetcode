package Array;

public class Medium200T600 {
    public static void main(String[] args) {

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
