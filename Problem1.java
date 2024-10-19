// Time Complexity : O(mxn) 
// Space Complexity : O(mxn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : used the same logic Jaspinder explained in the class.

package BFS-4;

public class Problem1 {
    public char[][] updateBoard(char[][] board, int[] click) {
        int [][] dirs = new int [][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        int m = board.length, n = board[0].length;
        //if user clicks on mine itself then change that to explod and finish the game.
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>(); //O(mxn)
        q.add(click);
        //make the block as revealed blank square 'B'
        board[click[0]][click[1]] = 'B';

        //bfs
        while(!q.isEmpty()){
            int [] cur = q.poll();
            int count = countMine(board, cur[0],cur[1], dirs);
            //process all the neighboring square since mine is not around
            if(count == 0){
                for(int [] dir: dirs){
                    int nr = dir[0] + cur[0];
                    int nc = dir[1] + cur[1];
                    //bound check
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'E'){
                        q.add(new int[]{nr,nc});
                        board[nr][nc] = 'B';
                    }
                }
            }else{
                board[cur[0]][cur[1]] = (char)(count + '0');
            }
        }
        return board;
    }

    private int countMine(char[][] board, int r, int c, int [][] dirs){
        int count = 0;
        for(int [] dir: dirs){
            int nr = dir[0] + r;
            int nc = dir[1] + c;
            //bound check
            if(nr >=0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count ;
    }
}
