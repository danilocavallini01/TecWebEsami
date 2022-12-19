package Servlet;

import java.io.IOException;


import javax.websocket.*;
import javax.websocket.server.*;

import com.google.gson.Gson;

import Beans.OperationReq;

@ServerEndpoint("/actions")
public class ProvaWS{
	
	private Gson gson;
	private static int [][] matrix;

	public ProvaWS() {
		this.gson = new Gson();
	}
	
	@OnOpen
	public void onOpen(Session session) {
		try {
			if ( matrix == null ) {
	        	matrix = new int[3][3];
			}
			
			session.getBasicRemote().sendText(gson.toJson(matrix));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @OnMessage
    public void textMessage(Session session, String msg) {
        OperationReq req = gson.fromJson(msg, OperationReq.class);
        
        int row = req.getRow();
        int col = req.getCol();
        
        matrix[row][col] = req.getNumber();
  
        try {
			for (Session sess : session.getOpenSessions()) { 
				if (sess.isOpen()) {
					sess.getBasicRemote().sendText(gson.toJson(matrix));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    } 
}
