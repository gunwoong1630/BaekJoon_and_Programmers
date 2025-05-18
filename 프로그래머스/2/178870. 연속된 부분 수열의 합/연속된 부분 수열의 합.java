import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new Solution().solution(
                        new int[]{1, 2, 3, 4, 5}, 7
                ))
        );
    }
}

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] range = null;
        int[] sum = new int[sequence.length + 1];
        for (int i = 0; i < sequence.length; i++) {
            sum[i + 1] = sum[i] + sequence[i];
        }
        int i = 0;
        int j = 1;
        while (i <= sequence.length && j <= sequence.length) {
            int tmp = sum[j] - sum[i];
            if (tmp < k) {
                j++;
            } else {
                if (tmp == k) {
                    if (range == null || (range != null && (range[1] - range[0] > j - i)) || (range != null && (range[1] - range[0] == j - i) && range[0] > i)) {
                        range = new int[]{i, j};
                    }
                }
                i++;
            }
        }

        return new int[]{range[0], range[1] - 1};
    }

}

