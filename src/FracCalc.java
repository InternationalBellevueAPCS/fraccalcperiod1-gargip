import java.util.*;
public class FracCalc {

    public static void main (String[] args) {
    	Scanner console = new Scanner (System.in); 
        System.out.print ("Input your equation (type 'quit' to quit): ");
        String input = console.nextLine ();      
        while (!(input.equals("quit"))) {
        	produceAnswer (input); //calls produceAnswer 
        	System.out.print ("Input your equation (print 'quit' to quit): ");
        	input = console.nextLine ();
    	}	        
    }
    
    public static String produceAnswer(String input) {
    	String equation[] = input.split(" "); //splits the input into two operands and one operator and makes an array with them
	    int [] operand1 = operandHolder (equation[0]); //finds the num, denom, and whole for the first operand and puts into array 'operand1'
	    int [] operand2 = operandHolder(equation[2]); //finds the num, denom, and whole for the second operand and puts into array 'operand2'
	    if (operand1[0] > 0) { //if operand1 is a mixed fraction
	    	operand1 = improper(operand1); //makes operand1 an improper fraction
	    }
	    if (operand2[0] > 0) { //if operand2 is a mixed fraction
	    	operand2 = improper(operand2); //makes operand2 an improper fraction
	    }
	    if (equation[1].equals("*")) { //checks whether the equation needs to multiply
	    	int [] answer = multiply (operand1, operand2);
	    	reduce (answer);
	    	if (answer[2] == 1) { //if the answer is an integer
	    		System.out.println(answer[1]);
	    	}
	    	else if (answer[0] > 0) {//if the fraction is improper
	    		System.out.println(answer[0] + "_" + answer[1] + "/" + answer[2]);
	    	}
	    	else {
	    		System.out.println(answer[1] + "/" + answer[2]);
	    	}
	    }
	    if (equation[1].equals("/")) { //checks whether the equation needs to divide
	    	int [] answer = divide (operand1, operand2);
	    	reduce (answer);
	    	if (answer[2] == 1) { //if the answer is an integer
	    		System.out.println(answer[1]);
	    	}
	    	else if (answer[0] > 0) {//if the fraction is improper
	    		System.out.println(answer[0] + "_" + answer[1] + "/" + answer[2]);
	    	}
	    	else {
	    		System.out.println(answer[1] + "/" + answer[2]);
	    	}
	    }
	    if (equation[1].equals("+")) { //checks whether the equation needs to add
	    	int [] answer = addition (operand1, operand2);
	    	reduce (answer);
	    	if (answer[2] == 1) { //if the answer is an integer
	    		System.out.println(answer[1]);
	    	}
	    	else if (answer[0] > 0) {//if the fraction is improper
	    		System.out.println(answer[0] + "_" + answer[1] + "/" + answer[2]);
	    	}
	    	else {
	    		System.out.println(answer[1] + "/" + answer[2]);
	    	}
	    }
	    if (equation[1].equals("-")) { //checks whether the equation needs to subtract
	    	int [] answer = subtraction (operand1, operand2);
	    	reduce (answer);
	    	if (answer[2] == 1) { //if the answer is an integer
	    		System.out.println(answer[1]);
	    	}
	    	else if (answer[0] > 0) {//if the fraction is improper
	    		System.out.println(answer[0] + "_" + answer[1] + "/" + answer[2]);
	    	}
	    	else {
	    		System.out.println(answer[1] + "/" + answer[2]);
	    	}
	    }
    }
    
	public static int[] operandHolder (String operand) { //returns the numerator, denominator, and whole number for each operand
    	int mixedChecker = operand.indexOf("_"); //checks if the operand is a mixed fraction by searching for '_'
	    if (mixedChecker == -1) { //if not a mixed fraction...
	    	int fracChecker = operand.indexOf ("/"); //checks if the operand is a regular fraction by searching for '/'
	    	if (fracChecker == -1) { //if not a regular fraction...
	    		return justAnInt(operand); //it's just an integer! calls 'justAnInt', and makes an array 'justInt' to hold the values returned by 'justAnInt'
	    	}
	    	else { //if a regular fraction...
	    		return fraction(operand); //calls 'fraction', and makes an array 'fraction' to hold the values returned by 'fraction'
	    	}
	    }
	    else { //if a mixed fraction
	    	return mixedFraction(operand); //calls mixedFraction method
	   	}
    }
    
    public static int[] mixedFraction (String operand) {//returns whole, num, and denom values if operand is mixed fraction
    	String mixed [] = operand.split("_"); //splits an operand around _
    	int [] mixedList = fraction (mixed[1]); //makes array for the values returned by 'fraction' (num and denom), fills index 1 and 2
    	mixedList[0] = Integer.parseInt(mixed[0]); //makes the first element an int and assigns it to index 0
    	return mixedList; //returns the array, now complete with whole, num, and denom
    }
    
