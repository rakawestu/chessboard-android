package com.rakawm.chessboard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.GridView;

import com.rakawm.chessboard.adapter.BoardAdapter;
import com.rakawm.chessboard.adapter.PieceAdapter;
import com.rakawm.chessboard.model.Board;
import com.rakawm.chessboard.model.Piece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.net.SocketFactory;


public class MainActivity extends ActionBarActivity {
    public static final String SERVER_IP = "http://www.xinuc.org/";
    public static final int SERVER_PORT = 7387;
    Socket socket;
    GridView chessboard;
    GridView piece;
    PieceAdapter adapterPiece;
    BoardAdapter adapter;
    List<Board> boardList;
    List<Piece> pieceList;
    String[] data = new String[]{
            "Ka7", "Qb2", "Bd5", "Nf6", "Rh8", "kc4", "qg6", "ba8", "ne4", "rd4",
            //"Kb3", "Qa6", "Be5", "Nb1", "Rd6", "kf2", "qc8", "be2", "nb4", "rh3"

    };
    int[] rowList = new int[]{
            8, 7, 6, 5, 4, 3, 2, 1
    };
    String[] columnList = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new BoardAdapter(this);
        adapterPiece = new PieceAdapter(this);
        createBoardDataModel();
        adapter.setList(boardList);
        adapter.notifyDataSetChanged();
        chessboard = (GridView) findViewById(R.id.listview);
        piece = (GridView) findViewById(R.id.listpiece);
        chessboard.setAdapter(adapter);
        piece.setAdapter(adapterPiece);
        new GetData().execute();
    }

    private void createBoardDataModel() {
        boardList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                Board board = new Board();
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        board.setColor("white");
                    } else {
                        board.setColor("black");
                    }
                } else {
                    if (j % 2 == 0) {
                        board.setColor("black");
                    } else {
                        board.setColor("white");
                    }
                }

                board.setColumn(columnList[j]);
                board.setRow(rowList[i]);
                boardList.add(board);
            }
        }
    }

    private void createPieceDataModel() {
        pieceList = new ArrayList<Piece>(Collections.nCopies(64, new Piece()));
        for (int i = 0; i < data.length; i++) {
            Piece piece = new Piece();
            if (!data[i].substring(0, 1).toUpperCase().equals(data[i].substring(0, 1))) {
                piece.setColor("black");
            } else {
                piece.setColor("white");
            }
            piece.setName(data[i].substring(0, 1));
            piece.setColumn(data[i].substring(1, 2));
            piece.setRow(Integer.valueOf(data[i].substring(2, 3)));
            int position = ((8 - piece.getRow()) * 8) + Util.getIntegerFromChar(piece.getColumn());
            pieceList.set(position, piece);
        }
        adapterPiece.setList(pieceList);
        adapterPiece.notifyDataSetChanged();
    }

    public void readAll(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            data = line.split(" ");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    createPieceDataModel();
                }
            });
            sb.append(line).append("\n");
        }
    }

    class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Log.d("Main", "Connecting to server...");
                socket = SocketFactory.getDefault().createSocket(URI.create(SERVER_IP).getHost(), SERVER_PORT);
                socket.setKeepAlive(true);
                readAll(socket);
            } catch (IOException e) {
                Log.e("Main", e.getMessage());
                //e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        }
    }
}
