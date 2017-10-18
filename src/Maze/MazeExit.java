package Maze;

import Stack.StackArray.StackArray;

public class MazeExit {
    static char[][] maze = {
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '1', '1', '0', '0', '1', '1'},
            {'1', '0', '0', '1', '1', '0', '0', '1', '0', '1'},
            {'1', '0', '0', '0', '0', '0', '0', '1', '0', '1'},
            {'1', '0', '0', '0', '0', '1', '1', '0', '0', '1'},
            {'1', '0', '0', '1', '1', '1', '0', '0', '0', '1'},
            {'1', '0', '0', '0', '0', '1', '0', '1', '0', '1'},
            {'1', '0', '1', '1', '0', '0', '0', '1', '0', '1'},
            {'1', '1', '0', '0', '0', '0', '1', '0', '0', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}
    };

    public static void main(String[] args) {
        MazeExit mazeExit = new MazeExit();
        mazeExit.walkMaze(maze,8,8,1,7);

    }

    /**
     *
     * @param maze represent maze;
     * @param sx the start x-coordinate;
     * @param sy the start y-coordinate;
     * @param ex the end x-coordinate;
     * @param ey the end y-coordinate;
     */
    public void walkMaze(char[][] maze, int sx, int sy, int ex, int ey) {
//        first, create a maze;
        Cell[][] cells = createMaze(maze);
        printMaze(cells);
//        create stack to store information;
        StackArray stackArray = new StackArray();
        Cell startCell = cells[sx][sy];
        Cell endCell = cells[ex][ey];
        System.out.println(startCell == endCell);
        //endCell.c = '*';
        stackArray.push(startCell);
        startCell.visited = true;
        while (!stackArray.isEmpty()) {
            Cell current = (Cell) stackArray.peek(); //get the first object,not pop operation;
            if (current == endCell) { //
                while (!stackArray.isEmpty()) {
                    Cell tmp = (Cell) stackArray.pop();
                    tmp.c = '*';
                    /*while (!stackArray.isEmpty() && !isAdjoin(tmp, (Cell) stackArray.peek())) {
                        stackArray.pop();
                    }*/
                }
                System.out.println("Find path.");
                printMaze(cells);
                return;
            } else {  //if the current position is not the end cell,push the adjoin cell into stack;
                int x = current.x;
                int y = current.y;
                int count = 0;
                if (isValidCell(cells[x][y+1])) {
                    stackArray.push(cells[x][y+1]);
                    cells[x][y+1].visited = true;
                    count++;
                }
                if (isValidCell(cells[x+1][y])) {
                    stackArray.push(cells[x+1][y]);
                    cells[x+1][y].visited = true;
                    count++;
                }
                if (isValidCell(cells[x][y-1])) {
                    stackArray.push(cells[x][y-1]);
                    cells[x][y-1].visited = true;
                    count++;
                }
                if (isValidCell(cells[x-1][y])) {
                    stackArray.push(cells[x-1][y]);
                    cells[x-1][y].visited = true;
                    count++;
                }
                //当前位置的四周都是墙或者已经被访问过了；
                if (count == 0) {
                    stackArray.pop();
                }
            }
        }
        System.out.println("No path found.");
    }

    public boolean isValidCell(Cell cell) {
        if (cell.visited == false && cell.c == '0') {
            return true;
        }
        return false;
    }

    public Cell[][] createMaze(char[][] maze) {
        Cell[][] cells = new Cell[maze.length][];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                cells[i] = new Cell[maze[i].length];
            }
        }
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                cells[i][j] = new Cell(i,j,false,maze[i][j]);
            }
        }
        return cells;
    }

    public void printMaze(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j <cells[i].length; j++) {
                System.out.print(cells[i][j].c+" ");
            }
            System.out.println();
        }
    }

    public boolean isAdjoin(Cell c1, Cell c2) {
        if (c1.x == c2.x && Math.abs(c1.y - c2.y) < 2) {
            return true;
        }
        if (c1.y == c2.y && Math.abs(c1.x - c2.x) < 2) {
            return true;
        }
        return false;
    }
}
