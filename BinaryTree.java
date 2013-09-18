package General;

import java.util.ArrayList;
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
           levelOrderTraversalUsingDFS(root, level);
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
        boolean readFromLeftToRight = true;
        for(int level=0; level<binaryTreeHeight(); ++level){
            readFromLeftToRight = !readFromLeftToRight;
            levelOrderTraversalSpirally(root, level, readFromLeftToRight);
            System.out.println();
        }

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

    private void levelOrderTraversalSpirally(BinaryTreeNode node, int level, boolean readFromLeftToRight){
        if(level == 0){
            System.out.print(node+" ");
        }
        else{
            if(readFromLeftToRight){
               levelOrderTraversalSpirally(node.leftChild,level-1,readFromLeftToRight);
               levelOrderTraversalSpirally(node.rightChild, level-1, readFromLeftToRight);
            }
            else{
               levelOrderTraversalSpirally(node.rightChild,level-1,readFromLeftToRight);
               levelOrderTraversalSpirally(node.leftChild, level-1, readFromLeftToRight);
            }
        }
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

    public void createLevelOrderLinkedLists(String functionCallType){
        ArrayList<LinkedList<BinaryTreeNode>> result = null;
        if(functionCallType.equals("Recursive")){
            result = createLevelOrderLinkedListsRecursive();
        }
        else if(functionCallType.equals("Iterative")){
            result = createLevelOrderLinkedListsIterative();
        }
        /* I have chosen the default function in this case to be the iterative function,
        since it doesn't use the O(logN) additional calls
        that the recursive function needs.
         */
        else {
            result = createLevelOrderLinkedListsIterative();
        }

        for(LinkedList<BinaryTreeNode> list : result){
            for(BinaryTreeNode node : list){
                System.out.print(node+ "->");
            }
            System.out.println();
        }
    }

    private ArrayList<LinkedList<BinaryTreeNode>> createLevelOrderLinkedListsIterative(){
        ArrayList<LinkedList<BinaryTreeNode>> result = new ArrayList<LinkedList<BinaryTreeNode>>();

        LinkedList<BinaryTreeNode> currentList = new LinkedList<BinaryTreeNode>();
        if(root == null){
            return null;
        }
        currentList.add(root);
        while(!currentList.isEmpty()){
            result.add(currentList);
            LinkedList<BinaryTreeNode> parentsList = currentList;
            currentList = new LinkedList<BinaryTreeNode>();
            for(BinaryTreeNode node : parentsList){
                if(node.leftChild!=null){
                    currentList.add(node.leftChild);
                }
                if(node.rightChild!=null){
                    currentList.add(node.rightChild);
                }
            }
        }
        return result;
    }

    private ArrayList<LinkedList<BinaryTreeNode>> createLevelOrderLinkedListsRecursive(){
        ArrayList<LinkedList<BinaryTreeNode>> result = new ArrayList<LinkedList<BinaryTreeNode>>();
        createLevelOrderLinkedListsRecursive(root, result, 0);
        return result;
    }

    private void createLevelOrderLinkedListsRecursive(
            BinaryTreeNode currentNode,
            ArrayList<LinkedList<BinaryTreeNode>> lists,
            int level){

        if(currentNode == null){
            return;
        }
        LinkedList<BinaryTreeNode> currentList = null;
        if(lists.size() == level){
            currentList = new LinkedList<BinaryTreeNode>();
            lists.add(currentList);
        }
        else {
            currentList = lists.get(level);
        }
        currentList.add(currentNode);
        createLevelOrderLinkedListsRecursive(currentNode.leftChild, lists, level+1);
        createLevelOrderLinkedListsRecursive(currentNode.rightChild, lists, level+1);
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

        System.out.println("\nLevel-order traversal (DFS)");
        tree.levelOrderTraversalUsingDFS();

        System.out.println("\nLevel-order traversal spirally");
        tree.levelOrderTraversalSpirally();

        System.out.println("\nCreating level order linked lists (Iterative)");
        tree.createLevelOrderLinkedLists("");

        System.out.println("\nCreating level order linked lists (Recursive)");
        tree.createLevelOrderLinkedLists("Recursive");

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
