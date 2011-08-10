package org.hxzon.util;

import java.util.List;
import java.util.Vector;

public class HUtils {

    public static <E> Vector<E> convertToEVector(List<E> list){
        if(list instanceof Vector){
            return (Vector<E>) list;
        }
        else{
            Vector<E> v=new Vector<E>(list.size());
            for(E o:list){
                v.add(o);
            }
            return v;
        }
    }
    
    public static Vector convertToVector(List list){
        if(list instanceof Vector){
            return (Vector) list;
        }
        else{
            Vector v=new Vector(list.size());
            for(Object o:list){
                v.add(o);
            }
            return v;
        }
    }
}
