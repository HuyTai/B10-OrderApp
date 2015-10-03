package khtn.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import khtn.models.DsSPham;
import khtn.models.SanPham;
import khtn.utils.SPhamUtils;

@SuppressWarnings("serial")
public class SanPhamUI extends JFrame{
	public SanPhamUI(String title) {
		this.setTitle(title);
	}
	public void showUI() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 800);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	JLabel lblTitle,lblTenSP,lblGiaSP,lblHinhSP,lblMaSP;
	JTextField txtTenSP,txtGiaSP,txtHinhSP,txtMaSP;
	JButton btnTaoSP,btnXoaSP,btnSuaSP;
	JPanel pn, pnNorth, pnCenter, pnUp, pnDown,
		pnDown1, pnDown2, pnDown3, pnDown4;
//	DsSanPham dsSP = SanPhamUtils.taoDsSanPham();
	DsSPham dsSP = SPhamUtils.importDsSanPhamFromFile();
	
	public void addControls() {
		Container con = getContentPane();con.add(pn = new JPanel());
		pn.setLayout(new BorderLayout());
		pn.add(pnNorth = new JPanel(),BorderLayout.NORTH);
		pnNorth.add(lblTitle = new JLabel("DANH MỤC SẢN PHẨM"));
		pn.add(pnCenter = new JPanel(),BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnCenter.add(pnUp = new JPanel());
		lblTenSP = new JLabel("Nhập tên:");txtTenSP = new JTextField(25);
		lblGiaSP = new JLabel("Nhập giá:");txtGiaSP = new JTextField(25);
		lblHinhSP = new JLabel("Tên Hình:");txtHinhSP = new JTextField(25);
		lblMaSP = new JLabel(" Nhập mã sản phẩm để xóa hoặc sửa:");
		txtMaSP = new JTextField(10);btnTaoSP = new JButton("Thêm sản phẩm");
		btnXoaSP = new JButton("Xóa sản phẩm");btnSuaSP = new JButton("Sửa sản phẩm");
		pnUp.add(lblTenSP);pnUp.add(txtTenSP);pnUp.add(lblGiaSP);
		pnUp.add(txtGiaSP);pnUp.add(lblHinhSP);pnUp.add(txtHinhSP);
		pnUp.add(btnTaoSP);pnUp.add(btnXoaSP);pnUp.add(btnSuaSP);
		pnUp.add(lblMaSP);pnUp.add(txtMaSP);
		pnCenter.add(pnDown = new JPanel(),BorderLayout.SOUTH);
		pnDown.setLayout(new BoxLayout(pnDown, BoxLayout.X_AXIS));
		pnDown.add(pnDown1 = new JPanel());pnDown.add(pnDown2 = new JPanel());
		pnDown.add(pnDown3 = new JPanel());pnDown.add(pnDown4 = new JPanel());
		pnDown1.setLayout(new BoxLayout(pnDown1, BoxLayout.Y_AXIS));
		pnDown2.setLayout(new BoxLayout(pnDown2, BoxLayout.Y_AXIS));
		pnDown3.setLayout(new BoxLayout(pnDown3, BoxLayout.Y_AXIS));
		pnDown4.setLayout(new BoxLayout(pnDown4, BoxLayout.Y_AXIS));
		pnDown1.add(new JLabel("Mã SP    "));
		pnDown2.add(new JLabel("Tên sản phẩm    "));
		pnDown3.add(new JLabel("Giá tiền    "));
		pnDown4.add(new JLabel("Tên hình           "));
		for (SanPham sp2 : dsSP.getDsSPham()) {
			pnDown1.add(new JLabel(sp2.getMaSP().toString()+"   "));
			pnDown2.add(new JLabel(sp2.getTenSP().toString()+"   "));
			pnDown3.add(new JLabel(String.valueOf(sp2.getGiaSP())+"   "));
			pnDown4.add(new JLabel(sp2.getImgSP().toString()+"   "));
		}SPhamUtils.exportDsSanPhamToFile(dsSP);
	}
	public void addEvents() {
		btnTaoSP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(SPhamUtils.isNumber(txtGiaSP.getText())){
					String maMax = dsSP.getDsSPham().get((dsSP.size()-1)).getMaSP();
					StringTokenizer token = new StringTokenizer(maMax,"SP");
					int soMax=0;String taoMa = "SP";
					if(token.hasMoreTokens())soMax = Integer.parseInt(token.nextToken());
					if(soMax<10)taoMa += "0"+(soMax+1);
					else taoMa +=(soMax+1);
					SanPham sp = new SanPham(taoMa, txtTenSP.getText(), 
							Integer.parseInt(txtGiaSP.getText()), txtHinhSP.getText());
					dsSP.addSPham(sp);
					SPhamUtils.exportDsSanPhamToFile(dsSP);
					pnDown1.add(new JLabel(sp.getMaSP().toString()+"   "));
					pnDown2.add(new JLabel(sp.getTenSP().toString()+"   "));
					pnDown3.add(new JLabel(String.valueOf(sp.getGiaSP())+"   "));
					pnDown4.add(new JLabel(sp.getImgSP().toString()+"   "));
					txtTenSP.setText("");txtGiaSP.setText("");txtHinhSP.setText("");
					pnDown.revalidate();pnDown.repaint();
				}
			}
		});
		btnXoaSP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DsSPham dssp = SPhamUtils.importDsSanPhamFromFile();
				for (SanPham sp : dssp.getDsSPham()) {
					if(sp.getMaSP().equalsIgnoreCase(txtMaSP.getText())){
						dssp.getDsSPham().remove(sp);txtMaSP.setText("");
						SPhamUtils.exportDsSanPhamToFile(dssp);
						dssp = SPhamUtils.importDsSanPhamFromFile();
						pnDown.revalidate();pnDown.repaint();
						pnDown1.removeAll();pnDown2.removeAll();
						pnDown3.removeAll();pnDown4.removeAll();
						pnDown.revalidate();pnDown.repaint();
						pnDown1.add(new JLabel("Mã SP    "));
						pnDown2.add(new JLabel("Tên sản phẩm    "));
						pnDown3.add(new JLabel("Giá tiền    "));
						pnDown4.add(new JLabel("Tên hình           "));
						pnDown.revalidate();pnDown.repaint();
						for (SanPham sp2 : dssp.getDsSPham()) {
							pnDown1.add(new JLabel(sp2.getMaSP().toString()+"   "));
							pnDown2.add(new JLabel(sp2.getTenSP().toString()+"   "));
							pnDown3.add(new JLabel(String.valueOf(sp2.getGiaSP())+"   "));
							pnDown4.add(new JLabel(sp2.getImgSP().toString()+"   "));
							pnDown.revalidate();pnDown.repaint();
						}
					}
				}
			}
		});
		btnSuaSP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DsSPham dssp = SPhamUtils.importDsSanPhamFromFile();
				for (SanPham sp : dssp.getDsSPham()) {
					if(sp.getMaSP().equalsIgnoreCase(txtMaSP.getText())){
						if(SPhamUtils.isNumber(txtGiaSP.getText())&&txtTenSP!=null&&txtHinhSP!=null){
							sp.setTenSP(txtTenSP.getText());
							sp.setGiaSP(Integer.parseInt(txtGiaSP.getText()));
							sp.setImgSP(txtHinhSP.getText());
						}
						txtMaSP.setText("");txtTenSP.setText("");
						txtGiaSP.setText("");txtHinhSP.setText("");
						SPhamUtils.exportDsSanPhamToFile(dssp);
						dssp = SPhamUtils.importDsSanPhamFromFile();
						pnDown.revalidate();pnDown.repaint();
						pnDown1.removeAll();pnDown2.removeAll();
						pnDown3.removeAll();pnDown4.removeAll();
						pnDown.revalidate();pnDown.repaint();
						pnDown1.add(new JLabel("Mã SP    "));
						pnDown2.add(new JLabel("Tên sản phẩm    "));
						pnDown3.add(new JLabel("Giá tiền    "));
						pnDown4.add(new JLabel("Tên hình           "));
						pnDown.revalidate();pnDown.repaint();
						for (SanPham sp2 : dssp.getDsSPham()) {
							pnDown1.add(new JLabel(sp2.getMaSP().toString()+"   "));
							pnDown2.add(new JLabel(sp2.getTenSP().toString()+"   "));
							pnDown3.add(new JLabel(String.valueOf(sp2.getGiaSP())+"   "));
							pnDown4.add(new JLabel(sp2.getImgSP().toString()+"   "));
							pnDown.revalidate();pnDown.repaint();
						}
					}
				}
				
			}
		});
	}
}
