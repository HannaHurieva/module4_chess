package com.alevel.module4.pieces;

import com.alevel.module4.chessboard.Chessboard;
import com.alevel.module4.chessboard.Position;
import com.alevel.module4.enums.Player;

public class King extends Piece {

    private boolean moved;

    public King(Player color, Position pos, Chessboard board) {
        super(color, pos, board);
    }

    public King(Chessboard board, Piece other) {
        super(board, other);
    }

    @Override
    public void updateCurrentPos(Position pos) {
        this.currentPos = pos;
        moved = true;
    }

    @Override
    protected void addAllPossibleMoves() {
        posMoves.clear();
        Position pos = new Position(currentPos.getRank() - 1,
                currentPos.getFile() - 1);

        checkSpace(pos);

        pos = new Position(currentPos.getRank() - 1, currentPos.getFile());
        checkSpace(pos);

        pos = new Position(currentPos.getRank() - 1, currentPos.getFile() + 1);
        checkSpace(pos);

        pos = new Position(currentPos.getRank(), currentPos.getFile() - 1);
        checkSpace(pos);

        pos = new Position(currentPos.getRank(), currentPos.getFile() + 1);
        checkSpace(pos);

        pos = new Position(currentPos.getRank() + 1, currentPos.getFile() - 1);
        checkSpace(pos);

        pos = new Position(currentPos.getRank() + 1, currentPos.getFile());
        checkSpace(pos);

        pos = new Position(currentPos.getRank() + 1, currentPos.getFile() + 1);
        checkSpace(pos);

    }

    private void checkCastling() {
        queenSideCastling();
        kingSideCastling();

    }

    private void queenSideCastling() {
        if (moved) {
            return;
        }
        for (int file = 4; file < 7; file++) {
            Position pos = new Position(currentPos.getRank(), file);
            if (!board.isFree(pos)) {
                return;
            }
        }

        Position queenSideTower = new Position(currentPos.getRank(), 7);
        if (board.containsEnemy(currentPos, queenSideTower)) {
            return;
        }

        if (!board.isUnmovedTower(queenSideTower)) {
            return;
        }
        addMove(queenSideTower);
    }

    private void kingSideCastling() {
        if (moved) {
            return;
        }
        for (int file = 2; file > 0; file--) {
            Position pos = new Position(currentPos.getRank(), file);
            if (!board.isFree(pos)) {
                return;
            }
        }

        Position kingSideTower = new Position(currentPos.getRank(), 0);
        if (board.containsEnemy(currentPos, kingSideTower)) {
            return;
        }

        if (!board.isUnmovedTower(kingSideTower)) {
            return;
        }
        addMove(kingSideTower);
    }

    private void checkSpace(Position pos) {
        if (board.containsEnemy(currentPos, pos)) {
            addMove(pos);
        } else if (board.isFree(pos)) {
            addMove(pos);
        }
    }
}
