import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class Algorithm {
	
	JTable tableAI = new JTable();
	public static int moves = 0; 
	public int gameCounter;
	
	public Algorithm(JTable table1, int gameCounterImp){
	tableAI = table1;
	gameCounter = gameCounterImp;
	}
	
	public void defend(int x, int y){

		if(gameCounter % 2 == 0){
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
				}

				if(moves == 3){

					if(checkDefend()){
						checkDefend();
						moves++;
						System.out.println("AI Move 4: " + moves);
						return;
					}
					else
					{
						findNull();

					}


				}
				if(moves == 5){

					if(checkWin()){
						moves++;
						return;
					}
					if(checkDefend()){

						checkDefend();

						System.out.println("Something happened at move 5 corner " + moves);
						moves++;

					}


				}
				if(moves == 7){					
					if(checkWin()){
						moves++;
						return;
					}
					if(checkDefend()){


						checkDefend();
						moves++;
						System.out.println("Something happened at move 7 cross");
					}
				}

				if(moves == 9){
					if(checkWin() == false){
						System.out.println("IT'S A DRAW!");
					}
				}
			}// End of first move condition

			//Second move condition
			if(tableAI.getValueAt(1, 1) != "X"){

				if(moves == 1){
					tableAI.setValueAt("O", 1, 1);
					moves++;
					System.out.println("AI Move 2:" + moves);
				}


				if(moves == 3){
					if(checkDefend()){
						checkDefend();
						moves++;
						System.out.println("AI Move 4: " + moves);
						return;
					}	
					else
					{
						findNull();
					}


				}
				if(moves == 5){
					if(checkWin()){
						moves++;
						return;
					}
					if(checkDefend()){
						checkDefend();
						moves++;
						System.out.println("AI Move 6: " + moves);
					}
					else
					{
						findNull();
					}


				}

				if(moves == 7){

					if(checkWin()){
						moves++;
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
				}
				if(moves == 9){
					if(checkWin() == false){
						System.out.println("IT'S A DRAW!");
					}
				} 
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
	
	private boolean cornerPlacement(int x, int y){
		if(((x == 0) && (y == 0)) || ((x == 2) && (y == 2)) || ((x == 0) && (y == 2)) || ((x == 2) && (y == 0))){
			return true;
		}else
			return false;
		
	}
	
	private boolean crossPlacement(int x, int y){
		if(((x == 0) && (y == 1)) || ((x == 1) && (y == 0)) || ((x == 1) && (y == 2)) || ((x == 2) && (y == 1))){
			return true;
		}else
			return false;
	}
	
	private boolean checkWin(){
		//Horizontal Win
		if(((tableAI.getValueAt(0, 0) == "O" && tableAI.getValueAt(0, 1) == "O") || 
		   (tableAI.getValueAt(0, 0) == "O" && tableAI.getValueAt(0, 2) == "O") ||
		   (tableAI.getValueAt(0, 1) == "O" && tableAI.getValueAt(0, 2) == "O")) &&
		   (tableAI.getValueAt(0, 0) == null || tableAI.getValueAt(0, 1) ==  null || tableAI.getValueAt(0, 2) == null)){
			tableAI.setValueAt("O", 0, 0);
			tableAI.setValueAt("O", 0, 1);
			tableAI.setValueAt("O", 0, 2);
			return true;
		}
		if(((tableAI.getValueAt(1, 0) == "O" && tableAI.getValueAt(1, 1) == "O") || 
	       (tableAI.getValueAt(1, 0) == "O" && tableAI.getValueAt(1, 2) == "O") ||
		   (tableAI.getValueAt(1, 1) == "O" && tableAI.getValueAt(1, 2) == "O")) &&
			(tableAI.getValueAt(1, 0) == null || tableAI.getValueAt(1, 1) ==  null || tableAI.getValueAt(1, 2) == null)){
			tableAI.setValueAt("O", 1, 0);
			tableAI.setValueAt("O", 1, 1);
			tableAI.setValueAt("O", 1, 2);
			return true;
		}
		if(((tableAI.getValueAt(2, 0) == "O" && tableAI.getValueAt(2, 1) == "O") || 
		   (tableAI.getValueAt(2, 0) == "O" && tableAI.getValueAt(2, 2) == "O") ||
		   (tableAI.getValueAt(2, 1) == "O" && tableAI.getValueAt(2, 2) == "O")) &&
		   (tableAI.getValueAt(2, 0) == null || tableAI.getValueAt(2, 1) ==  null || tableAI.getValueAt(2, 2) == null)){
			tableAI.setValueAt("O", 2, 0);
			tableAI.setValueAt("O", 2, 1);
			tableAI.setValueAt("O", 2, 2);
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
			return true;
		}
		if(((tableAI.getValueAt(0, 1) == "O" && tableAI.getValueAt(1, 1) == "O") || 
		   (tableAI.getValueAt(0, 1) == "O" && tableAI.getValueAt(2, 1) == "O") ||
		   (tableAI.getValueAt(1, 1) == "O" && tableAI.getValueAt(2, 1) == "O")) &&
		   (tableAI.getValueAt(0, 1) == null || tableAI.getValueAt(1, 1) ==  null || tableAI.getValueAt(2, 1) == null)){
			tableAI.setValueAt("O", 0, 1);
			tableAI.setValueAt("O", 1, 1);
			tableAI.setValueAt("O", 2, 1);
			return true;
		}
		if(((tableAI.getValueAt(0, 2) == "O" && tableAI.getValueAt(1, 2) == "O") || 
		   (tableAI.getValueAt(0, 2) == "O" && tableAI.getValueAt(2, 2) == "O") ||
		   (tableAI.getValueAt(1, 2) == "O" && tableAI.getValueAt(2, 2) == "O")) &&
		   (tableAI.getValueAt(0, 2) == null || tableAI.getValueAt(1, 2) ==  null || tableAI.getValueAt(2, 2) == null)){
			tableAI.setValueAt("O", 0, 2);
			tableAI.setValueAt("O", 1, 2);
			tableAI.setValueAt("O", 2, 2);
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

			return true;
		}
		
		return false;
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
					if(tableAI.getValueAt(0, 1) == null){
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
			for(j = 0; j < 3; j++){
				for(i = 0; i < 3; i++){
				  
					
					int row = j;
			        int column = i;
			      // do some action if appropriate column
			      
			      tableAI.setValueAt(null, row, column);
			      
			      
				}
				if((i == 2) && (j == 2)){
					break;
				}
				
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

	