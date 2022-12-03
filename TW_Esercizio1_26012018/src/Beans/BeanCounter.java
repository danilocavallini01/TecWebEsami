package Beans;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BeanCounter extends Thread {
    private String filename;
    private int counter;

    public BeanCounter(String filename) {
        this.filename = filename;
        this.counter = 0;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void run() {
        synchronized(this) {
            try (FileReader fr = new FileReader(new File(this.filename))) {
                int chr;
                while ( ( chr = fr.read()) != -1 ) {
                    if ( chr >= 48 && chr <= 57 ) {
                        counter++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}