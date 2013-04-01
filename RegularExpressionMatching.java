package leetcode;

/*
 * Implement regular expression matching with support for ‘.’ and ‘*’.
‘.’ Matches any single character.
‘*’ Matches zero or more of the preceding element.
 * 
 * c* means match 0 or mutil c
 * */
public class RegularExpressionMatching {
	
	public static boolean isMatch(String s, String p, int sp, int pp) {
		if (pp == p.length() - 1) {
			if (sp == s.length()-1) {
				 if (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '.') {
					 return true;
				 }
			} else {
				return false;
			}
		}
		
		if (pp == p.length()-1 || p.charAt(pp+1) != '*') {
			// deal with simplest case, when p.charAt(pp+1) != '*'
			// we have 2 conditions for the above case
			// case 1 , pp is already the last element of pattern, which is pp == p.length()-1
			// case 2, pp is not the last element, but pp's next element is not "*"
			if (p.charAt(pp) == s.charAt(sp) || p.charAt(pp) == '.') {
				return isMatch(s,p,sp+1,pp+1);
			} else {
				return false;
			}
		} else if (pp < p.length()-1 && p.charAt(pp+1) == '*' ) {
			// deal with the case that the next element is "*"
			// case it still has next element, so pp is not the last element, and the next element must be *
			// we need to find the first rightest element that can not match the *
			while (p.charAt(pp) == s.charAt(sp) || p.charAt(pp) == '.') {
				if (isMatch(s,p,sp,pp+2)) {
					// the p[pp+1] = '*', so next element in pattern should be pp+2
					return true;
				}
				sp++;
			}
		}
		
		return false;
	}
	
	
	/*
	public static boolean isMatch(String s, String p, int sp, int pp) {
		if (pp > p.length() || sp > s.length()) {
			return false;
		}
		
		// has a chance to go inside this block
		// because we call isMatch(s,p,sp,pp+2), sometimes
		if (pp == p.length() && p.charAt(pp-1) == '*') {
			// deal with the case that, the last element of parttern is '*'
			return sp == s.length()-1;
		}
		
		if (sp == s.length()-1 && pp != p.length() - 1)
			return false;
		
		// only chance to return true
		// if its the end of p
		// and sp is also the end of s, return true, otherwise return false
//		if (pp == p.length()-1) {
//			
//			return sp == s.length()-1;
//		}
		
		if (pp == p.length()-1 && sp == s.length()-1) {
			if (p.charAt(pp) == s.charAt(sp) || (p.charAt(pp) == '*' && (p.charAt(pp-1) == s.charAt(sp) || p.charAt(pp-1) == '.'))) {
				return true;
			} else {
				return false;
			}
		}
		
		
		if (p.charAt(pp+1) != '*') {
			// if the next element is not "*"
			if (p.charAt(pp) == s.charAt(sp) || p.charAt(pp) == '.') {
				return isMatch(s,p,sp+1,pp+1);
			} else {
				return false;
			}
		} else {
			// next element is "*"
			while(sp < s.length() && (p.charAt(pp) == s.charAt(sp) || p.charAt(pp) == '.')) {
				if (isMatch(s,p,sp,pp+2)) {
					return true;
				}
				sp++;
			}
			return isMatch(s,p,sp,pp+2);
		}
	} */
	
	public static boolean isMatch(String s, String p) {
		return isMatch(s,p,0,0);
	}
	
	public static void main(String[] args) {
		//System.out.println(isMatch("aab","c*a*b"));
		System.out.println(isMatch("aab",".*a"));
	}
}
