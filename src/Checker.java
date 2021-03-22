
public final class Checker {
	//method for check duplicates in entered number
			public static boolean hasDuplicates(int number) {

		        boolean[] digits = new boolean[10];

		        while (number > 0) {
		            int last = number % 10;

		            if (digits[last])
		                return true;

		            digits[last] = true;
		            number = number / 10;
		        }
		        return false;
			}
			
			// clue check for validated
			   public static boolean checkUserInputIsClue(String userInput) {
			    	
			        if (userInput.startsWith("+")) {
			            return true;
			        }
			        
			        return false;

			    }

}
