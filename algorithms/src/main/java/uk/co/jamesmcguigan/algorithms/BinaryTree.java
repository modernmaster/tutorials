package uk.co.jamesmcguigan.algorithms;

import java.util.*;

public class BinaryTree {

    class BinaryTreeNode {

        BinaryTreeNode leftSubTree;
        BinaryTreeNode rightSubTree;
        Object  data;
    }

    class BinaryTreeTraversal {

        public void breadthTraverse(BinaryTreeNode binaryTreeNode) {
            Queue<BinaryTreeNode> binaryTreeNodeQueue = new LinkedList<BinaryTreeNode>();
            if(binaryTreeNode == null) {
                return;
            }
            binaryTreeNodeQueue.clear();
            binaryTreeNodeQueue.add(binaryTreeNode);
            while(!binaryTreeNodeQueue.isEmpty()){
                BinaryTreeNode currentNode = binaryTreeNodeQueue.remove();
                if(currentNode.leftSubTree!=null) binaryTreeNodeQueue.add(currentNode.leftSubTree);
                if(currentNode.rightSubTree!=null) binaryTreeNodeQueue.add(currentNode.rightSubTree);
            }
        }

        public void depthTraversal(BinaryTreeNode binaryTreeNode) {
            Deque<BinaryTreeNode> binaryTreeNodeStack = new ArrayDeque<BinaryTreeNode>();
            if(binaryTreeNode == null) {
                return;
            }
            binaryTreeNodeStack .clear();
            binaryTreeNodeStack .add(binaryTreeNode);
            while(!binaryTreeNodeStack .isEmpty()){
                BinaryTreeNode currentNode = binaryTreeNodeStack.pop();
                if(currentNode.leftSubTree!=null) binaryTreeNodeStack.add(currentNode.leftSubTree);
                if(currentNode.rightSubTree!=null) binaryTreeNodeStack.add(currentNode.rightSubTree);
            }
        }




    }



}
