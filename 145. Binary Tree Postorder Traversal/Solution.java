import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        traversal(root, ret);
        return ret;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        traversal(root.left, list);
        traversal(root.right, list);
        list.add(root.val);
    }
}
