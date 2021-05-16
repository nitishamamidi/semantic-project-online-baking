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
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Cust extends JFrame {
	
	public static final String SOURCE = "./src/main/java/OnlineBaking/";
    
    public static final String CUS = "http://www.semanticweb.org/aishw/ontologies/2021/4/untitled-ontology-11#";
    
    private JScrollPane sp=new JScrollPane();

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cust frame = new Cust();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cust() {
		setTitle("Customers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 900, 580);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblimag = new JLabel("");
		Image img6 = new ImageIcon (this.getClass().getResource("/cust.png")).getImage();
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home ho = new Home();
				ho.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnNewButton.setBounds(0, 0, 85, 21);
		contentPane.add(btnNewButton);
		lblimag.setIcon(new ImageIcon(img6));
		lblimag.setBounds(-254, -12, 606, 545);
		contentPane.add(lblimag);
		
		JLabel lblNewLabel = new JLabel("CUSTOMER LIST");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblNewLabel.setBounds(444, 32, 287, 50);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(428, 132, 235, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("SEARCH");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String add = textField.getText().toString().toLowerCase();
				
				OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
				
				FileManager.get().readModel( m, SOURCE + "obs.owl" );
				
				String prefix = "prefix ob: <" + CUS + ">\n" +     
								"prefix rdfs: <" + RDFS.getURI() + ">\n" +
								"prefix owl: <" + OWL.getURI() + ">\n";
				
				String query_text=  prefix +
						"select ?name ?address where { ?f  a ob:customer. ?f ob:Name ?name." + 
					" ?f ob:Address ?address. \n\r"; 

				if(add != null && !add.isEmpty()) {
					query_text += "FILTER(regex(str(?address),\""+add+"\",\"i\")) ";
				}
				query_text +="}" ; 
				System.out.println(query_text);
				
				Query query = QueryFactory.create( query_text );
		        QueryExecution qexec = QueryExecutionFactory.create( query, m );
		        
		        List<String> columns = new ArrayList<String>();
		        List<String[]> values = new ArrayList<String[]>();

		        columns.add("Name");
		        columns.add("Address");
		        
		          try {
					ResultSet results = qexec.execSelect();
				    int i = 0;
				    while ( results.hasNext() ) {
				    	QuerySolution qs = results.next();
				               //arr.add(qs.get("name").toString());
				        values.add(new String[] {qs.get("?name").toString(), qs.get("?address").toString()});
				        i++;
				    }
				    				   
				    TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
				    JTable table = new JTable(tableModel);
				    table.setForeground(Color.DARK_GRAY);
				    table.setBackground(Color.WHITE);
				    table.setRowHeight(35);		
				    sp.setViewportView(table);		           
				    sp.setBounds(350, 200, 500, 317);
				    contentPane.add(sp);
				    contentPane.repaint();
		        }
		        finally {
		            qexec.close();
		        }
			}
		});
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnNewButton_1.setBounds(673, 132, 103, 32);
		contentPane.add(btnNewButton_1);
	}

}
