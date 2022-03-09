package s_grammar;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class RunAssignment {

	private JFrame frame;
	private JTextField txtNonterminal;
	private JTextField txtTerminal;
	private JTextField txtStart;
	private JTextField txtInputString;
	private JTextField txtProdRule;
	private JTextArea txtProdRules;
	public JTextArea txtOutput ;
	
	private JLabel lblErr1, lblErr2, lblErr3, lblErr4;
	
	Grammar grammar ;
	ArrayList<String> products;
	private JButton btnRemove;
	private JLabel lblInputFile;
	private JTextField txtFileInput;
	private JButton btnNewButton;
	private JButton btnInput;
	private JLabel label;
	private JButton btnParse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunAssignment window = new RunAssignment();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RunAssignment() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		products = new ArrayList<>();
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 128, 0));
		frame.setBounds(100, 100, 632, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 616, 38);
		frame.getContentPane().add(panel);
		
		JLabel lblContextFreeGrammar = new JLabel("S-Grammar");
		lblContextFreeGrammar.setForeground(new Color(255, 255, 255));
		lblContextFreeGrammar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblContextFreeGrammar);
		
		JLabel lblNewLabel = new JLabel("Non-terminal Symbols");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(50, 127, 188, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTerminalSymbols = new JLabel("Terminal Symbols");
		lblTerminalSymbols.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTerminalSymbols.setBounds(50, 178, 188, 20);
		frame.getContentPane().add(lblTerminalSymbols);
		
		JLabel lblStartSymbol = new JLabel("Start Symbol");
		lblStartSymbol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStartSymbol.setBounds(50, 229, 188, 20);
		frame.getContentPane().add(lblStartSymbol);
		
		JLabel lblProductRules = new JLabel("Product Rules");
		lblProductRules.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductRules.setBounds(50, 323, 188, 20);
		frame.getContentPane().add(lblProductRules);
		
		txtNonterminal = new JTextField();
		txtNonterminal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNonterminal.setToolTipText("(eg. SABC) Note: Do not include spaces and commas");
		txtNonterminal.setBounds(263, 127, 283, 20);
		frame.getContentPane().add(txtNonterminal);
		txtNonterminal.setColumns(10);
		
		txtTerminal = new JTextField();
		txtTerminal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTerminal.setToolTipText("(eg. ab)  Note: Do not include spaces and commas");
		txtTerminal.setColumns(10);
		txtTerminal.setBounds(263, 180, 283, 20);
		frame.getContentPane().add(txtTerminal);
		
		txtStart = new JTextField();
		txtStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtStart.setToolTipText("(eg. S) Note: Do not include spaces and commas");
		txtStart.setColumns(10);
		txtStart.setBounds(263, 231, 86, 20);
		frame.getContentPane().add(txtStart);
		
		
		
		txtProdRules = new JTextArea();
		txtProdRules.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtProdRules.setEditable(false);
		txtProdRules.setBounds(263, 323, 283, 146);
		frame.getContentPane().add(txtProdRules);
		
		JScrollPane scrollbar = new JScrollPane(txtProdRules);
		scrollbar.setBounds(263, 323, 283, 146);
		frame.getContentPane().add(scrollbar);
		
		txtOutput = new JTextArea();
		txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtOutput.setEditable(false);
		txtOutput.setBounds(50, 695, 496, 144);
		frame.getContentPane().add(txtOutput);
		
		JScrollPane scrollbar2 = new JScrollPane(txtOutput);
		scrollbar2.setBounds(50, 695, 496, 144);
		frame.getContentPane().add(scrollbar2);
		
		JButton btnTest = new JButton("TEST");
		btnTest.setToolTipText("Tests if the grammar entered is valid s-grammar");
		btnTest.setForeground(Color.WHITE);
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isInputValid()) {
					String[] arr = new String[products.size()];
					int count = 0;
					for (String s : products) {
						arr[count] = s;
						count++;
					}
					grammar = new Grammar(txtNonterminal.getText(),txtTerminal.getText(),txtStart.getText() ,arr );
					if (grammar.isSGrammar())
						JOptionPane.showMessageDialog(null, "The entered grammar is valid s-grammar", "Infomation", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "The entered grammar is not valid s-grammar", "Infomation", JOptionPane.INFORMATION_MESSAGE);
					lblErr1.setVisible(false);
					lblErr2.setVisible(false);
					lblErr3.setVisible(false);
					lblErr4.setVisible(false);
				}
			}
		});
		btnTest.setBackground(new Color(0, 128, 0));
		btnTest.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTest.setBounds(263, 534, 283, 31);
		frame.getContentPane().add(btnTest);
		
		JLabel lblInputString = new JLabel("Input String");
		lblInputString.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInputString.setBounds(50, 602, 188, 19);
		frame.getContentPane().add(lblInputString);
		
		txtInputString = new JTextField();
		txtInputString.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtInputString.setToolTipText("(eg. abcfde)");
		txtInputString.setColumns(10);
		txtInputString.setBounds(263, 603, 283, 20);
		frame.getContentPane().add(txtInputString);
		
		JLabel lblNewLabel_1 = new JLabel("_______________________________________________________________________");
		lblNewLabel_1.setBounds(50, 577, 496, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtProdRule = new JTextField();
		txtProdRule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProdRule.setToolTipText("(eg. S->aAB) Note: Do not include spaces and commas");
		txtProdRule.setColumns(10);
		txtProdRule.setBounds(264, 277, 159, 20);
		frame.getContentPane().add(txtProdRule);
		
		JLabel lblProductionRule = new JLabel("Production Rule");
		lblProductionRule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductionRule.setBounds(50, 275, 188, 20);
		frame.getContentPane().add(lblProductionRule);
		
		JButton btnAddRule = new JButton("Add Rule");
		btnAddRule.setToolTipText("Adds the production rule to the set of production rules for the grammar");
		btnAddRule.setForeground(Color.WHITE);
		btnAddRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtProdRule.getText() != null || !txtProdRule.getText().equals("")) {
					products.add(txtProdRule.getText());
					txtProdRules.append(txtProdRule.getText()+"\n");
				}
			}
		});
		btnAddRule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddRule.setBackground(new Color(105, 105, 105));
		btnAddRule.setBounds(432, 271, 114, 28);
		frame.getContentPane().add(btnAddRule);
		
		lblErr1 = new JLabel("Input Required");
		lblErr1.setForeground(new Color(255, 0, 0));
		lblErr1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblErr1.setBounds(263, 147, 86, 14);
		lblErr1.setVisible(false);
		frame.getContentPane().add(lblErr1);
		
		lblErr2 = new JLabel("Input Required");
		lblErr2.setForeground(Color.RED);
		lblErr2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblErr2.setBounds(263, 198, 86, 14);
		lblErr2.setVisible(false);
		frame.getContentPane().add(lblErr2);
		
		lblErr3 = new JLabel("Input Required");
		lblErr3.setForeground(Color.RED);
		lblErr3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblErr3.setBounds(263, 249, 86, 14);
		lblErr3.setVisible(false);
		frame.getContentPane().add(lblErr3);
		
		lblErr4 = new JLabel("Input Required");
		lblErr4.setForeground(Color.RED);
		lblErr4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblErr4.setBounds(263, 467, 86, 14);
		lblErr4.setVisible(false);
		frame.getContentPane().add(lblErr4);
		
		btnRemove = new JButton("CLEAR");
		btnRemove.setToolTipText("Clears the above fields");
		btnRemove.setForeground(Color.WHITE);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNonterminal.setText("");
				txtTerminal.setText("");
				txtProdRule.setText("");
				txtProdRules.setText("");
				txtStart.setText("");
				products.clear();
				txtOutput.setText("");
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemove.setBackground(new Color(204, 0, 0));
		btnRemove.setBounds(263, 492, 283, 31);
		frame.getContentPane().add(btnRemove);
		
		lblInputFile = new JLabel("Input File");
		lblInputFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInputFile.setBounds(50, 59, 188, 20);
		frame.getContentPane().add(lblInputFile);
		
		txtFileInput = new JTextField();
		txtFileInput.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtFileInput.setEditable(false);
		txtFileInput.setColumns(10);
		txtFileInput.setBounds(263, 61, 237, 20);
		frame.getContentPane().add(txtFileInput);
		
		btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				FileNameExtensionFilter restrict = new FileNameExtensionFilter(".txt files", "txt"); 
	            fileChooser.setAcceptAllFileFilterUsed(false); 
				fileChooser.addChoosableFileFilter(restrict); 
				int r = fileChooser.showOpenDialog(null);
				if (r == JFileChooser.APPROVE_OPTION)  { 
	                txtFileInput.setText(fileChooser.getSelectedFile().getAbsolutePath()); 
	            } 
			}
		});
		btnNewButton.setBounds(510, 60, 36, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnInput = new JButton("INPUT DATA");
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner sc = new Scanner(new File(txtFileInput.getText()));
					if (isFileValid(txtFileInput.getText())) {
						txtNonterminal.setText("");
						txtTerminal.setText("");
						txtProdRule.setText("");
						txtProdRules.setText("");
						txtStart.setText("");
						products.clear();
						
						txtNonterminal.setText(sc.nextLine());
						txtTerminal.setText(sc.nextLine());
						txtStart.setText(sc.nextLine());
						while (sc.hasNextLine()) {
							String s = sc.nextLine();
							txtProdRules.append(s+"\n");
							products.add(s);
						}
					}
					else 
						JOptionPane.showMessageDialog(null, "Invalid input format in the file \nAccepted format: \nLine 1: Set of non-terminal symbols (i.e SAB)"
								+ "\nLine 2: Set of terminal symbols (i.e. ab)  \nLine 3: Start symbol (i.e. S) \nLine 4: Product rules (i.e. S->aAB) ", "Input Invalid", JOptionPane.INFORMATION_MESSAGE);
					sc.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Select a file ", "No File Selected", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnInput.setForeground(Color.WHITE);
		btnInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInput.setBackground(new Color(105, 105, 105));
		btnInput.setBounds(432, 88, 114, 28);
		frame.getContentPane().add(btnInput);
		
		label = new JLabel("____________________________________________________");
		label.setBounds(50, 90, 374, 14);
		frame.getContentPane().add(label);
		
		btnParse = new JButton("PARSE");
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = null;
				if (grammar == null) {
					if (isInputValid()) {
						arr = new String[products.size()];
						int count = 0;
						for (String s : products) {
							arr[count] = s;
							count++;
						}
					}
					grammar = new Grammar(txtNonterminal.getText(),txtTerminal.getText(),txtStart.getText() ,arr );
				}
				if (grammar.isSGrammar()) {
					txtOutput.setText("");
					if (grammar.parseGrammar(txtInputString.getText())) {
						txtOutput.append("  S\t→\t" + arr[0].substring(3)+ "\n");
						while (!grammar.getOutput().isEmpty()) {
							txtOutput.append("\t→\t"+grammar.getOutput().poll() + "\n");
						}
						
//						try {
//							wait(300);
//						} catch (InterruptedException e1) {
//							e1.printStackTrace();
//						}
						JOptionPane.showMessageDialog(null, "The string belongs to the above s-grammar", "Infomation", JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "The string does not belong to the above s-grammar", "Infomation", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "The above grammar is not valid s-grammar", "Infomation", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnParse.setToolTipText("Checks if the string is of the above grammar");
		btnParse.setForeground(Color.WHITE);
		btnParse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnParse.setBackground(new Color(0, 128, 0));
		btnParse.setBounds(263, 643, 283, 31);
		frame.getContentPane().add(btnParse);
		
	}
	
	private boolean isFileValid(String file) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(file));
		int count = 0;
		String line;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			if (count == 0) {
				for (Character c : line.toCharArray()) {
					if ((int)c < 65 || (int)c > 90) {
						return false;
					}
				}
			}
			else if (count == 1) {
				for (Character c : line.toCharArray()) {
					if ((int)c >= 65 && (int)c <= 90) {
						return false;
					}
				}
			}
			else if (count == 2) {
				if (line.length() > 1) return false;
			}
			else {
				if (line.charAt(1) != '-' && line.charAt(2) != '>') return false;
			}
			count++;
		}
		return true;
	}
	
	public boolean isInputValid() {
		boolean isValid = true;
		if (txtNonterminal.getText().equals("")) {
			lblErr1.setVisible(true);
			isValid = false;
		}
		if (txtTerminal.getText().equals("")) {
			lblErr2.setVisible(true);
			isValid = false;
		}
		if (txtStart.getText().equals("")) {
			lblErr3.setVisible(true);
			isValid = false;
		}
		if (txtProdRules.getText().equals("")) {
			lblErr4.setVisible(true);
			isValid = false;
		}
		return isValid;
	}
}
