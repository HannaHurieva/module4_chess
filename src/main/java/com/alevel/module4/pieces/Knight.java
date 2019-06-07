package com.alevel.module4.pieces;

import com.alevel.module4.chessboard.Chessboard;
import com.alevel.module4.chessboard.Position;
import com.alevel.module4.enums.Player;

public class Knight extends Piece {

    public Knight(Player color, Position pos, Chessboard board) {
        super(color, pos, board);
    }

    public Knight(Chessboard board, Piece other) {
        super(board, other);
    }

    @Override
    public void addAllPossibleMoves() {
        posMoves.clear();

        //1 north, 2 west:
        Position pos = new Position(currentPos.getRank() +1, currentPos.getFile() -2);
        checkEncounter(pos);

        //1 north, 2 east:
        pos = new Position(currentPos.getRank() + 1, currentPos.getFile() +2);
        checkEncounter(pos);

        //2 north, 1 west:
        pos = new Position(currentPos.getRank()+2, currentPos.getFile() -1);
        checkEncounter(pos);

        //2 north, 1 east:
        pos = new Position(currentPos.getRank()+2, currentPos.getFile()+1);
        checkEncounter(pos);

        //2 south, 1 west:
        pos = new Position(currentPos.getRank()-2, currentPos.getFile() -1);
        checkEncounter(pos);

        //2 south, 1 east:
        pos = new Position(currentPos.getRank()-2, currentPos.getFile()+1);
        checkEncounter(pos);

        //1 south, 2 east:
        pos = new Position(currentPos.getRank()-1, currentPos.getFile()+2);
        checkEncounter(pos);

        //1 south, 2 west:
        pos = new Position(currentPos.getRank()-1, currentPos.getFile()-2);
        checkEncounter(pos);
    }

    private void checkEncounter(Position pos) {
        if (board.containsEnemy(currentPos,pos)) {
            addMove(pos);
            return;
        }
        if(board.isFree(pos)){
            addMove(pos);
        }
    }
}
