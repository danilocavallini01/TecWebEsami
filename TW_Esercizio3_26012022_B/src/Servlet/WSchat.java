package Servlet;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import Beans.Request;
import Beans.Response;

import com.google.gson.Gson;

@ServerEndpoint("/actions")
public class WSchat {

	private Gson gson;
	private static String[][] field;
	private static int users = 0;
	private String playerSymbol;
	private static String player1;
	private static String player2;
	private static boolean isTurnPlayer1 = true;

	public WSchat() {
		this.gson = new Gson();
		if (field == null) {
			field = new String[3][3];
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		try {
			if (users == 1) {
				player2 = session.getId();
				users++;
				this.playerSymbol = "O";

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						field[i][j] = "";
					}
				}

				for (Session sess : session.getOpenSessions()) {
					if (sess.isOpen()) {
						sess.getBasicRemote().sendText(gson.toJson(new Response(true, field, null)));
					}
				}

				session.getBasicRemote().sendText(gson.toJson(new Response(true, field, this.playerSymbol)));
			} else if (users == 0) {
				player1 = session.getId();
				users++;
				this.playerSymbol = "X";
				session.getBasicRemote().sendText(gson.toJson(new Response(false, null, this.playerSymbol)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnMessage
	public void textMessage(Session session, String msg) {
		if (isTurnPlayer1) {
			if (!session.getId().equals(player1)) {
				return;
			}
		} else {
			if (!session.getId().equals(player2)) {
				return;
			}
		}

		Request req = gson.fromJson(msg, Request.class);

		field[req.getRow()][req.getCol()] = this.playerSymbol;

		String whoWin = this.checkWin();

		try {
			if (whoWin != null) {
				// win case
				for (Session sess : session.getOpenSessions()) {
					if (sess.isOpen()) {
						sess.getBasicRemote().sendText(gson.toJson(new Response(false, field, true, whoWin)));
					}
				}
			} else {
				// game ongoing case
				for (Session sess : session.getOpenSessions()) {
					if (sess.isOpen()) {
						sess.getBasicRemote().sendText(gson.toJson(new Response(true, field, null)));
					}
				}
				isTurnPlayer1 = !isTurnPlayer1;
			}

			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		//
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		//
	}


	private String checkWin() {
		
		for ( int i = 0; i < 3; i++ ) {
			int symbolCount1 = 0;
			int symbolCount2 = 0;

			for ( int j = 0; j < 3; j++ ) {
				if ( field[i][j].equals("X") ) {
					symbolCount1++;
				} else if (field[i][j].equals("O") ) {
					symbolCount2++;
				}
			}

			
			if ( symbolCount1 == 3) {
				return "player 1";
			} else if ( symbolCount2 == 3) {
				return "player 1";
			}
		}

		for ( int i = 0; i < 3; i++ ) {
			int symbolCount1 = 0;
			int symbolCount2 = 0;

			for ( int j = 0; j < 3; j++ ) {
				if ( field[j][i].equals("X") ) {
					symbolCount1++;
				} else if (field[j][i].equals("O") ) {
					symbolCount2++;
				}
			}

			if ( symbolCount1 == 3) {
				return "player 1";
			} else if ( symbolCount2 == 3) {
				return "player 2";
			}
		}

		if ( field[0][0].equals("X") &&  field[1][1].equals("X") && field[2][2].equals("X")) {
			return "player 1";
		}

		
		if ( field[2][0].equals("X") &&  field[1][1].equals("X") && field[0][2].equals("X")) {
			return "player 1";
		}

		if ( field[0][0].equals("O") &&  field[1][1].equals("O") && field[2][2].equals("O")) {
			return "player 2";
		}

		if ( field[2][0].equals("O") &&  field[1][1].equals("O") && field[0][2].equals("O")) {
			return "player 2";
		}
		return null;
	}
}
