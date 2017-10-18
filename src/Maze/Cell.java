package Maze;

public class Cell {
    int x = 0;
    int y = 0;
    boolean visited = false;
    char c;

    public Cell(int x, int y, boolean visited, char c) {
        this.x = x;
        this.y = y;
        this.visited = visited;
        this.c = c;
    }

}
