package leetcode;

public class lengthOfLastWord {
	 public static int lengthOfLastWord(String s) {
		 if (s == null || s.length() == 0) {
			 return 0;
		 }
		 
		 char[] ch = s.toCharArray();
		 int start = -1;
		 int end = -1;
		 
		 for (int i = ch.length-1; i >= 0; i--) {
			 if (ch[i] != ' ') {
				 end = i;
				 break;
			 }
		 }
		 
		 for (int i = end-1; i >= 0; i--) {
			 if (ch[i] == ' ') {
				 start = i;
				 break;
			 }
		 }
		 
		 return (end-start) > 0 ? (end-start) : 0;
	 }
	 
	 public static void main(String[] args) {
		 String s = "Hello World";
		 System.out.println(lengthOfLastWord(s));
	 }
}

