package com.alevel.module4.chessboard;

public class Position {
    private int rank;
    private int file;

    public Position(int rank, int file){
        this.rank = rank;
        this.file = file;
    }

    public Position(Position other){
        this.rank = other.rank;
        this.file = other.file;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public boolean equals(Position other){
        return (this.rank == other.rank) && (this.file == other.file);
    }

}
