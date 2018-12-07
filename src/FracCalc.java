import java.util.*;
public class FracCalc {

    public static void main(String[] args) 
    {
        Scanner console = new Scanner (System.in); 
        System.out.print ("Input your equation (type 'quit' to quit): ");
        String input = console.nextLine ();      
        while (!(input.equals("quit"))) {
        	produceAnswer (input); //calls produceAnswer to return an answer and assigns 'answer' to it
        	System.out.print ("Input your equation (print 'quit' to quit) ");
        	input = console.nextLine ();
    	}	        
    }
    public static void produceAnswer(String input) {
    	String equation[] = input.split(" "); //splits the input into two operands and one operator and makes an array with them
    	int mixChecker = equation[2].indexOf("_"); //checks if the operand is a mixed fraction by searching for '_'
    	if (mixChecker == -1) { //if not a mixed fraction...
    		int fracChecker = equation[2].indexOf ("/"); //checks if the operand is a regular fraction by searching for '/'
    		if (fracChecker == -1) { //if not a regular fraction...
    			int [] justInt = justAnInt(equation[2]); //it's just an integer! calls 'justAnInt', and makes an array 'justInt' to hold the values returned by 'fraction'
    			System.out.println("whole:" + justInt[0] + " numerator:" + justInt[1] + " denominator:" + justInt[2]);
    		}
    		else { //if a regular fraction...
    			int [] fraction = fraction(equation[2]); //calls 'fraction', and makes an array 'fraction' to hold the values returned by 'fraction'
    			System.out.println("whole:" + fraction[0] + " numerator:" + fraction[1] + " denominator:" + fraction[2]);
    		}
    	}
    	else { //if a mixed fraction
    		int [] mixedFrac = mixedFraction(equation[2]); //calls mixedFraction method
    		System.out.println("whole:" + mixedFrac[0] + " numerator:" + mixedFrac[1] + " denominator:" + mixedFrac[2]);
    	}
    }
    
    public static int[] mixedFraction (String operand) {
    	String mixed [] = operand.split("_"); //splits an operand around _
    	int [] mixedList = fraction (mixed[1]);
    	mixedList[0] = Integer.parseInt(mixed[0]); //makes the whole number an integer and assigns it to variable 'wholeNumber'
    	return mixedList;
    	// System.out.println ("whole: " + wholeNumber);
    	// fraction(mixed[1]);
    }
    public static int[] fraction (String operand) {
    	String fraction [] = operand.split("/"); //splits the simple fraction (x/y) around / and makes array 'fraction' with the split parts
    	int [] fracList = new int [3]; //makes an array to hold the whole, numerator and denominator
    	fracList[1] = Integer.parseInt(fraction[0]); //assigns the numerator to the 2nd index in fracList
    	fracList[2] = Integer.parseInt(fraction[1]); //assigns the denominator to the 3rd index in fracList
    	return fracList;
    }
    public static int[] justAnInt (String operand) {
    	int [] wholeList = new int [3]; //makes an array to hold the whole, num and denom
    	wholeList[0] = Integer.parseInt(operand);
    	wholeList[1] = 0;
    	wholeList[2] = 1;
    	return wholeList;
    	
    	
    	/*System.out.println ("whole: " + operand);
    	int numerator = 0;
    	int denominator = 1;
    	System.out.println ("numerator: " + numerator);
    	System.out.println ("denominator: " + denominator);
    	*/
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
