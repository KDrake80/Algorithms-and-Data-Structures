/*
 * Kevin Drake
 * 2/22/22
 * This program prompts the user to enter a string of characters. Then analyzes the string and tells you the maximum
 * amount of consecutive characters
 */
import java.util.*;
public class Exercise22_01 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a string: ");
		String string = input.nextLine();
		string.toLowerCase();

		System.out.println(getIndex(string));
	}
	
	/*
	 * Time Complexity can be broken down to, two constants before the method, two (if-else)constants * (if-else constant),
	 *  to a linear for loop to a constant, two more (if-else) constants, to another linear while loop 
	 *  T(n) = 2c * 2(if-else) * (if-else)c * O(n) * 2(if-else) * O(n)
	 *       = 4c * 5(if-else)c * n (squared)
	 *       = O(n(squared)) = quadratic;
	 */
	
	
	
	
	
	public static void getSubString(String s) {
		String s1 = null;
		String result = null;

		if (getIndex(s) == 0)
			result = s;
		else if (getIndex(s) == 1) {
			if (s.charAt(0) < s.charAt(1))
				result = s;
			else
				result = s.substring(getIndex(s));
		}
		else {
			List<String> subs = new ArrayList<>();
			int i = 0;
			while (s.length() != 0) {
				i = getIndex(s);
				subs.add(s.substring(0, i + 1));
				s1 = s.substring(getIndex(s) + 1);
				s = s1;
			}
			result = subs.get(0);
			for (String m: subs) {
				if (result.length() < m.length())
					result = m;
			}
		}
		System.out.println("Max Consecutive String: " + result);
	}
	public static int getIndex(String s) {
		if (s.length() <= 1) {
			return 0;
		}
		else if (s.length() == 2) {
			if (s.charAt(0) > s.charAt(1))
				return 1;
			else 
				return 0;
		}
		else {
			int index = 0;
			char lowest  = s.charAt(0);
			for (int i = 1; i < s.length() && lowest < s.charAt(i); i++) {
				index = i;
				lowest = s.charAt(i);
			}
			return index;
		}
	}
}