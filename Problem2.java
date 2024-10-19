// Time Complexity : O(nxn)
// Space Complexity : O(nxn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : used the same logic Jaspinder explained in the class.

package BFS-4;

public class Problem2 {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int [] arr = new int[n*n]; // O(nxn)
        int idx =0;
        int r = n-1;
        int c =0;
        boolean flag = true; //left to right

        //convert board to 1d array
        while(idx < arr.length){
            if(board[r][c] == -1){
                arr[idx] = board[r][c];
            }else{
                arr[idx] = board[r][c] - 1;
            }
            idx++;

            //new row and coloum
            if(flag){
                c++;
                if(c == n){
                    c--;
                    r--;
                    flag = false;
                }
            }else{
                c--;
                if(c < 0){
                    c++;
                    r--;
                    flag = true;
                }        
            }
        }

        //bfs
        Queue<Integer> q = new LinkedList<>(); //O(nxn) // in worst case q can have all 36 elements
        HashSet<Integer> visited = new HashSet<>(); //O(nxn)
        q.add(0);
        visited.add(0);
        int count = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int cur = q.poll();
                //roll the dice
                for(int j = 1; j <=6; j++){
                    int baby = cur + j;
                    if(baby >= arr.length) break;
                    if(arr[baby] == -1){
                        if(baby == n*n - 1) return count + 1; 
                        if(!visited.contains(baby)){
                            visited.add(baby);
                            q.add(baby);
                        }
                    }else{
                        if(arr[baby] == n*n - 1) return count + 1;
                        if(!visited.contains(arr[baby])){
                            visited.add(arr[baby]);
                            q.add(arr[baby]);
                        }
                    }
                }
            }
            count ++;
        }
        return -1;
    }
}
