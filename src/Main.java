import java.util.logging.*;
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
     * 
     * @return
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
}
