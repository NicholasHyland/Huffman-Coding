import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanConverter {

	public static final int NUMBER_OF_CHARACTERS = 256;
	private String huffText = "";
	private static String contents = "";
	private String codedMessage = "";
	private String unCodedMessage = "";
	private char[] charArray;
	private HuffmanTree huffmanTree = new HuffmanTree(null);
	private int count[];
	private String code[];
	
	public HuffmanConverter(String input) {
		contents = input;
		this.count = new int[NUMBER_OF_CHARACTERS];
		this.code = new String[NUMBER_OF_CHARACTERS];
	}
	
	public void recordFrequencies() {
		this.charArray = contents.toCharArray();
		for (int i = 0; i < this.charArray.length; i++) {
			int temp = (int)this.charArray[i];
			this.count[temp] += 1;
		}
		
		for (int x = 0; x < this.count.length; x++) {
			if (this.count[x] == 0) continue;
			else {
				if(x == 9) {
					System.out.printf("[<\\t, %d>]", this.count[x]);
					System.out.println();
				}
				else if (x == 10) {
					System.out.printf("[<\\n, %d>]", this.count[x]);
					System.out.println();
				}
				else {
					char temp = (char)x;
					System.out.printf("[<%c, %d>]", temp, this.count[x]);
					System.out.println();
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void frequenciesToTree() throws UnderflowException {
		for (int x = 0; x < this.count.length; x++) {
			if (this.count[x] == 0) continue;
			else {
				char temp = (char)x;
				this.huffText += temp + "£" + this.count[x] + "£";
			}
		}
		//this.huffmanTree = this.huffmanTree.createFromHeap(this.huffmanTree.fileToHeap(this.huffText));
		this.huffmanTree = HuffmanTree.createFromHeap(HuffmanTree.fileToHeap(this.huffText));
	}
	
	public void treeToCode() {
		treeToCode(this.huffmanTree.root, "");
	}
	
	private void treeToCode(HuffmanNode t, String s) {

		if (t.letter.length() > 1) {
			treeToCode(t.left, s + "0");
			treeToCode(t.right, s + "1");
		}
		else {
			char ch = t.letter.charAt(0);
			code[(int)ch] = s;
		}
	}
	
	public String encodeMessage() {
		
		for (int i = 0; i < this.charArray.length; i++) {
			int temp = (int)this.charArray[i];
			this.codedMessage += this.code[temp];

		}
		return this.codedMessage;
	}
	
	
	
	
	
	
	public static String readContents(String filename) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader(filename));
		while ((line = in.readLine()) != null) {
			contents += line;
			contents += "\n";
		}
		return contents;
	}
	
	public String decodeMessage(String encodedStr) {
		String[] encodedStrSplit = encodedStr.split("");
		int i = 1;

		while (i < encodedStrSplit.length) {
			HuffmanNode node = huffmanTree.root;
			
			while (node.letter.length() > 1) {
					if (encodedStrSplit[i].equals("0")) {
						node = node.left;
						i++;
					}
					else {
						node = node.right;
						i++;
					}
			}
			this.unCodedMessage += node.letter;
		}
		return this.unCodedMessage;
	}
	
	public static void main(String args[]) throws IOException, UnderflowException {
		

//		String myString = readContents(args[0]);
		// YOU CAN SUBSTITUTE THE STRING BELOW FOR "myString"
		HuffmanConverter myConverter = new HuffmanConverter("Hello my name is Nicholas!");
		System.out.println("Character Frequencies:");
		System.out.println();
		myConverter.recordFrequencies();
		myConverter.frequenciesToTree();
		
		System.out.println();
		System.out.println("Legend:");
		System.out.println();
		myConverter.huffmanTree.printLegend();

		myConverter.treeToCode();
		
		String encodedString = myConverter.encodeMessage();
		System.out.println();
		System.out.println("Encoded Message: ");
		System.out.println();
		System.out.println(encodedString);
		System.out.println();
		System.out.println("Decoded Message:");
		System.out.println();
		System.out.println(myConverter.decodeMessage(encodedString));
	}
}
