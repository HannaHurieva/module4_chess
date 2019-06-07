package com.alevel.module4.pieces;

import com.alevel.module4.chessboard.Chessboard;
import com.alevel.module4.chessboard.Move;
import com.alevel.module4.chessboard.Position;
import com.alevel.module4.enums.Player;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(Player color, Position pos, Chessboard board) {
        super(color, pos, board);
    }

    public Queen(Chessboard board, Piece other){
        super(board,other);
    }

    @Override
    public void addAllPossibleMoves() {
        posMoves.clear();

        Bishop bishop = new Bishop(pieceColor, currentPos, board);
        Tower tower = new Tower(pieceColor, currentPos, board);
        ArrayList<Move> bishopMoves = bishop.getMoves();
        ArrayList<Move> towerMoves = tower.getMoves();

        bishopMoves.addAll(towerMoves);
        for (Move m : bishopMoves) {
            addMove(m.getEndPos());
        }

    }
}
