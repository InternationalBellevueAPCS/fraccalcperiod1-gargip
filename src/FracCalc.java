import java.util.*;
public class FracCalc {

    public static void main(String[] args) 
    {
        Scanner console = new Scanner (System.in); 
        System.out.print ("Input your equation  ");
        String input = console.nextLine ();        
        String last = produceAnswer (input);
        System.out.println (last);
        
       /* while (!(input.equals("-1"))) {
        	String answer = produceAnswer (input);
        	System.out.println(answer);
        	System.out.print ("Input your equation (print -1 to quit) ");
        	input = console.nextLine ();
        }
        */
    }
    public static String produceAnswer(String input) {
    	String equation[] = input.split(" ");
    	return equation[equation.length-1];
    }
    
    // TODO: Fill in the space below with helper methods
    
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
