package khtn.main;

import khtn.UI.OrderUI;
import khtn.UI.PhieuThuUI;
import khtn.UI.SanPhamUI;

public class Main {
	public static void main(String[] args) {
//		openSanPhamUI();
		openOrderUI();
//		openPhieuThuUI();
	}
	public static void openPhieuThuUI() {
		PhieuThuUI ptUI = new PhieuThuUI("Danh Mục Phiếu Thu");
		ptUI.addControls();
		ptUI.addEvents();
		ptUI.showUI();
	}
	public static void openSanPhamUI() {
		SanPhamUI spUI = new SanPhamUI("Danh Mục Sản Phẩm");
		spUI.addControls();
		spUI.addEvents();
		spUI.showUI();
	}
	public static void openOrderUI() {
		OrderUI odrUI = new OrderUI("Danh Mục Order");
		odrUI.addControls();
		odrUI.addEvents();
		odrUI.showUI();
	}
}
