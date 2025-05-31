public class PerfectBST {

    static class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
            left = right = null;
        }
    }

    private static Node constructBST(int[] sortedArray) {
        return constructBST(sortedArray, 0, sortedArray.length-1);
    }

    private static Node constructBST(int[] sortedArray, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node root = new Node(sortedArray[mid]);
        root.left = constructBST(sortedArray, start, mid-1);
        root.right = constructBST(sortedArray, mid+1, end);

        return root;
    }

    private static void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }

    private static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static int[] countNodes(Node root) {
        int[] counts = new int[2];
        countNodesHelper(root, counts);
        return counts;
    }

    private static void countNodesHelper(Node root, int[] counts) {
        if (root == null) {
            return;
        }
        counts[1]++;
        if (root.left != null || root.right != null) {
            counts[0]++;
        }
        countNodesHelper(root.left, counts);
        countNodesHelper(root.right, counts);
    }

    public static void main(String[] args) {
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        Node root = constructBST(sortedArray);
    
        inorderTraversal(root);
    
        System.out.println("\nTree height: " + height(root));
    
        int[] counts = countNodes(root);
        System.out.println("Non-leaf nodes: " + counts[0]);
        System.out.println("Total  nodes: " + counts[1]);
    }   
}