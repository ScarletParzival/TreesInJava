package General;

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
            System.out.print(node.toString()+" ");
            inOrderTraversal(node.rightChild);
        }
    }

    public void preOrderTraversal(BinaryTreeNode node){
        if(node!=null){
            System.out.print(node.toString()+" ");
            preOrderTraversal(node.leftChild);
            preOrderTraversal(node.rightChild);
        }
    }

    public void postOrderTraversal(BinaryTreeNode node){
        if(node!=null){
            postOrderTraversal(node.leftChild);
            postOrderTraversal(node.rightChild);
            System.out.print(node.toString()+" ");
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

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTreeNode( 7);
        tree.addNode(2);
        tree.addNode(24);
        tree.addNode(30);
        System.out.println("Printing root: "+tree.root);
        System.out.println("\nIn-order traversal");
        tree.inOrderTraversal(tree.root);
        System.out.println("\n\nPre-order traversal");
        tree.preOrderTraversal(tree.root);
        System.out.println("\n\nPost-order traversal");
        tree.postOrderTraversal(tree.root);
        System.out.println("\n\nFinding a node:");
        System.out.println(tree.findNode(30));
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
