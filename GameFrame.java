import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements ActionListener{
	// frame components
	private JButton singlePlayer, twoPlayers, exit;
	private JPanel buttonsPanel, gamePanel;
	// frame size
	private int frameX = 320, frameY = 350;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private XO[] xos = new XO[9]; 	// used to prevent multiple clicked, control computer turn and determine winner
	// constant coordinates for Xs and Os drawing 
	private Coordinate[] COORDINATES = {new Coordinate(40, 50), new Coordinate(140, 50), new Coordinate(240, 50),
			new Coordinate(40, 150), new Coordinate(140, 150), new Coordinate(240, 150),
			new Coordinate(40, 250), new Coordinate(140, 250), new Coordinate(240, 250)};
	private boolean isX = true; 	// control players turns
	private boolean isTwoPlayers;	// Control single player mood and two players mood 
	private boolean gameEnd; 		// true for when all spaces are filled
	public GameFrame(){
		// Initialize the frame
		super("Tic Tac Toe");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(frameX, frameY);
		setResizable(false);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//	initialize components 
		singlePlayer = new JButton("One Player");
		singlePlayer.addActionListener(this);
		twoPlayers = new JButton("Two Players");
		twoPlayers.addActionListener(this);
		exit = new JButton("Play Again");
		exit.addActionListener(this);
		buttonsPanel = new JPanel();
		gamePanel = new JPanel();
		gamePanel.addMouseListener(new BoardClicking());
		// add components to the frame
		add(gamePanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.add(singlePlayer);
		buttonsPanel.add(twoPlayers);
		buttonsPanel.add(exit);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
			case "One Player" :
				isTwoPlayers = false;
				newGame();
				break;
			case "Two Players" :
				isTwoPlayers = true;
				newGame();
				break;
			case "Play Again" :
				newGame();
		}
	}
	private class BoardClicking extends MouseAdapter{

		public void mouseClicked(MouseEvent e){
			if(!gameEnd){
				// Determine clicked square, create object of X or O alternatively and paint it  
				if(xos[0] == null && e.getX() < 100 && e.getY() < 120){

					if(isX){
						xos[0] = new X(COORDINATES[0]);
					}
					else{
						xos[0] = new O(COORDINATES[0]);
					}
					isX = !isX;
				}
				else if(xos[1] == null && e.getX() > 100 && e.getX() < 200 && e.getY() < 120){

					if(isX){
						xos[1] = new X(COORDINATES[1]);
					}
					else{
						xos[1] = new O(COORDINATES[1]);
					}
					isX = !isX;
				}	
				else if(xos[2] == null && e.getX() > 200 && e.getY() < 120){

					if(isX){
						xos[2] = new X(COORDINATES[2]);
					}
					else{
						xos[2] = new O(COORDINATES[2]);
					}
					isX = !isX;
				}
				else if(xos[3] == null && e.getX() < 100 && e.getY() > 120 && e.getY() < 220){

					if(isX){
						xos[3] = new X(COORDINATES[3]);
					}
					else{
						xos[3] = new O(COORDINATES[3]);
					}
					isX = !isX;
				}
				else if(xos[4] == null && e.getX() > 100 && e.getX() < 200 && e.getY() > 120 && e.getY() < 220){

					if(isX){
						xos[4] = new X(COORDINATES[4]);
					}
					else{
						xos[4] = new O(COORDINATES[4]);
					}
					isX = !isX;
				}	
				else if(xos[5] == null && e.getX() > 200 && e.getY() > 120 && e.getY() < 220){

					if(isX){
						xos[5] = new X(COORDINATES[5]);
					}
					else{
						xos[5] = new O(COORDINATES[5]);
					}
					isX = !isX;
				}
				else if(xos[6] == null && e.getX() < 100 && e.getY() > 220){

					if(isX){
						xos[6] = new X(COORDINATES[6]);
					}
					else{
						xos[6] = new O(COORDINATES[6]);
					}
					isX = !isX;
				}
				else if(xos[7] == null && e.getX() > 100 && e.getX() < 200 && e.getY() > 220){

					if(isX){
						xos[7] = new X(COORDINATES[7]);
					}
					else{
						xos[7] = new O(COORDINATES[7]);
					}
					isX = !isX;
				}	
				else if(xos[8] == null && e.getX() > 200 && e.getY() > 220){

					if(isX){
						xos[8] = new X(COORDINATES[8]);
					}
					else{
						xos[8] = new O(COORDINATES[8]);
					}
					isX = !isX;
				}
				repaint();
				// true for single player
				if(!isTwoPlayers && !isFilled() && !isWin()){ 	// computer turn, randomly;
					Random r = new Random();
					int square = r.nextInt(9);
					while(xos[square] != null){
						square = r.nextInt(9);
					}
					xos[square] = new O(COORDINATES[square]);
					isX = !isX;
				}
				repaint();
			}
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		drawBoard();
		for (int i = 0; i < xos.length; i++) {
			if(xos[i] != null)
				xos[i].draw((Graphics2D) g);
		}
		gameEnd = isWin();
	}
	public void drawBoard(){
		Graphics2D g2d = (Graphics2D) this.getGraphics();
		g2d.setStroke(new BasicStroke(3.0f));
		g2d.setColor(Color.BLACK);
		// Initialize game board
		Line2D.Float[] gameBoard = new Line2D.Float[4];
		// Vertical
		gameBoard[0] = new Line2D.Float(110f, 30f, 110f, 310f);
		gameBoard[1] = new Line2D.Float(210f, 30f, 210f, 310f);
		// Horizontal
		gameBoard[2] = new Line2D.Float(20f, 120f, 300f, 120f);
		gameBoard[3] = new Line2D.Float(20f, 220f, 300f, 220f);
		// draw the game board
		for (int i = 0; i < gameBoard.length; i++) {
			g2d.draw(gameBoard[i]);
		}
	}
	// used to detect game end to prevent computer entering infinite loop checking for empty space
	public boolean isFilled(){
		for (int i = 0; i < xos.length; i++) {
			if(xos[i] == null)
				return false;
		}
		return true;
	}
	public void newGame(){
		gameEnd = false;
		isX = true;	// to make X first player
		for (int i = 0; i < xos.length; i++) {
			xos[i] = null;	// remove all drawn Xs and Os
		}
		repaint();
	}
	// used to detect win
	public boolean isWin(){
		Graphics g = getGraphics();
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.setStroke(new BasicStroke(3.0f));
		g2d.setColor(Color.GREEN);
		// check for X and draw crossing line
		if(xos[0] instanceof X && xos[1] instanceof X && xos[2] instanceof X)	// first row
			g2d.drawLine(COORDINATES[0].getX(), COORDINATES[0].getY()+25, COORDINATES[2].getX()+50, COORDINATES[2].getY()+25);
		else if(xos[3] instanceof X && xos[4] instanceof X && xos[5] instanceof X)	// second row
			g2d.drawLine(COORDINATES[3].getX(), COORDINATES[3].getY()+25, COORDINATES[5].getX()+50, COORDINATES[5].getY()+25);
		else if(xos[6] instanceof X && xos[7] instanceof X && xos[8] instanceof X)	// third row
			g2d.drawLine(COORDINATES[6].getX(), COORDINATES[6].getY()+25, COORDINATES[8].getX()+50, COORDINATES[8].getY()+25);
		else if(xos[0] instanceof X && xos[3] instanceof X && xos[6] instanceof X)	// first column 
			g2d.drawLine(COORDINATES[0].getX()+25, COORDINATES[0].getY(), COORDINATES[6].getX()+25, COORDINATES[6].getY()+50);
		else if(xos[1] instanceof X && xos[4] instanceof X && xos[7] instanceof X)	// second column
			g2d.drawLine(COORDINATES[1].getX()+25, COORDINATES[1].getY(), COORDINATES[7].getX()+25, COORDINATES[7].getY()+50);
		else if(xos[2] instanceof X && xos[5] instanceof X && xos[8] instanceof X)	// third column
			g2d.drawLine(COORDINATES[2].getX()+25, COORDINATES[2].getY(), COORDINATES[8].getX()+25, COORDINATES[8].getY()+50);
		else if(xos[0] instanceof X && xos[4] instanceof X && xos[8] instanceof X)	// right to left diagonal
			g2d.drawLine(COORDINATES[0].getX(), COORDINATES[0].getY(), COORDINATES[8].getX()+50, COORDINATES[8].getY()+50);
		else if(xos[2] instanceof X && xos[4] instanceof X && xos[6] instanceof X)	// left to right diagonal
			g2d.drawLine(COORDINATES[2].getX()+50, COORDINATES[2].getY(), COORDINATES[6].getX(), COORDINATES[6].getY()+50);
		// check for O and draw crossing line
		else if(xos[0] instanceof O && xos[1] instanceof O && xos[2] instanceof O)	// first row
			g2d.drawLine(COORDINATES[0].getX(), COORDINATES[0].getY()+25, COORDINATES[2].getX()+50, COORDINATES[2].getY()+25);
		else if(xos[3] instanceof O && xos[4] instanceof O && xos[5] instanceof O)	// second row
			g2d.drawLine(COORDINATES[3].getX(), COORDINATES[3].getY()+25, COORDINATES[5].getX()+50, COORDINATES[5].getY()+25);
		else if(xos[6] instanceof O && xos[7] instanceof O && xos[8] instanceof O)	// third row
			g2d.drawLine(COORDINATES[6].getX(), COORDINATES[6].getY()+25, COORDINATES[8].getX()+50, COORDINATES[8].getY()+25);
		else if(xos[0] instanceof O && xos[3] instanceof O && xos[6] instanceof O)	// first column 
			g2d.drawLine(COORDINATES[0].getX()+25, COORDINATES[0].getY(), COORDINATES[6].getX()+25, COORDINATES[6].getY()+50);
		else if(xos[1] instanceof O && xos[4] instanceof O && xos[7] instanceof O)	// second column
			g2d.drawLine(COORDINATES[1].getX()+25, COORDINATES[1].getY(), COORDINATES[7].getX()+25, COORDINATES[7].getY()+50);
		else if(xos[2] instanceof O && xos[5] instanceof O && xos[8] instanceof O)	// third column
			g2d.drawLine(COORDINATES[2].getX()+25, COORDINATES[2].getY(), COORDINATES[8].getX()+25, COORDINATES[8].getY()+50);
		else if(xos[0] instanceof O && xos[4] instanceof O && xos[8] instanceof O)	// right to left diagonal
			g2d.drawLine(COORDINATES[0].getX(), COORDINATES[0].getY(), COORDINATES[8].getX()+50, COORDINATES[8].getY()+50);
		else if(xos[2] instanceof O && xos[4] instanceof O && xos[6] instanceof O)	// left to right diagonal
			g2d.drawLine(COORDINATES[2].getX()+50, COORDINATES[2].getY(), COORDINATES[6].getX(), COORDINATES[6].getY()+50);
		else
			return false;	// no win
		return true;	// win
	}
	public static void main(String[] args){
		new GameFrame();
	}

}
