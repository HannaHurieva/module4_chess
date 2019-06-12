package com.alevel.module4.pieces;

import com.alevel.module4.chessboard.Chessboard;
import com.alevel.module4.chessboard.Position;
import com.alevel.module4.enums.Player;

public class Bishop extends Piece {

    public Bishop(Player color, Position position, Chessboard board) {
        super(color, position, board);
    }

    public Bishop(Chessboard board, Piece other) {
        super(board, other);
    }

    @Override
    public void addAllPossibleMoves() {
        posMoves.clear();
        checkNorthWest();
        checkNorthEast();
        checkSouthWest();
        checkSouthEast();
    }

    private void checkNorthWest() {
        int rank = currentPos.getRank() + 1;
        int file = currentPos.getFile() - 1;

        while (rank < 8 && file >= 0) {
            Position pos = new Position(rank, file);
            if (checkEncounter(pos)) return;
            rank++;
            file--;
        }
    }

    private void checkNorthEast() {
        int rank = currentPos.getRank() + 1;
        int file = currentPos.getFile() + 1;

        while (rank < 8 && file < 8) {
            Position pos = new Position(rank, file);
            if (checkEncounter(pos)) return;
            rank++;
            file++;
        }
    }

    private void checkSouthWest() {
        int rank = currentPos.getRank() - 1;
        int file = currentPos.getFile() - 1;
        while (rank >= 0 && file >= 0) {
            Position pos = new Position(rank, file);
            if (checkEncounter(pos)) return;
            rank--;
            file--;
        }
    }

    private void checkSouthEast() {
        int rank = currentPos.getRank() - 1;
        int file = currentPos.getFile() + 1;
        while (rank >= 0 && file < 8) {
            Position pos = new Position(rank, file);
            if (checkEncounter(pos)) return;
            rank--;
            file++;
        }
    }

    private boolean checkEncounter(Position pos) {
        if (!board.isFree(pos)) {
            if (board.containsEnemy(currentPos, pos)) addMove(pos);
            return true;
        } else {
            addMove(pos);
        }
        return false;
    }

}
