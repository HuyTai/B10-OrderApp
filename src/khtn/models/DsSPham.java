package khtn.models;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DsSPham implements Serializable{
	ArrayList<SanPham> dsSPham = new ArrayList<SanPham>();

	public DsSPham() {
		super();
	}
	public DsSPham(ArrayList<SanPham> dsSPham) {
		super();
		this.dsSPham = dsSPham;
	}
	public ArrayList<SanPham> getDsSPham() {
		return dsSPham;
	}
	public void setDsSPham(ArrayList<SanPham> dsSPham) {
		this.dsSPham = dsSPham;
	}
	public void addSPham(SanPham sp) {
		dsSPham.add(sp);
	}
	public int size() {
		return this.dsSPham.size();
	}
	@Override
	public String toString() {
		return "DsSPham [" + dsSPham + "]";
	}
}
