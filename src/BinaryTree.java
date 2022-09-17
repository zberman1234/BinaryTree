public class BinaryTree {

    Node root;


    public void newInsert(Node newNode) {

    }

    public void insert(Node newNode) {
        Node newParent = findInsertionNode(newNode.getKey());
        if(newParent == null) root = newNode;
        else {
            if(newParent.getKey() < newNode.getKey()) newParent.setRChild(newNode);
            else newParent.setLChild(newNode);
        }
    }

    public Node findInsertionNode(int key) {
        Node retval = null;
        Node x = root;
        while(x!=null) {
            retval = x;
            if(key < x.getKey()) x = x.getLChild();
            else x = x.getRChild();
        }

        return retval;
    }

    /**
     * Finds the Node with the specified key value
     * @param key the key of the Node to be found
     * @return the Node with the given key
     */
    public Node search(int key) {

        Node x = root;

        while(x!=null && x.getKey() != key) {
            if(key < x.getKey()) x = x.getLChild();
            else x = x.getRChild();
        }

        return x;
    }

    public Node rsearch(Node x, int key) {

        while(x!=null && x.getKey() != key) {
            if(key < x.getKey()) x = x.getLChild();
            else x = x.getRChild();
        }

        return x;
    }

    /**
     * Inserts a new Node at the root of the list
     * @param e the Node to be inserted
     */
    public void OldInsert(Node e) {
        // Stack behavior
        e.setLChild(root);
        if(root != null) {
            root.setParent(e);
        }
        root = e;
        e.setParent(null);

        // Queue behavior (I think)
        // if(root == null) root = e;
        // else {
        //     root.setLChild(e);
        //     e.setParent(root);
        //     e.setLChild(null);
        // }
        

    }

    /**
     * Deletes a given Node from the list
     * @param x the Node to be deleted
     */
    public void delete(Node x) {
        if(x.getParent() != null) {
            x.getParent().setLChild(x.getLChild());
        } else {
            root = x.getLChild();
        }
        if(x.getLChild() != null) {
            x.getLChild().setParent(x.getParent());
        }

    }
    

}
