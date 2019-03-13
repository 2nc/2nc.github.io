import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JApplet;


public class Maze extends JApplet{
	public static  int mazeSize =10 ;
	public static  int[][] mazeStand = {
		{2,2,2,2,2,2,2,2,2,2},
		{2,0,0,0,0,0,2,2,2,2},
		{2,0,2,0,2,0,0,0,2,2},
		{2,0,0,2,0,2,2,0,2,2},
		{2,2,0,2,0,2,2,0,2,2},
		{2,0,0,0,0,0,2,0,0,2},
		{2,2,2,2,2,2,2,2,0,2},
		{2,0,0,0,2,0,2,0,0,2},
		{2,2,2,2,0,0,0,0,0,2},
		{2,2,2,2,2,2,2,2,2,2}};
	public static int startI = 1;
	public static int startJ = 1;
	public static int endI =8;
	public static int endJ =8;
	public static int success = 0;
	public Cell[][] a = new Cell[mazeSize][mazeSize];
	public void init(){
		
		MyFrame mazeFrame = new MyFrame(mazeSize,mazeStand,a);
		
		
		mazeFrame.setBackground(Color.yellow);
	
		JButton beginMaze = new JButton("start");
		beginMaze.addActionListener(new ButtonListener());
		GridLayout maze1 = new GridLayout(mazeSize,mazeSize,2,2);
		JPanel maze = new JPanel(maze1);
	
		for(int i=0;i<mazeSize;i++){
			for(int j=0;j<mazeSize;j++){
				if(mazeStand[i][j]==2){
					a[i][j] = new Cell(2);
				}else{
					a[i][j] = new Cell(0);
				}
					
				maze.add(a[i][j]);
			}
		}
	
		mazeFrame.getContentPane().add(BorderLayout.SOUTH,beginMaze);
		mazeFrame.getContentPane().add(BorderLayout.CENTER,maze);
		add(BorderLayout.SOUTH,beginMaze);
		add(BorderLayout.CENTER,maze);
			
		
	}


	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Runnable threadJob = new MyRunnable();
			Thread myThread = new Thread(threadJob);
			myThread.start();
		}
	}

	public void go1(){
		visit(startI,startJ,a);
	}

	public void visit (int i,int j,Cell[][] a){
		mazeStand[i][j] = 1;
		a[i][j].visitMaze();
		if(i == endI && j == endJ){
			success = 1;
		}
		if(success!=1&&mazeStand[i-1][j]==0){
			visit(i-1,j,a);		//
		}
		if(success!=1&&mazeStand[i][j-1]==0){
			visit(i,j-1,a);			//
		}
		if(success!=1&&mazeStand[i+1][j]==0){
			visit(i+1,j,a);		//
		}
		if(success!=1&&mazeStand[i][j+1]==0){
			visit(i,j+1,a);		//
		}
		if(success!=1){
			mazeStand[i][j]=0;
			a[i][j].dVisitMaze();
		}
		//return success;
	}
	public static void sleep(){
		try{
			Thread.sleep(500);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}	
	public class MyRunnable implements Runnable{
		public void run(){
			go1();
		}
	}
}


class Cell extends JPanel{
	public Cell(int color){
		if(color ==2){
			setBackground(Color.red);
		}else{
			setBackground(Color.green);
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	public void visitMaze(){
		
		try{
			Thread.sleep(300);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		setBackground(Color.yellow);
		repaint();
	}
	public void dVisitMaze(){
		try{
			Thread.sleep(300);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		setBackground(Color.green);
		repaint();
	}
}