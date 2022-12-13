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


@ServerEndpoint("/actions")
public class ProvaWS{
	
	private Gson gson;
	public ProvaWS() {
		this.gson = new Gson();
	}
	
    @OnMessage
    public void textMessage(Session session, String msg) {
        OperationReq req = gson.fromJson(msg, OperationReq.class);
        OperationResp response = new OperationResp();
        if ( req.getOperazione().equals("+") ) {
        	
        	response.setRisultato(req.getOp1() + req.getOp2());
        } else if ( req.getOperazione().equals("-")) {
        	
        	response.setRisultato(req.getOp1() - req.getOp2());
        } else if ( req.getOperazione().equals("*")) {
        	
        	response.setRisultato(req.getOp1() * req.getOp2());
        } else if ( req.getOperazione().equals("/")) {
        	
        	response.setRisultato(req.getOp1() / req.getOp2());
        } else {
        	response.setSuccess(false);
        	try {
				session.getBasicRemote().sendText(gson.toJson(response));
			} catch (IOException e) {
				e.printStackTrace();
			}
        	return;
        }
        
        response.setSuccess(true);
        response.setOp1(req.getOp1());
        response.setOp2(req.getOp2());
        response.setTipoOp(req.getOperazione());
        
        try {
			session.getBasicRemote().sendText(gson.toJson(response));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
       
    } 
}
