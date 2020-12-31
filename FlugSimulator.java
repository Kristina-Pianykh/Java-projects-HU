/**
 * @author Kristina Pianykh
 * Moodle: pianykhk
 * Group: 114
 */
public class FlugSimulator {
	
	public static void main(String[] args) {
		
		int anzahlFluege = Integer.parseInt(args[0]);
		// terminate the program when the number form the input is less than 1
		if (anzahlFluege < 1) {
			System.out.println("Illegal input, try again.");
			System.exit(-1);
		}
		
		int Ueberbuchungen = 0;
		double seat_capacity = 75;
		double prob_showup = 0.92;
		
		//double prob = (77*78)/2*Math.pow(0.92, 76)*Math.pow(0.08, 2) + 78*Math.pow(0.92, 77)*0.08 + Math.pow(0.92, 78);
		//System.out.println(prob); // 0.04566698111174875
		
		double tickets_sold = 78;
		int Mittelwert = 0;
		
		// loop to iterate through the number of flights
		for (int k=0; k<anzahlFluege; k++) {
			int will_appear_for_sure = 0;
			
			// loop to generate the probability of appearing of each of the 78 passengers who bought the flight tickets
			// and calculate the mean of the appearing passengers for each flight
			for (int i=0; i<tickets_sold; i++) {
				double probability_to_appear = Math.random();
				if (probability_to_appear <= prob_showup) {
					will_appear_for_sure = will_appear_for_sure + 1;
				}
				if (Mittelwert == 0)
					Mittelwert = will_appear_for_sure;
				else
					Mittelwert = (Mittelwert + will_appear_for_sure) / 2;
			}

			// if the number of the passengers, highly likely to show up on board, is greater than the number
			// of available seats (75), increment the variable Ueberbuchungen by 1
			if (will_appear_for_sure > seat_capacity)
				Ueberbuchungen = Ueberbuchungen + 1;
		}
		
		// calculate the percentage of overbookings for a given number of flights
		double percentage = (double) Ueberbuchungen * 100 / (double) anzahlFluege;
		int percentInt = (int) Math.round(percentage * 100);
		percentage = percentInt / 100.0;
		
		System.out.println("Überbuchungen: " + Ueberbuchungen + " (" + percentage + "%)" + "\n" + "Mittelwert: " + Mittelwert);
	}
}
