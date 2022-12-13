package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.websocket.*;
import javax.websocket.server.*;

import com.google.gson.Gson;

import beans.OperationReq;
import beans.OperationResp;
import beans.UpdateReq;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/actions2")
public class ProvaWS_2 {
	

	private Gson gson;
	public ProvaWS_2() {
		this.gson = new Gson();
	}
	
	@OnMessage
    public void updateMessage(Session session, String msg) {

    	UpdateReq update = gson.fromJson(msg, UpdateReq.class);
    	
        try {
			for (Session sess : session.getOpenSessions()) { 
				if (sess.isOpen()) {
					sess.getBasicRemote().sendText(gson.toJson(update));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
