public class Node {
    private Node parent;
    private Node lchild;
    private Node rchild;
    private int key; 

    public Node(int k) {
        key = k;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }


    public Node getParent() {
        return parent;
    }

    public Node getLChild() {
        return lchild;
    }
    
    public Node getRChild() {
        return rchild;
    }

    public void setParent(Node _parent) {
        parent = _parent;
    }

    public void setLChild(Node _lchild) {
        lchild = _lchild;
    }

    public void setRChild(Node _rchild) {
        rchild = _rchild;
    }

    public int childCount() {
        int out = 0;
        if(lchild != null) out++;
        if(rchild != null) out++;
        return out;
    }

    /**
     * Finds the minimum under this Node
     * @return the minimum Node
     */
    public Node minimum() {
        Node out = this;
        while(out.getLChild() != null) {
            out = out.getLChild();
        }

        return out;
    }

    /**
     * Finds the next largest or equal Node in the tree
     * @return this Node
     */
    public Node successor() {
        Node retval = this;
        if(retval.rchild != null) {
            retval = retval.rchild.minimum();
        } else {
            while(retval != null && retval != retval.parent.lchild) {
                retval = retval.parent;
            }
        }
        while(retval.getLChild() != null) {
            retval = retval.getLChild();
        }

        return retval;
    }
    
    /**
     * ToString
     * @return example: "Node with key 4, children: 2, and reference Node@36baf30cgot"
     */
    public String toString() {
        return " Node with key " + key + ", children: " + childCount() + ", and reference " + super.toString();
    }

    public void printWalk() {
        // if(lchild != null) lchild.printWalk();
        // System.out.println(" " + key);
        // if(rchild != null) rchild.printWalk();
        System.out.println(stringWalk());
    }

    /**
     * Same structure as printWalk, but...
     * @return a string, not void
     */
    private String stringWalk() {
        String retval = "";
        if(lchild != null) retval += lchild.stringWalk();
        retval += " " + key + "\n";
        if(rchild != null) retval += rchild.stringWalk();

        return retval;
    }


    
}
