import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.JTextPane;

public class Algorithm {
	
	JTable tableAI = new JTable();
	JTextPane textPane;
	JTextPane textPane1;
	public static int moves = 0; 
	public int gameCounter;
	public static int counter = 0;
	public Algorithm(JTable table1, int gameCounterImp){
	tableAI = table1;
	gameCounter = gameCounterImp;
	
	}
	
	public void defend(int x, int y){

		if(counter % 2 == 0){
			if(tableAI.getValueAt(1, 1) ==  "X"){
				
				if(moves == 1){
					if((x == 1) && (y == 1)){
						tableAI.setValueAt("O", 0, 0);
						moves++;
						System.out.println("AI Move 2:" + moves);
					}else{
						tableAI.setValueAt("O", 1, 1);
						moves++;
						System.out.println("AI Move 2:" + moves);
					}
				} // End of move 1

				if(moves == 3){
					if(tableAI.getValueAt(1, 1) == "X" && tableAI.getValueAt(2, 2) == "X")
					{
						tableAI.setValueAt("O", 0, 2);
						moves++;
					}
					else if(checkDefend())
					{
						checkDefend();
						moves++;
						System.out.println("AI Move 4: " + moves);
						return;
					}
					else
					{
						findNull();

					}
				} // End of move 3
				
				if(moves == 5){

					if(checkWin()){
						moves++;
						
						if(checkGameWin() == 1){
							tableAI.disable();
							counter++;
						}
						return;
					}
					if(checkDefend()){

						checkDefend();

						System.out.println("Something happened at move 5 corner " + moves);
						moves++;
					}else
					{
						findNull();
					}
					
				} 
				if(moves == 7){					
					if(checkWin()){
						moves++;
						if(checkGameWin() == 1){
							tableAI.disable();
							counter++;
						}
						return;
					}
					if(checkDefend()){
						checkDefend();
						moves++;
						System.out.println("Something happened at move 7 cross");
					}
					else
					{
						findNull();
					}
				} // End of move 7

				if(moves == 9){
					if(checkGameWin() == 3){
						tableAI.disable();
						counter++;
					}
					if(checkWin() == false){
						System.out.println("IT'S A DRAW!");
					}
				} // End of move 9
				
			}// End of first move condition

			//Second move condition
			if(tableAI.getValueAt(1, 1) != "X"){

				if(moves == 1){
					tableAI.setValueAt("O", 1, 1);
					moves++;
					System.out.println("AI Move 2:" + moves);
				} // End of move 1
				
				if(moves == 3){
					if(checkDefend()){
						checkDefend();
						moves++;
						System.out.println("AI Move 4: " + moves);
						return;
					}else if(tableAI.getValueAt(2, 0) == "X" && tableAI.getValueAt(0, 2) == "X")
					{
						tableAI.setValueAt("O", 0, 1);
						moves++;
					}else if(tableAI.getValueAt(0, 2) == "X" && tableAI.getValueAt(2, 1) == "X"){
						tableAI.setValueAt("O", 1, 2);
						moves++;
					}
					else if(tableAI.getValueAt(0, 0) == "X" && tableAI.getValueAt(2, 1) == "X"){
						tableAI.setValueAt("O", 1, 0);
						moves++;
					}
					else
					{
						findNull();
					}
				} // End of move 3
				
				if(moves == 5){
					if(checkWin()){
						moves++;
						
						if(checkGameWin() == 1){
							tableAI.disable();
							counter++;
						}
						return;
					}
					if(checkDefend()){
						checkDefend();
						moves++;
						System.out.println("AI Move 6: " + moves);
					}
					else if(tableAI.getValueAt(0, 0) == "X" && tableAI.getValueAt(1, 2) == "X" && tableAI.getValueAt(2, 1) == "X"){
						tableAI.setValueAt("O", 2, 2);
						moves++;
					}
					else if(tableAI.getValueAt(0, 0) == "X" && tableAI.getValueAt(2, 1) == "X"){
						tableAI.setValueAt("O", 0, 2);
						moves++;
					}else if(tableAI.getValueAt(0, 0) == "X" && tableAI.getValueAt(1, 2) == "X" && tableAI.getValueAt(2, 1) == "X"){
						tableAI.setValueAt("O", 1, 0);
						moves++;
					}
					else
					{
						findNull();
					}

				} // End of move 5

				if(moves == 7){

					if(checkWin()){
						moves++;
						
						if(checkGameWin() == 1){
							tableAI.disable();
							counter++;
						}
						return;
					}
					if(checkDefend()){
						checkDefend();
						moves++;
						System.out.println("AI Move 7: " + moves);
					}
					else
					{
						findNull();
					}	
				} // End of move 7
				
				if(moves == 9){
					if(checkGameWin() == 3){
						tableAI.disable();
						counter++;
					}
					if(checkWin() == false){
						System.out.println("IT'S A DRAW!");
					}
				} // End of move 9
				
			}// Second Move
		}//End of turn counter if
	} // End of defent()
	
	
	public void userTurn(){
	tableAI.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1) {
		    	JTable target = (JTable)e.getSource();
				int row = target.getSelectedRow();
			    int column = target.getSelectedColumn();
			    // do some action if appropriate column
				      
			    target.setValueAt("X", row, column);
			    moves++;
			    System.out.println("UserTurn:" + moves);
			    defend(row, column);
			    
		    }
		}
		});
	}
	
	
	public boolean checkWin(){
		//Horizontal Win
		int comp = 0;
		int playa = 1;
		if(((tableAI.getValueAt(0, 0) == "O" && tableAI.getValueAt(0, 1) == "O") || 
		   (tableAI.getValueAt(0, 0) == "O" && tableAI.getValueAt(0, 2) == "O") ||
		   (tableAI.getValueAt(0, 1) == "O" && tableAI.getValueAt(0, 2) == "O")) &&
		   (tableAI.getValueAt(0, 0) == null || tableAI.getValueAt(0, 1) ==  null || tableAI.getValueAt(0, 2) == null)){
			tableAI.setValueAt("O", 0, 0);
			tableAI.setValueAt("O", 0, 1);
			tableAI.setValueAt("O", 0, 2);
			//endGame(0);
			return true;
		}
		if(((tableAI.getValueAt(1, 0) == "O" && tableAI.getValueAt(1, 1) == "O") || 
	       (tableAI.getValueAt(1, 0) == "O" && tableAI.getValueAt(1, 2) == "O") ||
		   (tableAI.getValueAt(1, 1) == "O" && tableAI.getValueAt(1, 2) == "O")) &&
			(tableAI.getValueAt(1, 0) == null || tableAI.getValueAt(1, 1) ==  null || tableAI.getValueAt(1, 2) == null)){
			tableAI.setValueAt("O", 1, 0);
			tableAI.setValueAt("O", 1, 1);
			tableAI.setValueAt("O", 1, 2);
			//endGame(0);
			return true;
		}
		if(((tableAI.getValueAt(2, 0) == "O" && tableAI.getValueAt(2, 1) == "O") || 
		   (tableAI.getValueAt(2, 0) == "O" && tableAI.getValueAt(2, 2) == "O") ||
		   (tableAI.getValueAt(2, 1) == "O" && tableAI.getValueAt(2, 2) == "O")) &&
		   (tableAI.getValueAt(2, 0) == null || tableAI.getValueAt(2, 1) ==  null || tableAI.getValueAt(2, 2) == null)){
			tableAI.setValueAt("O", 2, 0);
			tableAI.setValueAt("O", 2, 1);
			tableAI.setValueAt("O", 2, 2);
			//endGame(0);
			return true;
		}
		//Vertical Wins
		if(((tableAI.getValueAt(0, 0) == "O" && tableAI.getValueAt(1, 0) == "O") || 
		   (tableAI.getValueAt(0, 0) == "O" && tableAI.getValueAt(2, 0) == "O") ||
		   (tableAI.getValueAt(1, 0) == "O" && tableAI.getValueAt(2, 0) == "O")) &&
		   (tableAI.getValueAt(0, 0) == null || tableAI.getValueAt(1, 0) ==  null || tableAI.getValueAt(2, 0) == null)){
			tableAI.setValueAt("O", 0, 0);
			tableAI.setValueAt("O", 1, 0);
			tableAI.setValueAt("O", 2, 0);
			//endGame(0);
			return true;
		}
		if(((tableAI.getValueAt(0, 1) == "O" && tableAI.getValueAt(1, 1) == "O") || 
		   (tableAI.getValueAt(0, 1) == "O" && tableAI.getValueAt(2, 1) == "O") ||
		   (tableAI.getValueAt(1, 1) == "O" && tableAI.getValueAt(2, 1) == "O")) &&
		   (tableAI.getValueAt(0, 1) == null || tableAI.getValueAt(1, 1) ==  null || tableAI.getValueAt(2, 1) == null)){
			tableAI.setValueAt("O", 0, 1);
			tableAI.setValueAt("O", 1, 1);
			tableAI.setValueAt("O", 2, 1);
			//endGame(0);
			return true;
		}
		if(((tableAI.getValueAt(0, 2) == "O" && tableAI.getValueAt(1, 2) == "O") || 
		   (tableAI.getValueAt(0, 2) == "O" && tableAI.getValueAt(2, 2) == "O") ||
		   (tableAI.getValueAt(1, 2) == "O" && tableAI.getValueAt(2, 2) == "O")) &&
		   (tableAI.getValueAt(0, 2) == null || tableAI.getValueAt(1, 2) ==  null || tableAI.getValueAt(2, 2) == null)){
			tableAI.setValueAt("O", 0, 2);
			tableAI.setValueAt("O", 1, 2);
			tableAI.setValueAt("O", 2, 2);
			//endGame(0);
			return true;
		}
		//Diagonal Wins
		if(((tableAI.getValueAt(0, 0) == "O" && tableAI.getValueAt(1, 1) == "O") || 
		   (tableAI.getValueAt(0, 0) == "O" && tableAI.getValueAt(2, 2) == "O") ||
		   (tableAI.getValueAt(1, 1) == "O" && tableAI.getValueAt(2, 2) == "O")) &&
		   (tableAI.getValueAt(0, 0) == null || tableAI.getValueAt(1, 1) ==  null || tableAI.getValueAt(2, 2) == null)){
			tableAI.setValueAt("O", 0, 0);
			tableAI.setValueAt("O", 1, 1);
			tableAI.setValueAt("O", 2, 2);
			System.out.println("this statement");
			//endGame(0);
			return true;
		}
		if(((tableAI.getValueAt(0, 2) == "O" && tableAI.getValueAt(1, 1) == "O") || 
		   (tableAI.getValueAt(0, 2) == "O" && tableAI.getValueAt(2, 0) == "O") ||
		   (tableAI.getValueAt(1, 1) == "O" && tableAI.getValueAt(2, 0) == "O")) &&
		   (tableAI.getValueAt(0, 2) == null || tableAI.getValueAt(1, 1) ==  null || tableAI.getValueAt(2, 0) == null)){
			tableAI.setValueAt("O", 0, 2);
			tableAI.setValueAt("O", 1, 1);
			tableAI.setValueAt("O", 2, 0);
			System.out.println("that statement");
			//endGame(0);
			return true;
		}
		
		return false;
	}
	
	public int checkGameWin(){
		if(tableAI.getValueAt(0, 0) ==  "O" && tableAI.getValueAt(0, 1) == "O" && tableAI.getValueAt(0, 2) ==  "O"){
			System.out.println("O wins");
			return 1;
		}else if(tableAI.getValueAt(1, 0) ==  "O" && tableAI.getValueAt(1, 1) == "O" && tableAI.getValueAt(1, 2) ==  "O"){
			System.out.println("O wins");
			return 1;
		}else if(tableAI.getValueAt(2, 0) ==  "O" && tableAI.getValueAt(2, 1) == "O" && tableAI.getValueAt(2, 2) ==  "O"){
			System.out.println("O wins");
			return 1;
		}else if(tableAI.getValueAt(0, 0) ==  "X" && tableAI.getValueAt(0, 1) == "X" && tableAI.getValueAt(0, 2) ==  "X"){
			System.out.println("X wins");
			return 0;
		} else if(tableAI.getValueAt(1, 0) ==  "X" && tableAI.getValueAt(1, 1) == "X" && tableAI.getValueAt(1, 2) ==  "X"){
			System.out.println("X wins");
			return 0;
		}else if(tableAI.getValueAt(2, 0) ==  "X" && tableAI.getValueAt(2, 1) == "X" && tableAI.getValueAt(2, 2) ==  "X"){
			System.out.println("X wins");
			return 0;
		}else if(tableAI.getValueAt(0, 0) ==  "O" && tableAI.getValueAt(1, 0) == "O" && tableAI.getValueAt(2, 0) ==  "O"){
			System.out.println("O wins");
			return 1;
		}else if(tableAI.getValueAt(0, 1) ==  "O" && tableAI.getValueAt(1, 1) == "O" && tableAI.getValueAt(2, 1) ==  "O"){
			System.out.println("O wins");
			return 1;
		}else if(tableAI.getValueAt(0, 2) ==  "O" && tableAI.getValueAt(1, 2) == "O" && tableAI.getValueAt(2, 2) ==  "O"){
			System.out.println("O wins");
			return 1;
		}else if(tableAI.getValueAt(0, 0) ==  "X" && tableAI.getValueAt(1, 0) == "X" && tableAI.getValueAt(2, 0) ==  "X"){
			System.out.println("X wins");
			return 0;
		}else if(tableAI.getValueAt(0, 1) ==  "X" && tableAI.getValueAt(1, 1) == "X" && tableAI.getValueAt(2, 1) ==  "X"){
			System.out.println("X wins");
			return 0;
		}else if(tableAI.getValueAt(0, 2) ==  "X" && tableAI.getValueAt(1, 2) == "X" && tableAI.getValueAt(2, 2) ==  "X"){
			System.out.println("X wins");
			return 0;
		}else if(tableAI.getValueAt(0, 0) ==  "O" && tableAI.getValueAt(1, 1) == "O" && tableAI.getValueAt(2, 2) ==  "O"){
			System.out.println("O wins");
			return 1;
		}else if(tableAI.getValueAt(0, 2) ==  "O" && tableAI.getValueAt(1, 1) == "O" && tableAI.getValueAt(2, 0) ==  "O"){
			System.out.println("O wins");
			return 1;
		}else if(tableAI.getValueAt(0, 0) ==  "X" && tableAI.getValueAt(1, 1) == "X" && tableAI.getValueAt(2, 2) ==  "X"){
			System.out.println("X wins");
			return 0;
		}else if(tableAI.getValueAt(0, 2) ==  "X" && tableAI.getValueAt(1, 1) == "X" && tableAI.getValueAt(2, 0) ==  "X"){
			System.out.println("X wins");
			return 0;
		}
		return 2;
	}
	
	public boolean checkDefend(){
		if(((tableAI.getValueAt(0, 0) == "X" && tableAI.getValueAt(0, 1) == "X") || 
				   (tableAI.getValueAt(0, 0) == "X" && tableAI.getValueAt(0, 2) == "X") ||
				   (tableAI.getValueAt(0, 1) == "X" && tableAI.getValueAt(0, 2) == "X")) &&
				   (tableAI.getValueAt(0, 0) == null || tableAI.getValueAt(0, 1) ==  null || tableAI.getValueAt(0, 2) == null)){
					if(tableAI.getValueAt(0, 0) == null){
						tableAI.setValueAt("O", 0, 0);
						return true;
					}
					if(tableAI.getValueAt(0, 1) == null){
						tableAI.setValueAt("O", 0, 1);
						return true;
					}
					if(tableAI.getValueAt(0, 2) == null){
						tableAI.setValueAt("O", 0, 2);
						return true;
					}
					
					return true;
				}
				if(((tableAI.getValueAt(1, 0) == "X" && tableAI.getValueAt(1, 1) == "X") || 
			       (tableAI.getValueAt(1, 0) == "X" && tableAI.getValueAt(1, 2) == "X") ||
				   (tableAI.getValueAt(1, 1) == "X" && tableAI.getValueAt(1, 2) == "X")) &&
				   (tableAI.getValueAt(1, 0) == null || tableAI.getValueAt(1, 1) ==  null || tableAI.getValueAt(1, 2) == null)){
					if(tableAI.getValueAt(1, 0) == null){
						tableAI.setValueAt("O", 1, 0);
						return true;

					}
					if(tableAI.getValueAt(1, 1) == null){
						tableAI.setValueAt("O", 1, 1);
						return true;

					}
					if(tableAI.getValueAt(1, 2) == null){
						tableAI.setValueAt("O", 1, 2);
						return true;

					}
					return true;
				}
				if(((tableAI.getValueAt(2, 0) == "X" && tableAI.getValueAt(2, 1) == "X") || 
				   (tableAI.getValueAt(2, 0) == "X" && tableAI.getValueAt(2, 2) == "X") ||
				   (tableAI.getValueAt(2, 1) == "X" && tableAI.getValueAt(2, 2) == "X")) &&
				   (tableAI.getValueAt(2, 0) == null || tableAI.getValueAt(2, 1) ==  null || tableAI.getValueAt(2, 2) == null)){
					if(tableAI.getValueAt(2, 0) == null){
						tableAI.setValueAt("O", 2, 0);
						return true;

					}
					if(tableAI.getValueAt(2, 1) == null){
						tableAI.setValueAt("O", 2, 1);
						return true;

					}
					if(tableAI.getValueAt(2, 2) == null){
						tableAI.setValueAt("O", 2, 2);
						return true;

					}
					return true;
				}
				//Vertical Defend
				if(((tableAI.getValueAt(0, 0) == "X" && tableAI.getValueAt(1, 0) == "X") || 
				   (tableAI.getValueAt(0, 0) == "X" && tableAI.getValueAt(2, 0) == "X") ||
				   (tableAI.getValueAt(1, 0) == "X" && tableAI.getValueAt(2, 0) == "X")) &&
				   (tableAI.getValueAt(0, 0) == null || tableAI.getValueAt(1, 0) ==  null || tableAI.getValueAt(2, 0) == null)){
					if(tableAI.getValueAt(0, 0) == null){
						tableAI.setValueAt("O", 0, 0);
						return true;

					}
					if(tableAI.getValueAt(1, 0) == null){
						tableAI.setValueAt("O", 1, 0);
						return true;

					}
					if(tableAI.getValueAt(2, 0) == null){
						tableAI.setValueAt("O", 2, 0);
						return true;

					}
					return true;
				}
				if(((tableAI.getValueAt(0, 1) == "X" && tableAI.getValueAt(1, 1) == "X") || 
				   (tableAI.getValueAt(0, 1) == "X" && tableAI.getValueAt(2, 1) == "X") ||
				   (tableAI.getValueAt(1, 1) == "X" && tableAI.getValueAt(2, 1) == "X")) &&
				   (tableAI.getValueAt(0, 1) == null || tableAI.getValueAt(1, 1) ==  null || tableAI.getValueAt(2, 1) == null)){
					if(tableAI.getValueAt(0, 1) == null){
						tableAI.setValueAt("O", 0, 1);
						return true;

					}
					if(tableAI.getValueAt(1, 1) == null){
						tableAI.setValueAt("O", 1, 1);
						return true;

					}
					if(tableAI.getValueAt(2, 1) == null){
						tableAI.setValueAt("O", 2, 1);
						return true;

					}
					return true;
				}
				if(((tableAI.getValueAt(0, 2) == "X" && tableAI.getValueAt(1, 2) == "X") || 
				   (tableAI.getValueAt(0, 2) == "X" && tableAI.getValueAt(2, 2) == "X") ||
				   (tableAI.getValueAt(1, 2) == "X" && tableAI.getValueAt(2, 2) == "X")) &&
				   (tableAI.getValueAt(0, 2) == null || tableAI.getValueAt(1, 2) ==  null || tableAI.getValueAt(2, 2) == null)){
					if(tableAI.getValueAt(0, 2) == null){
						tableAI.setValueAt("O", 0, 2);
						return true;

					}
					if(tableAI.getValueAt(1, 2) == null){
						tableAI.setValueAt("O", 1, 2);
						return true;

					}
					if(tableAI.getValueAt(2, 2) == null){
						tableAI.setValueAt("O", 2, 2);
						return true;

					}
					return true;
				}
				//Diagonal Defend
				if(((tableAI.getValueAt(0, 0) == "X" && tableAI.getValueAt(1, 1) == "X") || 
				   (tableAI.getValueAt(0, 0) == "X" && tableAI.getValueAt(2, 2) == "X") ||
				   (tableAI.getValueAt(1, 1) == "X" && tableAI.getValueAt(2, 2) == "X")) &&
				   (tableAI.getValueAt(0, 0) == null || tableAI.getValueAt(1, 1) ==  null || tableAI.getValueAt(2, 2) == null)){
					if(tableAI.getValueAt(0, 0) == null){
						tableAI.setValueAt("O", 0, 0);
						return true;

					}
					if(tableAI.getValueAt(1, 1) == null){
						tableAI.setValueAt("O", 1, 1);
						return true;

					}
					if(tableAI.getValueAt(2, 2) == null){
						tableAI.setValueAt("O", 2, 2);
						return true;
					}
					return true;
				}
				if(((tableAI.getValueAt(0, 2) == "X" && tableAI.getValueAt(1, 1) == "X") || 
				   (tableAI.getValueAt(0, 2) == "X" && tableAI.getValueAt(2, 0) == "X") ||
				   (tableAI.getValueAt(1, 1) == "X" && tableAI.getValueAt(2, 0) == "X")) &&
				   (tableAI.getValueAt(0, 2) == null || tableAI.getValueAt(1, 1) ==  null || tableAI.getValueAt(2, 0) == null)){
					if(tableAI.getValueAt(0, 2) == null){
						tableAI.setValueAt("O", 0, 2);
						System.out.println("got to case 1");
						return true;

					}
					if(tableAI.getValueAt(1, 1) == null){
						tableAI.setValueAt("O", 1, 1);
						System.out.println("got to case 2");
						return true;

					}
					if(tableAI.getValueAt(2, 0) == null){
						tableAI.setValueAt("O", 2, 0);
						System.out.println("got to case 3");
						return true;

					}
					return true;
				}
				
				return false;
	}
	
	public void resetGUI(){
		moves = 0;
		System.out.println(moves);
		int j;
		int i;
		String[][] myStringArray = new String[3][3];
		int winValue = checkGameWin();
		for(j = 0; j < 3; j++){
				for(i = 0; i < 3; i++){
				  
					
					int row = j;
			        int column = i;
			      // do some action if appropriate column
			      myStringArray[row][column] = (String) tableAI.getValueAt(row, column);
			      if(myStringArray[row][column] == null)
			      {
			    	  myStringArray[row][column] = "_";
			      }
			      tableAI.setValueAt(null, row, column);
			      tableAI.enable();
			      counter = 0;
			      
				}
				if((i == 2) && (j == 2)){
					break;
				}
				
			}
		
		try
		{
		    String file= "Moves.txt";
		    FileWriter fw = new FileWriter(file,true); //the true will append the new data
		    fw.write(myStringArray[0][0]+','+
		    		 myStringArray[0][1]+','+
		    		 myStringArray[0][2]+','+System.getProperty( "line.separator" )+
		    		 myStringArray[1][0]+','+
		    		 myStringArray[1][1]+','+
		    		 myStringArray[1][2]+','+System.getProperty( "line.separator" )+
		    		 myStringArray[2][0]+','+
		    		 myStringArray[2][1]+','+
		    		 myStringArray[2][2]+", \r\n");//appends the string to the file
		    if(winValue==0){
		    	fw.write("X WINS \r\n\r\n");
		    }
		    if(winValue==1){
		    	fw.write("O WINS \r\n\r\n");
		    }
		    if(winValue==2){
		    	fw.write("TIE \r\n\r\n");
		    }
		    fw.flush();
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	
	
	}

	public void findNull(){
		int j;
		int i;
			for(j = 0; j < 3; j++){
				for(i = 0; i < 3; i++){
				  
					
					int row = j;
			        int column = i;
			      // look for the null value and set it if null
			      if(tableAI.getValueAt(row, column) == null){
			    	  tableAI.setValueAt("O", row, column);
			    	  moves++;
			    	  return;
			      }
			      
			      
				}
				if((i == 2) && (j == 2)){
					break;
				}
				
			}
	}
	

}

	