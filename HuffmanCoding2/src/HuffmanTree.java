import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanTree {

	public static HuffmanNode[] nodeArray = null;
	HuffmanNode root;
	
	public static void main(String[] args) throws IOException, UnderflowException { 

		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
			String line = in.readLine();
		
			String test = "A 50 B 40 C 30 D 20 E 5 F 6 G 7 H 8";
			// if "line" doesn't work you can try "test"
			HuffmanTree huffmanTree = createFromHeap(fileToHeap(line));
			huffmanTree.printLegend();
	}
	
	public HuffmanTree(HuffmanNode huff) {
		this.root = huff;
	}
	
	public void printLegend() {
		printLegend(root, "");
	}
	
	public void printLegend(HuffmanNode t, String s) {
		if (t.letter.length() > 1) {
			printLegend(t.left, s + "0");
			printLegend(t.right, s + "1");
		}
		else {
			System.out.println(t.letter + " = " + s);
		}
	}
	
	public static BinaryHeap fileToHeap(String filename) {
		String[] legend = filename.split("£");
		HuffmanNode[] nodeArray = new HuffmanNode[legend.length/2];
		
		int x = 0;
		for (int i = 0; i < legend.length; i += 2) {
			HuffmanNode node = new HuffmanNode(legend[i], Double.valueOf(legend[i+1]));
			nodeArray[x] = node;
			x++;
		}
		
		BinaryHeap<HuffmanNode> binaryHeap = new BinaryHeap<HuffmanNode>(nodeArray);
		return binaryHeap;
	}

	
	
	public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> b) throws UnderflowException {
		
		while (b.getSize() != 1) {
			HuffmanNode right = b.deleteMin();
			HuffmanNode left = b.deleteMin();
			HuffmanNode nextNode = new HuffmanNode(left, right);
			b.insert(nextNode);
		}
		
		HuffmanTree huffmanTree = new HuffmanTree(b.deleteMin());
		return huffmanTree;
	}
}
