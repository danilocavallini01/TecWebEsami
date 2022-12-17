package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import Beans.InvalideMessageThread;

@ServerEndpoint("/actions")
public class WSchat {

	private Gson gson;
	private static List<String> messagges;
	private static Integer sessionCount;
	private static InvalideMessageThread thread;

	public WSchat() {
		this.gson = new Gson();
		if ( messagges == null ) {
			messagges = new ArrayList<>();
		}

		if ( sessionCount == null ) {
			sessionCount = 1;
		} else {
			sessionCount++;
			if ( sessionCount >= 2 ) {
				if ( thread == null ) {
					thread = new InvalideMessageThread(messagges);
					thread.start();
				}
			}
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		try {
			if ( messagges.size() > 0 ) {
				session.getBasicRemote().sendText(gson.toJson(messagges));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnMessage
	public void textMessage(Session session, String msg) {
		msg = gson.fromJson(msg, String.class);
		if ( msg.startsWith("S") || msg.startsWith("A") ) return;

		messagges.add(msg);
		
		try {
			for (Session sess : session.getOpenSessions()) {
				if (sess.isOpen()) {
					sess.getBasicRemote().sendText(gson.toJson(msg));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
