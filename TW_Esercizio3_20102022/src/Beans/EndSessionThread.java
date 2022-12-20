package Beans;

import java.io.IOException;
import java.util.List;

import javax.websocket.Session;

public class EndSessionThread extends Thread {
    private Session session;
    private List<Session> chat;

    public EndSessionThread(Session session, List<Session> chat) {
        this.session = session;
        this.chat = chat;
    }

    public void run() {
        try {
            Thread.sleep(10 * 60 * 1000);
            this.chat.remove(session);
            session.close();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
