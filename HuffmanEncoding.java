import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoding {

    private String text;
    private HashMap<String, Integer> map;
    private String encodedText;

    /**
     * Takes in text and initializes a HuffmanEncoding Object 
     * @param text
     */
    public HuffmanEncoding(String text) {
        this.text = text;
    }

    /**
     * Takes text and gets frequencies of each char in text. 
     * 
     * eg:
     *  "aabc" => {'a': 2, 'b': 1, 'c' : 1 } 
     * 
     * @return char -> freq
     */
    protected HashMap<Character, Integer> getFrequencies() {

        HashMap<Character, Integer> result = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {

            char currChar = text.charAt(i);

            // update freq of char 
            if (result.get(currChar) == null) {
                result.put(currChar, 1);
            } else {
                result.put(currChar, result.get(currChar) + 1);
            }
        }

        return result;
    }

    public BinaryTree getTree() {

        // get map of frequencies
        HashMap<Character, Integer> freqMap = this.getFrequencies();

        // create priority queue using nodes of each word 
        // and prioritize by lowest frequency first
        PriorityQueue<BinaryTree> pQueue = new PriorityQueue<>(freqMap.keySet().size(), new SortByFreq());

        // while priority queue has at least two items,
        // get lowest two items and merge them together
        // put them back into the queue
        // if queue has only one item, then return that item. 
        for (char c : freqMap.keySet()) {
            pQueue.add(new BinaryTree(String.valueOf(c), null, null, freqMap.get(c)));
        }

        while (pQueue.size() >= 2) {
            BinaryTree first = pQueue.poll();
            BinaryTree second = pQueue.poll();

            int jointFreq = first.getFreq() + second.getFreq();
            BinaryTree join = new BinaryTree(String.valueOf(jointFreq), first, second, jointFreq);

            pQueue.offer(join);
        }

        // the queue has only one item, return it as the huffman tree
        return pQueue.poll();
    }

    /**
     * Returns the huffman encoding for a given text;
     * 
     * (going left is 0, going right is 1)
     * 
     * eg:
     *  "aabc" => {'a': 0, 'b': 11, 'c' : 10 } 
     * 
     * @return
     */
    public Map<String, Integer> getEncoding() {

        // get the huffman tree of the text,
        // traverse the tree,
        // create a string such that everytime we go left add 0 and right add 1
        // once we reach a leaf, return the huffman tree created

        BinaryTree tree = this.getTree();

        this.map = new HashMap<>();

        this.getEncodingHelper(tree, "");

        return this.map;

    }

    protected void getEncodingHelper(BinaryTree tree, String encoding) {

        if (tree.getValue() == null) {
            return;
        }

        // if this is a leaf
        if (tree.getLeft() == null && tree.getRight() == null) {
            this.map.put(tree.getValue(), Integer.parseInt(encoding));
            return;
        }

        // we know it has at least a left or right sub tree
        // go left
        if (tree.getLeft() != null) {
            // add a 0 to the end of current encoding
            String newEncoding = encoding + "0";

            // make a recusive call with new encoding after going left
            this.getEncodingHelper(tree.getLeft(), newEncoding);
        }

        // go right
        if (tree.getRight() != null) {
            // add a 1 to the end of current encoding
            String newEncoding = encoding + "1";

            // make a recusive call with new encoding after going right
            this.getEncodingHelper(tree.getRight(), newEncoding);
        }
    }

    /**
     * Given the text that was taken in by this huffman, 
     * encode it and return the encoding.
     * 
     * @return
     */
    public String encodeText() {
        // construct encoded text and return it

        this.getEncoding();

        String result = "";

        for (int i = 0; i < this.text.length(); i++) {
            String c = String.valueOf(this.text.charAt(i));
            result += String.valueOf(this.map.get(c));
        }

        this.encodedText = result;

        return result;
    }

    public String decodeText(String encodedText, BinaryTree huffmanTree) {

        BinaryTree curr = huffmanTree;

        String result = "";

        for (int i = 0; i < encodedText.length(); i++) {

            String c = String.valueOf(encodedText.charAt(i));

            if (c != null && c.equals("0")) {
                // go left
                curr = curr.getLeft();
            }

            if (c != null && c.equals("1")) {
                // go right
                curr = curr.getRight();
            }

            // check if we are at leaf
            if (curr.getLeft() == null && curr.getRight() == null) {
                result += curr.getValue();
                curr = huffmanTree;
            }
        }

        return result;
    }
}

class SortByFreq implements Comparator<BinaryTree> {
    // Used for sorting in ascending order of 
    // roll number 
    public int compare(BinaryTree a, BinaryTree b) {
        return a.getFreq() - b.getFreq();
    }
}