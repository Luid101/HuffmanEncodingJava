import static junit.framework.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class TestBinaryTree extends TestCase {

    String newLine = System.getProperty("line.separator");

    @Test
    public void testCreate() {
        BinaryTree b = new BinaryTree("6", new BinaryTree(null, null, null), new BinaryTree(null, null, null));

        assertEquals("|--H: 6".concat(newLine).concat(" ".repeat(6)).concat("|--L: null").concat(newLine)
                .concat(" ".repeat(6)).concat("|--R: null"), b.toString());

        BinaryTree a = new BinaryTree("6", new BinaryTree("2", new BinaryTree("4", null, null), null),
                new BinaryTree(null, null, null));

        assertEquals("|--H: 6".concat(newLine).concat(" ".repeat(6)).concat("|--L: 2").concat(newLine)
                .concat(" ".repeat(12)).concat("|--L: 4").concat(newLine).concat(" ".repeat(6)).concat("|--R: null"),
                a.toString());

    }
}
