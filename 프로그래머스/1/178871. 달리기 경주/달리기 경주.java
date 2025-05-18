import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Main {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new Solution().solution(
                        new String[]{"mumu", "soe", "pow", "kai", "mine"},
                        new String[]{"kai", "kai", "mine", "mine"}
                ))
        );
    }
}

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = players.clone();
        Map<String, Integer> order = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            order.put(players[i], i);
        }
        for (String calling : callings) {
            int rank = order.get(calling);

            String front = answer[rank - 1];

            swap(answer, rank - 1, rank);

            order.put(calling, order.get(calling) - 1);
            order.put(front, order.get(front) + 1);
        }
        return answer;
    }

    private void swap(String[] target, int i, int j) {
        String tmp = target[i];
        target[i] = target[j];
        target[j] = tmp;
    }
}


