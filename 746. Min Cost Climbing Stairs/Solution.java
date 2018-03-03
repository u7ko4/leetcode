public class Solution {

     public int minCostClimbingStairs(int[] cost) {
        int a = 0;
        int b = 0;
        for (int num : cost){
            int temp = Math.min(a, b) + num;
            a = b;
            b = temp;
        }
        return Math.min(a, b);
    }
}
