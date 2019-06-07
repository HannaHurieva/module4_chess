package com.alevel.module4.pieces;

import com.alevel.module4.chessboard.Chessboard;
import com.alevel.module4.chessboard.Position;
import com.alevel.module4.enums.Player;

public class Pawn extends Piece {

    public Pawn(Player color, Position pos, Chessboard board) {
        super(color, pos, board);
    }

    public Pawn(Chessboard board, Piece other) {
        super(board, other);
    }

    @Override
    public void addAllPossibleMoves() {
        posMoves.clear();
        if (pieceColor == Player.BLACK) {
            moveBlackForward();
            attackBlack();
        } else {
            moveWhiteForward();
            attackWhite();
        }
    }

    private void isFree(Position pos){
        if (board.isFree(pos)) addMove(pos);
    }

    private boolean isInStartPos() {
        if (pieceColor == Player.BLACK) {
            return currentPos.getRank() == 6;
        } else {
            return currentPos.getRank() == 1;
        }
    }

    private void containsEnemy(Position pos){
        if (board.containsEnemy(currentPos, pos)) addMove(pos);
    }

    private void moveBlackForward() {
        Position pos = new Position(currentPos.getRank() - 1, currentPos.getFile());
        isFree(pos);

        if (isInStartPos()) {
            pos = new Position(currentPos.getRank() - 2, currentPos.getFile());
            isFree(pos);
        }
    }

    private void attackBlack() {
        //Check to the left
        Position pos = new Position(currentPos.getRank() - 1, currentPos.getFile() - 1);
        containsEnemy(pos);

        //Check to the right:
        pos = new Position(currentPos.getRank() - 1, currentPos.getFile() + 1);
        containsEnemy(pos);
    }

    private void moveWhiteForward() {
        Position pos = new Position(currentPos.getRank() + 1, currentPos.getFile());
        isFree(pos);

        //If in start position
        if (isInStartPos()) {
            pos = new Position(currentPos.getRank() + 2, currentPos.getFile());
            isFree(pos);
        }
    }

    private void attackWhite() {
        //Check to the left
        Position pos = new Position(currentPos.getRank() + 1, currentPos.getFile() - 1);
        containsEnemy(pos);

        //Check to the right:
        pos = new Position(currentPos.getRank() + 1, currentPos.getFile() + 1);
        containsEnemy(pos);
    }
}