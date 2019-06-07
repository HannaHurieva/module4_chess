package com.alevel.module4.persistence;

public class Coordinate {
    int positionX;
    int positionY;

    public Coordinate(int x, int y) {
        positionX = x;
        positionY = y;
    }

    // Creates a coordinate from a UCI move string
    public Coordinate(String coordinate) {
        positionX = (char) coordinate.toCharArray()[0] - 97;
        positionY = Integer.parseInt(coordinate.substring(1, 2)) - 1;
    }

    public boolean isValid() {
        if ((positionX >= 0 && positionX < 8)
                && (positionY >= 0 && positionY < 8)) {
            return true;
        }
        return false;
    }

    public int getX() {
        return positionX;
    }

    public int getY() {
        return positionY;
    }

    public void setX(int x) {
        positionX = x;
    }

    public void setY(int y) {
        positionY = y;
    }

    public String toString() {
        return Integer.toString(positionX) + "," + Integer.toString(positionY);
    }

    // Checks if two coordinates are equal. The x and y coordinates should be
    public boolean equals(Coordinate coordinate) {
        if ((positionX == coordinate.getX())
                && (positionY == coordinate.getY())) {
            return true;
        }
        return false;
    }

    // This converts the coordinate to UCI chess notation
    public String getParsedCoordinate() {
        String parsedString = "";
        parsedString = (char) (positionX + 97)
                + Integer.toString(positionY + 1);
        return parsedString;
    }
}