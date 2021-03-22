

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.List;
	import java.util.Random;

	
	public class Guesser extends JFrame implements ActionListener{
		
		private final JLabel instructions = new JLabel (Constants.Message.GUESS_LABEL);
		
		private final JLabel clue_for_computer = new JLabel (Constants.Message.CLUE_LABEL);
		
		private final JLabel computer_guess = new JLabel (Constants.Message.COMPUTER_GUESS_LABEL);
		
		private JButton guessButton;
		
		private JButton restart;

		private JTextField inputLine;
		
		private JTextField inputLine_clue;
		
		private JTextField inputLine_computer_guess;
		
		private JTextArea textArea;
		
		private List<Integer> digits;
		
		private List<Integer> possibleNumberList = new ArrayList<>(); // list of possible number
		
		public static final Random RANDOM = new Random();
		
		public static boolean isUser = false;
			
		private int number;
		
		private int computerNumberGuesses;
		
		private int numOfGuesses;
		
		private int computerNumber;
		
		private boolean guessed;
		
		private String clueHistory="";

		public Guesser()
		{
			Container contentPane;

			//set the frame properties
			setSize(Constants.View.FRAME_WIDTH, Constants.View.FRAME_HEIGHT);
			setResizable(false);
			setTitle("CS-TECH Assessment");
			setLocation(Constants.View.FRAME_X_ORIGIN,Constants.View.FRAME_Y_ORIGIN);

			contentPane = getContentPane();
			contentPane.setLayout(null);
			contentPane.setBackground(Color.lightGray);
			
			//generate random four-digit number
			digits = new ArrayList<Integer>();
			
			for (int i = 1; i <= 9; i++)
			{
				digits.add(i);
			}
			
			Collections.shuffle(digits);
			
			number = digits.get(0)*1000 + digits.get(1) * 100 + digits.get(2) * 10 + digits.get(3);
			
			Collections.shuffle(digits);
			
			computerNumber = digits.get(0)*1000 + digits.get(1) * 100 + digits.get(2) * 10 + digits.get(3);
			
			
			//set number of guesses to 0
			numOfGuesses = 0;
			
			//create and place guess button on the frame
			guessButton = new JButton("Guess!");
			guessButton.setBounds(600, 60, Constants.View.BUTTON_WIDTH, Constants.View.BUTTON_HEIGHT);
			guessButton.setBorder (BorderFactory.createRaisedBevelBorder());
			contentPane.add(guessButton);
			
			//add a restart button on the frame
			restart = new JButton ("Restart");
			restart.setBounds (820, 60, Constants.View.BUTTON_WIDTH, Constants.View.BUTTON_HEIGHT);
			restart.setBorder (BorderFactory.createRaisedBevelBorder());
			contentPane.add(restart);

			//register this frame as an action listener of both buttons
			guessButton.addActionListener (this);
			restart.addActionListener (this);

			//adding the inputLine text field
			inputLine = new JTextField();
			inputLine.setBounds(95, 60, 220, 25);
			contentPane.add(inputLine);
			
			//adding the inputLine_2 text field
			inputLine_clue = new JTextField();
			inputLine_clue.setBounds(320, 60, 120, 25);
			contentPane.add(inputLine_clue);
			
			
			//adding the computer_guess text field
			inputLine_computer_guess = new JTextField();
			inputLine_computer_guess.setBounds(490, 60, 50, 25);
			inputLine_computer_guess.setEditable(false);
			contentPane.add(inputLine_computer_guess);

			//register this frame as an action listener of the text field
			inputLine.addActionListener(this);

			//add a text area with a cyan border around it and scrollbars
			textArea = new JTextArea();
			textArea.setEditable(false);
			JScrollPane scrollText = new JScrollPane(textArea);
			scrollText.setBounds(100, 100, 800, 600);
			scrollText.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			textArea.append(Constants.Message.RULES);

			contentPane.add(scrollText);

			//add instructions
			instructions.setBounds(100, 33, 300, 20);
			contentPane.add(instructions);
			
			//add clue for computer label
			clue_for_computer.setBounds(325, 33, 300, 20);
			contentPane.add(clue_for_computer);
			
			//add computer guess label
			computer_guess.setBounds(470, 33, 140, 20);                          
			contentPane.add(computer_guess);
			
			init();
			//register 'exit upon closing' as default close operation
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

		public void actionPerformed(ActionEvent event)
		{
			//restart the game
			if (event.getSource().equals(restart))
			{
				//generate another random number
				Collections.shuffle(digits);
				
				number = digits.get(0)*1000 + digits.get(1) * 100 + digits.get(2) * 10 + digits.get(3);
				
				//set number of guesses to 0
				numOfGuesses = 0;
						
				//clear the text area
				textArea.setText (Constants.Message.EMPTY_STRING);
				
				//re-append the rules to the text area
				textArea.append (Constants.Message.RULES);
				
				updateInputLineComputerGuess(String.valueOf(computerNumber));
				
				clearText();
				
			}
			
			//check which button the action performed is from
			else
			{
				System.out.print(computerNumber);
				String temp = inputLine.getText();
				String temp_comp = inputLine_computer_guess.getText();
				String temp_clue = inputLine_clue.getText();
				try {

		            if (Checker.checkUserInputIsClue(temp_clue)) {
		            	
		                parseUserInput(temp_clue);
		                
		                clueHistory = temp_comp;
		                
		                updateClueText("");
		                
		                return;
		            }
        
		        } catch (Exception e) {

		        	JOptionPane.showMessageDialog(null, "You must enter an integer", "ERROR!", JOptionPane.ERROR_MESSAGE);
		            
		            return;
		        }
				clearText();
				
				//cheat code in case the number is impossible to guess
				if (temp.equals ("number"))
				{
					JOptionPane.showMessageDialog (null, number);
				}
				
				//checks to make sure text box is not empty
				if (!temp.equals(""))
				{
					numOfGuesses++;
					runGuess (temp);
				}
			}
		}
		
		private void runGuess (String numString)
		{
			int plus = 0;
			
			int minus = 0;
			
			int guess = Integer.parseInt(numString);
			
			//make sure guess is valid
			if ((guess/1000) <= 0 || (guess/1000) >= 10)
			{
				JOptionPane.showMessageDialog (null, "Please enter in a four-digit number without repeats or 0.");
				numOfGuesses--;
			}
			// check duplicates in input
			else if (Checker.hasDuplicates(guess))
			{
				JOptionPane.showMessageDialog (null, "Please enter in a four-digit number without repeats or 0.");
				numOfGuesses--;
			}
				
			// game won
			else if (guess == number)
			{
				textArea.append ("You won the game. The number was " + number + ". "
						+ Constants.Message.NEWLINE + "It took you " + numOfGuesses + " guesses.");
				textArea.append (Constants.Message.NEWLINE + "Click the 'Restart' button below to play again.");
			}
			
			//calculate clue for plus elements and minus elements
			else
			{  
				
				for (int i = 0; i < 4; i++)
				{
					if ((number / (int) Math.pow(10, i)) % 10 == (guess / (int) Math.pow(10, i)) % 10)
					{
						plus++;
					}
					
					else
					{
						for (int j = 0; j < 4; j++)
						{
							if ((number / (int) Math.pow(10, i)) % 10 == (guess / (int) Math.pow(10, j)) % 10)
							{
								minus++;
							}
						}
					}
				}
				if (minus != 0)
				{
					textArea.append ("     " + numOfGuesses + "       |       Your guess: " + guess + 
							"       |       Clue for your guess " + plus + "  and  -" + minus + Constants.Message.NEWLINE);
				}
				else 
				{
					textArea.append ("     " + numOfGuesses + "       |       Your guess: " + guess + 
							"       |       Clue for your guess " + plus + "  and   " + minus + Constants.Message.NEWLINE);
				}
				
		        
				computerNumberGuesses++;
				
			}
			
			clearText();
		}
		
		 // take clue from user
		 private void parseUserInput(String userInput) { 

		        int indexOfPlus = userInput.indexOf('+');
		        
		        int indexOfMinus = userInput.indexOf('-');

		        String plusClue = userInput.substring(indexOfPlus + 1, indexOfMinus);
		        
		        String minusClue = userInput.substring(indexOfMinus + 1);

		        guessUserNumber(Integer.parseInt(plusClue), Integer.parseInt(minusClue), userInput);
		       
		        inputLine_clue.setText(Constants.Message.EMPTY_STRING);
		        
		    }
		 
		 // find user's number with input clue
		 private void guessUserNumber(int plusClue, int minusClue, String userInput) { 

		    	if (plusClue == 4 ) {
		    		      	
		        	JOptionPane.showMessageDialog(null, "\n\n Computer won after " + numOfGuesses + " guesses!" +" Your number: " + computerNumber , "SUCCESS!", JOptionPane.ERROR_MESSAGE);
	                
	                return;
		        	
		        }else if (plusClue == 0 && minusClue == 0) {
		        	
		            if(clueHistory.equals(userInput)){
		            	
		                JOptionPane.showMessageDialog(null, "No possible answer fits the scores you gave!", "ERROR!", JOptionPane.ERROR_MESSAGE);
		                
		                return;
		            }
		            removeFromPossibleNumbers(computerNumber, false, false);

		        }else if(minusClue ==0 && plusClue != 0){
		        	

		            removeFromPossibleNumbers(computerNumber, false, true);

		        }else if(minusClue!=0 && plusClue == 0){
		        	

		            removeFromPossibleNumbers(computerNumber, true, false);
		            
		        }else if (minusClue != 0 && plusClue != 0) {
		        	

		            removeFromPossibleNumbers(computerNumber, true, false);
		            
		            removeFromPossibleNumbers(computerNumber, false, true);
		        }
		         
		    	updateInputLineComputerGuess(String.valueOf(computerNumber));
		    	System.out.print(rootPaneCheckingEnabled);

		    }
		 
		 
		//update computer guess for every turn
		 private void updateInputLineComputerGuess(String str) 
		 {
			 	computerNumber = possibleNumberList.get(new Random().nextInt(possibleNumberList.size() - 1));
		        inputLine_computer_guess.setText(String.valueOf(computerNumber));
		 }
		 
		// eliminate numbers from the list for find valid numbers
		 private void removeFromPossibleNumbers(int computeNumber, boolean computeForMinus, boolean computeForPlus) { 

		        ArrayList<Integer> toBeRemovedList = new ArrayList<>();

		        ArrayList<Integer> toBeStayedList = new ArrayList<>();


		        for (int i = 0; i < 4; i++) {
		        	
		            int last = computerNumber % 10;
		            
		            for (Integer num : possibleNumberList) {
		            	
		                if (String.valueOf(num).contains(last + "")) {
		                	
		                    toBeRemovedList.add(num);
		                }

		                if (computeForPlus && getDigit(last, i, num)) {
		                	
		                    toBeStayedList.add(num);
		                }
		            }
		            computerNumber = computerNumber / 10;
		        }

		        if (!computeForMinus && !computeForPlus) {
		        	
		            possibleNumberList.removeAll(toBeRemovedList);
		        }


		        if (computeForMinus && !computeForPlus) {
		        	
		            possibleNumberList = toBeRemovedList;
		        }


		        if (!computeForMinus && computeForPlus) {
		        	
		            possibleNumberList = toBeStayedList;
		        }


		    }
		 
		 // find location of digit
		 	private boolean getDigit(int compNumberLast, int index, int number) {

		        char character = String.valueOf(number).charAt(Math.abs(index - 3));
		        
		        return String.valueOf(character).equals(String.valueOf(compNumberLast));

		    }
		 

		    private void updateClueText(String message) {

		    	inputLine.setText(message);
		    }

		    private void updateText(String message) {

		    	inputLine_computer_guess.setText(inputLine_computer_guess.getText() + "\n" + message);
		    } 
		    
		    //perform initial operations for computer guess , create and update numbers 
		    private void init() {

		        
		        numOfGuesses = 0;
		        
		        computerNumberGuesses = 0;
		        
		        guessed = false;
		        	        
		        
		        fillPossibleNumber();

		        while (Checker.hasDuplicates(computerNumber)) {
		        	
		            computerNumber = RANDOM.nextInt(9000) + 1000;
		        }

		        updateInputLineComputerGuess(String.valueOf(computerNumber));
		        
		        
		        computerNumberGuesses++;
		    }
		    
		    
		    private void fillPossibleNumber() {

		        for (int i = 1234; i <= 9876; i++) {
		        	
		            if (!Checker.hasDuplicates(i)) {
		            	
		                possibleNumberList.add(i);
		            }
		        }
		    }   
		    
 
		private void clearText()
		{
			//clears the input line
			inputLine.setText(Constants.Message.EMPTY_STRING);
			inputLine_computer_guess.setText(Constants.Message.EMPTY_STRING);
			inputLine_computer_guess.setText(String.valueOf(computerNumber));
			inputLine_clue.setText(Constants.Message.EMPTY_STRING);
		}
		
		
	    

}
