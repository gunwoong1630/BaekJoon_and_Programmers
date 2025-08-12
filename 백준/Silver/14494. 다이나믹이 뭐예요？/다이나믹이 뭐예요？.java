import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 1 1
// 1 3
// 1 5
public class Main {
	private static final int MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		long[][] dp = new long[n][m];
		Arrays.fill(dp[0], 1);
		for (int i = 1; i < n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j < m; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1] + dp[i - 1][j - 1]) % MOD;
			}
		}
		System.out.println(dp[n-1][m-1]);
	}
}

