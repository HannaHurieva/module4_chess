package com.alevel.module4.chessboard;

import com.alevel.module4.pieces.Piece;

public class Move {
    private Position startPos;
    private Position endPos;
    private Piece movingPiece;
    private boolean castling;

    public Move(Position startPos, Position endPos, Piece movingPiece){
        this.startPos = startPos;
        this.endPos = endPos;
        this.movingPiece = movingPiece;
    }

    //For castling
    public Move(Position startPos, Position endPos){
        this.startPos = startPos;
        this.endPos = endPos;
        castling = true;
    }

    public Position getEndPos(){
        return endPos;
    }
    public Position getStartPos(){
        return startPos;
    }

    public Piece getMovingPiece(){
        return movingPiece;
    }

    public void setMovingPiece(Piece piece){
        movingPiece = piece;
    }

    @Override
    public String toString(){
        String result = positionToString(startPos) + "->" + positionToString(endPos);
        return result;
    }

    private String positionToString(Position pos){
        char letter = (char) (pos.getFile() + 65);
        String result = letter + "" + (pos.getRank()+1);
        return result;
    }

}
