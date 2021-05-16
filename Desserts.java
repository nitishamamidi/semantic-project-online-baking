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

import java.awt.Color;
import javax.swing.JLabel;
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
public class Desserts extends JFrame {

	public static final String SOURCE = "./src/main/java/OnlineBaking/";
    
    public static final String DES = "http://www.semanticweb.org/aishw/ontologies/2021/4/untitled-ontology-11#";
    
    private JScrollPane sp=new JScrollPane();
	private JPanel contentPane;
	private JButton btnice;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panelice;
	private JPanel panelcake;
	private JLayeredPane layeredPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Desserts frame = new Desserts();
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
	public Desserts() {
		setTitle("Desserts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 1000, 580);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblimag = new JLabel("");
		Image img6 = new ImageIcon (this.getClass().getResource("/des.jpg")).getImage();
		
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
		lblimag.setBounds(0, 0, 328, 543);
		contentPane.add(lblimag);
		
		JButton btncak = new JButton("CAKE");
		btncak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelcake);
			}
		});
		btncak.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btncak.setBounds(403, 26, 144, 30);
		contentPane.add(btncak);
		
		btnice = new JButton("ICE CREAM");
		btnice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelice);
			}
		});
		btnice.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnice.setBounds(572, 26, 144, 30);
		contentPane.add(btnice);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(366, 89, 462, 56);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		panelcake = new JPanel();
		panelcake.setBackground(Color.WHITE);
		layeredPane.add(panelcake, "name_1556986489508600");
		panelcake.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(64, 10, 219, 36);
		panelcake.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("SEARCH");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String add = textField.getText().toString().toLowerCase();
				
				OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
				
				FileManager.get().readModel( m, SOURCE + "obs.owl" );
				
				String prefix = "prefix ob: <" + DES + ">\n" +     
						"prefix rdfs: <" + RDFS.getURI() + ">\n" +
						"prefix owl: <" + OWL.getURI() + ">\n";
				
				String query_text=  prefix +
						"select ?Sponge_flavour (count(*)as ?count) where { ?x  a ob:cake. ?x ob:sponge_flavour ?Sponge_flavour. \n\r" ; 
					
				query_text +="}" ; 
				query_text +="GROUP BY (?Sponge_flavour) \n\r" +
							"ORDER BY ASC(?name)";
				System.out.println(query_text);
				
				Query query = QueryFactory.create( query_text );
		        QueryExecution qexec = QueryExecutionFactory.create( query, m );
		        
		        List<String> columns = new ArrayList<String>();
		        List<String[]> values = new ArrayList<String[]>();

		        columns.add("Sponge Flavour");
		        columns.add("Count");
		        
		          try {
					ResultSet results = qexec.execSelect();
				    int i = 0;
				    while ( results.hasNext() ) {
				    	QuerySolution qs = results.next();
				               //arr.add(qs.get("name").toString());
				        values.add(new String[] {qs.get("?Sponge_flavour").toString(), qs.get("?count").toString()});
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
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnNewButton_1.setBounds(281, 10, 104, 36);
		panelcake.add(btnNewButton_1);
		
		panelice = new JPanel();
		panelice.setBackground(Color.WHITE);
		layeredPane.add(panelice, "name_1556989636079700");
		panelice.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(62, 10, 219, 36);
		panelice.add(textField_1);
		
		JButton btnNewButton_1_1 = new JButton("SEARCH");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String add = textField_1.getText().toString().toLowerCase();
				
				OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
				
				FileManager.get().readModel( m, SOURCE + "obs.owl" );
				
				String prefix = "prefix ob: <" + DES + ">\n" +     
						"prefix rdfs: <" + RDFS.getURI() + ">\n" +
						"prefix owl: <" + OWL.getURI() + ">\n";
				
				String query_text=  prefix +
						"select ?flavour ?name ?size ?topping ?syrup_name where { ?x  a ob:Icecream. "
						+ "?x ob:flavour ?flavour. ?x ob:name ?name. ?x ob:size ?size. ?x ob:topping ?topping. ?x ob:syrup_name ?syrup_name. \n\r" ; 
					
				if(add != null && !add.isEmpty()) {
					query_text += "FILTER(regex(str(?flavour),\""+add+"\",\"i\")) ";
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
		        columns.add("Syrup Name");
		        
		          try {
					ResultSet results = qexec.execSelect();
				    int i = 0;
				    while ( results.hasNext() ) {
				    	QuerySolution qs = results.next();
				               //arr.add(qs.get("name").toString());
				        values.add(new String[] {qs.get("?flavour").toString(), qs.get("?name").toString(), qs.get("?size").toString(), qs.get("?topping").toString(), qs.get("?syrup_name").toString()});
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
		btnNewButton_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnNewButton_1_1.setBounds(279, 10, 104, 36);
		panelice.add(btnNewButton_1_1);
	}

}
