package changeMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

public class CoinCalculator {
	TreeMap<Integer, Integer> changeAmount;
	public CoinCalculator() {
		changeAmount = new TreeMap<Integer, Integer>();
	}
	
	public void calculateChange(ArrayList<Integer> denominations, Integer total) {
		Collections.sort(denominations, Collections.reverseOrder());
		for (Integer denomination : denominations) {
			changeAmount.put(denomination, Integer.valueOf(total/denomination));
			total = total % denomination;
		}
	}
	public TreeMap<Integer, Integer> getChangeAmount() {
		return changeAmount;
	}
	
	public void setChangeAmount(TreeMap<Integer, Integer> changeAmount) {
		this.changeAmount = changeAmount;
	}
}
