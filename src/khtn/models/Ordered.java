package khtn.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Ordered implements Serializable{
	private String tenOdr;
	private int soLg;
	private int dGia;
	private int tTien;
	
	public Ordered() {
		super();
	}
	public Ordered(String tenOdr, int soLg) {
		super();
		this.tenOdr = tenOdr;
		this.soLg = soLg;
		DsSPham arr = new DsSPham();
		for (SanPham sp : arr.getDsSPham()) {
			if(sp.getTenSP().equals(tenOdr)){
				this.dGia = sp.getGiaSP();
				this.tTien = soLg*dGia;
			}
		}
	}
	public Ordered(String tenOdr, int soLg, int dGia,
			int tTien) {
		super();
		this.tenOdr = tenOdr;
		this.soLg = soLg;
		this.dGia = dGia;
		this.tTien = tTien;
	}
	public String getTenOdr() {
		return tenOdr;
	}
	public void setTenSP(String tenOdr) {
		this.tenOdr = tenOdr;
	}
	public int getSoLg() {
		return soLg;
	}
	public void setSoLg(int soLg) {
		this.soLg = soLg;
	}
	public int getDGia() {
		return dGia;
	}
	public void setDGia(int dGia) {
		this.dGia = dGia;
	}
	public int getTTien() {
		return tTien;
	}
	public void setTTien(int tTien) {
		this.tTien = tTien;
	}
	@Override
	public String toString() {
		return tenOdr + " ("
				+ soLg + "x" + (dGia/1000)
				+ "=" + (tTien/1000)+")";
	}
	
}
