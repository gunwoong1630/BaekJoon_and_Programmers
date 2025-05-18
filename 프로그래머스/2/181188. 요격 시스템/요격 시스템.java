import java.util.Arrays;
import java.util.Comparator;

class Main {
    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(
                        new int[][]{{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 13}, {3, 7}, {1, 4}}
                )
        );
    }
}

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, Comparator.comparingInt(o -> o[0]));
//        for (int[] target : targets) {
//            System.out.println(Arrays.toString(target));
//        }
        int l = targets[0][0];
        int r = targets[0][1];
        answer++;

        for (int i = 1; i < targets.length; i++) {
            if (targets[i][0] < r) {
//                l = targets[i][0];
                r = Math.min(r, targets[i][1]);
            } else {
//                l = targets[i][0];
                r = targets[i][1];
                answer++;
            }
        }
        return answer;
    }
}



