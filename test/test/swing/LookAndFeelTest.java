package test.swing;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.swing.UIManager;

public class LookAndFeelTest {

    public static void main(String[] args) {
        Set set=new TreeSet();
        for (Entry<Object, Object> e : UIManager.getDefaults().entrySet()) {
            set.add(e.getKey().toString());
//            System.out.println(e.getKey() + "      = " + e.getValue());
        }
        for(Object key:set){
            Object value=UIManager.get(key);
            System.out.println(key+"         = "+value);
        }

        System.out.println("ok");
    }

}
