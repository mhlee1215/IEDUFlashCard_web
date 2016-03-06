package edu.iedu.flashcard.dao.util;

import java.lang.Character.UnicodeBlock;
public class MyEncoder {

	public static String decode(String unicode) throws Exception {
		StringBuffer str = new StringBuffer();

		char ch = 0;
		for (int i = unicode.indexOf("\\u"); i > -1; i = unicode.indexOf("\\u")) {
			ch = (char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16);
			str.append(unicode.substring(0, i));
			str.append(String.valueOf(ch));
			unicode = unicode.substring(i + 6);
		}
		str.append(unicode);

		return str.toString();
	}

	public static String encode(String unicode) throws Exception {
		StringBuffer str = new StringBuffer();

		for (int i = 0; i < unicode.length(); i++) {
			if (((int) unicode.charAt(i) == 32)) {
				str.append(" ");
				continue;
			}
			str.append("\\u");
			str.append(Integer.toHexString((int) unicode.charAt(i)));

		}

		return str.toString();

	}
	
	public boolean containsHangul(String str)
	{
	    for(int i = 0 ; i < str.length() ; i++)
	    {
	        char ch = str.charAt(i);
	        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
	        if(UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock) ||
	                UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock) ||
	                UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock))
	            return true;
	    }
	    return false;
	}
	
	public static String dropHangul(String str)
	{
		String non_hangul_str = "";
	    for(int i = 0 ; i < str.length() ; i++)
	    {
	        char ch = str.charAt(i);
	        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
	        if(UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock) ||
	                UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock) ||
	                UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock)){
	        	//non_hangul_str += ch;
	        }else{
	        	non_hangul_str += ch;
	        }
	            
	    }
	    return non_hangul_str;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(dropHangul("abdf한글12312"));
//		String str = encode("한 글");
//		System.out.println(str);
//		System.out.println(decode(str));
//		
//		String word = "무궁화 꽃이 피었습니다.";
//		System.out.println("utf-8 -> euc-kr        : " + new String(word.getBytes("utf-8"), "euc-kr"));
//		System.out.println("utf-8 -> ksc5601       : " + new String(word.getBytes("utf-8"), "ksc5601"));
//		System.out.println("utf-8 -> x-windows-949 : " + new String(word.getBytes("utf-8"), "x-windows-949"));
//		System.out.println("utf-8 -> iso-8859-1    : " + new String(word.getBytes("utf-8"), "iso-8859-1"));
//		System.out.println("iso-8859-1 -> euc-kr        : " + new String(word.getBytes("iso-8859-1"), "euc-kr"));
//		System.out.println("iso-8859-1 -> ksc5601       : " + new String(word.getBytes("iso-8859-1"), "ksc5601"));
//		System.out.println("iso-8859-1 -> x-windows-949 : " + new String(word.getBytes("iso-8859-1"), "x-windows-949"));
//		System.out.println("iso-8859-1 -> utf-8         : " + new String(word.getBytes("iso-8859-1"), "utf-8"));
//		System.out.println("euc-kr -> utf-8         : " + new String(word.getBytes("euc-kr"), "utf-8"));
//		System.out.println("euc-kr -> ksc5601       : " + new String(word.getBytes("euc-kr"), "ksc5601"));
//		System.out.println("euc-kr -> x-windows-949 : " + new String(word.getBytes("euc-kr"), "x-windows-949"));
//		System.out.println("euc-kr -> iso-8859-1    : " + new String(word.getBytes("euc-kr"), "iso-8859-1"));
//		System.out.println("ksc5601 -> euc-kr        : " + new String(word.getBytes("ksc5601"), "euc-kr"));
//		System.out.println("ksc5601 -> utf-8         : " + new String(word.getBytes("ksc5601"), "utf-8"));
//		System.out.println("ksc5601 -> x-windows-949 : " + new String(word.getBytes("ksc5601"), "x-windows-949"));
//		System.out.println("ksc5601 -> iso-8859-1    : " + new String(word.getBytes("ksc5601"), "iso-8859-1"));
//		System.out.println("x-windows-949 -> euc-kr     : " + new String(word.getBytes("x-windows-949"), "euc-kr"));
//		System.out.println("x-windows-949 -> utf-8      : " + new String(word.getBytes("x-windows-949"), "utf-8"));
//		System.out.println("x-windows-949 -> ksc5601    : " + new String(word.getBytes("x-windows-949"), "ksc5601"));
//		System.out.println("x-windows-949 -> iso-8859-1 : " + new String(word.getBytes("x-windows-949"), "iso-8859-1"));
	}
}