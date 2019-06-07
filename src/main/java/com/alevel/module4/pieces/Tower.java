package com.alevel.module4.pieces;

import com.alevel.module4.chessboard.Chessboard;
import com.alevel.module4.chessboard.Position;
import com.alevel.module4.enums.Player;

public class Tower extends Piece {

    private boolean moved;

    public Tower(Player color, Position pos, Chessboard board) {
        super(color, pos, board);
    }

    public Tower(Chessboard board, Piece other) {
        super(board, other);
    }

    @Override
    public void updateCurrentPos(Position pos) {
        this.currentPos = pos;
        moved = true;
    }

    public boolean isMoved() {
        return moved;
    }

    @Override
    public void addAllPossibleMoves() {
        posMoves.clear();
        goNorth();
        goSouth();
        goWest();
        goEast();
    }

    private void goNorth() {
        int file = currentPos.getFile();
        for (int i = currentPos.getRank() - 1; i >= 0; i--) {
            Position pos = new Position(i, file);
            if (checkEncounter(pos)) return;
        }
    }

    private void goSouth() {
        int file = currentPos.getFile();
        for (int i = currentPos.getRank() + 1; i < 8; i++) {
            Position pos = new Position(i, file);
            if (checkEncounter(pos)) return;
        }
    }

    private void goWest() {
        int rank = currentPos.getRank();
        for (int i = currentPos.getFile() - 1; i >= 0; i--) {
            Position pos = new Position(rank, i);
            if (checkEncounter(pos)) return;
        }
    }

    private void goEast() {
        int rank = currentPos.getRank();
        for (int i = currentPos.getFile() + 1; i < 8; i++) {
            Position pos = new Position(rank, i);
            if (checkEncounter(pos)) return;
            }
    }

    private boolean checkEncounter(Position pos) {
        if (board.isFree(pos)) {
            addMove(pos);
        } else {
            if (board.containsEnemy(currentPos, pos)) {
                addMove(pos);
                return true;
            }
        }
        return false;
    }
}
