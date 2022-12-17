package Beans;

import java.util.List;

public class InvalideMessageThread extends Thread {
    private List<String> messagges;

    public InvalideMessageThread(List<String> messages ) {
        this.messagges = messages;
    }

    public void run() {
        try {
            Thread.sleep(30 * 60  * 1000);
        } catch (InterruptedException e) {
            messagges.clear();
        }
    }
}