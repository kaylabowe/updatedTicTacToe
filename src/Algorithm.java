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
		int px = x; 
		int py = y;
		int prvX = px;
		int prvY = py;
		if(gameCounter % 2 == 0){
			if(moves == 1){
				if((x == 1) && (y == 1)){
					tableAI.setValueAt("O", 0, 0);
					moves++;
					System.out.println("AI Move 2:" + moves);
					//userTurn();
				}else{
					tableAI.setValueAt("O", 1, 1);
					moves++;
					System.out.println("AI Move 2:" + moves);
					//userTurn();
				}
			}
						
			if(moves == 3){
				if(cornerPlacement(x, y)){
					
					if((x == 0 && y == 2)){					
						tableAI.setValueAt("O", 2, 0);
						moves++;
						System.out.println("AI Move 4:" + moves);
						//userTurn();
					}else if((x == 2 && y == 2)){
						attack(x, y, 1);
						System.out.println("AI WINS");
					}else{
						tableAI.setValueAt("O", 0, 2);
						moves++;
						System.out.println("AI Move 4:" + moves);
						//userTurn();
					}
					
				}
				if(crossPlacement(x,y)){
					if((x == 0 && y == 1)){					
						tableAI.setValueAt("O", 2, 1);
						moves++;
						System.out.println("AI Move 4:" + moves);
						
						//userTurn();
					}else if((x == 1 && y == 0)){
						tableAI.setValueAt("O", 1, 2);
						moves++;
						System.out.println("AI Move 4:" + moves);
						//userTurn();
					}else if((x == 1 && y == 2)){
						tableAI.setValueAt("O", 1, 0);
						moves++;
						System.out.println("AI Move 4:" + moves);
						//userTurn();
					}else{
						tableAI.setValueAt("O", 0, 1);
						moves++;
						System.out.println("AI Move 4:" + moves);
						//userTurn();
					}
				}
		    }
			if(moves == 5){
				if(cornerPlacement(x, y)){
					
					if((tableAI.getValueAt(0, 2) == "X" && tableAI.getValueAt(2, 2) == "X") && tableAI.getValueAt(1, 1) == "X"){					
						attack(x,y, 2);
						System.out.println("AI Move 6:" + moves + " AI WINS");
					}else if((tableAI.getValueAt(0, 2) ==  "X" && tableAI.getValueAt(0, 1) == "X" && tableAI.getValueAt(2, 0) == "O")){
						attack(x, y, 2);
						System.out.println("AI Move 6:" + moves + " AI WINS");
					}else if((tableAI.getValueAt(0, 2) ==  "X" && tableAI.getValueAt(0, 1) == "X" && tableAI.getValueAt(2, 1) == "O")){
						tableAI.setValueAt("O", 2, 0);
						moves++;
						System.out.println("AI Move 6:" + moves);
					}
					
				}
				if(crossPlacement(x,y)){
					if((tableAI.getValueAt(0, 2) ==  "X" && tableAI.getValueAt(0, 1) == "X")){
						attack(x, y, 2);
						System.out.println("AI Move 6:" + moves + " AI WINS");
					}else if((tableAI.getValueAt(2, 1) ==  "X" && tableAI.getValueAt(0, 2) == "X")){
						attack(x,y,2);
						System.out.println("AI Move 6:" + moves + " AI WINS");
					}else if((tableAI.getValueAt(1, 2) ==  "X" && tableAI.getValueAt(0, 2) == "X")){
						attack(x,y,2);
						System.out.println("AI Move 6:" + moves + " AI WINS");
					}else if((tableAI.getValueAt(1, 0) ==  "X" && tableAI.getValueAt(1, 1) == "X")){
						tableAI.setValueAt("O", 1, 2);
						moves++;
						//userTurn();
					}
				}
		    }
		}
	}
	
	private void attack(int x, int y, int attackMove){
		if(attackMove == 1){
			tableAI.setValueAt("O", 0, 2);
			moves++;
			//userTurn();
		}
		if(attackMove == 2){
			moves++;
			tableAI.setValueAt("O", 1, 0);
			//checkWin(1);
		}
	}
	public void userTurn(){
	tableAI.addMouseListener(new MouseAdapter() {
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
	
	private void checkWin(int winner){
		Gui g1 = new Gui();
		if(winner == 1){
			
		}
	}
}

	