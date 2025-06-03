class Solution {
    private static final int MOD = 10007;
    public int solution(int n, int[] tops) {
        int[] dp = new int[2*n + 1];

        dp[0] = 1;
        dp[1] = tops[0] == 1 ? 3 : 2;
        dp[2] = dp[0] + dp[1];

        for (int i = 1; i < tops.length; i++) {
            int idx = 2 * i + 1;
            if (tops[i] == 1) {
                dp[idx] = (dp[idx - 1] * 2 + dp[idx - 2]) % MOD;
            } else {
                dp[idx] = (dp[idx - 1] + dp[idx - 2])%MOD;
            }
            dp[idx + 1] = (dp[idx] + dp[idx - 1])%MOD;
        }

        return dp[2 * n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 4;
        int[] tops = {1,1,0,1};
        System.out.println(sol.solution(n, tops)); // Example usage
    }
}
