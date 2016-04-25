import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class Gui extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable table;
	public JTextPane textPane = new JTextPane();
	public JTextPane textPane_1 = new JTextPane();
	static ListSelectionModel listSelectionModel; 
	public static int gameCounter = 0;
	public int playerScore = 0;
	public int computerScore = 0;
	public static int comp =0;
	public static int player = 0;
	public static int tie = 0;
	public static int moves = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
	//			int moves = 0;
				int comp = 0;
				Algorithm a1 = new Algorithm(table, gameCounter);
				if(moves <9 && a1.checkGameWin() != 1)
				{	
					
					a1.userTurn();
					moves++;	
					System.out.print(moves);
				}else{
					if(a1.checkGameWin() == 1){
						
						table.setCellSelectionEnabled(false);
					}
				}
				
					
			}
		});
		
		
	}

	
	/**
	 * Create the frame.
	 */
	public Gui() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 251);
		contentPane.add(panel);
		panel.setLayout(null);
		Algorithm a3 = new Algorithm(table, gameCounter);
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setFocusTraversalKeysEnabled(false);
		table.setBackground(Color.WHITE);
		table.setRowHeight(50);
		table.setBounds(25, 52, 146, 150);
		table.setFont(new Font("Vineta BT", Font.PLAIN, 20));
		panel.add(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.setBorder(new LineBorder(new Color(20, 0, 0)));
		DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer)
		table.getDefaultRenderer(String.class);
		stringRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(197, 152, 195, 47);
		panel.add(btnReset);
		
		btnReset.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e){
				Algorithm a2 = new Algorithm(table, gameCounter);
				
				if(a2.checkGameWin() == 0){
					player++;
					textPane.setText(Integer.toString(player));
				}else if(a2.checkGameWin() == 1){
					comp++;
					textPane_1.setText(Integer.toString(comp));
				} else if(a2.checkGameWin() == 2){
					tie++;
					System.out.println("It was a tie");
				}
				a2.resetGUI();
			}
				
			
		});
		
		JLabel lblPlayer = new JLabel("Player");
		lblPlayer.setFont(new Font("SimSun", Font.PLAIN, 18));
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setBackground(Color.WHITE);
		lblPlayer.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblPlayer.setBounds(197, 52, 89, 32);
		panel.add(lblPlayer);
		
		JLabel lblComputer = new JLabel("Computer");
		lblComputer.setHorizontalAlignment(SwingConstants.CENTER);
		lblComputer.setFont(new Font("SimSun", Font.PLAIN, 18));
		lblComputer.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblComputer.setBackground(Color.WHITE);
		lblComputer.setBounds(303, 52, 89, 32);
		panel.add(lblComputer);
		
		
		textPane.setBounds(197, 95, 89, 32);
		textPane.setText("0");
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		panel.add(textPane);
		
		
		
		textPane_1.setBounds(303, 95, 89, 32);
		textPane_1.setText("0");
		StyledDocument doc1 = textPane_1.getStyledDocument();
		SimpleAttributeSet center1 = new SimpleAttributeSet();
		StyleConstants.setAlignment(center1, StyleConstants.ALIGN_CENTER);
		doc1.setParagraphAttributes(0, doc1.getLength(), center1, false);
		panel.add(textPane_1);
		
			
	}
	
	
	
	
	
	
	
}
