package Beans;

import java.util.List;

public class ForbiddenThread extends Thread {
    private List<Capitolo> capitoli;
    
    
    public ForbiddenThread(List<Capitolo> capitoli) {
        this.capitoli = capitoli;
    }


    public void run() {
        while(true) {
            for ( Capitolo capitolo : capitoli ) {
                String content = capitolo.getContent();
                if ( content.split("aaaaaaaa").length >= 11) {
                    capitolo.setContent("");
                }
            }
    
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                break;
            }   
        }
    }
}
