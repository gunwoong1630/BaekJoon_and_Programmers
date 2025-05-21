import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    private int[][] move = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int ans = Integer.MAX_VALUE;

    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();

        int[] robot = new int[3];
        int[] goal = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    robot = new int[]{i, j, 0};
                } else if (board[i].charAt(j) == 'G') {
                    goal = new int[]{i, j};
                }
            }
        }

        boolean[][] visit = new boolean[n][m];
        visit[robot[0]][robot[1]] = true;
        Queue<int[]> needVisit = new ArrayDeque<>();
        needVisit.add(robot);
        while (!needVisit.isEmpty()) {
            int[] now = needVisit.poll();
            if (now[0] == goal[0] && now[1] == goal[1]) {
                ans = Math.min(ans, now[2]);
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int ny = now[0];
                int nx = now[1];
                while (ny + move[i][0] > -1 && ny + move[i][0] < n && nx + move[i][1] > -1 && nx + move[i][1] < m && board[ny + move[i][0]].charAt(nx + move[i][1]) != 'D') {
                    ny += move[i][0];
                    nx += move[i][1];
                }
                if (!visit[ny][nx]) {
                    visit[ny][nx] = true;
                    needVisit.add(new int[]{ny, nx, now[2] + 1});
                }
            }
        }
//        for (boolean[] booleans : visit) {
//            System.out.println(Arrays.toString(booleans));
//        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."}
        ));
    }
}
