/**
 * @author Kristina Pianykh
 * Moodle: pianykhk
 * Group: 114
 */
public class Sieb {
	
	//function to find primes up to the given number
	static boolean [] find_primes(int upper_limit) {
		boolean [] n = new boolean [upper_limit];
		for (int i= 2; i < upper_limit; i++) {
			n[i]= true; // initialization: no factors found yet
		}
		for (int i= 2; i*i < upper_limit; i++) {
			if (n[i]) {
				// i is prime number -> leave p[i] == true
				// mark multiples of i as nonprime
				for (int k = 2; k*i<upper_limit; k++)
					n[k*i]= false;
			}
		}
		return n;
	}
	
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int M = 9;
		boolean[] curr_primes;
		int num_curr_primes = 0; //the number of primes given the current M
		
		while (num_curr_primes <= N) {
			curr_primes = find_primes(M); //create an array with primes set to true with the current M
			
			//count the number of primes up to M
			for (int i=2; i<M; i++) {
				if (curr_primes[i])
					num_curr_primes++;
				}
			
			//number of primes with the current M < N ==> increase M by a factor of 10
			if (num_curr_primes < N) {
				M = M * 10;
				num_curr_primes = 0;
			}
		}
		
		boolean[] end_primes = find_primes(M); //create an array with the M the number of primes >= N
		int count = 1;
		
		for (int i=2; i<M; i++) {
			if (count <= N) {
				if (end_primes[i]) {
					System.out.println(i);
					count = count + 1;
				}
			}
		}
	}
}
