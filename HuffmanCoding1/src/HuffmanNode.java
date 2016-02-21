public class HuffmanNode implements Comparable<Object>{

	public String letter;
	public Double frequency;
	public HuffmanNode left, right;
	
	public HuffmanNode(String letter, Double frequency) {
		this.letter = letter;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	}
	
	public HuffmanNode(HuffmanNode left, HuffmanNode right) {
		this.left = left;
		this.right = right;
		this.letter = this.left.letter + this.right.letter;
		this.frequency = this.left.frequency + this.right.frequency;
	}
	
	public String toString() {
		return "<" + this.letter + ", " + this.frequency + ">";
	}
	
	public int compareTo(Object o) {
		HuffmanNode huff = (HuffmanNode)o;
		return this.frequency.compareTo(huff.frequency);
	}
	
}

