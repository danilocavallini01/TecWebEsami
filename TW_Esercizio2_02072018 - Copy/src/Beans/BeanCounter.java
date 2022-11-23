    package Beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Date;

public class BeanCounter extends Thread {
    private Long elapsedTime;
    private int count;
    private String[] filenames;

    public Long getElapsedTime() {
        return elapsedTime;
    }
    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String[] getFilenames() {
        return filenames;
    }
    public void setFilenames(String[] filenames) {
        this.filenames = filenames;
    }

    
    public BeanCounter(String[] filenames) {
        super();
        this.count = 0;
        this.elapsedTime = 0l;
        this.filenames = filenames;
    }

    public void run() {
        long time = new Date().getTime();

        int c;
        for ( int i = 0; i < 3;  i++ ) {
            try {
                FileInputStream ir = new FileInputStream(new File(filenames[i]));
                
                while((c = ir.read()) > 0) {
                    if ( c >= 65 && c <= 90 ) {
                        this.count++;
                    }
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.setElapsedTime(new Date().getTime() - time);
    }
}