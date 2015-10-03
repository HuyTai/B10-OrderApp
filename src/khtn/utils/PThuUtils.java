package khtn.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import khtn.models.DsPThu;

public class PThuUtils {
	static String pathDsPThu = "D:/Android/Java/B10-OrderApp/src/Database/DsPhieuThu.txt";
	public static void exportDsPThuToFile(DsPThu dsPT) {
		try {
			ObjectOutputStream objToExport = new ObjectOutputStream
					(new BufferedOutputStream(new FileOutputStream(pathDsPThu)));
			objToExport.writeObject(dsPT);
			objToExport.close();
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy đường dẫn: "+pathDsPThu);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static DsPThu importDsPThuFromFile() {
		try {
			ObjectInputStream objToImport = new ObjectInputStream
					(new BufferedInputStream(new FileInputStream(pathDsPThu)));
			DsPThu dsPT = (DsPThu) objToImport.readObject();
			objToImport.close();
			return dsPT;
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file: "+pathDsPThu);
			return null;
		} catch (IOException e) {
			System.out.println("Các Class đã bị chỉnh sửa, không thể import!");
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Không tìm thấy Class DsPThu!");
			return null;
		}
	}
	public static String[] hashToArr(DsPThu dspt) {
		String strDsPT = dspt.getDsPT().toString().replace("], ", "@");
		strDsPT = strDsPT.replace("{", "");strDsPT = strDsPT.replace("}", "");
		strDsPT = strDsPT.replace("[[", "[");strDsPT = strDsPT.replace("]]", "]");
		String[]arrDsPT = strDsPT.split("@");
		return arrDsPT;
	}
	public static String[] dsID(String[] arrDsPT) {
		String[]dsID = new String[arrDsPT.length];int i=0;
		for (String pt : arrDsPT) {
			dsID[i]=getID(pt);
			i+=1;
		}return dsID;
	}
	public static String creatID(String tongTien) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
		StringBuilder ID = new StringBuilder();StringTokenizer dateToken =
				new StringTokenizer(dateFormat.format(new Date()).toString());
		while(dateToken.hasMoreTokens()){
			ID.append(dateToken.nextToken());ID.append("#");
		}ID.append(tongTien);ID.append("#");
		return String.valueOf(ID);
	}
	public static String getDay(String pt) {
		String[]keyToken = pt.split("#");
		return keyToken[0];
	}
	public static String getTime(String pt) {
		String[]keyToken = pt.split("#");
		return keyToken[1];
	}
	public static String getTong(String pt) {
		String[]keyToken = pt.split("#");
		return keyToken[2];
	}
	public static String getID(String pt) {
		String[]keyToken = pt.split("#");
		return keyToken[0]+"#"+keyToken[1]+"#"+keyToken[2]+"#";
	}
	public static String getCTiet(String pt) {
		String[]keyToken = pt.split("#");
		return keyToken[3].replaceAll("=DsOrder ", "");
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
