package com.rakawm.chessboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.rakawm.chessboard.R;
import com.rakawm.chessboard.model.Board;

/**
 * Created by raka on 06/03/2015.
 */
public class BoardAdapter extends MyCustomAdapter<Board> {
    private Context context;

    public BoardAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder<Board> createHolder() {
        return new ViewHolder<Board>() {
            ImageView color;

            @Override
            public View createView(Context context) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_board, null);
                color = (ImageView) view.findViewById(R.id.boardItem);
                return view;
            }

            @Override
            public void bind(int pos, Board board) {
                if (board.getColor().equals("black")) {
                    color.setBackgroundResource(android.R.color.black);
                } else if (board.getColor().equals("white")) {
                    color.setBackgroundResource(android.R.color.white);
                }
            }
        };
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
