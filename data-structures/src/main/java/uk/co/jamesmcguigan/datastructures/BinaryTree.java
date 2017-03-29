package uk.co.jamesmcguigan.datastructures;

import java.util.Arrays;

public class BinaryTree {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
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

    public static int findIdx(int[] a, int d) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == d) {
                return i;
            }
        }
        return -1;
    }
}

