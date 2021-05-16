package OnlineBaking;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDFS;

import Clothes.Customers;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;

@SuppressWarnings("serial")
public class Bak extends JFrame {
	
	public static final String SOURCE = "./src/main/java/OnlineBaking/";
    
    public static final String BAK = "http://www.semanticweb.org/aishw/ontologies/2021/4/untitled-ontology-11#";
    
    private JScrollPane sp=new JScrollPane();

	private JPanel contentPane;
	private JTextField textField;
	private JPanel panelcook;
	private JPanel panelpuff;
	private JPanel panelbun;
	private JPanel panelbread;
	private JLayeredPane layeredPane;

	/**
	 * Launch the application.
	 */
	public static Bak frame = new Bak();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void switchPanels(JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	/**
	 * Create the frame.
	 */
	public Bak() {
		setTitle("Baking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 900, 580);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblimag = new JLabel("");
		Image img6 = new ImageIcon (this.getClass().getResource("/baki.jpg")).getImage();
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu me = new Menu();
				me.setVisible(true);
				dispose();
			}
		});
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(257, 74, 619, 102);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		panelcook = new JPanel();
		panelcook.setBackground(Color.WHITE);
		layeredPane.add(panelcook, "name_1548138003580500");
		panelcook.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		textField.setBounds(131, 39, 256, 38);
		panelcook.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("SEARCH");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String add = textField.getText().toString().toLowerCase();
				
				OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
				
				FileManager.get().readModel( m, SOURCE + "obs.owl" );
				
				String prefix = "prefix ob: <" + BAK + ">\n" +     
						"prefix rdfs: <" + RDFS.getURI() + ">\n" +
						"prefix owl: <" + OWL.getURI() + ">\n";
				
				String query_text=  prefix +
						"select ?name ?topping ?quantity where { ?0  a ob:cookies. ?o ob:name ?name." + 
					"?o ob:topping  ?topping. ?o ob:quantity ?quantity \n\r"; 
				
				if(add != null && !add.isEmpty()) {
					query_text += "FILTER(regex(str(?topping),\""+add+"\",\"i\")) ";
				}
				query_text +="}" ; 
				System.out.println(query_text);
				
				Query query = QueryFactory.create( query_text );
		        QueryExecution qexec = QueryExecutionFactory.create( query, m );
		        
		        List<String> columns = new ArrayList<String>();
		        List<String[]> values = new ArrayList<String[]>();

		        columns.add("Name");
		        columns.add("Topping");
		        columns.add("Quantity");
		        
