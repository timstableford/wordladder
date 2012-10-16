package tis4.cs211.assignment1.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIMain extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField startWord, endWord;
	public static void main(String[] args){
		new GUIMain();
	}
	public GUIMain(){
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//menu
		JMenuBar menuBar = new JMenuBar();
		this.add(menuBar,BorderLayout.NORTH);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		//panel
		JPanel mainPanel = new JPanel();
		this.add(mainPanel,BorderLayout.CENTER);
		GridBagConstraints c = new GridBagConstraints();
		mainPanel.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(2,2,2,2);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.NORTHWEST;
		//////////////////////////
		//items///////////////////
		
		c.gridx = 0; c.gridy = 0;
		startWord = new JTextField(10);
		mainPanel.add(startWord,c);
		
		c.gridx = 1; c.gridy = 0;
		endWord = new JTextField(10);
		mainPanel.add(endWord,c);
		
		c.gridx = 2; c.gridy = 0;
		JButton calculate = new JButton("Calculate Shortest");
		calculate.addActionListener(this);
		mainPanel.add(calculate,c);
		//////////////////////////
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
