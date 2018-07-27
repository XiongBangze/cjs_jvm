package leetcode;

public class sixEightSeven {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left =new TreeNode(2);
        System.out.println(longestUnivaluePath(root));
    }

    public static int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] longestPath = new int[1];
        getUnivaluePathCount(root, longestPath);
        return longestPath[0];
    }

    private static int getUnivaluePathCount(TreeNode node, int[] longestPath) {
        int left = 0;
        if (node.left != null) {
            left += getUnivaluePathCount(node.left, longestPath);
            if (node.val == node.left.val) {
                /* 如果本节点和左节点值相同，路径能继续，再累加1 */
                left += 1;
            } else {
                /* 否则，就说明左边的路径已经断开，本节点的左方向的单向路径值清零 */
                left = 0;
            }
        }

        int right = 0;
        if (node.right != null) {
            right += getUnivaluePathCount(node.right, longestPath);
            if (node.val == node.right.val) {
                /* 如果本节点和右节点值相同，路径能继续，再累加1 */
                right += 1;
            } else {
                /* 否则，就说明右边的路径已经断开，本节点的右方向的单向路径值清零 */
                right = 0;
            }
        }
        int currentLongestPath;
        if (node.left != null && node.right != null && node.left.val == node.right.val) {
            /* 如果左右节点的值相同，那么可以相加连接起来。但如果左右相同但和本节点值不同，那么left和right的值都是0 */
            currentLongestPath = left + right;
        } else {
            /* 否则，最大的路径值只能是左右路径的最大值 */
            currentLongestPath = Math.max(left, right);
        }
        if (currentLongestPath > longestPath[0]) {
            /* 最终返回的结果在这里，每次递归都是以当前节点为核心，计算最大路径值，在多次递归当中，仅保留最大的值 */
            longestPath[0] = currentLongestPath;
        }
        /* 递归返回的是本节点与左右两个子节点的同值路径的最大值，注意，如果本节点的值与左右节点的值都不同，那么返回的是0 */
        return Math.max(left, right);
    }

}
