import java.util.Map;

class Solution {
    private int[][] efficiency = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    private Map<String, Integer> pickMap = Map.of("diamond", 0, "iron", 1, "stone", 2);
    private int ans = Integer.MAX_VALUE;
    private String[] minerals;

    public int solution(int[] picks, String[] minerals) {
        this.minerals = minerals;
        dig(0, picks[0], picks[1], picks[2], 0);
        return ans;
    }

    private void dig(int depth, int remainDiamondPickCnt, int remainIronPickCnt, int remainStonePickCnt, int fatigue) {
//        System.out.println("depth = " + depth + ", remainDiamondPickCnt = " + remainDiamondPickCnt + ", remainIronPickCnt = " + remainIronPickCnt + ", remainStonePickCnt = " + remainStonePickCnt + ", fatigue = " + fatigue);
        if (depth == minerals.length || (remainDiamondPickCnt == 0 && remainIronPickCnt == 0 && remainStonePickCnt == 0)) {
            ans = Math.min(ans, fatigue);
            return;
        }
        int nextDepth = Math.min(depth + 5, minerals.length);
        if (remainDiamondPickCnt > 0) {
            dig(nextDepth, remainDiamondPickCnt - 1, remainIronPickCnt, remainStonePickCnt, digWithPick(depth, nextDepth,0, fatigue));
        }
        if (remainIronPickCnt > 0) {
            dig(nextDepth, remainDiamondPickCnt, remainIronPickCnt - 1, remainStonePickCnt, digWithPick(depth, nextDepth, 1,fatigue));
        }
        if (remainStonePickCnt > 0) {
            dig(nextDepth, remainDiamondPickCnt, remainIronPickCnt, remainStonePickCnt - 1, digWithPick(depth, nextDepth,2, fatigue));
        }
    }

    private int digWithPick(int depth,int nextDepth,int pickType,int fatigue) {
        int tmp = fatigue;
        for (int i = depth; i < nextDepth; i++) {
            tmp += efficiency[pickType][pickMap.get(minerals[i])];
        }
        return tmp;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{1, 3, 2}, new String[]{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"}));;
    }
}
