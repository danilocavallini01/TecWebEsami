package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import Beans.SetGroupResponse;
import Beans.SetGroupRequest;
import Beans.EndSessionThread;
import Beans.Response;
import Beans.SendTextRequest;
import Beans.SendTextResponse;

@ServerEndpoint("/actions")
public class WSchat {

	private Gson gson;
	private static List<String> [] chat;
	private static List<Session> [] user;

	private int chatId;

	public WSchat() {
		gson = new Gson();
		if ( chat == null ) {
			chat = new List[4];

			for ( int i = 0; i < 4; i++ ) {
				chat[i] = new ArrayList<String>();
			}
		}

		if ( user == null ) {
			user = new List[4];

			for ( int i = 0; i < 4; i++ ) {
				user[i] = new ArrayList<Session>();
			}
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		chatId = -1;
	}

	@OnMessage
	public void onMessage(Session session, String msg) {
		try {
			

			if ( msg.contains("set_group_request") ) {
				
				SetGroupRequest req = gson.fromJson(msg, SetGroupRequest.class );
				System.out.println(msg);
				if ( req.getId() < 0 || req.getId() > 3) {
					send(session, new SetGroupResponse());
					return;
				}

				this.chatId = req.getId();
				user[this.chatId].add(session);

				EndSessionThread t = new EndSessionThread(session, user[this.chatId]);
				t.start();

				send(session, new SetGroupResponse(chat[this.chatId], this.chatId));
				
				return;
			} else if ( msg.contains("send_text_request") ) {
				SendTextRequest req =  gson.fromJson(msg, SendTextRequest.class );

				if ( req.getText().contains("1234567890")) {
					return;
				}

				chat[this.chatId].add(req.getText());

				for ( Session s : user[this.chatId] ) {
					send(s, new SendTextResponse(req.getText()));
				}

				return;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		user[this.chatId].remove(session);
		if ( user[this.chatId].size() == 0 ) {
			//log
		}
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		//
	}

	public void broadcast(Session session, Response Response) throws IOException {
		for (Session sess : session.getOpenSessions()) {
			if (sess.isOpen()) {
				sess.getBasicRemote().sendText(gson.toJson(Response));
			}
		}
	}

	public void send(Session session, Response response) throws IOException {
		session.getBasicRemote().sendText(gson.toJson(response));
	}

}
