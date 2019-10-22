package cpsc331.assignment1;

//referenced material:
//https://www.w3schools.com/java/
//https://www.youtube.com/watch?v=WPvGqX-TXP0&t=943s

public class FMacGonagall{
    //This method returns the nth MacGonagall number
	//Precondition: integer n as input
	//postcondition: If n>=0 nth MacGonagall number will be returned, Illegal Argument exception otherwise
    protected static int fmacG(int n){

        if(n < 0)
            throw new IllegalArgumentException("Fiddlesticks! The integer input cannot be negative"); //double checking non-negative for inner method testing
        //Returns an integer depending on n given
        else if(n == 0)
            return 1;
        else if(n == 1)
            return 0;
        else if(n == 2)
            return 5;
        else if(n == 3)
            return 8;
        else{
            //assert that n >=4
            
            int[] M = new int[n + 1];
            M[0] = 1;
            M[1] = 0;
            M[2] = 5;
            M[3] = 8;
            
            int i = 4;
            
            //Loop Invariant:
            // integer n >= 4
            // M has length n+1
            // 4<=i<=n+1
            // M[j] = jth MacGonnagal for 0<=j<=i-1
            while(i <= n){
            M[i] = 2 * M[i - 1] - 2 * M[i - 3] + M[i - 4];
            i++;
            }
            //Returns the resulted integer of n
            return M[n];
        }
    }

    
    //This main method checks if there is a single non negative integer input and calls fmacG if that is true
    public static void main(String[] args){
		
		try{	
			if(args.length != 1) throw new IllegalArgumentException(); //Check to see if only one input supplied
			
			//assert that only one input supplied
			int number = Integer.parseInt(args[0]);	//try to parse input to a integer 

			try{
				if(number < 0) throw new IllegalArgumentException(); //check if negative 
				System.out.println(fmacG(number));
			}catch(IllegalArgumentException e){
				System.out.println("Fiddlesticks! The integer input cannot be negative."); //fail gracefully 
			}

		}catch(IllegalArgumentException e){
			System.out.println("Fiddlesticks! One integer input is required."); //fail gracefull
		}
	}  
}
