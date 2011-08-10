package org.hxzon.util;

import ognl.Ognl;
import ognl.OgnlException;

public class OgnlUtils {

    public static Object getValue(Object o,String ex){
        Object expression=null;
        try {
            expression=Ognl.parseExpression(ex);
        } catch (OgnlException e) {
            e.printStackTrace();
        }
        Object result=null;
        try {
            result=Ognl.getValue(expression, o);
        } catch (OgnlException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static boolean setValue(Object o,String ex,Object value){
        Object expression=null;
        try {
            expression=Ognl.parseExpression(ex);
        } catch (OgnlException e) {
            return false;
        }
        
        try {
            Ognl.setValue(expression, o, value);
        } catch (OgnlException e) {
            return false;
        }
        return true;
    }
    
    public static Class getClass(Object o,String ex){
        Object value=getValue(o,ex);
        return value==null?null:value.getClass();
    }
    
//    public static Object[][] get(List list,List<String> ex){
//        int row=list.size();
//        int col=ex.size();
//        Object[][] objs=new Object[row][col];
//        for(int i=0;i<row;i++){
//            for(int j=0;j<col;j++){
//                objs[i][j]=OgnlUtils.getValue(list.get(i), ex.get(i));
//            }
//        }
//        return objs;
//    }
//    
//    public static Vector convertToVector(List list,List<String> ex){
//        int row=list.size();
//        int col=ex.size();
//        Vector v=new Vector(row);
//        for(int i=0;i<row;i++){
//            Vector vi=new Vector(col);
//            for(int j=0;j<col;j++){
//                vi.add(OgnlUtils.getValue(list.get(i), ex.get(i)));
//            }
//            v.add(vi);
//        }
//        return v;
//    }
    
}
