package khtn.main;

import javax.swing.JOptionPane;
import khtn.UI.OrderUI;
import khtn.UI.PhieuThuUI;
import khtn.UI.SanPhamUI;
import khtn.utils.PThuUtils;
import khtn.utils.SPhamUtils;

public class Main {
	public static void main(String[] args) {
		openSanPhamUI();
		openOrderUI();
		openPhieuThuUI();
	}
	public static void openSanPhamUI() {
		SanPhamUI spUI = new SanPhamUI("Danh Mục Sản Phẩm");
		spUI.addControls();
		spUI.addEvents();
		spUI.showUI();
	}
	public static void openOrderUI() {
		if(SPhamUtils.importDsSanPhamFromFile().getDsSPham().isEmpty())
			JOptionPane.showMessageDialog(null, "Danh sách sản phẩm trống");
		else{
			OrderUI odrUI = new OrderUI("Danh Mục Order");
			odrUI.addControls();
			odrUI.addEvents();
			odrUI.showUI();
		}
	}
	public static void openPhieuThuUI() {
		if(PThuUtils.importDsPThuFromFile().getDsPT().isEmpty())
			JOptionPane.showMessageDialog(null, "Danh sách phiếu thu trống");
		else{
			PhieuThuUI ptUI = new PhieuThuUI("Danh Mục Phiếu Thu");
			ptUI.addControls();
			ptUI.addEvents();
			ptUI.showUI();
		}
	}
}
