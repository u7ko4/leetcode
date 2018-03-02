public class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(result, root);
        return result;
    }

    public void traversal(List<Integer> ret, TreeNode node) {
        if (node == null) {
            return;
        }
        traversal(ret, node.left);
        ret.add(node.val);
        traversal(ret, node.right);
    }
}