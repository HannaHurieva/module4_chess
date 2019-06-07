package com.alevel.module4.chessboard;

import com.alevel.module4.enums.Player;
import com.alevel.module4.pieces.*;

import java.util.ArrayList;

public class Chessboard {

    private Piece pieces[][];

    private Position whiteKingPosition;
    private Position blackKingPosition;

    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;
    public Chessboard(){

    }

    protected void setPieces(Piece pieces[][]){
        this.pieces = pieces;
    }

    protected void setBlackPieces(ArrayList<Piece> bPieces){
        this.blackPieces = bPieces;
    }

    protected void setWhitePieces(ArrayList<Piece> wPieces){
        this.blackPieces = wPieces;
    }

    protected void setKingPosition(Position white, Position black){
        whiteKingPosition = white;
        blackKingPosition = black;
    }

    protected Piece[][] getPieces(){
        return pieces;
    }

    public ArrayList<Piece> getPieceList() {
        ArrayList<Piece> all = getPieceList(Player.BLACK);
        all.addAll(getPieceList(Player.WHITE));
        return all;
    }

    public ArrayList<Piece> getPieceList(Player player) {
        ArrayList<Piece> pieceList = new ArrayList<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (pieces[x][y] != null && pieces[x][y].getPieceColor()
                        == player) {
                    pieceList.add(pieces[x][y]);
                }
            }
        }
        return pieceList;
    }

    public Piece getPiece(Position pos) {
        return pieces[pos.getRank()][pos.getFile()];
    }

    public ArrayList<Move> getLegalMovesFrom(Position pos){
        ArrayList<Move> moves = new ArrayList<>();
        if(isOutOfBounds(pos)){
            return moves;
        }
        Piece piece = pieces[pos.getRank()][pos.getFile()];
        if(piece == null){
            return moves;
        }

        for(Move move : piece.getMoves()){
            if(!movesPlayerIntoCheck(move)){
                moves.add(move);
            }
        }
        return moves;
    }

    private ArrayList<Move> getLegalMoves(Piece piece){
        ArrayList<Move> moves = new ArrayList<>();

        if(piece == null){
            return moves;
        }

        for(Move move: piece.getMoves()){
            if(!movesPlayerIntoCheck(move)){
                moves.add(move);
            }
        }
        return moves;
    }


    public ArrayList<Move> getLegalMoves(Player player){
        ArrayList<Move> moves = new ArrayList<>();
        for(Piece piece: getPieceList(player)){
            moves.addAll(getLegalMoves(piece));
        }
        return moves;
    }

    /**
     * Updates a move on the chessboard piece array. Does not enforce any rules!
     *
     * @param move
     */
    public void updateMove(Move move) {
        Piece movedPiece = pieces[move.getStartPos().getRank()][move.getStartPos().getFile()];
        movedPiece.updateCurrentPos(move.getEndPos());

        pieces[move.getStartPos().getRank()][move.getStartPos().getFile()] = null;

        pieces[move.getEndPos().getRank()][move.getEndPos().getFile()] = movedPiece;
        if(movedPiece instanceof King){
            updateKingPosition(move);
        }
        if(movedPiece instanceof Pawn){
            checkPawnPromotion(move);
        }
    }

    private void updateCastlingMove(Move move){
        int kingX = move.getStartPos().getRank();
        int kingY = move.getEndPos().getFile();
        Piece king = pieces[kingX][kingY];
        int towerX = move.getEndPos().getRank();
        int towerY = move.getEndPos().getFile();
        Piece tower = pieces[towerX][towerY];

        pieces[kingX][kingY] = null;
        pieces[towerX][towerY] = null;

        if(towerY > kingY){ //Queenside
            Position kPos = new Position(kingX,kingY-2 );
            king.updateCurrentPos(kPos);
        }

    }

    private void checkPawnPromotion(Move move) {
        int rank = move.getEndPos().getRank();
        if (rank == 7 || rank == 0) {
            Player pieceColor = move.getMovingPiece().getPieceColor();
            Queen queen = new Queen(pieceColor, move.getEndPos(), this);
            int file = move.getEndPos().getFile();
            pieces[rank][file] = queen;
            move.setMovingPiece(queen);
        }
    }

    private void updateKingPosition(Move move){
        Piece piece = move.getMovingPiece();
        if(piece.getPieceColor() == Player.WHITE){
            whiteKingPosition = move.getEndPos();
        } else {
            blackKingPosition = move.getEndPos();
        }
    }

    public boolean containsEnemy(Position startPos, Position endPos) {
        if (isOutOfBounds(endPos)) {
            return false;
        }
        if (isFree(endPos)) {
            return false;
        }
        Player movingPieceColor = pieces[startPos.getRank()][startPos.getFile()].getPieceColor();

        Player receivingPieceColor = pieces[endPos.getRank()][endPos.getFile()].getPieceColor();

        return (movingPieceColor != receivingPieceColor);

    }

    public boolean isFree(Position endPos) {
        if (isOutOfBounds(endPos)) {
            return false;
        }
        return (pieces[endPos.getRank()][endPos.getFile()] == null);
    }

    public boolean isOutOfBounds(Position endPos) {
        return endPos.getRank() >= 8 || endPos.getRank() < 0
                || endPos.getFile() >= 8 || endPos.getFile() < 0;
    }

    // Retrives the other players possible moves and checks if he can hit the king
    public boolean isInCheck(Player player) {
        Position kingPos = kingPosition(player);

        Player opponent = switchPlayer(player);
        ArrayList<Piece> pieceList = getPieceList(opponent);
        for (Piece p : pieceList) {
            for (Move m : p.getMoves()) {
                if (m.getEndPos().equals(kingPos)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Player switchPlayer(Player player){
        if(player == Player.WHITE){
            return Player.BLACK;
        } else {
            return Player.WHITE;
        }
    }

    public Position kingPosition(Player player) {
        if(player == Player.WHITE){
            return whiteKingPosition;
        } else {
            return blackKingPosition;
        }
    }

    public boolean isUnmovedTower(Position pos){
        Piece piece = pieces[pos.getRank()][pos.getFile()];
        if(!(piece instanceof Tower)){
            return false;
        }
        Tower tower = (Tower) piece;
        if(tower.isMoved()){
            return false;
        }
        return true;
    }

    public boolean isValidMove(Move move) {

        if (!isIsolatedValidMove(move)) {
            return false;
        }

        if (movesPlayerIntoCheck(move)) {
            return false;
        }
        return true;
    }

    public boolean movesPlayerIntoCheck(Move move) {
        Player player = move.getMovingPiece().getPieceColor();
        Chessboard newBoard = ChessboardBuilder.copy(this);
        newBoard.updateMove(move);
        if (newBoard.isInCheck(player)) {
            return true;
        }
        return false;
    }

    private boolean isIsolatedValidMove(Move move) {
        Piece piece = move.getMovingPiece();
        Position candidate = move.getEndPos();
        //Check that the move is in the possible moves list
        for (Move m : piece.getMoves()) {
            Position endPos = m.getEndPos();
            if (candidate.equals(endPos)) {
                return true;
            }
        }
        return false;
    }

}