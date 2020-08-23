package discrete.math;

public class Nodes {
	private int level, value, bound;
	private double weight;
	
	public Nodes() {
		super();
	}
	public static Nodes getCloneNodes(Nodes node) {
		Nodes cloneNode = new Nodes();
		cloneNode.bound = node.getBound();
		cloneNode.level = node.getLevel();
		cloneNode.value = node.getValue();
		cloneNode.weight = node.getWeight();
		return cloneNode;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getBound() {
		return bound;
	}
	public void setBound(int bound) {
		this.bound = bound;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
}
