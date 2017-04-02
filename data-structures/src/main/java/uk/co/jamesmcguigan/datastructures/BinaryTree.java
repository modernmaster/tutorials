package uk.co.jamesmcguigan.datastructures;

import java.util.Arrays;

public class BinaryTree {

    public static int findIdx(final int[] a, final int d) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == d) {
                return i;
            }
        }
        return -1;
    }

    public TreeNode buildTree(final int[] inorder, final int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        int head = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(head);
        int i = findIdx(inorder, head);
        int[] newIl = Arrays.copyOfRange(inorder, 0, i);
        int[] newPl = Arrays.copyOfRange(postorder, 0, i);
        int[] newIr = Arrays.copyOfRange(inorder, i + 1, inorder.length);
        int[] newPr = Arrays.copyOfRange(postorder, i, inorder.length - 1);
        root.left = buildTree(newIl, newPl);
        root.right = buildTree(newIr, newPr);
        return root;
    }

    // Definition for a binary tree node.
    public class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }
}

