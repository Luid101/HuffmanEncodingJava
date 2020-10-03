import static junit.framework.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

public class TestHuffman extends TestCase {

    @Test
    public void testGetFrequencies() {

        // test 1
        HuffmanEncoding a = new HuffmanEncoding("aabc");

        HashMap<Character, Integer> result = new HashMap<>();
        result.put('a', 2);
        result.put('b', 1);
        result.put('c', 1);

        assertEquals(result, a.getFrequencies());

        // test 2
        a = new HuffmanEncoding("aaabbc");

        result = new HashMap<>();
        result.put('a', 3);
        result.put('b', 2);
        result.put('c', 1);

        assertEquals(result, a.getFrequencies());

        // test 3
        a = new HuffmanEncoding("");

        result = new HashMap<>();

        assertEquals(result, a.getFrequencies());

        // test 4
        a = new HuffmanEncoding("aaawwwcccbbb");

        result = new HashMap<>();
        result.put('a', 3);
        result.put('w', 3);
        result.put('c', 3);
        result.put('b', 3);

        assertEquals(result, a.getFrequencies());
    }

    @Test
    public void testGetTree() {

        // test 1
        HuffmanEncoding a = new HuffmanEncoding("aaabbc");
        BinaryTree tree = a.getTree();

        BinaryTree result = new BinaryTree("6", new BinaryTree("a"),
                new BinaryTree("3", new BinaryTree("c"), new BinaryTree("b")));

        assertEquals(result.toString(), tree.toString());

        // TODO: Add more tests
    }

    @Test
    public void testGetEncoding() {

        // test1
        HuffmanEncoding huff = new HuffmanEncoding("aabc");
        Map<String, Integer> map = huff.getEncoding();
        Map<String, Integer> result = new HashMap<>();
        result.put("a", 0);
        result.put("b", 10);
        result.put("c", 11);

        assertEquals(result, map);

        // test2
        huff = new HuffmanEncoding("aaaabbbccd");
        map = huff.getEncoding();

        if (map.get("a") > map.get("b") || map.get("b") > map.get("c")) {
            fail();
        }

        System.out.println(map);

        // TODO: Add more tests
    }

    @Test
    public void testEncodeText() {

        // test1
        HuffmanEncoding huff = new HuffmanEncoding("aabc");
        String encoding = huff.encodeText();
        String result = "001011";

        assertEquals(result, encoding);

        // test2
        huff = new HuffmanEncoding("aaabbcd");
        encoding = huff.encodeText();
        result = "0001010110111";

        assertEquals(result, encoding);

        // TODO: Add more tests
    }

    @Test
    public void testDecodeText() {

        // test1
        String text = "aabc";
        HuffmanEncoding huff = new HuffmanEncoding(text);
        String encoding = huff.encodeText();
        String result = "001011";

        assertEquals(result, encoding);

        // decode test1
        String original = huff.decodeText(encoding, huff.getTree());

        assertEquals(text, original);

        // test2
        text = "aaabbcd";
        huff = new HuffmanEncoding(text);
        encoding = huff.encodeText();
        result = "0001010110111";

        assertEquals(result, encoding);

        original = huff.decodeText(encoding, huff.getTree());

        assertEquals(text, original);

        // TODO: Add more tests
    }

}