		          try {
					ResultSet results = qexec.execSelect();
				    int i = 0;
				    while ( results.hasNext() ) {
				    	QuerySolution qs = results.next();
				               //arr.add(qs.get("name").toString());
				        values.add(new String[] {qs.get("?name").toString(), qs.get("?topping").toString(), qs.get("?quantity").toString()});
				    //    System.out.println(qs.get("?address"));
				        i++;
			          }
				   
				    TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
				    JTable table = new JTable(tableModel);
				    table.setForeground(Color.DARK_GRAY);
				    table.setBackground(Color.WHITE);
				    table.setRowHeight(35);		
				    sp.setViewportView(table);		           
				    sp.setBounds(280, 180, 570, 317);
				    contentPane.add(sp);
				    contentPane.repaint();
		        }
		        finally {
		            qexec.close();
		        }
			}
		});
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnNewButton_1.setBounds(388, 39, 102, 38);
		panelcook.add(btnNewButton_1);
		
		panelpuff = new JPanel();
		panelpuff.setBackground(Color.WHITE);
		layeredPane.add(panelpuff, "name_1548160763104800");
		panelpuff.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("LIST");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
				
				FileManager.get().readModel( m, SOURCE + "obs.owl" );
				
				String prefix = "prefix ob: <" + BAK + ">\n" +     
						"prefix rdfs: <" + RDFS.getURI() + ">\n" +
						"prefix owl: <" + OWL.getURI() + ">\n";
				
				String query_text=  prefix +
						"select ?name ?price ?quantity where { ?o  a ob:Puffs. ?o ob:name ?name." + 
					"?o ob:price  ?price. ?o ob:quantity ?quantity \n\r"; 
				
				query_text +="}" ; 
				System.out.println(query_text);
				
				Query query = QueryFactory.create( query_text );
		        QueryExecution qexec = QueryExecutionFactory.create( query, m );
		        
		        List<String> columns = new ArrayList<String>();
		        List<String[]> values = new ArrayList<String[]>();

		        columns.add("Name");
		        columns.add("Price");
		        columns.add("Quantity");
		        
		          try {
					ResultSet results = qexec.execSelect();
				    int i = 0;
				    while ( results.hasNext() ) {
				    	QuerySolution qs = results.next();
				               //arr.add(qs.get("name").toString());
				        values.add(new String[] {qs.get("?name").toString(), qs.get("?price").toString(), qs.get("?quantity").toString()});
				    //    System.out.println(qs.get("?address"));
				        i++;
			          }
				   
				    TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
				    JTable table = new JTable(tableModel);
				    table.setForeground(Color.DARK_GRAY);
				    table.setBackground(Color.WHITE);
				    table.setRowHeight(35);		
				    sp.setViewportView(table);		           
				    sp.setBounds(280, 180, 570, 317);
				    contentPane.add(sp);
				    contentPane.repaint();
		        }
		        finally {
		            qexec.close();
		        }
			}
		});
		btnNewButton_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnNewButton_2.setBounds(246, 30, 85, 31);
		panelpuff.add(btnNewButton_2);
		
		panelbun = new JPanel();
		panelbun.setBackground(Color.WHITE);
		layeredPane.add(panelbun, "name_1548162848025300");
		panelbun.setLayout(null);
		
		JButton btnNewButton_2_1 = new JButton("LIST");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
				
				FileManager.get().readModel( m, SOURCE + "obs.owl" );
				
				String prefix = "prefix ob: <" + BAK + ">\n" +     
						"prefix rdfs: <" + RDFS.getURI() + ">\n" +
						"prefix owl: <" + OWL.getURI() + ">\n";
				
				String query_text=  prefix +
						"select ?name ?price ?size where { ?o  a ob:Bun. ?o ob:name ?name. " + 
					"?o ob:price  ?price. ?o ob:size ?size. } \n\r"; 
				
				System.out.println(query_text);
				
				Query query = QueryFactory.create( query_text );
		        QueryExecution qexec = QueryExecutionFactory.create( query, m );
		        
		        List<String> columns = new ArrayList<String>();
		        List<String[]> values = new ArrayList<String[]>();

		        columns.add("Name");
		        columns.add("Price");
		        columns.add("Size");
		        
		          try {
					ResultSet results = qexec.execSelect();
				    int i = 0;
				    while ( results.hasNext() ) {
				    	QuerySolution qs = results.next();
				               //arr.add(qs.get("name").toString());
				        values.add(new String[] {qs.get("?name").toString(), qs.get("?price").toString(), qs.get("?size").toString()});
				        i++;
			          }
				   
				    TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
				    JTable table = new JTable(tableModel);
				    table.setForeground(Color.DARK_GRAY);
				    table.setBackground(Color.WHITE);
				    table.setRowHeight(35);		
				    sp.setViewportView(table);		           
				    sp.setBounds(280, 180, 570, 317);
				    contentPane.add(sp);
				    contentPane.repaint();
		        }
		        finally {
		            qexec.close();
		        }
			}
		});
		btnNewButton_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnNewButton_2_1.setBounds(246, 30, 85, 31);
		panelbun.add(btnNewButton_2_1);
		
		panelbread = new JPanel();
		panelbread.setBackground(Color.WHITE);
		layeredPane.add(panelbread, "name_1548168334571800");
		panelbread.setLayout(null);
		
		JButton btnNewButton_2_1_1 = new JButton("LIST");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
				
				FileManager.get().readModel( m, SOURCE + "obs.owl" );
				
				String prefix = "prefix ob: <" + BAK + ">\n" +     
								"prefix rdfs: <" + RDFS.getURI() + ">\n" +
								"prefix owl: <" + OWL.getURI() + ">\n";
				
				String query_text=  prefix + "select ?name ?price ?size \n\r"
						+ "where {?o a ob:Bread. ?o ob:name ?name." + 
						"?o ob:price ?price. ?o ob:size ?size } \n\r"; 
				
				System.out.println(query_text);
				
				Query query = QueryFactory.create( query_text );
		        QueryExecution qexec = QueryExecutionFactory.create( query, m );
		        
		        List<String> columns = new ArrayList<String>();
		        List<String[]> values = new ArrayList<String[]>();

		        columns.add("Name");
		        columns.add("Price");
		        columns.add("Size");
		        
		          try {
					ResultSet results = qexec.execSelect();
				    int i = 0;
				    while ( results.hasNext() ) {
				    	QuerySolution qs = results.next();
				               //arr.add(qs.get("name").toString());
				        values.add(new String[] {qs.get("?name").toString(), qs.get("?price").toString(), qs.get("?size").toString()});
				    //    System.out.println(qs.get("?name"));
				        i++;
			          }
				   
				    TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
				    JTable table = new JTable(tableModel);
				    table.setForeground(Color.DARK_GRAY);
				    table.setBackground(Color.WHITE);
				    table.setRowHeight(35);		
				    sp.setViewportView(table);		           
				    sp.setBounds(280, 180, 570, 317);
				    contentPane.add(sp);
				    contentPane.repaint();
		        }
		        finally {
		            qexec.close();
		        }
			}
		});
		btnNewButton_2_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnNewButton_2_1_1.setBounds(246, 30, 85, 31);
		panelbread.add(btnNewButton_2_1_1);
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnNewButton.setBounds(0, 0, 103, 27);
		contentPane.add(btnNewButton);
		lblimag.setIcon(new ImageIcon(img6));
		lblimag.setBounds(0, 0, 247, 550);
		contentPane.add(lblimag);
		
		JButton btnpuff = new JButton("PUFF");
		btnpuff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelpuff);
			}
		});
		btnpuff.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		btnpuff.setBounds(420, 10, 145, 34);
		contentPane.add(btnpuff);
		
		JButton btnbun = new JButton("BUN");
		btnbun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelbun);
			}
		});
		btnbun.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		btnbun.setBounds(575, 10, 145, 34);
		contentPane.add(btnbun);
		
		JButton btnbread = new JButton("BREAD");
		btnbread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelbread);
			}
		});
		btnbread.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		btnbread.setBounds(730, 10, 145, 34);
		contentPane.add(btnbread);
		
		JButton btncook = new JButton("COOKIES");
		btncook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelcook);
			}
		});
		btncook.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		btncook.setBounds(257, 10, 145, 34);
		contentPane.add(btncook);
		
	}
}
