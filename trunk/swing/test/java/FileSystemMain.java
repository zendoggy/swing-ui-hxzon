package test.java;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class FileSystemMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        FileSystemView view=FileSystemView.getFileSystemView();
        File[] roots=view.getRoots();
        for(File file:roots){
            System.out.println(file);
        }
    }

}
