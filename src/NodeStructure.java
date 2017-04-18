/**
 * Created by tdrs on 17/04/17.
 */
public class NodeStructure {

    public class Node {
        int value;
        Node next;

        public Node(int value){
            this.value = value;
        }
    }

    private Node initialNode;

    public NodeStructure(int value){
        initialNode = new Node(value);
    }

    public NodeStructure(){

    }

    public void add(int value){
        Node init = initialNode;
        if(init == null){
            initialNode = new Node(value);
            return;
        }
        while(init.next != null){
            init = init.next;
        }
        init.next = new Node(value);
    }


    public String representStructure(){
        Node init = initialNode;
        String r = "[";
        while(init != null){
            r += init.value + ",";
            init = init.next;
        }
        return r.substring(0, r.length()-1)+"]";
    }

    public boolean setNewValueTo(int position, int value){
        Node init = initialNode;
        int i = 0;  //node structure position counter
        while(init != null){
            if(i == position){
                init.value = value;
                return true;
            }
            init = init.next;
            i++;
        }
        return false;
    }

    public boolean contains(int value){
        Node init = initialNode;
        while(init != null){
            if(init.value == value) return true;
            init = init.next;
        }
        return false;
    }

    public boolean erase(int value){
        int erased_nodes = 0;
        Node init = initialNode;
        Node previous = null;
        while (init != null){
            if(init.value == value){ //erase zone
                erased_nodes++;
                //if node in the beginning
                if(init == initialNode){
                    initialNode = initialNode.next;
                    init = init.next;
                    continue;
                }
                //if node at the end
                if(init.next == null){
                    previous.next = null;
                }
                //node is between two nodes, previous and next
                previous.next = init.next;

                //start from the beginning again!
                init = previous;
                continue;
            }
            previous = init;
            init = init.next;
        }

        return erased_nodes != 0;
    }

    public NodeStructure oddElements(){
        NodeStructure nodeStructure = new NodeStructure(-1);
        Node init = initialNode;
        while(init != null){
            if(init.value % 2 != 0){
                nodeStructure.add(init.value);
            }
            init = init.next;
        }
        nodeStructure.erase(-1);
        return nodeStructure;
    }

    public NodeStructure clone(){
        NodeStructure nodeStructure = new NodeStructure();
        Node init = initialNode;
        while(init != null){
            nodeStructure.add(init.value);
            init = init.next;
        }
        return nodeStructure;
    }

    public Node getInitialNode(){
        return this.initialNode;
    }

    public void setInitialNode(Node initialNode){
        this.initialNode = initialNode;
    }

    public NodeStructure sort(){
        NodeStructure nodeStructure = this.clone();

        boolean sorted = false;

        while(!sorted){
            sorted = true;
            Node init = nodeStructure.getInitialNode();
            Node prev = null;
            while(init != null && init.next != null){
                if(init.value > init.next.value){
                    if(prev == null){
                        Node node1 = init;
                        Node node2 = node1.next;
                        Node node3 = node2.next;
                        node1.next = node3;
                        node2.next = node1;
                        nodeStructure.setInitialNode(node2);
                    }else if(init.next.next == null){ //at end
                        Node nodo4 = prev;
                        Node nodo5 = init;
                        Node nodo6 = nodo5.next;

                        nodo5.next = null;
                        nodo6.next = nodo5;
                        nodo4.next = nodo6;
                    }else{ //between
                        Node node1 = prev;
                        Node node2 = node1.next;
                        Node node3 = node2.next;
                        Node node4 = node3.next;

                        node1.next = node3;
                        node2.next = node4;
                        node3.next = node2;
                    }
                    sorted = false;
                }

                prev = init;
                init = init.next;
            }
        }

        return nodeStructure;
    }
}

