package tis4.cs211.assignment1.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tis4.cs211.assignment1.Dictionaries;
import tis4.cs211.assignment1.DictionaryLoader;
import tis4.cs211.assignment1.Feedback;
import tis4.cs211.assignment1.Graph;

public class GUIMain extends JFrame implements ActionListener, Feedback{
	private static final long serialVersionUID = 1L;
	private JTextField startWord, endWord;
	private JTextField generationStart, generationLength;
	private JButton calculate = null, generation = null;
	private JTextArea resultsArea;
	private DictionaryLoader dictLoader;
	public static void main(String[] args){
		new GUIMain();
	}
	public GUIMain(){
		dictLoader = new DictionaryLoader(this);
		
		this.setSize(400,300);
		this.setTitle("Ladder Generator 5000");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//menu
		JMenuBar menuBar = new JMenuBar();
		this.add(menuBar,BorderLayout.NORTH);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		//main panel
		JPanel mainPanel = new JPanel();
		this.add(mainPanel,BorderLayout.CENTER);
		
		//shortest path panel///////////////////////////////////////////
		GridBagConstraints c = new GridBagConstraints();
		mainPanel.setLayout(new GridBagLayout());
		c.gridx = 0; c.gridy = 0;
		c.insets = new Insets(2,2,2,2);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1; c.weighty = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		//////////////////////////
		//items//
		
		c.gridx = 0; c.gridy = 0;
		JLabel sW = new JLabel("Start Word");
		mainPanel.add(sW,c);
		c.gridy = 1;
		startWord = new JTextField(10);
		mainPanel.add(startWord,c);
		
		c.gridx = 1; c.gridy = 0;
		JLabel eW = new JLabel("End Word");
		mainPanel.add(eW,c);
		c.gridy = 1;
		endWord = new JTextField(10);
		mainPanel.add(endWord,c);
		
		c.gridx = 2; c.gridy = 1;
		calculate = new JButton("Calculate Shortest");
		calculate.addActionListener(this);
		mainPanel.add(calculate,c);
		//////////////////////////
		
		//generation panel/////////////////////////////////////////////
		//items
		c.gridx = 0; c.gridy = 2;
		JLabel gSW = new JLabel("Generation Word");
		mainPanel.add(gSW,c);
		c.gridy = 3;
		generationStart = new JTextField(10);
		mainPanel.add(generationStart,c);
		
		c.gridx = 1; c.gridy = 2;
		JLabel gC = new JLabel("Generation Length");
		mainPanel.add(gC,c);
		c.gridy = 3;
		generationLength = new JTextField(10);
		mainPanel.add(generationLength,c);
		
		c.gridx = 2; c.gridy = 3;
		generation = new JButton("Generate");
		generation.addActionListener(this);
		mainPanel.add(generation,c);
		////////////////////////////////////////////////////////////
		
		////////////results panel///////////////////////////////////
		c.gridx = 0; c.gridy = 4;
		c.insets = new Insets(2,2,2,2);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1; c.weighty = 1;
		c.anchor = GridBagConstraints.NORTHWEST;
		//items//
		c.gridwidth = 3;
		resultsArea = new JTextArea();
		resultsArea.setRows(5);
		resultsArea.setAutoscrolls(true);
		JScrollPane resScroll = new JScrollPane(resultsArea);
		resScroll.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(resScroll,c);
		
		///////////////////
		this.pack();
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==calculate){
			String start,end;
			start = startWord.getText();
			end = endWord.getText();
			if(start==null){
				error("Start word null");
			}else if(end==null){
				error("End word null");
			}else if(end.length()!=start.length()){
				error("Words not equal in length");
			}else{
				calculateShortest(start,end);
			}
		}else if(e.getSource()==generation){
			String start;
			int length;
			if(generationStart==null){
				error("Start word null");
			}else if(generationLength==null){
				error("End word null");
			}else{
				try{
					length = Integer.parseInt(generationLength.getText());
					start = generationStart.getText();
					generation(start,length);
				}catch(NumberFormatException nfe){
					error("Length not a number");
				}
			}
		}
	}
	private void calculateShortest(String start, String end){
		Dictionaries dict = Dictionaries.valueOf("N"+start.length()); 
		if(dict==null){
			error("Dictionary for this length undefined");
		}else{
			status("",false);
			calculate.setEnabled(false);
			generation.setEnabled(false);
			Graph g = new Graph(dictLoader,dict,this,start,end);
			new Thread(g).start();
		}
	}
	private void generation(String start, int length){
		Dictionaries dict = Dictionaries.valueOf("N"+start.length()); 
		if(dict==null){
			error("Dictionary for this length undefined");
		}else{
			status("",false);
			calculate.setEnabled(false);
			generation.setEnabled(false);
			Graph g = new Graph(dictLoader,dict,this,start,length);
			new Thread(g).start();
		}
	}
	public void done(){
		calculate.setEnabled(true);
		generation.setEnabled(true);
	}
	public void error(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	public void status(String status, boolean append){
		if(!append){
			resultsArea.setText(" ");
		}
		resultsArea.append(status);
	}
	
}
