/**
 * @author Kristina Pianykh
 * Moodle: pianykhk
 * Group: 114
 */
public class Nolindrom {

	static long reverse(long v) {
			long result = 0;
			while(v != 0){
				result = result*10+v%10;   
				v = v/10;
			}
			return result;
		}
	
	static int[] long_to_array(long long_num) {
		String long_to_str = Long.toString(long_num); //convert long to string
		int long_lgth = long_to_str.length(); //length of the given long
		int [] arr = new int[long_lgth]; //create an array of the same length as the given long
		//System.out.println(arr.length);
		for (int i = long_lgth - 1; i >= 0; i--) {
			//System.out.println(i);
			arr[i] = (int) long_num % 10;
			//System.out.println(arr[i]);
			//System.out.println(arr);
			long_num = long_num / 10;
		}
		return arr;
	}
	
	static boolean is_equal(int[] arr1, int[] arr2) {
		boolean equal = true;
		if (arr1.length != arr2.length)
			equal = false;
		else {
			for (int i = 0; i < arr1.length; i++) {
				if (arr1[i] == arr2[i])
					continue;
				else {
					equal = false;
					break;
				}
			}
		}
		return equal;
	}
	
	static int [] add_array(int[] arr1, int[] arr2) {
		int [] sum_arr = new int[arr1.length];
		int remains = 0;
		for (int i = arr1.length - 1; i >= 0; i--) {
			int sum = arr1[i] + arr2[i] + remains;
			sum_arr[i] = sum % 10;
			remains = sum / 10;
		}
		return sum_arr;
	}

	
	// converting array to string (without leading zeros)
	static String array2string(int[] n) {
		String s = "";
		// looking for the end of leading zeros
		int first_digit = 0;
		while (n[first_digit]==0) {
			first_digit++;
		} 
		// adding symbols to string
		for (int i = first_digit; i<=n.length-1;i++) {
			s += n[i];
		}
		return s;
	}
	
	static int[] long2array(long n_long) {
		int[] n_array = new int[100];
		for (int i = n_array.length-1; i >= 0; i--) {
			n_array[i] = (int) (n_long % 10);
			n_long = (long) n_long / 10;
		}
		return n_array;
	}
 	
//	static boolean treshold_exceeded(int[] arr) {
//		boolean exceeds_tresh = false;
//		int [] arr_max = long_to_array(Long.MAX_VALUE);
//		if (arr.length < arr_max.length)
//			exceeds_tresh = false;
//		else if (arr.length > arr_max.length) {
//			exceeds_tresh = true;
//		}
//		else {
//			for (int i = 0; i <= arr.length - 1; i++) {
//				if (arr[i] > arr_max[i]) {
//					exceeds_tresh = true;
//					break;
//			}
//		}
//		}
//		return exceeds_tresh;
//	}
	
//	static int [] reverse_arr(int[] arr) {
//		int [] reversed = new int[arr.length];
//		//System.out.println(arr.length);
//		int count = 0;
//			for (int i = arr.length-1; i >= 0; i--) {
//				if (count <= arr.length) {
//				//System.out.println(i);
//				//System.out.println(count);
//				reversed[count] = arr[i];
//				count ++;
//				}
//			}
//		return reversed;
//	}
	
	// checking if n1 bigger than n2
	static boolean is_bigger(int[] n1, int[] n2) {
		boolean bigger  = false;
		for (int i = 0; i <= n1.length -1; i++) {
			if (n1[i] > n2[i]) {
				bigger = true;
				break;
			} else {
				if (n1[i] < n2[i]) {
					break;
				}
			}
		}
		return bigger;
	}
	
	static int[] reverse_arr(int [] n) {
		int[] n2 = new int[n.length];
		// looking for the end of leading zeros
		int first_digit = 0;
		while (n[first_digit]==0) {
			first_digit++;
		}
		// creating palindrom
		for (int i = first_digit; i <= n.length-1; i++) {
			n2[n.length - (i + 1) + first_digit] = n[i];
		}
		return n2;
	}
		
		public static void main(String[] args) {
			if ((args.length == 0) || (Integer.parseInt(args[0]) > 100000)) {
				System.out.println("Bitte geben Sie die Obergrenze als Parameter an.");
				System.exit(-1);
			}
			
			long limit = Integer.parseInt(args[0]);
			int iter = 100;
			boolean threshold_long_exceeded = false;
			
			if (args.length == 1) {
			for (int i = 1; i <= limit; i++) {
				int count = 0;
				long N = i;
				while (count < iter) {
					long R = reverse(N);
					long sum = N + R;
					// check the counter, N, R and their sum
					//System.out.println("i is " + i + ", count is " + count + ", N is " + N + ", R is " + R + ", Palindrom is " + sum);
					if (sum + reverse(sum) < 0) {
							System.out.println(i);
							break;
						}
						else {
							N = sum;
							if (sum == reverse(sum)) {
								break;
							}
							else
								count = count + 1;
							if (count == iter)
								System.out.println(i);
						}
						}
				}
			}
						else if (args.length == 2) {
							outer: for (int i=1; i <= limit; i++) {
								int count = 1;
								int [] N = long2array((long) i);
								while (count <= 100) {
									int [] R = reverse_arr(N);
									int [] sum = add_array(N, R);
									if (is_equal(sum, reverse_arr(sum)) && is_bigger(sum, long2array(Long.MAX_VALUE))) {
										threshold_long_exceeded = true;
										System.out.println(i + " braucht " + count +  " Iterationen bis zum Palindrom " + array2string(sum));
										break outer;
									}
									else if (is_equal(sum, reverse_arr(sum))) {
										break;
									}
									N = sum;
									count += 1;
								}
							}
							if (threshold_long_exceeded == false) {
								System.out.println("alle Zahlen werden auch durch Abbruch per Ueberlauf gefunden");
							}
										}
							}
}
	