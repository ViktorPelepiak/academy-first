package multiton;

import java.util.HashMap;
import java.util.Map;

public class MultitonExample {
    private static final Map<Integer, MultitonExample> instances = new HashMap();
    private static int maxObjectsQuantity = 10;

    private MultitonExample(Integer key){}

    public static void setMaxObjectsQuantity(int maxQuantity){
        maxObjectsQuantity = maxQuantity;
    }

    public static MultitonExample getInstance(Integer key){
        if (instances.containsKey( key))return instances.get(key);
        if (instances.keySet().size()==maxObjectsQuantity) throw new RuntimeException("Object limit has been reached");
        instances.put(key, new MultitonExample(key));
        return instances.get(key);
    }

    public void someMethod(){
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        MultitonExample obj1 = MultitonExample.getInstance(1);
        obj1.someMethod();
    }
}
