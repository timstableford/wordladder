package tis4.cs211.assignment1.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tis4.cs211.assignment1.Dictionaries;
import tis4.cs211.assignment1.DictionaryLoader;
import tis4.cs211.assignment1.Graph;
import tis4.cs211.assignment1.Word;

public class GUIMain extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField startWord, endWord;
	private JButton calculate = null;
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
		calculate = new JButton("Calculate Shortest");
		calculate.addActionListener(this);
		mainPanel.add(calculate,c);
		//////////////////////////
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
		}
	}
	private void calculateShortest(String start, String end){
		String s = start;
		start = end;
		end = s;
		Dictionaries dictName = Dictionaries.valueOf("N"+start.length()); 
		if(dictName==null){
			error("Dictionary for this length undefined");
		}else{
			status("Loading Dictionary...");
			DictionaryLoader dl = new DictionaryLoader(dictName);
			HashMap<String, Word> dict = dl.getDictionary();
			@SuppressWarnings("unchecked")
			Graph g = new Graph((HashMap<String,Word>)dict.clone());

			Word startW = dict.get(start);
			Word endW = dict.get(end);
			status("Checking words in dictionary...");
			if(startW==null){
				error("End word not in dictionary");
			}else if(endW==null){
				error("Start word not in dictionary");
			}else{
				startW.setRoot();

				HashMap<String, Word> hashMap;
				status("First iteration");
				hashMap = g.iterateWord(startW);
				int i=0;
				while(endW.getParent()==null&&hashMap.size()>0){
					status("Iteration "+i);
					hashMap = g.iterateMap(hashMap);
					i++;
				}
				status("Displaying tree");
				displayWordTree(endW);
			}		
		}

	}
	private void error(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	private void status(String status){
		System.out.println(status);
	}
	private void displayWordTree(Word end){
		Word tWord = end;
		System.out.print(end.getWord()+" ");
		if(end.getParent()==null&&end.getDistance()!=0){
			System.out.println("no link found");
			return;
		}
		int i=0;
		while(i<20&&tWord.getDistance()!=0){
			System.out.print(" - "+tWord.getParent().getWord());
			tWord = tWord.getParent();
			i++;
		}
		System.out.println();
	}
}
