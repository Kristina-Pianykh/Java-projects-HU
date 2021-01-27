import gdp.stdlib.*;
	 
public class Pythagoras_Tree {
	
	/** generates a random angle, that is the smallest of the two angles on the side of hypotenuse
	 * @return angle value as double
	 */
	static double generateAngle () {
		final double DEGREE_30=Math.PI/6;
		return (Math.random() + 1)*DEGREE_30;
	}
	
	/** calculate ankathete
	 * @return ankathete length as double
	 */
	public static double ankathete(double squareSide, double angle) {
		return squareSide * Math.cos(angle);
	}
	
	/** calculate gegenkathete
	 * @return gegenkathete length as double
	 */
	public static double gegenkathete(double squareSide, double angle) {
		return squareSide * Math.sin(angle);
	}
	
		/** draws the coordinates of a moved square at the reference point
	 * @return nothing
	 */
	public static void drawSquare(double [][] squareCoord) {
		StdDraw.line(squareCoord[0][0], squareCoord[0][1], squareCoord[1][0], squareCoord[1][1]);
		StdDraw.line(squareCoord[1][0], squareCoord[1][1], squareCoord[2][0], squareCoord[2][1]);
		StdDraw.line(squareCoord[2][0], squareCoord[2][1], squareCoord[3][0], squareCoord[3][1]);
		StdDraw.line(squareCoord[3][0], squareCoord[3][1], squareCoord[0][0], squareCoord[0][1]);
	}
	
	/** create square coordinates at the origin point for left or right square
	 * @return 2D array with coordinates of rotated square at the origin point
	 */
	public static double[][] initSquareCoord(double sideLength, boolean whichSquare, double angle) {
		double [] origin = {0.0, 0.0};
		double [][] squareCoord = new double[4][2];
		if (whichSquare) { //true for the left upper square
			squareCoord[0] = origin;
			squareCoord[1][0] = 0.0;
			squareCoord[1][1] = 0.0 + sideLength;
			squareCoord[2][0] = 0.0 + sideLength;
			squareCoord[2][1] = 0.0 + sideLength;
			squareCoord[3][0] = 0.0 + sideLength;
			squareCoord[3][1] = 0.0;
		}
		else {
			squareCoord[0][0] = 0.0 - sideLength;
			squareCoord[0][1] = 0.0;
			squareCoord[1][0] = 0.0 - sideLength;
			squareCoord[1][1] = 0.0 + sideLength;
			squareCoord[2][0] = 0.0;
			squareCoord[2][1] = 0.0 + sideLength;
			squareCoord[3][0] = 0.0;
			squareCoord[3][1] = 0.0;
		}
			
		return rotateSquare(squareCoord, angle);
	}
	
	/** create a rotation matrix
	 * @return 2D array with values of the rotation matrix
	 */
	public static double[][] RotationMatrix(double angle) { //try with void
		double [][] matrix = new double [2][2];
		matrix[0][0] = Math.cos( angle);
		matrix[0][1] = -1 * Math.sin(angle);
		matrix[1][0] = Math.sin(angle);
		matrix[1][1] = Math.cos(angle);
		return matrix;
	}
	
	/** multiplies coordinates of a point (x, y) by the values of the rotation matrix
	 * @return an array with multiplied coordinates of a point
	 */
	public static double[] multVector(double [][] matrix, double [] vector) {
		double [] newVector = new double[2];
		newVector[0] = matrix[0][0] * vector[0] + matrix[0][1] * vector[1];
		newVector[1] = matrix[1][0] * vector[0] + matrix[1][1] * vector[1];
		return newVector;
	}
	
	/** multiplies coordinates of each point of the square by the values of the rotation matrix
	 * @return 2D array with coordinates of the rotated square
	 */
	public static double [][] rotateSquare(double [][] initCoord, double angle) {
		double [][] rotMatrix = RotationMatrix(angle);
		for (int i=0; i<initCoord.length; i++) {
			initCoord[i] = multVector(rotMatrix, initCoord[i]);
		}
			return initCoord;
	}
	
	/** moves coordinates of each point of the square from the origin point to the reference point
	 * @return 2D array with coordinates of the rotated square
	 */
	public static double [][] moveSquare(double [][] squareCoord, double [] referencePoint) {
		squareCoord[0][0] = squareCoord[0][0] + referencePoint[0];
		squareCoord[0][1] = squareCoord[0][1] + referencePoint[1];	
		squareCoord[1][0] = squareCoord[1][0] + referencePoint[0];
		squareCoord[1][1] = squareCoord[1][1] + referencePoint[1];
		squareCoord[2][0] = squareCoord[2][0] + referencePoint[0];
		squareCoord[2][1] = squareCoord[2][1] + referencePoint[1];
		squareCoord[3][0] = squareCoord[3][0] + referencePoint[0];
		squareCoord[3][1] = squareCoord[3][1] + referencePoint[1];
		return squareCoord;
	}
	
	/** creates and calculates coordinates for the first base square and then launches
	 * recursion of the function for each left and right square
	 * @return nothing
	 */
	public static void createSquare(double squareLength, double angle, double[] refPoint, boolean whichSquare, int depth) {
		double[][] coordinates = new double[4][2];
		if (whichSquare) {
			coordinates = initSquareCoord(squareLength, true, angle); //construct 4 points from the centre with angle
			coordinates = moveSquare(coordinates, refPoint);
		}
		else {
			coordinates = initSquareCoord(squareLength, false, angle); //construct 4 points from the centre with angle
			coordinates = moveSquare(coordinates, refPoint);
		}
		drawSquare(coordinates);
		if (depth>0) {	
			double alpha = generateAngle(); //generate new alpha
			double beta = Math.PI / 2 - alpha; //beta angle
			double[] refPointLeft = new double[2]; //new reference point for the left square
			double[] refPointRight = new double[2]; //new reference point for the right square
			for (int i=0; i<refPoint.length; i++) {
				refPointLeft[i] = coordinates[1][i];
				refPointRight[i] = coordinates[2][i];
			}
			createSquare(ankathete(squareLength, alpha), angle + alpha, refPointLeft, true, depth - 1);
			createSquare(gegenkathete(squareLength, alpha), angle - beta, refPointRight, false, depth - 1);
		}
	}
	
	public static void main(String[] args) {
		StdDraw.setXscale(-1.9, 3.0);
		StdDraw.setYscale(-1.0, 4.0);
		
		double [] referPoint = {0.0, 0.0};
		int depth = Integer.parseInt(args[0]);
		createSquare(0.6, 0, referPoint, true, depth);
	}
}
