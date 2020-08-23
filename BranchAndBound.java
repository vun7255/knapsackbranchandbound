package discrete.math;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BranchAndBound {
	public static void main(String args[]) {
		Scanner scBagWeight = new Scanner(System.in);
		System.out.print("Trọng lượng túi là: ");
		int bagWeight = scBagWeight.nextInt();

		System.out.print("Số lượng đồ vật là: ");
		Scanner scQuantity = new Scanner(System.in);
		int quantity = scQuantity.nextInt();

		ArrayList<Items> listItems = inputItems(quantity);
		System.out.println("Kết quả là: " + findMaxValue(listItems, bagWeight));

	}

	public static ArrayList<Items> inputItems(int quantity) {
		ArrayList<Items> listItems = new ArrayList<Items>();
		for (int i = 0; i < quantity; i++) {
			Items item = new Items();
			Scanner scWeight = new Scanner(System.in);
			System.out.print("Trọng lượng đồ vật thứ " + (i + 1) + " là: ");
			item.setWeight(scWeight.nextDouble());

			System.out.print("Giá trị của đồ vật thứ " + (i + 1) + " là: ");
			Scanner scValue = new Scanner(System.in);
			item.setValue(scValue.nextInt());

			listItems.add(item);
		}

		// sắp xếp giảm dần
		// theo chỉ số value chia weight
		  listItems.sort(new Comparator<Items>() {
		  @Override
		  public int compare(Items o1, Items o2) { 
			  // TODO Auto-generated method stub
			  return (int) (o2.getValuePerWeight() > o1.getValuePerWeight() ? 1
		  : -1); }
		  });
		return listItems;
	}

	public static int findMaxValue(ArrayList<Items> listItems, int bagWeight) {
		Queue<Nodes> queueNode = new LinkedList<Nodes>();
		Nodes currentNode = new Nodes();
		Nodes tempNode = new Nodes();
		
		//khởi tạo node ban đầu
		tempNode.setLevel(-1);
		tempNode.setValue(0);
		tempNode.setWeight(0);
		queueNode.offer(tempNode);
		
		int maxValue = Integer.MIN_VALUE;
		
		while(!queueNode.isEmpty()) {
			tempNode = queueNode.poll();
			
			if(tempNode.getLevel() == -1) {
				currentNode.setLevel(0);
			}
			else if(tempNode.getLevel() == listItems.size() - 1) {
				continue;
			}
			else {
				currentNode.setLevel(tempNode.getLevel() + 1);
			}
			
			currentNode.setWeight(tempNode.getWeight() + listItems.get(currentNode.getLevel()).getWeight());
			currentNode.setValue(tempNode.getValue() + listItems.get(currentNode.getLevel()).getValue());
			
			if(currentNode.getWeight() <= bagWeight && currentNode.getValue() > maxValue) {
				maxValue = currentNode.getValue();
			}
			
			currentNode.setBound(upperBound(listItems, currentNode, bagWeight));
			if(currentNode.getBound() > maxValue) {
				queueNode.offer(Nodes.getCloneNodes(currentNode));
			}
			
			//node này chứa item không lấy
			
			currentNode.setWeight(tempNode.getWeight());
			currentNode.setValue(tempNode.getValue());
			currentNode.setBound(upperBound(listItems, currentNode, bagWeight));
			if(currentNode.getBound() > maxValue) {
				queueNode.offer(Nodes.getCloneNodes(currentNode));
			}
		}
		return maxValue;
	}

	public static int upperBound(ArrayList<Items> listItems, Nodes nextNodes, int bagWeight) {
		if (nextNodes.getWeight() >= bagWeight) {
			return 0;
		}
		int nextLevel = nextNodes.getLevel() + 1;
		int totalValue = nextNodes.getValue();
		double totalWeight = nextNodes.getWeight();

		while ((nextLevel < listItems.size()) && (totalWeight + listItems.get(nextLevel).getWeight() <= bagWeight)) {
			totalWeight += listItems.get(nextLevel).getWeight();
			totalValue += listItems.get(nextLevel).getValue();
			nextLevel++;
		}
		
		if (nextLevel < listItems.size()) {
			totalValue += (bagWeight - totalWeight) * listItems.get(nextLevel).getValuePerWeight();
		}
		
		return totalValue;
	}

}
