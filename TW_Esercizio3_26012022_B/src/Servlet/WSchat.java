package Servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import Beans.MapResponse;
import Beans.Request;
import Beans.Response;
import Beans.WinResponse;

@ServerEndpoint("/actions")
public class WSchat {

	private Gson gson;
	private static String[][] field;

	private String playerSymbol;
	private static Set<String> players;
	private static boolean isTurnPlayer1 = true;

	public WSchat() {
		this.gson = new Gson();
		if (field == null) {
			field = new String[3][3];
		}
		if (players == null) {
			players = new CopyOnWriteArraySet<String>();
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		try {
			if (players.size() == 1) {
				players.add(session.getId());

				this.playerSymbol = "O";

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						field[i][j] = "";
					}
				}

				broadcast(session, new MapResponse(true, field, "X", "X"));
				send(session, new MapResponse(true, field, "O", "X"));

			} else if (players.size() == 0) {

				players.add(session.getId());

				this.playerSymbol = "X";

				send(session, new MapResponse(false, null, this.playerSymbol, "X"));

			} else if (players.size() >= 2) {
				session.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnMessage
	public void textMessage(Session session, String msg) {
		String [] player = players.toArray(new String[2]);

		if (isTurnPlayer1) {
			if (!session.getId().equals(player[0])) {
				return;
			}
		} else {
			if (!session.getId().equals(player[1])) {
				return;
			}
		}

		Request req = gson.fromJson(msg, Request.class);

		field[req.getRow()][req.getCol()] = this.playerSymbol;

		String whoWin = this.checkWin();

		try {
			if (whoWin != null) {
				broadcast(session, new WinResponse(true, whoWin));
			} else {
				isTurnPlayer1 = !isTurnPlayer1;
				broadcast(session, new MapResponse(field, isTurnPlayer1 ? "X" : "O"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		players.remove(session.getId());
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

	private String checkWin() {

		for (int i = 0; i < 3; i++) {
			int symbolCount1 = 0;
			int symbolCount2 = 0;

			for (int j = 0; j < 3; j++) {
				if (field[i][j].equals("X")) {
					symbolCount1++;
				} else if (field[i][j].equals("O")) {
					symbolCount2++;
				}
			}

			if (symbolCount1 == 3) {
				return "player 1";
			} else if (symbolCount2 == 3) {
				return "player 1";
			}
		}

		for (int i = 0; i < 3; i++) {
			int symbolCount1 = 0;
			int symbolCount2 = 0;

			for (int j = 0; j < 3; j++) {
				if (field[j][i].equals("X")) {
					symbolCount1++;
				} else if (field[j][i].equals("O")) {
					symbolCount2++;
				}
			}

			if (symbolCount1 == 3) {
				return "player 1";
			} else if (symbolCount2 == 3) {
				return "player 2";
			}
		}

		if (field[0][0].equals("X") && field[1][1].equals("X") && field[2][2].equals("X")) {
			return "player 1";
		}

		if (field[2][0].equals("X") && field[1][1].equals("X") && field[0][2].equals("X")) {
			return "player 1";
		}

		if (field[0][0].equals("O") && field[1][1].equals("O") && field[2][2].equals("O")) {
			return "player 2";
		}

		if (field[2][0].equals("O") && field[1][1].equals("O") && field[0][2].equals("O")) {
			return "player 2";
		}
		return null;
	}
}
