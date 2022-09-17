public class BinaryTree {

    Node root;

    
    public Node getRoot() {
        return root;
    }

    /**
     * Splice out a given Node a and connect its left child to a's parent or the right child to a's parent if left child is null
     * @param a the node to splice out
     */
    public void spliceOut(Node a) {
        Node child = a.getLChild();
        Node parent = a.getParent();
        if(child == null) child = a.getRChild();

        if(child != null) child.setParent(parent);

        if(parent == null) root = child;
        else {
            if(a == parent.getRChild()) parent.setRChild(child);
            else parent.setLChild(child);
        }
    }

    /**
     * If the node has two children, they must be preserved
     *  Implements successor to find the replacement key for the deleted node, as there must be a new connector to connect both of the deleted nodes children
     *  Implements spliceOut to splice out that successor
     * @param newNode the node to be rotated out
     */
    public void rotateOut(Node a) {
        if(a == null) return;
        Node suc = a.successor();
        spliceOut(suc);
        a.setKey(suc.getKey());
    }

    
    /**
     * Inserts a given node at the location that will keep up the binary tree rules
     * @param newNode the node to be inserted
     */
    public void insert(Node newNode) {
        Node newParent = findInsertionNode(newNode.getKey());
        if(newParent == null) root = newNode;
        else {
            newNode.setParent(newParent);
            if(newParent.getKey() > newNode.getKey()) newParent.setLChild(newNode);
            else newParent.setRChild(newNode);
        }
    }

    /**
     * Find the parent of the location where a node with the given key is to be inserted
     * @param key the key of the node we are looking to insert
     * @return the parent Node of the location to be inserted
     */
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
        return rsearch(root, key);
    }

    /**
     * Recursive search
     * @param x starting search node
     * @param key key being searched for
     * @return the Node with the given key
     */
    public Node rsearch(Node x, int key) {

        if(x!=null && x.getKey() != key) {
            if(key < x.getKey()) 
                return rsearch(x.getLChild(), key);
            else {
                return rsearch(x.getRChild(), key);
            }
        }

        return x;
    }

    /**
     * Tells wheter a Node has no children
     * @param a the node to detect children
     * @return whether node a has no children
     */
    public boolean childless(Node a) {
        if(a == null) return true;
        return a.getLChild() == null && a.getRChild() == null;
    }

    /**
     * Disconnects a given node from its parent
     * @param a the node to disconnect from its parent
     */
    public void scrubParent(Node a) {
        if(a == null) return;
        Node p = a.getParent();
        if(p == null) root = null;
        else {
            if(a == p.getRChild()) p.setRChild(null);
            else p.setLChild(null);
        }
    }


    public void OldInsert(Node e) {//defunct
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
     * Deletes a given Node from the tree
     *  Scrubs if no children
     *  Splices if one children
     *  Rotates if two children (which implements scrub)
     * @param a the Node to be deleted
     */
    public void delete(Node a) {
        if(childless(a)) {
            scrubParent(a);
            return;
        }

        if(a.childCount() == 1) spliceOut(a);
        else rotateOut(a);

    }
    

}
