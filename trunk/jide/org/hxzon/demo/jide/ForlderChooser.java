package org.hxzon.demo.jide;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;

import com.jidesoft.swing.CheckBoxList;
import com.jidesoft.swing.CheckBoxListWithSelectable;

public class ForlderChooser {

 
    public static void main(String[] args) {
        JFrame f=new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        FolderChooser fc=new FolderChooser();
//        fc.showOpenDialog(f);
        CheckBoxList list=new CheckBoxList(new String[]{"1","2","3","4"});
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        f.add(list);
        CheckBoxListWithSelectable list2=new CheckBoxListWithSelectable(new String[]{"1","2","3","4"});
//        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        f.add(list2);
        f.pack();
        f.setVisible(true);
    }

}
