package khtn.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import khtn.models.DsPThu;
import khtn.utils.PThuUtils;

@SuppressWarnings("serial")
public class PhieuThuUI extends JFrame{
	public PhieuThuUI(String title){
		this.setTitle(title);
	}
	public void showUI(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 800);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	JPanel pn,pnNorth,pnSouth,pnCenter,pnCenUp,pnCenDwn,
			pnDwnDate,pnDwnTime,pnDwnTong,pnDwnCTiet;
	JLabel lblTitle,lblTong,lblChon;
	JComboBox<String> cbxDsPT;
	JButton btnXoaPT;
	DsPThu dsPT = PThuUtils.importDsPThuFromFile();
	String[]arrDsPT = PThuUtils.hashToArr(dsPT);
	String chonPT = "";
	public void addControls() {
		Container con = getContentPane();con.add(pn = new JPanel());
		pn.setLayout(new BorderLayout());
		pn.add(pnNorth = new JPanel(),BorderLayout.NORTH);
		pn.add(pnSouth = new JPanel(),BorderLayout.SOUTH);
		pn.add(pnCenter = new JPanel(),BorderLayout.CENTER);
		pnNorth.add(lblTitle = new JLabel("DANH SÁCH PHIẾU THU"));
		pnSouth.add(lblTong = new JLabel("TỔNG CỘNG: "));
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnCenter.add(pnCenUp =new JPanel());pnCenter.add(pnCenDwn =new JPanel());
		pnCenUp.add(lblChon = new JLabel("Chọn phiếu muốn xóa: "));
		pnCenUp.add(cbxDsPT = new JComboBox<>(PThuUtils.dsID(arrDsPT)));
		pnCenUp.add(btnXoaPT = new JButton("Xóa phiếu"));
		pnCenDwn.setLayout(new BoxLayout(pnCenDwn, BoxLayout.X_AXIS));
		pnCenDwn.add(pnDwnDate = new JPanel());pnCenDwn.add(pnDwnTime = new JPanel());
		pnCenDwn.add(pnDwnTong = new JPanel());pnCenDwn.add(pnDwnCTiet = new JPanel());
		pnDwnDate.setLayout(new BoxLayout(pnDwnDate, BoxLayout.Y_AXIS));
		pnDwnTime.setLayout(new BoxLayout(pnDwnTime, BoxLayout.Y_AXIS));
		pnDwnTong.setLayout(new BoxLayout(pnDwnTong, BoxLayout.Y_AXIS));
		pnDwnCTiet.setLayout(new BoxLayout(pnDwnCTiet, BoxLayout.Y_AXIS));
		pnDwnDate.add(new JLabel("      Ngày     "));
		pnDwnTime.add(new JLabel("     Giờ     "));
		pnDwnTong.add(new JLabel("Tổng tiền     "));
		pnDwnCTiet.add(new JLabel(" Chi tiết  "));
		for (String pt : arrDsPT) {
			pnDwnDate.add(new JLabel(PThuUtils.getDay(pt)+"   "));
			pnDwnTime.add(new JLabel(PThuUtils.getTime(pt)+"   "));
			pnDwnTong.add(new JLabel("  "+PThuUtils.getTong(pt)+"   "));
			pnDwnCTiet.add(new JLabel(PThuUtils.getTenSP(pt)+"   "));
		}
	}
	public void addEvents() {
		cbxDsPT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chonPT = (String) cbxDsPT.getSelectedItem();
			}
		});
		btnXoaPT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(dsPT.getDsPT().containsKey(chonPT)){
					dsPT.getDsPT().remove(chonPT);
					pnDwnDate.removeAll();pnDwnTime.removeAll();
					pnDwnTong.removeAll();pnDwnCTiet.removeAll();
					pnCenDwn.revalidate();pnCenDwn.repaint();
					pnDwnDate.add(new JLabel("      Ngày     "));
					pnDwnTime.add(new JLabel("     Giờ     "));
					pnDwnTong.add(new JLabel("Tổng tiền     "));
					pnDwnCTiet.add(new JLabel(" Chi tiết  "));
					pnCenDwn.revalidate();pnCenDwn.repaint();
					PThuUtils.exportDsPThuToFile(dsPT);
					dsPT = PThuUtils.importDsPThuFromFile();
					arrDsPT = PThuUtils.hashToArr(dsPT);
					for (String pt : arrDsPT) {
						pnDwnDate.add(new JLabel(PThuUtils.getDay(pt)+"   "));
						pnDwnTime.add(new JLabel(PThuUtils.getTime(pt)+"   "));
						pnDwnTong.add(new JLabel("  "+PThuUtils.getTong(pt)+"   "));
						pnDwnCTiet.add(new JLabel(PThuUtils.getTenSP(pt)+"   "));
					}pnCenDwn.revalidate();pnCenDwn.repaint();
					pnCenUp.removeAll();
					pnCenUp.revalidate();pnCenUp.repaint();
					pnCenUp.add(lblChon = new JLabel("Chọn phiếu muốn xóa: "));
					pnCenUp.add(cbxDsPT = new JComboBox<>(PThuUtils.dsID(arrDsPT)));
					pnCenUp.add(btnXoaPT = new JButton("Xóa phiếu"));
				}
			}
		});
	}
}
