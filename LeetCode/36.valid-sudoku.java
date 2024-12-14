/*
                                  https://leetcode.com/problems/valid-sudoku/
                                                        
                                                36. Valid Sudoku
                                    Medium │ 11081  1166  │ 61.1% of 2.9M



Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

	1. Each row must contain the digits 1-9 without repetition.
	
	2. Each column must contain the digits 1-9 without repetition.
	
	3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.

Note:

	* A Sudoku board (partially filled) could be valid but is not necessarily solvable.
	
	* Only the filled cells need to be validated according to the mentioned rules.



󰛨 Example 1:

[img](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)

	│ Input: board = 
	│ [["5","3",".",".","7",".",".",".","."]
	│ ,["6",".",".","1","9","5",".",".","."]
	│ ,[".","9","8",".",".",".",".","6","."]
	│ ,["8",".",".",".","6",".",".",".","3"]
	│ ,["4",".",".","8",".","3",".",".","1"]
	│ ,["7",".",".",".","2",".",".",".","6"]
	│ ,[".","6",".",".",".",".","2","8","."]
	│ ,[".",".",".","4","1","9",".",".","5"]
	│ ,[".",".",".",".","8",".",".","7","9"]]
	│ Output: true

󰛨 Example 2:

	│ Input: board = 
	│ [["8","3",".",".","7",".",".",".","."]
	│ ,["6",".",".","1","9","5",".",".","."]
	│ ,[".","9","8",".",".",".",".","6","."]
	│ ,["8",".",".",".","6",".",".",".","3"]
	│ ,["4",".",".","8",".","3",".",".","1"]
	│ ,["7",".",".",".","2",".",".",".","6"]
	│ ,[".","6",".",".",".",".","2","8","."]
	│ ,[".",".",".","4","1","9",".",".","5"]
	│ ,[".",".",".",".","8",".",".","7","9"]]
	│ Output: false
	│ Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.



 Constraints:

	* board.length == 9
	
	* board[i].length == 9
	
	* board[i][j] is a digit 1-9 or '.'.

 * */
// @leet start

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        return checkRow(board) && checkCol(board) && checkBox(board);
    }

    public boolean checkRow(char[][] board) {
        Set<Character> set = new HashSet<>();
        int beforeCnt = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == '.') {
                    continue;
                }

                set.add(board[i][j]);

                if (beforeCnt == set.size()) {
                    return false;
                }

                beforeCnt = set.size();
            }

            beforeCnt = 0;
            set.clear();
        }
        return true;
    }

    public boolean checkCol(char[][] board) {
        Set<Character> set = new HashSet<>();
        int beforeCnt = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[j][i] == '.') {
                    continue;
                }

                set.add(board[j][i]);

                if (beforeCnt == set.size()) {
                    return false;
                }

                beforeCnt = set.size();

            }

            beforeCnt = 0;
            set.clear();
        }
        return true;
    }

    public boolean checkBox(char[][] board) {
        Set<Character> set = new HashSet<>();
        int beforeCnt = 0;
        int col = 0;
        int row = 0;
        for (int t = 0; t < 3; t++) {
            for (int k = 0; k < 3; k++) {
                for (int i = col; i < col + 3; i++) {
                    for (int j = row; j < row + 3; j++) {

                        if (board[i][j] == '.') {
                            continue;
                        }

                        set.add(board[i][j]);

                        if (beforeCnt == set.size()) {
                            return false;
                        }

                        beforeCnt = set.size();
                    }
                }

                beforeCnt = 0;
                set.clear();

                row += 3;
            }

            col += 3;
            row = 0;
        }

        return true;
    }
}
// @leet end
