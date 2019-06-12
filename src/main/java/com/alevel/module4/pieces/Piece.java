package com.alevel.module4.pieces;

import com.alevel.module4.chessboard.Chessboard;
import com.alevel.module4.chessboard.Move;
import com.alevel.module4.chessboard.Position;
import com.alevel.module4.enums.Player;

import java.util.ArrayList;

public abstract class Piece{
    protected ArrayList<Move> posMoves = new ArrayList<>();
    protected Player pieceColor;
    protected Position currentPos;
    protected Chessboard board;

    public Piece(Player color, Position position, Chessboard board) {
        currentPos = position;
        pieceColor = color;
        this.board = board;
    }

    //Cloning
    public Piece(Chessboard board, Piece other) {
        pieceColor = other.getPieceColor();
        this.board = board;
        currentPos = new Position(other.getPosition());
    }

    abstract protected void addAllPossibleMoves();

    public Player getPieceColor(){
        return pieceColor;
    }

    public void updateCurrentPos(Position pos){
        this.currentPos = pos;
    }

    public Position getPosition(){
        return currentPos;
    }

    protected void addMove(Position destination) {
        Move move = new Move(currentPos, destination, this);
        posMoves.add(move);
    }

    public ArrayList<Move> getMoves(){
        addAllPossibleMoves();
        return posMoves;
    }

}
