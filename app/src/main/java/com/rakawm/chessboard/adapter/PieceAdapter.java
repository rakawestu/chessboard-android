package com.rakawm.chessboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.rakawm.chessboard.R;
import com.rakawm.chessboard.model.Piece;

/**
 * Created by raka on 06/03/2015.
 */
public class PieceAdapter extends MyCustomAdapter<Piece> {
    /*private int[] blackPiece = new int[]{
            R.drawable.black_bishop,
            R.drawable.black_king,
            R.drawable.black_knight,
            R.drawable.black_queen,
            R.drawable.black_rook
    };

    private int[] whitePiece = new int[]{
            R.drawable.white_bishop,
            R.drawable.white_king,
            R.drawable.white_knight,
            R.drawable.white_queen,
            R.drawable.white_rook
    };*/

    private Context context;

    public PieceAdapter(Context context) {
        this.context = context;
    }


    @Override
    public ViewHolder<Piece> createHolder() {
        return new ViewHolder<Piece>() {
            ImageView status;

            @Override
            public View createView(Context context) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_piece, null);
                status = (ImageView) view.findViewById(R.id.status);
                return view;
            }

            @Override
            public void bind(int pos, Piece piece) {
                if (piece.getColor() != null) {
                    status.setVisibility(View.VISIBLE);
                    if (piece.getColor().equals("black")) {
                        switch (piece.getName().toLowerCase()) {
                            case "k":
                                status.setImageResource(R.drawable.black_king);
                                break;
                            case "q":
                                status.setImageResource(R.drawable.black_queen);
                                break;
                            case "n":
                                status.setImageResource(R.drawable.black_knight);
                                break;
                            case "b":
                                status.setImageResource(R.drawable.black_bishop);
                                break;
                            case "r":
                                status.setImageResource(R.drawable.black_rook);
                                break;
                        }
                    } else if (piece.getColor().equals("white")) {
                        switch (piece.getName().toLowerCase()) {
                            case "k":
                                status.setImageResource(R.drawable.white_king);
                                break;
                            case "q":
                                status.setImageResource(R.drawable.white_queen);
                                break;
                            case "n":
                                status.setImageResource(R.drawable.white_knight);
                                break;
                            case "b":
                                status.setImageResource(R.drawable.white_bishop);
                                break;
                            case "r":
                                status.setImageResource(R.drawable.white_rook);
                                break;

                        }
                    }
                } else {
                    status.setVisibility(View.INVISIBLE);
                }

            }
        };
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
