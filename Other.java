import java.util.*;

public class Other {

    public static String[] findRelativeRanks(int[] nums) {
        int[][] pair = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            pair[i][0] = nums[i];
            pair[i][1] = i;
        }

        Arrays.sort(pair, (a, b) -> (b[0] - a[0]));

        String[] result = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                result[pair[i][1]] = "Gold Medal";
            } else if (i == 1) {
                result[pair[i][1]] = "Silver Medal";
            } else if (i == 2) {
                result[pair[i][1]] = "Bronze Medal";
            } else {
                result[pair[i][1]] = (i + 1) + "";
            }
        }

        return result;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }
        for (int i : count) {
            if (i != 0) return false;
        }
        return true;
    }

    public static int romanToInt(String s) {
        int sum = 0;
        if (s.contains("IV")) {
            sum -= 2;
        }
        if (s.contains("IX")) {
            sum -= 2;
        }
        if (s.contains("XL")) {
            sum -= 20;
        }
        if (s.contains("XC")) {
            sum -= 20;
        }
        if (s.contains("CD")) {
            sum -= 200;
        }
        if (s.contains("CM")) {
            sum -= 200;
        }

        char c[] = s.toCharArray();
        int count = 0;

        for (; count <= s.length() - 1; count++) {
            if (c[count] == 'M') sum += 1000;
            if (c[count] == 'D') sum += 500;
            if (c[count] == 'C') sum += 100;
            if (c[count] == 'L') sum += 50;
            if (c[count] == 'X') sum += 10;
            if (c[count] == 'V') sum += 5;
            if (c[count] == 'I') sum += 1;

        }

        return sum;
    }

    public static int numberOfBoomerangs(int[][] points) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }

                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];

                int d = dx * dx + dy * dy;

                map.put(d, map.getOrDefault(d, 0) + 1);
            }

            for (int val : map.values()) {
                res += val * (val - 1);
            }
            map.clear();
        }
        return res;
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) >>> 1;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, right);
        return root;
    }

    public static int maxProfit(int[] prices) {
        int res = 0, buy = Integer.MAX_VALUE;
        for (int price : prices) {
            buy = Math.min(buy, price);
            res = Math.max(res, price - buy);
        }
        return res;
    }

    public static boolean isHappy(int n) {
        Set<Integer> s = new HashSet<>();

        while (n != 1) {
            int t = 0;
            while (n > 0) {
                t += (n % 10) * (n % 10);
                n /= 10;
            }
            n = t;
            if (s.contains(n)) {
                break;
            } else {
                s.add(n);
            }
        }

        return n == 1;
    }

    public static int findLHS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Map<Long, Integer> map = new HashMap<>();
        for (long num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int result = 0;
        for (long key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                result = Math.max(result, map.get(key + 1) + map.get(key));
            }
        }
        return result;
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        return s != null && (isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t));
    }

    private static boolean isSame(TreeNode s, TreeNode t) {
        return s == null && t == null || s != null && t != null && s.val == t.val && isSame(s.left, t.left) && isSame(s.right, t.right);
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int count = queue.size();
            for (int i = 0; i < count; ++i) {
                TreeNode node = queue.poll();

                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(0, level);
        }
        return ret;
    }

    public static TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        if (root.val < L) {
            return trimBST(root.right, L, R);
        }

        if (root.val > R) {
            return trimBST(root.left, L, R);
        }

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }

    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private static boolean isMirror(TreeNode left, TreeNode right) {
        return left == null && right == null || left != null && right != null
                && left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public static int findSecondMinimumValue(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        dfs(root, set);

        int count = 0;
        for (Integer i : set) {
            count++;
            if (count == 2) {
                return i;
            }
        }

        return -1;
    }

    private static void dfs(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
    }

    public static int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        return helper(root, 0, sum, preSum);
    }

    public static int helper(TreeNode root, int currSum, int target, Map<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }

        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }

    public static boolean isIsomorphic(String s, String t) {
        int[] c1 = new int[256], c2 = new int[256];

        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (c1[s.charAt(i)] != c2[t.charAt(i)]) {
                return false;
            }
            c1[s.charAt(i)] = i + 1;
            c2[t.charAt(i)] = i + 1;
        }
        return true;
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static boolean isBalanced(TreeNode root) {
        return root == null || Math.abs(getDepth(root.left) - getDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }

    public static String addBinary(String a, String b) {
        if (a == null || a.isEmpty()) {
            return b;
        }
        if (b == null || b.isEmpty()) {
            return a;
        }

        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();

        int i = ac.length - 1;
        int j = bc.length - 1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        Stack<Integer> stack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;
        stack.push(slow.val);
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            stack.push(slow.val);
        }
        if (fast.next == null) {
            stack.pop();
        }

        while (slow.next != null) {
            slow = slow.next;
            int temp = stack.pop();
            if (temp != slow.val) {
                return false;
            }
        }

        return true;
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public static int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    public static int findLengthOfLCIS(int[] nums) {
        int res = 0, cnt = 0, cur = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > cur) ++cnt;
            else cnt = 1;
            res = Math.max(res, cnt);
            cur = num;
        }
        return res;
    }

    public static int countBinarySubstrings(String s) {
        int[] groups = new int[s.length()];
        groups[0] = 1;
        int c = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) != s.charAt(i)) {
                groups[++c] = 1;
            } else {
                groups[c]++;
            }
        }

        int ret = 0;
        for (int i = 1; i <= c; i++) {
            ret += Math.min(groups[i - 1], groups[i]);
        }
        return ret;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        int length = nums.length;
        if (length == 0) return null;
        int max = Integer.MIN_VALUE;
        int idx = 0;
        for (int i = 0; i < length; i++) {
            if (max < nums[i]) {
                max = nums[i];
                idx = i;
            }
        }

        TreeNode node = new TreeNode(max);
        int[] leftNums = Arrays.copyOfRange(nums, 0, idx);
        int[] rightNums = Arrays.copyOfRange(nums, idx + 1, length);
        node.left = constructMaximumBinaryTree(leftNums);
        node.right = constructMaximumBinaryTree(rightNums);
        return node;
    }

    public static int findBottomLeftValue(TreeNode root) {
        if (root == null) return 0;
        int ret = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int s = queue.size();
            for (int i = 0; i < s; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    ret = node.val;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return ret;
    }

    public static int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            left.putIfAbsent(v, i);
            right.put(v, i);
            count.put(v, count.getOrDefault(v, 0) + 1);
        }
        int ret = nums.length;
        int degree = Collections.max(count.values());
        for (int k : count.keySet()) {
            if (count.get(k) == degree) {
                ret = Math.min(ret, right.get(k) - left.get(k) + 1);
            }
        }
        return ret;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int c = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            nums1[c--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0) {
            nums1[c--] = nums2[n--];
        }
    }

    public static int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE, second = Long.MIN_VALUE, third = Long.MIN_VALUE;
        for (int num : nums) {
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num > second && num < first) {
                third = second;
                second = num;
            } else if (num > third && num < second) {
                third = num;
            }
        }
        return (int) ((third == Long.MIN_VALUE || third == second) ? first : third);
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if (root != null) {
            dfs(root, "", ret);
        }
        return ret;
    }

    private static void dfs(TreeNode root, String path, List<String> ret) {
        path += String.valueOf(root.val);
        if (root.left == null && root.right == null) {
            ret.add(path);
        } else {
            if (root.left != null) {
                dfs(root.left, path + "->", ret);
            }
            if (root.right != null) {
                dfs(root.right, path + "->", ret);
            }
        }
    }

    public static void rotate(int[] nums, int k) {

    }
}
