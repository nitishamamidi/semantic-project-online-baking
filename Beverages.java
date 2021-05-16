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

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Beverages extends JFrame {
	
	public static final String SOURCE = "./src/main/java/OnlineBaking/";
    
    public static final String BEV = "http://www.semanticweb.org/aishw/ontologies/2021/4/untitled-ontology-11#";
    
    private JScrollPane sp=new JScrollPane();

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel panelmilk;
	private JPanel panelcof;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Beverages frame = new Beverages();
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
	public Beverages() {
		setTitle("Beverages");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 950, 580);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblimag = new JLabel("");
		Image img6 = new ImageIcon (this.getClass().getResource("/shak.jpg")).getImage();
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu me = new Menu ();
				me.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnNewButton.setBounds(0, 0, 85, 21);
		contentPane.add(btnNewButton);
		lblimag.setIcon(new ImageIcon(img6));
		lblimag.setBounds(-22, 0, 360, 543);
		contentPane.add(lblimag);
		
		JButton btnmilk = new JButton("MILKSHAKE");
		btnmilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelmilk);
			}
		});
		btnmilk.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		btnmilk.setBounds(449, 33, 141, 33);
		contentPane.add(btnmilk);
		
		JButton btncfe = new JButton("COFFEE");
		btncfe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelcof);
			}
		});
		btncfe.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		btncfe.setBounds(623, 33, 141, 33);
		contentPane.add(btncfe);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(348, 96, 528, 78);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		panelmilk = new JPanel();
		panelmilk.setBackground(Color.WHITE);
		layeredPane.add(panelmilk, "name_1550764220797200");
		panelmilk.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		textField.setBounds(88, 34, 263, 32);
		panelmilk.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("SEARCH");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String add = textField.getText().toString().toLowerCase();
				
				OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
				
				FileManager.get().readModel( m, SOURCE + "obs.owl" );
				
				String prefix = "prefix ob: <" + BEV + ">\n" +     
						"prefix rdfs: <" + RDFS.getURI() + ">\n" +
						"prefix owl: <" + OWL.getURI() + ">\n";
				
				String query_text=  prefix +
					"select ?flavour ?name ?size ?topping ?syrup_name \n\r"
					+ "where { ?x  a ob:milkshake. ?x ob:flavour ?flavour. " + 
					"?x ob:name  ?name. ?x ob:size ?size. ?x ob:topping ?topping. ?x ob:syrup_name ?syrup_name \n\r"; 
				
				if(add != null && !add.isEmpty()) {
					query_text += "FILTER(regex(str(?syrup_name),\""+add+"\",\"i\")) ";
				}
				query_text +="}" ; 
				System.out.println(query_text);
				
				Query query = QueryFactory.create( query_text );
		        QueryExecution qexec = QueryExecutionFactory.create( query, m );
		        
		        List<String> columns = new ArrayList<String>();
		        List<String[]> values = new ArrayList<String[]>();

		        columns.add("Flavour");
		        columns.add("Name");
		        columns.add("Size");
		        columns.add("Topping");
		        columns.add("Syrup_Name");
		        
		          try {
					ResultSet results = qexec.execSelect();
				    int i = 0;
				    while ( results.hasNext() ) {
				    	QuerySolution qs = results.next();
				               //arr.add(qs.get("name").toString());
				        values.add(new String[] {qs.get("?flavour").toString(), qs.get("?name").toString(),qs.get("?size").toString(), qs.get("?topping").toString(), qs.get("?syrup_name").toString()});
				    //    System.out.println(qs.get("?address"));
				        i++;
			          }
				   
				    TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
				    JTable table = new JTable(tableModel);
				    table.setForeground(Color.DARK_GRAY);
				    table.setBackground(Color.WHITE);
				    table.setRowHeight(35);		
				    sp.setViewportView(table);		           
				    sp.setBounds(350, 180, 570, 317);
				    contentPane.add(sp);
				    contentPane.repaint();
		        }
		        finally {
		            qexec.close();
		        }
			}
		});
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnNewButton_1.setBounds(351, 34, 98, 32);
		panelmilk.add(btnNewButton_1);
		
		panelcof = new JPanel();
		panelcof.setBackground(Color.WHITE);
		layeredPane.add(panelcof, "name_1550767909056000");
		panelcof.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("LIST");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
				
				FileManager.get().readModel( m, SOURCE + "obs.owl" );
				
				String prefix = "prefix ob: <" + BEV + ">\n" +     
						"prefix rdfs: <" + RDFS.getURI() + ">\n" +
						"prefix owl: <" + OWL.getURI() + ">\n";
				
				String query_text=  prefix +
						"select ?name ?topping ?size where { ?o  a ob:coffee. ?o ob:name ?name." + 
					"?o ob:topping  ?topping. ?o ob:size ?size \n\r"; 
				
				query_text +="}" ; 
				query_text +="ORDER BY DESC (?name)";
				System.out.println(query_text);
				
				Query query = QueryFactory.create( query_text );
		        QueryExecution qexec = QueryExecutionFactory.create( query, m );
		        
		        List<String> columns = new ArrayList<String>();
		        List<String[]> values = new ArrayList<String[]>();

		        columns.add("Name");
		        columns.add("Topping");
		        columns.add("Size");
		        
		          try {
					ResultSet results = qexec.execSelect();
				    int i = 0;
				    while ( results.hasNext() ) {
				    	QuerySolution qs = results.next();
				               //arr.add(qs.get("name").toString());
				        values.add(new String[] {qs.get("?name").toString(), qs.get("?topping").toString(), qs.get("?size").toString()});
				    //    System.out.println(qs.get("?address"));
				        i++;
			          }
				   
				    TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
				    JTable table = new JTable(tableModel);
				    table.setForeground(Color.DARK_GRAY);
				    table.setBackground(Color.WHITE);
				    table.setRowHeight(35);		
				    sp.setViewportView(table);		           
				    sp.setBounds(350, 180, 570, 317);
				    contentPane.add(sp);
				    contentPane.repaint();
		        }
		        finally {
		            qexec.close();
		        }
			}
		});
		btnNewButton_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnNewButton_2.setBounds(205, 21, 133, 31);
		panelcof.add(btnNewButton_2);
		
	}
}
