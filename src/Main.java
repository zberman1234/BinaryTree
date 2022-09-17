import java.util.logging.*;
import java.util.ArrayList;
import java.util.Collections;
public class Main {

    static int pass=0;
    static int fail=0;

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        
        testFindInsert1();
        testFindInsert2();
        testSuccessor1();
        testMinimum1();
        testDeleteHelpers();
        testDeleteMany();

        Node fe = new Node(3);
        if(testConstructor()) pass++;
        else fail++;
        if(testSearch()) pass++;
        else fail++;
        if(testSearchne()) pass++;
        else fail++;
        if(testSearchte()) pass++;
        else fail++;
        if(testDelete1()) pass++;
        else fail++;
        if(testDelete2()) pass++;
        else fail++;

        BinaryTree fl = new BinaryTree();

        fl.search(3); 


        System.out.println("Passes: " + pass + "Fails: " + fail);
    }

    /**
     * Tests general deletion of all Nodes of a randomly generated binary tree
     */
    private static void testDeleteMany() {
        BinaryTree bt = new BinaryTree();
        ArrayList<Integer> expected = new ArrayList<Integer>();
        int testSize = 64;
        for(int i = 0; i < testSize; i++) {
            int key = (int) (testSize*Math.random());
            Node n = new Node(key);
            bt.insert(n);
            expected.add(key);
        }
        Collections.sort(expected);
        System.out.println(expected);
        System.out.println(sortedKeyArrayList(bt.getRoot()));
        for(int i = 0; i < testSize; i++) {
            int remVal = expected.remove(0);
            Node rem = bt.search(remVal);
            bt.delete(rem);
            ArrayList<Integer> actual = sortedKeyArrayList(bt.getRoot());
            if(actual.equals(expected)) pass++;
            else {
                fail++;
                LOGGER.log(Level.WARNING, "Failed testDeleteMany: expected " + expected + ", got " + actual);
            }

        }
    }

    /**
     * Tests scrub parent by constructing a 0,1,2,3,4 binary tree and then deconstructing it exclusively using scrubParent
     */
    private static void testScrubParent() {
        BinaryTree bt = new BinaryTree();
        ArrayList<Integer> expected = new ArrayList<>();
        int testSize = 5;
        for(int i =0; i < testSize; i++) {
            Node n = new Node(i);
            bt.insert(n);
            expected.add(i);
        }

        for(int i = testSize - 1; i >= 0; i--) {
            expected.remove(Integer.valueOf(i));
            bt.scrubParent(bt.search(i));
            ArrayList<Integer> actual = sortedKeyArrayList(bt.getRoot());
            if(actual.equals(expected)) pass++;
            else {
                fail++;
                LOGGER.log(Level.WARNING, "Failed testScrubParent: expected " + expected + ", got " + actual);
            }

        }
    }

    /**
     * Tests splice out by creating a 0,1,2,3,4 binary tree, splicing two middle Nodes and then checking if they were actually removed and the list looks as it should
     */
    private static void testSpliceOut() {
        BinaryTree bt = new BinaryTree();
        ArrayList<Integer> expected = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Node n = new Node(i);
            bt.insert(n);
            expected.add(i);
        }
        bt.spliceOut(bt.search(4));
        expected.remove(Integer.valueOf(4));
        bt.spliceOut(bt.search(2));
        expected.remove(Integer.valueOf(2));

        ArrayList<Integer> actual = sortedKeyArrayList(bt.getRoot());

        if(expected.equals(actual)) pass++;
        else {
            LOGGER.log(Level.WARNING, "Failed testSpliceOut: expected " + expected + " got " + actual);
        }
    }

    /**
     * Tests rotateOut by generating a binary tree intentionally so that Nodes with power of 2 keys will always have to children, 
     * and thus always need to be rotated out to be deleted
     */
    private static void testRotateOut() {
        BinaryTree bt = new BinaryTree();
        ArrayList<Integer> expected = new ArrayList<>();
        int testSize = 64;
        for(int start = testSize; start > 0; start = start/2) {
            for(int key = start; key < 2*testSize; key+= 2*start) {
                Node n = new Node(key);
                bt.insert(n);
                expected.add(key);
            }
        }
        for(int i = 2; i < 2*testSize; i*=2) {
            bt.rotateOut(bt.search(i));
            ArrayList<Integer> actual = sortedKeyArrayList(bt.getRoot());
            Collections.sort(actual);
            Collections.sort(expected);
            expected.remove(Integer.valueOf(i));
            if(actual.equals(expected)) pass++;
            else {
                fail++;
                LOGGER.log(Level.WARNING, "Failed testRotateOut: expected " + expected + ", got " + actual);
            }
        }
    }

    /**
     * tests spliceOut by creating a two node list, splicing out both nodes and confirming that the list is null
     */
    private static void testSpliceOutTwoIters() {
        BinaryTree bt = new BinaryTree();
        Node zero = new Node(0);
        Node one = new Node(1);
        bt.insert(zero);
        bt.insert(one);
        bt.spliceOut(zero);
        bt.spliceOut(one);
        if(bt.getRoot() == null) pass++;
        else {
            LOGGER.log(Level.WARNING, "Failed testSpliceOutTwoIters: expected an empty tree, got " + bt);
        }
    }

    //calls 4 delete helper tests
    private static void testDeleteHelpers() {
        testScrubParent();
        testSpliceOut();
        testRotateOut();
        testSpliceOutTwoIters();
    }

    /**
     * tests findInsertionNode by creating a binary tree where we know where a new element should be inserted,
     * and then confirming that that is where findInsertionNode suggests to put it
     */
    public static void testFindInsert1() {
        BinaryTree bt = new BinaryTree();
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);

        bt.root = four;
        four.setLChild(two);
        four.setRChild(six);
        two.setLChild(one);
        two.setRChild(three);
        six.setLChild(five);
        six.setRChild(seven);

        Node expectSeven = bt.findInsertionNode(8);
        
        if(expectSeven == seven) pass++;
        else {
            fail++;
            LOGGER.log(Level.WARNING, "Failed testFindInsert1: expected " + seven + "got " + expectSeven);
        }
    }

    //same theory as testFindInsertion1
    public static void testFindInsert2() {
        BinaryTree bt = new BinaryTree();
        Node one = new Node(3);
        Node two = new Node(5);
        Node three = new Node(7);
        Node four = new Node(9);
        Node five = new Node(11);
        Node six = new Node(13);
        Node seven = new Node(15);
        Node n = new Node(8);

        bt.root = four;
        four.setLChild(two);
        four.setRChild(six);
        two.setLChild(one);
        two.setRChild(three);
        six.setLChild(five);
        six.setRChild(seven);

        Node expectThree = bt.findInsertionNode(8);
        
        if(expectThree == three) pass++;
        else {
            fail++;
            LOGGER.log(Level.WARNING, "Failed testFindInsert1: expected " + three + "got " + expectThree);
        }
    }

    /**
     * Tests deleting the first added Node
     * @return Whether or not the Node was actually deleted
     */
    private static boolean testDelete1() {
        BinaryTree fl = new BinaryTree();
        Node fe = new Node(3);
        Node se = new Node(4);
        fl.insert(fe);
        fl.insert(se);
        fl.delete(fe);
        return fl.search(3) == null;
    }

    /**
     * Tests deleting the second added Node
     * @return If the Node was actually deleted
     */
    private static boolean testDelete2() {
        BinaryTree fl = new BinaryTree();
        Node fe = new Node(3);
        Node se = new Node(4);
        fl.insert(fe);
        fl.insert(se);
        fl.delete(se);
        return fl.search(3) == fe;
    }

    /**
     * tests to check if successor actually finds the successor of a two node tree
     * @return whether the successor of 3 actually returns 4
     */
    public static void testSuccessor1() {
        BinaryTree fl = new BinaryTree();
        Node fe = new Node(3);
        Node se = new Node(4);
        fl.insert(fe);
        fl.insert(se);
        Node sfe = fe.successor();
        if(sfe == se) pass++;
        else {
            fail++;
            LOGGER.log(Level.WARNING, "Failed testSuccessor1: expected " + se + "got " + sfe);
        }
    }

    /**
     * Tests the minimum Node class method in a tree with two nodes, logs whether min found the correct min Node
     */
    public static void testMinimum1() {
        BinaryTree fl = new BinaryTree();
        Node fe = new Node(3);
        Node se = new Node(4);
        fl.insert(fe);
        fl.insert(se);
        Node min = fe.minimum();
        if(min == fe) pass++;
        else {
            fail++;
            LOGGER.log(Level.WARNING, "Failed testSuccessor1: expected " + fe + "got " + min);
        }
    }

    /**
     * Tests search with size one list
     * @return Whether search has found the right Node
     */
    public static boolean testSearchne() {
        BinaryTree fl = new BinaryTree();
        Node fe = new Node(3);
        fl.insert(fe);

        return fl.search(3) == fe;
    }

    /**
     * Tests search with size two list
     * @return Whether search finds the right Node
     */
    public static boolean testSearchte() {
        BinaryTree fl = new BinaryTree();
        Node fe = new Node(3);
        Node se = new Node(4);
        fl.insert(fe);
        fl.insert(se);

        return fl.search(3) == fe;
    }

    /**
     * Test search with empty list
     * @return Whether list is actually empty
     */
    public static boolean testSearch() {
        BinaryTree fl = new BinaryTree();

        return fl.search(3) == null;
    }

    /**
     * Tests Node constructor
     * @return whether constructor has created Node 
     * and assigned it the correct key value
     */
    public static boolean testConstructor() {
        Node fe = new Node(3);
        return fe.getKey() == 3;
    }

    /**
     * Same structure as Node.stringWalk(), but stores elements in array, not string
     * @param thisNode the starting node
     * @return an arraylist with thisNode and all its children, with left children lineage in the beginning, node in the middle, and right lineage at the end
     */
    public static ArrayList<Integer> sortedKeyArrayList(Node thisNode) {
        ArrayList<Integer> retval = new ArrayList<Integer>();
        if(thisNode == null) return retval;
        Node lchild = thisNode.getLChild();
        Node rchild = thisNode.getRChild();
        if(lchild != null) retval.addAll(sortedKeyArrayList(lchild));
        retval.add(thisNode.getKey());
        if(rchild != null) retval.addAll(sortedKeyArrayList(rchild));
        return retval;
    }
}
