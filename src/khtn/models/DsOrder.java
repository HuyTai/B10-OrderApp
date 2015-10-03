package khtn.models;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DsOrder implements Serializable{
	ArrayList<Ordered> dsOdr = new ArrayList<Ordered>();

	public DsOrder() {
		super();
	}
	public DsOrder(ArrayList<Ordered> dsOdr) {
		super();
		this.dsOdr = dsOdr;
	}
	public ArrayList<Ordered> getDsOdr() {
		return dsOdr;
	}
	public void setDsOdr(ArrayList<Ordered> dsOdr) {
		this.dsOdr = dsOdr;
	}
	public void addOrder(Ordered odr) {
		this.dsOdr.add(odr);
	}
	public int size() {
		return this.dsOdr.size();
	}
	@Override
	public String toString() {
		return "DsOrder [" + dsOdr + "]";
	}
}
