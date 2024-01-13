package Array;

import java.util.*;

public class RandomizedSet {
    //380. Insert Delete GetRandom O(1)
    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
    Map<Integer,Integer> map_store;
    List<Integer> list_store;
    Random rad;
    public RandomizedSet() {
        this.map_store=new HashMap<>();
        this.list_store=new ArrayList<>();
        this.rad=new Random();
    }

    public boolean insert(int val) {
        if(map_store.containsKey(val)){
            return false;
        }
        list_store.add(val);
        map_store.put(val,list_store.size()-1);
        return true;
    }

    public boolean remove(int val) {
        if(!map_store.containsKey(val)){
            return false;
        }
        int val_index=map_store.get(val);
        int last_index=list_store.size()-1;
        if(val_index!=last_index){
            int last_value=list_store.get(last_index);
            list_store.set(val_index,last_value);
            map_store.put(last_value,val_index);
        }
        list_store.remove(last_index);
        map_store.remove(val);
        return true;
    }

    public int getRandom() {
        int index= rad.nextInt(list_store.size()-1);
        return list_store.get(index);
    }

    public static void main(String[] args) {
        RandomizedSet tem=new RandomizedSet();
        tem.insert(1);
        tem.insert(2);
        tem.insert(3);
        tem.remove(2);
    }
}
