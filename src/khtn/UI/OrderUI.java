package khtn.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import khtn.models.DsOrder;
import khtn.models.DsPThu;
import khtn.models.DsSPham;
import khtn.models.Ordered;
import khtn.models.SanPham;
import khtn.utils.PThuUtils;
import khtn.utils.SPhamUtils;

@SuppressWarnings("serial")
public class OrderUI extends JFrame{
	public OrderUI(String title){
		this.setTitle(title);
	}
	public void showUI() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	JComboBox<String> cbxSanPham;
	JLabel lblImage,lblTongTien;
	JTextField	txtSoLg;
	JButton	btnAdd,btnTinhTien;
	JPanel	pn,pnNorth,pnSouth,pnSouthR,pnCenter,pnCentUp,
			pnCtDown,pnDown1,pnDown2,pnDown3,pnDown4;
	DsSPham dsSP = SPhamUtils.importDsSanPhamFromFile();
	DsOrder dsOdr = new DsOrder();
//	DsPThu dsPT = new DsPThu();
	DsPThu dsPT = PThuUtils.importDsPThuFromFile();
	public void addControls() {
		Container con = getContentPane();con.add(pn = new JPanel());
		pn.setLayout(new BorderLayout());
		pn.add(pnNorth = new JPanel(),BorderLayout.NORTH);
		pn.add(pnCenter = new JPanel(),BorderLayout.CENTER);
		pn.add(pnSouth = new JPanel(), BorderLayout.SOUTH);
		pnNorth.add(cbxSanPham = new JComboBox<>(SPhamUtils.dsTenSP(dsSP)));
		pnNorth.add(lblImage = new JLabel());
		pnCenter.setLayout(new BoxLayout(pnCenter,BoxLayout.Y_AXIS));
		pnCenter.add(pnCentUp = new JPanel());pnCenter.add(pnCtDown = new JPanel());
		pnCentUp.add(txtSoLg = new JTextField(4));
		pnCentUp.add(btnAdd = new JButton("Thêm order"));
		pnCtDown.setLayout(new BoxLayout(pnCtDown, BoxLayout.X_AXIS));
		pnCtDown.add(pnDown1 = new JPanel());pnCtDown.add(pnDown2 = new JPanel());
		pnCtDown.add(pnDown3 = new JPanel());pnCtDown.add(pnDown4 = new JPanel());
		pnDown1.setLayout(new BoxLayout(pnDown1, BoxLayout.Y_AXIS));
		pnDown2.setLayout(new BoxLayout(pnDown2, BoxLayout.Y_AXIS));
		pnDown3.setLayout(new BoxLayout(pnDown3, BoxLayout.Y_AXIS));
		pnDown4.setLayout(new BoxLayout(pnDown4, BoxLayout.Y_AXIS));
		pnSouth.add(btnTinhTien = new JButton("Tính Tiền"));
		pnSouth.add(lblTongTien = new JLabel("Tổng Tiền: "));
		pnSouth.add(pnSouthR = new JPanel());
		pnDown1.add(new JLabel("Tên sản phẩm    "));
		pnDown2.add(new JLabel("Số lượng    "));
		pnDown3.add(new JLabel("Đơn giá    "));
		pnDown4.add(new JLabel("Thành tiền    "));
	}
	public void addEvents() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(PThuUtils.isNumber(txtSoLg.getText())){
					int index = cbxSanPham.getSelectedIndex();
					String tenSP = dsSP.getDsSPham().get(index).getTenSP();
					int soLg = Integer.parseInt(txtSoLg.getText());
					int dGia = dsSP.getDsSPham().get(index).getGiaSP();
					int tTien = soLg*dGia;int tongTien = 0;
					Ordered odr = new Ordered(tenSP,soLg,dGia,tTien);
					dsOdr.addOrder(odr);
					for (Ordered odr2 : dsOdr.getDsOdr()) {
						tongTien += odr2.getTTien();
					}
					pnSouthR.removeAll();
					pnCtDown.revalidate();pnCtDown.repaint();
					pnDown1.add(new JLabel(" "+tenSP));
					pnDown2.add(new JLabel("       "+String.valueOf(soLg)));
					pnDown3.add(new JLabel(" "+String.valueOf(dGia)));
					pnDown4.add(new JLabel(" "+String.valueOf(tTien)));
					pnSouthR.add(new JLabel(" "+String.valueOf(tongTien)));
					pnCtDown.revalidate();pnCtDown.repaint();
				}
			}
		});
		btnTinhTien.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int tongTien=0;
				for (Ordered odr3 : dsOdr.getDsOdr()) {
					tongTien+=odr3.getTTien();
				}JOptionPane.showMessageDialog(null, "Tổng tiền là: "+tongTien);
				pnDown1.removeAll();pnDown2.removeAll();
				pnDown3.removeAll();pnDown4.removeAll();
				pnSouthR.removeAll();
				pnCtDown.revalidate();pnCtDown.repaint();
				pnDown1.add(new JLabel("Tên sản phẩm    "));
				pnDown2.add(new JLabel("Số lượng    "));
				pnDown3.add(new JLabel("Đơn giá    "));
				pnDown4.add(new JLabel("Thành tiền    "));
				pnCtDown.revalidate();pnCtDown.repaint();
				String ID = PThuUtils.creatID(String.valueOf(tongTien));
				dsPT.addDsOrder(ID, dsOdr);
				PThuUtils.exportDsPThuToFile(dsPT);
				dsOdr = new DsOrder();
			}
		});
		cbxSanPham.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = cbxSanPham.getSelectedIndex();
				SanPham sp = dsSP.getDsSPham().get(index);
				try {
					BufferedImage image = ImageIO.read
							(ClassLoader.getSystemResource(sp.getImgSP()));
					lblImage.setIcon(new ImageIcon(image));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy file hình");
				}
			}
		});
	}
}
