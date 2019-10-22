package cpsc331.assignment1;

//referenced material:
//https://www.w3schools.com/java/
//https://www.youtube.com/watch?v=WPvGqX-TXP0&t=943s

public class SMacGonagall{
	//This method returns the nth MacGonagall number
	//Precondition: integer n as input
	//postcondition: If n>=0 nth MacGonagall number will be returned, Illegal Argument exception otherwise
   protected static int smacG(int n){
	
		if (n<0) //have to recheck this for inner method testing
			throw new IllegalArgumentException("Fiddlesticks! The integer input cannot be negative.");	
		else if (n == 0)
			return 1;
		else if (n == 1)
			return 0;
		else if (n == 2)
			return 5;
		else if (n == 3)
			return 8;
		else{ 	//asserted that n >= 4
			return (2 * smacG(n - 1) - 2 * smacG(n - 3) + smacG(n - 4));	//Bound function: f(n) =n
		}
			
   }
   
    //This main method checks if there is a single non negative integer input and calls smacG if that is true
    public static void main(String[] args){
		
		try{	
			if(args.length != 1) throw new IllegalArgumentException(); //Check to see if only one input supplied
			
			//assert that only one input supplied
			int number = Integer.parseInt(args[0]);	//try to parse input to a integer 

			try{
				if(number < 0) throw new IllegalArgumentException(); //check if negative 
				System.out.println(smacG(number));
			}catch(IllegalArgumentException e){
				System.out.println("Fiddlesticks! The integer input cannot be negative."); //fail gracefully 
			}

		}catch(IllegalArgumentException e){
			System.out.println("Fiddlesticks! One integer input is required."); //fail gracefull
		}
	}  
}
