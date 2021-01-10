public class Bigs {
	
	// addiert die Ziffernfelder a und b
	public static int[ ] add (int[ ] a, int[ ] b) {
		int rest = 0;
		int count = 1;
		int [] bigger = a;
		int [] lesser = b;
		int [] sum_arr;
		if (!equal(a, b) && less(a, b)) {
			bigger = b;
			lesser = a;
		}
		for (int i = 0; i<lesser.length; i++) {
			int sum = lesser[i] + bigger[i] + rest;
			rest = sum/10;
			count ++;
			if (i == lesser.length-1 && rest > 0)
				count ++;
		}
		if (count>bigger.length)
			sum_arr = new int[bigger.length+1];
		else
			sum_arr = new int[bigger.length];
		
		rest = 0;
		for (int i = 0; i<lesser.length; i++) {
			int sum = lesser[i] + bigger[i] + rest;
			int residual = sum%10;
			rest = sum / 10;
			sum_arr[i] = residual;
			if (i == lesser.length-1 && bigger.length>lesser.length)
				sum_arr[i+1] = bigger[i+1] + rest;
			else if (i == lesser.length-1 && rest>0 && lesser.length==bigger.length) {
				sum_arr[i+1] = rest;
				}
			}
		//System.out.println(sum_arr.length);
		return sum_arr;
	}
			
	// gibt das Ziffernfeld n in lesbarer dezimaler Form aus
	static void print (int[ ] n) {
		String s = "";
		// check for leading zeros
		int last_nonzro = n.length-1;
		while (n[last_nonzro]==0) {
			last_nonzro--;
		} 
		// form a string
		for (int i = last_nonzro; i>=0;i--) {
			s += n[i];
		}
		System.out.println(s);
		//System.out.println("length is " + s.length());
	}
	
	// konstruiert ein einstelliges Ziffernfeld aus d
	static int[ ] digit(int d) {
		int [] arr = new int[1];
		arr[0] = d;
		return arr; //if d>10, throw an exception?
	}
 
	// konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
	static int[ ] Null() {
		int[] null_arr = new int[1];
		null_arr[0] = 0;
		return null_arr;
	}
	
	// konstruiert das Ziffernfeld, welches den Wert Eins repraesentiert
	static int[ ] One() {
		int[] one_arr = new int[1];
		one_arr[0] = 1;
		return one_arr;
	}

	// Rest des Ziffernfeldes n bei Division durch 10 (eine int-Zahl!)
	static int mod10(int[ ] n) {
		return n[0];
	}

	// ganzzahliger Quotient bei Division durch 10
	static int[ ] div10(int[ ] n) { //division of integers with the rest
		int []quotinent = null;
		if (n.length == 1) {
			quotinent = n;
		}
		else if (n.length > 1) {
			quotinent = new int[n.length-1];
			for (int i = 1; i < n.length; i++) {
				quotinent[i-1] = n[i];
			}
		}
		return quotinent;
	}

	// Umwandlung einer int-Zahl in ein Ziffernfeld	
	static int[ ] fromInt(int n) { //in reversed order
		int len = String.valueOf(n).length();
		//System.out.println(len);
		int []arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (n % 10);
			n = (int) n / 10;
		}
		return arr;
	}

	// kopiert den Wert von a
	static int[ ] copy(int[ ] n) {
		int[] b = new int[n.length];
		for (int i=0; i < n.length; i++)
			b[i] = n[i];
		return b;
	}

	// multipliziert das Ziffernfeld a mit einer int-Zahl
	// d ist einstellig
	static int[ ] times(int[ ] n, int d) {
		int [] arr = null;
		int rest = 0;
		int count = 0;
		for (int i = 0; i < n.length; i++) {
			int product = n[i]*d;
			rest = product/10;
			count++;
			if (i==n.length-1 && rest>0)
				count++;
		}
		if (count > n.length) {
			arr = new int[n.length+1];
		}
		else if (count==n.length) arr = new int[n.length];
		int rest_mult = 0;
		for (int i = 0; i < n.length; i++) {
			int product = n[i]*d + rest_mult;
			int residue = product%10;
			rest_mult = product/10;
			arr[i] = residue;
			if (i == n.length-1 && rest_mult > 0 && count > n.length)
				arr[i+1] = rest_mult;
		}
		//System.out.println(arr.length);
		return arr;
	}

    // multipliziert das Ziffernfeld n mit 10
	static int[ ] times10(int[ ] n) {
		int[] arr = new int[n.length+1];
		for (int i = 0; i<=n.length; i++) {
			if (i==0) arr[i] = 0;
			else arr[i] = n[i-1];
		}
		return arr;
	}
	
	// multipliziert zwei Ziffernfelder	
	static int[ ] times(int[ ]a, int[ ] b) {
		int [] will_be_overwritten_arr = null;
		int counter = 1;
		int [] sum = null;
		for (int i=0; i<b.length; i++) {
			will_be_overwritten_arr = times(a, b[i]);
			if (i==0) sum = will_be_overwritten_arr;
			else {
				for (int k = 1; k<=counter; k++)
					will_be_overwritten_arr = times10(will_be_overwritten_arr);
				sum = add(sum, will_be_overwritten_arr);
				counter++;
			}
		}
		//System.out.println(sum.length);
		return sum;
	}
	
    // Quadratzahl eines Ziffernfeldes
	static int[ ] square(int[ ]a) {
		return times(a, a);
	}
	
    // Kubikzahl eines Ziffernfeldes
	static int[ ] cubic(int[ ]a) {
		return times(times(a, a), a);
	}
	
	// Test auf kleiner-Relation zweier Ziffernfelder: a < b ?
	static boolean less (int[ ] a, int[ ] b) {
		if (a.length<b.length) return true;
		else if (a.length==b.length) {
			for (int i=a.length-1; i>=0; i--) {
				if (a[i] < b[i]) return true;
				else if (a[i] == b[i]) return false;
				else if (a[i] > b[i]) return false;
			}
			return true;
		}
		else return false;
	}

	// Test auf Gleichheit zweier Ziffernfelder
	static boolean equal (int[ ] a, int[ ] b){
		if (a.length != b.length)
			return false;
		else {
			for (int i=0; i<a.length; i++) {
				if (a[i] != b[i])
					return false;
			}
			return true;
		}
	}

	// Test auf Korrektheit eines Ziffernfeldes: Feld existiert und enthaelt
        // mindenstens eine Ziffer, alle Positionen liegen zwischen 0 und 9
        // keine fuehrenden Nullen (ausser bei Null selbst) 
	static boolean ok (int[ ] n) {
		boolean test = false;
		//check if the array is null or empty
		if (n==null) {
			System.out.println("array is null");
			return test;
		}
		else if (n.length==0) {
			System.out.println("array is empty");
			return test;
		}
		else test=true;
		//check that if an array if length 1 then it's holds a digit
		for (int i=n.length-1; i>=0; i--) {
			if (n.length>1 && n[i]==0) {
					System.out.println("Number has leading zero(s).");
					return test = false;
			}
			if (n[i]<10)
				continue;
		}
		test=true;
		return test;
	}

	// gibt die (kleinste) Ziffer mit der groessten Haeufigkeit in n aus	
	static void maxDigit(int[] n) {
		int [][] frequency = new int[2][10]; //two-dimensional array. First row are digits, second row - their freq
		//initializing freq of all digits two 0
		for (int i=0; i<frequency.length; i++) {
			for (int j=0; j<frequency[i].length; j++) {
				if (i==0) frequency[i][j] = j;
				else frequency[i][j] = 0;
			}
		}
		//iterate through the digits in n and add +1 to their freq every time one is encountered
		for (int i=0; i<n.length; i++) {
			for (int j=0; j<frequency[0].length; j++) {
			if (frequency[0][j] == n[i]) frequency[1][j] ++;
			}
		}
		//if there're more than one digit with max freq --> select the smallest one
		int most_freq_dig = 9;
		for (int i=0; i<frequency[0].length; i++) {
			if (frequency[1][i] == max(frequency[1])) {
					if (frequency[0][i] < most_freq_dig)
						most_freq_dig = frequency[0][i];
			}
		}
		//the most freq digit: its frequency
		System.out.println(most_freq_dig + ": " + frequency[1][most_freq_dig]);
	}
	
	//find max in array
	static int max(int[] n) {
		int max = n[0];
		for (int i=1; i < n.length; i++)
			if (n[i] > max) max= n[i];
		return max;
	}

	public static void main (String[ ] s) {
		//long start = System.currentTimeMillis();
  		int[] a = One();

		for (int i=0; i<33222; ++i) {
			a = times(a, 2);
		}
		

		System.out.println("2^33222 hat " + a.length + " Stellen");
		print(a); 
		System.out.println(); 

		int[] b = fromInt(13);
		int[] c = copy(b);
		print(b);
		print(c);
		
		for (int i=1; i<8978; ++i) {
			c=times(c, b);
		}
		
		System.out.println();
		System.out.println("13^8978 hat " + c.length + " Stellen");
		print(c);
		System.out.println(c.length);

		System.out.println(less(a, c)); // beantwortet die Frage aus der Aufgabenueberschrift
                maxDigit(a);
                maxDigit(c);
//                long end = System.currentTimeMillis();
//                long elapsedTime = end - start;
//                System.out.println(elapsedTime*0.001);
	}
}
