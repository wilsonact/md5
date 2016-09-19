package md5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;


public  class md5 {
    
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	

	// main function
	public static void main(String args[]) throws Exception {
//		String s = new String("wilsonact");
//		System.out.println("原始：" + s);
//		System.out.println("MD5后：" + string2MD5(s));
//		String finpath = args[0];
//		 String foutpath = args[1];

		File f = new File("C:/data/source.txt");
    File fout = new File("C:/data/result.txt");
    fout.createNewFile();
        		
		BufferedReader br = new BufferedReader(new FileReader(f));
		FileWriter fileWritter = new FileWriter(fout,true);
    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        
        String str = "";
		String[] strs = null;
		StringBuffer strbuf = new StringBuffer(); 
		
		str = br.readLine();
		strs = str.split(",");
		for(int i = 0; i < strs.length; i++)  {
				strbuf.append(",").append(strs[i]);  
		}
		
//	System.out.println(strbuf); 
		String str1 = strbuf.deleteCharAt(0).toString();
		bufferWritter.write(str1);
//  System.out.println(str1);
    bufferWritter.write("\n");
		
		int j = 0;
		while ((str = br.readLine()) != null && j<5 ) {
			System.out.println(str);
			strs = str.split("|");
			System.out.println(strs[0]);
			
			strs[0] = string2MD5(strs[0]);
			String a = strs[8];
			a = URLEncoder.encode(a, "GBK");
			a = URLDecoder.decode(a, "UTF-8");
			System.out.println(strs[10]);
			strs[8] = a;
			 
			strbuf.delete(0,strbuf.length());
			for(int i = 0; i < strs.length; i++)  {
					strbuf.append(",").append(strs[i]);  
			}
			
//		System.out.println(strbuf); 
			str1 = strbuf.deleteCharAt(0).toString();  
			
			j = j+1;
//		System.out.println(j); 
			
      bufferWritter.write(str1);
//    System.out.println(str1);
      bufferWritter.write("\n");

	  }
		br.close();
    bufferWritter.close();
	}
	
}
