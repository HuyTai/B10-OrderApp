package khtn.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

import khtn.models.DsSPham;
import khtn.models.SanPham;

public class SPhamUtils {
	static String pathDsSPham = "D:/Android/Java/B10-OrderApp/src/Database/DsSanPham.txt";
	public static String[] dsTenSP(DsSPham dsSP) {
		String[] dsTen = new String[dsSP.size()];
		for(int i=0;i<dsSP.size();i++){
			dsTen[i] = dsSP.getDsSPham().get(i).getTenSP();
		}return dsTen;
	}
	public static int sizeOfDsSP() {
		DsSPham dsSP = new DsSPham();
		return dsSP.size();
	}
	public static SanPham taoSanPham(DsSPham dsSP) {
		Scanner nhapLieu = new Scanner(System.in);
		SanPham sp = new SanPham();String taoMa = "SP";
		if(dsSP.size()<10)taoMa = "0"+dsSP.size()+1;
		else taoMa +=(dsSP.size()+1);
		sp.setMaSP(taoMa);
		System.out.print("Nhập tên sản phẩm");
		sp.setTenSP(nhapLieu.nextLine());
		System.out.print("Nhập giá sản phẩm");
		sp.setGiaSP(nhapLieu.nextInt());
		System.out.print("Nhập hình sản phẩm");
		sp.setImgSP(nhapLieu.nextLine());
		nhapLieu.close();
		return sp;
	}
	public static DsSPham taoDsSPham() {
		DsSPham dsSP = new DsSPham();
		dsSP.addSPham(new SanPham("SP01", "Nuoc ep", 25000, "menu-drinks.png"));
		dsSP.addSPham(new SanPham("SP02", "Bia", 30000, "britvic-tango-citrus.jpg"));
		dsSP.addSPham(new SanPham("SP03", "Nuoc ngot", 18000, "flavour-drink.png"));
		dsSP.addSPham(new SanPham("SP04", "sua", 15000, "yazoo-chocolate-milkshake.jpg"));
		return dsSP;
	}
	public static void exportDsSanPhamToFile(DsSPham dsSP) {
		try {
			ObjectOutputStream objToExport = new ObjectOutputStream
					(new BufferedOutputStream(new FileOutputStream(pathDsSPham)));
			objToExport.writeObject(dsSP);
			objToExport.close();
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy đường dẫn: "+pathDsSPham);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static DsSPham importDsSanPhamFromFile() {
		try {
			ObjectInputStream objFromFile = new ObjectInputStream
					(new BufferedInputStream(new FileInputStream(pathDsSPham)));
			DsSPham dsSP = (DsSPham) objFromFile.readObject();
			objFromFile.close();
			return dsSP;
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file: "+pathDsSPham);
			return null;
		} catch (IOException e) {
			System.out.println("Các Class đã bị chỉnh sửa, nên không thể import!");
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Không tìm thấy Class DsSPham!");
			return null;
		}
	}
	public static boolean isNumber(String number) {
		try{
			Integer.parseInt(number);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Lỗi! hãy nhập số vào ô đơn giá.");
			return false;
		}return true;
	}
}
