package discrete.math;

public class Items {
	private double weight;
	private int value;
	private double valuePerWeight;
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public double getValuePerWeight() {
		return (double) (this.value / this.weight);
	}
	
}
