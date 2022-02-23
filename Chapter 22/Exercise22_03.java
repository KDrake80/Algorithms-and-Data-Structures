/*
 * Kevin Drake
 * 2/22/22
 * This program prompts user to enter two strings, and then compare to see if the second string is a substring of the first
 * 
 */
import java.util.Scanner;
public class Exercise22_03 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the first string: ");
		String main = input.nextLine();
		System.out.println("Enter the second String: ");
		String compare = input.nextLine();

		int index = 0;
		boolean contains = false;
		for (int i = 0; i < main.length(); i++) {
			if (compare.charAt(0) == main.charAt(i)) {
				index = i;
				if (main.contains(compare)) {
					contains = true;
				}
			}
		}
		if (contains) {
			System.out.println("String 1: " + main);
			System.out.println("String 2: " + compare);
			System.out.println("Matched at index: " + index);
		}
	}
}
/*
 * 
 *  Time Complexity is O(n) (linear); My loop grows at a linear rate with if statements. The index = i; contains = true; are constants
 *  as well as the print statements. So time complexity is O(n);
 * 
 * 
 */
