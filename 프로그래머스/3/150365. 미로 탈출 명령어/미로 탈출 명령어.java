import java.util.Map;

// d(아래) -> l(왼쪽) -> r(오른쪽) -> u(위)
class Solution {
	private int n;
	private int m;
	private String answer;
	private static int[][] MOVE = new int[][] {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
	private static Map<Integer, String> MAPPER = Map.of(0, "d", 1, "l", 2, "r", 3, "u");

	public String solution(int n, int m, int x, int y, int r, int c, int k) {
		this.n = n;
		this.m = m;
		this.answer = "u".repeat(k);
		int tmp = Math.abs(x - r) + Math.abs(y - c);
		if (tmp > k || (k - tmp) % 2 != 0) {
			return "impossible";
		}

		dfs(x - 1, y - 1, r - 1, c - 1, "", k);
		return answer;
	}

	private void dfs(int x, int y, int r, int c, String path, int k) {
		if (getDist(x, y, r, c) > k || path.compareTo(answer) > 0) {
			return;
		}
		if (k == 0 && x == r && y == c) {
			answer = path;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + MOVE[i][0];
			int ny = y + MOVE[i][1];
			if (-1 < nx && nx < n && -1 < ny && ny < m) {
				dfs(nx, ny, r, c, path + MAPPER.get(i), k - 1);
			}
		}
	}

	private int getDist(int x, int y, int r, int c) {
		return Math.abs(x - r) + Math.abs(y - c);
	}

	// public static void main(String[] args) {
	// 	String solution = new Solution().solution(3, 4, 2, 3, 3, 1, 5);
	// 	System.out.println(solution);
	// }

}