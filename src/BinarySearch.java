public class BinarySearch {

    public class Node {

        Node left;
        Node right;
        int value;

        Node(int value){
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value+"";
        }
    }

    private Node root;

    public BinarySearch(){

    }

    public void add(int value){
        if(root == null) {
            this.root = new Node(value);
        }else
            recursiveAdd(root, value);
    }

    private void recursiveAdd(Node node, int value) {
        if (node.left == null && value < node.value) {
            node.left = new Node(value);
            return;
        }
        if(node.right == null && value > node.value) {
            node.right = new Node(value);
            return;
        }

        if (node.value < value) {
            recursiveAdd(node.right, value);
        } else if (node.value > value){
            recursiveAdd(node.left, value);
        } else {
            return;
        }
    }

    public boolean search(int value) {
        return recursiveSearch(root, value);
    }

    private boolean recursiveSearch(Node root, int value) {
        if(root == null) return false;
        if (root.value == value) return true;
        if(value < root.value) {
            return recursiveSearch(root.left, value);
        } else {
            return recursiveSearch(root.right, value);
        }
    }

    public Node getRoot() {
        return  this.root;
    }

    @Override
    public String toString() {
        return "["+representStructure(this.root)+"]";
    }

    private String representStructure(Node root) {
        String r = "";
        if(root == null) return r;  //avoiding NullPointerException
        if (root.left != null) r += representStructure(root.left)+",";
        r += root.toString()+",";
        if (root.right != null) r += representStructure(root.right)+",";

        return r.substring(0, r.length()-1);
    }

    public boolean erase(int value) {
        /*
         * When deleting a node, three different situations can be found
         *
         * 1. Erasing a leaf node (in which case it's just necessary to reference the parent to null)
         * 2. Erasing a non-leaf node with a child (in which case it's just necessary to reference
         *    the parent to the child)
         * 3. Removing a non-leaf node with a subtree . If the node to be erased has been found on the
         *    left side, find the maximum and replace the node with that value. Otherwise, if the node has been
         *    found on the right side, find the minimum and perform the same procedure. Then delete the maximum/minimum
         *    node found on the subtree (typically with a recursive call)
         */

        return recursiveErase(root, value) != null;
    }

    private Node recursiveErase(Node node, int value) {
        if (node == null) {
            return null;
        } else if (value < node.value) { //left side
            node.left = recursiveErase(node.left, value);
        } else if(value > node.value){
            node.right = recursiveErase(node.right, value);
        } else { //value has been found!
            //the three different cases come into play
            if(node.left == null && node.right == null){
                node = null;
                return null;
            } else if(node.left == null){
                Node temp = node;
                node = node.right;
                temp = null;
                return node;
            } else if(node.right == null) {
                Node temp = node;
                node = node.left;
                temp = null;
                return node;
            } else {
                Node min = getMinimum(node.right);
                node.value =  min.value;
                node.right = recursiveErase(node.right, min.value);
            }
        }
        return node;
    }

    public Node getMaximum(Node node) {
        return node.right == null ? node : getMaximum(node.right);
    }

    public Node getMinimum(Node node) {
        return node.left == null ? node : getMinimum(node.left);
    }
}

