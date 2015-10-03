package khtn.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import khtn.models.DsOrder;

public class OrderUtils {
	static String pathDsOrder = "D:/Android/Java/B10-OrderApp/src/Database/DsOrdered.txt";
	public static void exportDsOrderToFile(DsOrder dsOdr) {
		try {
			ObjectOutputStream objToExport = new ObjectOutputStream
					(new BufferedOutputStream(new FileOutputStream(pathDsOrder)));
			objToExport.writeObject(dsOdr);
			objToExport.close();
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy đường dẫn: "+pathDsOrder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static DsOrder importDsOrderFromFile() {
		try {
			ObjectInputStream objToImport = new ObjectInputStream
					(new BufferedInputStream(new FileInputStream(pathDsOrder)));
			DsOrder dsOdr = (DsOrder) objToImport.readObject();
			objToImport.close();
			return dsOdr;
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file: "+pathDsOrder);
			return null;
		} catch (IOException e) {
			System.out.println("Các Class đã bị chỉnh sửa, nên không thể import!");
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Không tìm thấy Class DsOrder!");
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
