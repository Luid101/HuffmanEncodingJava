
/**
 * A binary tree.
 */
public class BinaryTree {
    private String value = "null";
    private BinaryTree left = null;
    private BinaryTree right = null;

    // a value used by the priority queue
    private int freq;

    /**
     * This function creates a new binary tree.
     * 
     * @param value string value of the tree
     * @param left  left binary tree, nullable
     * @param right right binary tree, nullable
     */
    public BinaryTree(String value, BinaryTree left, BinaryTree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * This function creates a new binary tree. With frequencies.
     * 
     * @param value string value of the tree
     * @param left  left binary tree, nullable
     * @param right right binary tree, nullable
     * @param freq the number of times the item apprears
     */
    public BinaryTree(String value, BinaryTree left, BinaryTree right, int freq) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.freq = freq;
    }

    /**
     * This function creates an empty binary tree with value as null
     */
    public BinaryTree() {
        this.value = "null";
    }

    public BinaryTree(String value) {
        this.value = value;
    }

    /**
     * Does a dfs of binary tree and returns it as a string
     * 
     * Example: 
     * -- 6
     *   |--L: 4
     *      |--L: null
     *      |--R: 3
     *   |--R: 2 
     *      |--L: 1
     *      |--R: null
     * 
     */
    @Override
    public String toString() {

        return "|--H: ".concat(helper(this, 0));

    }

    public static String helper(BinaryTree head, int depth) {

        String result = head.getValue();
        String left_val;
        String right_val;
        String newLine = System.getProperty("line.separator");

        if (result == null) {
            return "null";
        }

        depth += 1;

        if (head.getLeft() != null) {
            left_val = helper(head.getLeft(), depth);
            result = result.concat(newLine).concat("      ".repeat(depth)).concat("|--L: ").concat(left_val);
        }

        if (head.getRight() != null) {
            right_val = helper(head.getRight(), depth);
            result = result.concat(newLine).concat("      ".repeat(depth)).concat("|--R: ").concat(right_val);
        }

        return result;

    }

    /**
     * @return String return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return BinaryTree return the left
     */
    public BinaryTree getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    /**
     * @return BinaryTree return the right
     */
    public BinaryTree getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(BinaryTree right) {
        this.right = right;
    }

    /**
     * @return int return the freq
     */
    public int getFreq() {
        return freq;
    }

    /**
     * @param freq the freq to set
     */
    public void setFreq(int freq) {
        this.freq = freq;
    }

}
