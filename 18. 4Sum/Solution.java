import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        return kSum(nums, target, 4);
    }

    private List<List<Integer>> kSum(int[] nums, int target, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < k || k < 2) return res;
        Arrays.sort(nums);
        sum(nums, target, k, 0, res, new ArrayList<>());
        return res;
    }

    private void sum(int[] nums, int target, int k, int start, List<List<Integer>> res, List<Integer> path) {
        int max = nums[nums.length - 1];
        if (k * nums[start] > target || k * max < target) return;
        if (k == 2) {
            int l = start, r = nums.length - 1;
            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum == target) {
                    res.add(new ArrayList<>(path));
                    res.get(res.size() - 1).addAll(Arrays.asList(nums[l], nums[r]));
                    l++;
                    r--;
                    // Remove duplicated
                    while (l < r && nums[l] == nums[l - 1]) l++;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                } else if (sum < target) l++;
                else r--;
            }
        } else {
            for (int i = start; i < nums.length - k + 1; i++) {
                // Check duplicated
                if (i > start && nums[i] == nums[i - 1]) continue;
                if (nums[i] + max * (k - 1) < target) continue;
                int sum = nums[i] * k;
                if (sum > target) break;
                if (sum == target) {
                    if (nums[i + k - 1] == nums[i]) {
                        res.add(new ArrayList<>(path));
                        List<Integer> temp = new ArrayList<>();
                        for (int x = 0; x < k; x++) temp.add(nums[i]);
                        res.get(res.size() - 1).addAll(temp);
                    }
                    break;
                }
                path.add(nums[i]);
                sum(nums, target - nums[i], k - 1, i + 1, res, path);
                path.remove(path.size() - 1);
            }
        }
    }
}