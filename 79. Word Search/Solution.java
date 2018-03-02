public class Solution {

    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) {
            return true;
        }
        if (board.length == 0 || board[0].length == 0) {
            return false;
        }
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (exist(board, i, j, words, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int i, int j, char[] words, int idx) {
        if (idx == words.length) {
            return true;
        }
        if (i < 0 || j < 0 || i == board.length || j == board[i].length) {
            return false;
        }
        if (board[i][j] != words[idx]) {
            return false;
        }
        board[i][j] = '\0';
        boolean exist = exist(board, i + 1, j, words, idx + 1) ||
                exist(board, i - 1, j, words, idx + 1) ||
                exist(board, i, j + 1, words, idx + 1) ||
                exist(board, i, j - 1, words, idx + 1);
        board[i][j] = words[idx];
        return exist;
    }
}
