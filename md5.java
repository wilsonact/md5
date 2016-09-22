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
     
	
	// 测试主函数
	public static void main(String[] args) throws Exception {
//		String s = new String("miaorunsheng");
//		System.out.println("原始：" + s);
//		System.out.println("MD5后：" + string2MD5(s));
//		String finpath = args[0];
//		 String foutpath = args[1];

//		File f = new File(finpath);
//        	File fout = new File(foutpath);
		
//		"C:/Users/uatrm990204/Desktop/GPS/data/testdata0809/trans_table_with_id_m7_0809.txt",
//		"C:/Users/uatrm990204/Desktop/GPS/data/testdata0809/output_0809_gps.txt"
		String inpath = args[0];
		String outpath = args[1];
		//String example: 2-3-11
		String lnum = args[2];
		String head = args[3];
		
		String[] lnlist = null;
		lnlist = lnum.split("-");
		int listlen = lnlist.length;

		File f = new File(inpath);
        	File fout = new File(outpath);
        	fout.createNewFile();
        		
		BufferedReader br = new BufferedReader(new FileReader(f));
		FileWriter fileWritter = new FileWriter(fout,true);
        	BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        
	 	String str = "";
		String str1 = "";
		String[] strs = null;
		StringBuffer strbuf = new StringBuffer(); 
		
		//deal with the header
		if(head == "Y"){
		str = br.readLine();	
		bufferWritter.write(str);
//        	System.out.println(str1);
        	bufferWritter.write("\n");
		}
		
		int j = 0;
		while ((str = br.readLine()) != null ) {
//			System.out.println(str);
			strs = str.split(",");
//			System.out.println(strs[0]);
			
			//do md5
			for(int k = 0; k < listlen; k++){
				int l = Integer.parseInt(lnlist[k]);
				strs[l-1] = string2MD5(strs[l-1]);
			}		
				
			// clear buff
			strbuf.delete(0,strbuf.length());
			for(int i = 0; i < strs.length; i++)  {
			
			    strbuf.append(",").append(strs[i]);  
			
			}
//			System.out.println(strbuf); 
			str1 = strbuf.deleteCharAt(0).toString();  
			
			j = j+1;
//			System.out.println(j); 
			
            bufferWritter.write(str1);
//          System.out.println(str1);
            bufferWritter.write("\n");

	}
	br.close();
        bufferWritter.close();
	}
        
	
}
