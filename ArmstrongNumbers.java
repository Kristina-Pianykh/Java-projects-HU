public class ArmstrongNumbers {
	    /**
     * 
     * @param args
     *        Returns as many Armstrong numbers as stipulated by a command-line argument
     */
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int []armstrongArr = giveArmstrongNumbers(n);
		printArray(armstrongArr);
	}

	public static boolean isArmstrongNumber(int number) {
		double sum = 0;
		int num = number;
		int numDigits  = String.valueOf(number).length();
		int [] digits = new int[numDigits];
		for (int i=digits.length-1; i>=0; i--) {
			int rest = num%10;
			num = num/10;
			digits[i] = rest;
		}
		for (int i=0; i<digits.length; i++)
			sum = sum + Math.pow((double) digits[i], (double) numDigits); //why undefined?
		if ((int) sum == number)
			return true;
		else return false;
	}

	public static int[] giveArmstrongNumbers(int n) {
		int number = 0;
		int idx = 0;
		int [] ArmstrongNums = new int[n];
		while (idx <= n-1) {
			if (isArmstrongNumber(number)) {
				ArmstrongNums[idx] = number;
				number++;
				idx++;
			}
			else number++;
		}
		return ArmstrongNums;
	}

	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			System.out.print(i < a.length - 1 ? ", " : "\n");
		}
	}
}
