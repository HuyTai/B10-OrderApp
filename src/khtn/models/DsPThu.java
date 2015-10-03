package khtn.models;

import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("serial")
public class DsPThu implements Serializable{
	HashMap<String, DsOrder> dsPT = new HashMap<>();

	public DsPThu() {
		super();
	}
	public DsPThu(HashMap<String, DsOrder> dsPT) {
		super();
		this.dsPT = dsPT;
	}
	public HashMap<String, DsOrder> getDsPT() {
		return dsPT;
	}
	public void setDsPT(HashMap<String, DsOrder> dsPT) {
		this.dsPT = dsPT;
	}
	public void addDsOrder(String ID, DsOrder dsOdr) {
		this.dsPT.put(ID, dsOdr);
	}
	public int size() {
		return this.dsPT.size();
	}
	@Override
	public String toString() {
		return "DsPThu [" + dsPT + "]";
	}
}
