package khtn.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SanPham implements Serializable{
	private String maSP;
	private String tenSP;
	private int giaSP;
	private String imgSP;
	
	public SanPham() {
		super();
	}
	public SanPham(String maSP, String tenSP, int giaSP,
			String imgSP) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaSP = giaSP;
		this.imgSP = imgSP;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public int getGiaSP() {
		return giaSP;
	}
	public void setGiaSP(int giaSP) {
		this.giaSP = giaSP;
	}
	public String getImgSP() {
		return imgSP;
	}
	public void setImgSP(String imgSP) {
		this.imgSP = imgSP;
	}
	@Override
	public String toString() {
		return maSP + "   " + tenSP
				+ "   	   	   " + giaSP + "    " + imgSP;
	}
}
