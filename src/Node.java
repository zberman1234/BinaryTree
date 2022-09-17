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

    public Node minimum() {
        Node out = this;
        while(out.getLChild() != null) {
            out = out.getLChild();
        }

        return out;
    }

    /**
     * 
     * @return
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

    public String toString() {
        String out = " ";
        out+= "Key= " + getKey();

        return out;
    }


    
}
