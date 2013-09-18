package General;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: sowmyahariharan
 * Date: 9/17/13
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinaryTree {

    BinaryTreeNode root;

    public void addNode(int key){

        BinaryTreeNode newNode = new BinaryTreeNode(key);
        BinaryTreeNode currentNode = root;
        BinaryTreeNode parentNode;

        while(true){
            parentNode = currentNode;
            if(key < currentNode.key){
                currentNode = currentNode.leftChild;
                if(currentNode == null){
                    parentNode.leftChild = newNode;
                    return;
                }
            }
            else{
                currentNode = currentNode.rightChild;
                if(currentNode == null){
                    parentNode.rightChild = newNode;
                    return;
                }
            }
        }
    }

    public void inOrderTraversal(BinaryTreeNode node){
        if(node!=null){
            inOrderTraversal(node.leftChild);
            System.out.print(node.toString() + " ");
            inOrderTraversal(node.rightChild);
        }
    }

    public void preOrderTraversal(BinaryTreeNode node){
        if(node!=null){
            System.out.print(node.toString() + " ");
            preOrderTraversal(node.leftChild);
            preOrderTraversal(node.rightChild);
        }
    }

    public void postOrderTraversal(BinaryTreeNode node){
        if(node!=null){
            postOrderTraversal(node.leftChild);
            postOrderTraversal(node.rightChild);
            System.out.print(node.toString() + " ");
        }
    }

    public void levelOrderTraversal(int numberOfQueues){
        switch(numberOfQueues){
            case 2:{
                levelOrderTraversalUsingTwoQueues();
                break;
            }
            case 1:{
                levelOrderTraversal();
                break;
            }
            default:
              break;
        }
    }

    private void levelOrderTraversal(){
       if(root == null){
           return;
       }
       int numberOfNodesInCurrentLevel = 1;
       int numberOfNodesInNextLevel = 0;
       Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
       queue.add(root);
       while(!queue.isEmpty()){
           BinaryTreeNode currentNode = queue.poll();
           --numberOfNodesInCurrentLevel;
           if(currentNode!=null){
               System.out.print(currentNode+" ");
               queue.add(currentNode.leftChild);
               queue.add(currentNode.rightChild);
               numberOfNodesInNextLevel += 2;
           }
           if(numberOfNodesInCurrentLevel == 0){
               if(currentNode!=null){
                   System.out.println();
               }
               numberOfNodesInCurrentLevel = numberOfNodesInNextLevel;
               numberOfNodesInNextLevel = 0;
           }
       }
    }
    public void levelOrderTraversalUsingTwoQueues(){
        if(root == null){
            return;
        }
        Queue<BinaryTreeNode> currentLevel = new LinkedList<BinaryTreeNode>();
        Queue<BinaryTreeNode> nextLevel = new LinkedList<BinaryTreeNode>();
        currentLevel.add(root);
        while(!currentLevel.isEmpty()){
            BinaryTreeNode currentNode = currentLevel.poll();
            if(currentNode!=null){
                System.out.print(currentNode+" ");
                nextLevel.add(currentNode.leftChild);
                nextLevel.add(currentNode.rightChild);
            }
            if(currentLevel.isEmpty()){
                System.out.println();
                currentLevel = nextLevel;
                nextLevel = new LinkedList<BinaryTreeNode>();
            }
        }
    }

    public void levelOrderTraversalUsingDFS(){
        int heightOfTree = binaryTreeHeight();
        for(int level=0; level<heightOfTree; ++level){
           levelOrderTraversalUsingDFS(root,level);
            System.out.println();
        }
    }

    private void levelOrderTraversalUsingDFS(BinaryTreeNode node, int level){
       if(level == 0){
           System.out.print(node+" ");
       }
       else{
           levelOrderTraversalUsingDFS(node.leftChild,level-1);
           levelOrderTraversalUsingDFS(node.rightChild, level-1);
       }
    }

    public void levelOrderTraversalSpirally(){

        /*
        if(root == null){
            return;
        }
        LinkedList<BinaryTreeNode> currentLevel = new LinkedList<BinaryTreeNode>();
        LinkedList<BinaryTreeNode> nextLevel = new LinkedList<BinaryTreeNode>();
        boolean readLeftToRight = true;
        currentLevel.add(root);
        while(!currentLevel.isEmpty()){
            BinaryTreeNode currentNode;
            if(readLeftToRight){
                currentNode = currentLevel.poll();
            }
            else{
                currentNode = currentLevel.pollLast();
            }
            if(currentNode!=null){
                System.out.print(currentNode+" ");
                nextLevel.add(currentNode.leftChild);
                nextLevel.add(currentNode.rightChild);
            }
            if(currentLevel.isEmpty()){
                System.out.println();
                readLeftToRight = !readLeftToRight;
                currentLevel = nextLevel;
                nextLevel = new LinkedList<BinaryTreeNode>();
            }
        }
        This wouldn't work.
        This actually prints the left-right pairs in reverse order.
        */
    }

    public BinaryTreeNode findNode(int key){
        BinaryTreeNode currentNode = root;
        while(currentNode!=null){
            if(currentNode.key == key)
                return currentNode;
            if(key < currentNode.key){
                currentNode = currentNode.leftChild;
            }
            else{
                currentNode = currentNode.rightChild;
            }
        }
        return null;
    }

    public int binaryTreeHeight(){
        return binaryTreeHeight(root);
    }

    private int binaryTreeHeight(BinaryTreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(binaryTreeHeight(root.leftChild),binaryTreeHeight(root.rightChild)) + 1;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.root = new BinaryTreeNode(4);
        tree.addNode(2);
        tree.addNode(6);
        tree.addNode(1);
        tree.addNode(3);
        tree.addNode(5);
        tree.addNode(7);

        System.out.println("Printing root: " + tree.root);

        System.out.println("\nIn-order traversal");
        tree.inOrderTraversal(tree.root);

        System.out.println("\n\nPre-order traversal");
        tree.preOrderTraversal(tree.root);

        System.out.println("\n\nPost-order traversal");
        tree.postOrderTraversal(tree.root);

        System.out.println("\n\nLevel-order traversal (BFS)");
        tree.levelOrderTraversal();

        System.out.println("\n\nLevel-order traversal (DFS)");
        tree.levelOrderTraversalUsingDFS();

        System.out.println("\n\nLevel-order traversal spirally");
        tree.levelOrderTraversalSpirally();

        System.out.println("\nFinding a node:");
        System.out.println(tree.findNode(3));
    }

}

class BinaryTreeNode{
    int key;
    BinaryTreeNode leftChild;
    BinaryTreeNode rightChild;

    BinaryTreeNode(int key){
        this.key = key;
    }

    public String toString(){
        return ""+key;
    }
}
