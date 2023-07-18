public class Main {
    public static void main(String[] args) {
        int[][] chessBoard = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        printChessBoard(chessBoard);
        if(solveQueens(chessBoard)) {
            System.out.println("solvable board");
        }
        else {
            System.out.println("unsolvable board");
        }
        solveQueens(chessBoard);
        printChessBoard(chessBoard);

//        int[][] board = {
//                {7, 0, 2, 0, 5, 0, 6, 0, 0},
//                {0, 0, 0, 0, 0, 3, 0, 0, 0},
//                {1, 0, 0, 0, 0, 9, 5, 0, 0},
//                {8, 0, 0, 0, 0, 0, 0, 9, 0},
//                {0, 4, 3, 0, 0, 0, 7, 5, 0},
//                {0, 9, 0, 0, 0, 0, 0, 0, 8},
//                {0, 0, 9, 7, 0, 0, 0, 0, 5},
//                {0, 0, 0, 2, 0, 0, 0, 0, 0},
//                {0, 0, 7, 0, 4, 0, 2, 0, 3}
//        };
//
//        printBoard(board);
//        if(solveBoard(board)) {
//            System.out.println("solved successfully");
//        }
//        else {
//            System.out.println("unsolvable board");
//        }
//        printBoard(board);
    }

    public static void printChessBoard(int[][] board) {
        for(int i=0; i<8; i++) {
            for(int k=0; k<8; k++) {
                if(k != 0) {
                    System.out.print("|");
                }
                System.out.print(board[i][k]);
            }
            System.out.println();
        }
    }

    public static boolean checkRow(int[][] board, int row) {
        for(int i=0; i<8; i++) {
            if(board[row][i] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkCol(int[][] board, int col) {
        for(int i=0; i<8; i++) {
            if(board[i][col] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagonal1(int[][] board, int row, int col) {
        int localRow = row;
        int localCol = col;
        if(row >= col) {
            while(localCol != 0) {
                localRow--;
                localCol--;
            }
        }
        else {
            while(localRow != 0) {
                localRow--;
                localCol--;
            }
        }
        if(localRow >= localCol) {
            for(int i=localRow, j=localCol; i<8; i++, j++) {
                if(board[i][j] == 1) {
                    return false;
                }
            }
        }
        else {
            for(int i=localRow, j=localCol; j<8; i++, j++) {
                if(board[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkDiagonal2(int[][] board, int row, int col) {
        int localRow = row;
        int localCol = col;
        if(row <= 7 - col) {
            while(localRow != 0) {
                localRow--;
                localCol++;
            }
        }
        else {
            while(localCol != 7) {
                localRow--;
                localCol++;
            }
        }
        if(7 - row <= col) {
            for(int i=localRow, j=localCol; i<8; i++, j--) {
                if(board[i][j] == 1) {
                    return false;
                }
            }
        }
        else {
            for(int i=localRow, j=localCol; j>0; i++, j--) {
                if(board[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValidPlacement(int[][] board, int row, int col) {
        return checkRow(board, row) &&
                checkCol(board, col) &&
                checkDiagonal1(board, row, col) &&
                checkDiagonal2(board, row, col);
    }

    public static boolean solveQueens(int[][] board) {
        for(int i=0; i<8; i++) {
            for(int k=0; k<8; k++) {
                if(board[i][k] == 0) {
                    if(isValidPlacement(board, i, k)) {
                        board[i][k] = 1;
                        if(solveQueens(board) && check8(board)) {
                            return true;
                        }
                        else {
                            board[i][k] = 0;
                        }
                    }
                }
                if(board[i][k] == 1) {
                    board[i][k] = 0;
                    if(!isValidPlacement(board, i, k)) {
                        board[i][k] = 1;
                        return false;
                    }
                    else {
                        board[i][k] = 1;
                    }
                }
            }
        }
        return true;
    }

    public static boolean check8(int[][] board) {
        for (int[] row : board) {
            boolean containsOne = false;
            for (int value : row) {
                if (value == 1) {
                    containsOne = true;
                    break;
                }
            }
            if (!containsOne) {
                return false;
            }
        }
        return true;
    }

    public static void printBoard(int[][] board) {
        for(int row=0; row<9; row++) {
            if(row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for(int col=0; col<9; col++) {
                if(col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

    public static boolean isNumInRow(int[][] board, int num, int row) {
        for(int i=0; i<9; i++) {
            if(board[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumInCol(int[][] board, int num, int col) {
        for(int i=0; i<9; i++) {
            if(board[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumInSquare(int[][] board, int num, int row, int col) {
        int localSquareRow = row - row % 3;
        int localSquareCol = col - col % 3;
        for(int i = localSquareRow; i < localSquareRow + 3; i++) {
            for(int k = localSquareCol; k < localSquareCol + 3; k++) {
                if(board[i][k] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValid(int[][] board, int num, int row, int col) {
        return !isNumInRow(board, num, row) &&
                !isNumInCol(board, num, col) &&
                !isNumInSquare(board, num, row, col);
    }

    public static boolean solveBoard(int[][] board) {
        for(int row=0; row<9; row++) {
            for(int col=0; col<9; col++) {
                if(board[row][col] == 0) {
                    for(int numToTry=1; numToTry<10; numToTry++) {
                        if(isValid(board, numToTry, row, col)) {
                            board[row][col] = numToTry;
                            if(solveBoard(board)) {
                                return true;
                            }
                            else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}