    public static int[] fraction (String operand) {//returns whole, num, and denom values if operand is regular/improper fraction
    	String fraction [] = operand.split("/"); //splits the simple fraction (x/y) around / and makes array 'fraction' with the split parts
    	int [] fracList = new int [3]; //makes an array to hold the whole, numerator and denominator
    	fracList[1] = Integer.parseInt(fraction[0]); //assigns the numerator to the 2nd index in fracList
    	fracList[2] = Integer.parseInt(fraction[1]); //assigns the denominator to the 3rd index in fracList
    	return fracList; //returns array with whole, num, and denom values
    }
    
    public static int[] justAnInt (String operand) {//returns whole, num, and denom values if operand is integer
    	int [] wholeList = new int [3]; //makes an array to hold the whole, num and denom
    	wholeList[0] = Integer.parseInt(operand); //turns String with the integer into integer value, assigns it to index 0 in 'wholeList'
    	wholeList[1] = 0;
    	wholeList[2] = 1;
    	return wholeList; //returns array with whole, num, and denom values
    }
    
    public static int[] improper (int [] array1) { //makes a mixed fraction improper and returns an array
    	int numerator = (array1[0] * array1[2]) + array1[1]; //changes numerator so fraction becomes improper
    	int [] arrayFinal = {0, numerator, array1[2]}; //makes array with whole = 0, new numerator, and original denom.
    	return arrayFinal; //returns array with whole, num, and denom values
    }
    
    public static int[] multiply (int[] array1, int[] array2) { //multiplies two operands
    	int numerator = array1[1] * array2[1];
    	int denominator = array1[2] * array2[2];
    	int[] answer = {0, numerator, denominator};//makes array with final whole, num, and denom values
    	return answer;
    }
    
    public static int[] divide (int[] array1, int[] array2) { // divide two operands
    	int num2New = array2[2]; //flips the numerator and denominator
    	int denom2New = array2[1];
    	array2[1] = num2New; 
    	array2[2] = denom2New;    	
    	int[] answer = multiply (array1, array2); //calls multiply and puts final values in array 'answer'
    	return answer;//returns array with final whole, num, and denom values
    }

    public static int[] addition (int[] array1, int[] array2) { //adds two operands
    	int[] new1 = commonDenominator (array1, array2);//makes the first operand have a common denominator
    	int[] new2 = commonDenominator (array2, array1);//makes the second operand have a common denominator
    	int numerator = new1[1] + new2[1]; //adds numerators
    	int denominator = new1[2];
    	int[] last = {0, numerator, denominator};//makes array with final whole, num, and denom values
    	return last;
    }
    
    public static int[] subtraction (int[] array1, int[] array2) { //subtracts two operands
    	int[] new1 = commonDenominator (array1, array2);//makes the first operand have a common denominator
    	int[] new2 = commonDenominator (array2, array1);//makes the second operand have a common denominator
    	int numerator = new1[1] - new2[1];
    	int denominator = new1[2];
    	int[] last = {0, numerator, denominator};//makes array with final whole, num, and denom values
    	return last;
    }
    
    public static int[] commonDenominator (int[] array1, int[] array2) { //makes the first operand have a common denominator
    	int numerator = array1[1] * array2[2]; //multiplies numerator of first operand by denominator of the other
    	int denominator = array1[2] * array2[2]; //multiplies denominator of first operand by the denominator of the other
    	int[] last = {0, numerator, denominator};//makes array with final whole, num, and denom values
    	return last;
    }
    
    public static int[] reduce (int[] array1) { //reduces a fraction
    	if (canItReduce (array1)) { //if the answer can be reduced
    		int divisor = greatestCommonDivisor (array1[1], array1[2]);//finds gcd for the fraction represented by array1
    		array1[1] = array1[1] / divisor;//divides numerator by gcd
    		array1[2] = array1[2] / divisor;//divides denominator by gcd
    		if (array1[1] > array1[2]) {//if the fraction is improper, turn it into mixed
    			if (array1[2] == 1) {
    				return array1;
    			}
    			while (array1[1] > array1[2]) {
    				array1[1] = array1[1] - array1[2];
    				array1[0]++;
    			}
    		}
    		return array1;
    	}
    	else {
    		return array1;
    	}
    }
    
    public static boolean canItReduce (int[] array1) { //checks if a fraction can be reduced
    	for (int i = 2; i <= array1[1]; i++) {
    		if (array1[1] % i == 0 && array1[2] % i == 0) { //checks which number the num and denom have a common divisor
    			return true;
    		}
    	}
    	return false;
    }
    
    public static void finalIsInteger (int[] array1) {
    	
    }
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
