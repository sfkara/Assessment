
public final class Constants {
	public final class View{
		public static final int FRAME_WIDTH = 1000;
		public  static final int FRAME_HEIGHT = 800;

		public  static final int FRAME_X_ORIGIN = 0;
		public  static final int FRAME_Y_ORIGIN = 0;

		public  static final int BUTTON_WIDTH = 80;
		public  static final int BUTTON_HEIGHT = 30;

		
	}
	public final static class Message{
		public  static final String EMPTY_STRING = "";
		public  static final String NEWLINE = System.getProperty("line.separator");
		public  static final String RULES = ("  Rules: \n - Guess four-digit numbers with no repeats and no zeros. A \"plus\" means that you have a correct digit in the correct \n   place, "
				+ "and a \"minus\" means that you have a correct digit, but in the wrong place." + "  \n - Also computer will try to guess your number. You should give clue computer like +2-2, +3-1 , +1-0 etc. format. "
				+ "  \n - Players cannot play at the same time, must play in turn, you can choose who starts. "+"  \n - [CHEAT] :Type \"number\" to input area to see computers's number"+  NEWLINE + NEWLINE);
		public  static final String GUESS_LABEL = "Guess a number: ";
		public  static final String CLUE_LABEL = "Clue for computer: ";
		public  static final String COMPUTER_GUESS_LABEL = "Computer's"+"\nguess: ";
		
		
	}

}
