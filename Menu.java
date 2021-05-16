package OnlineBaking;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 450, 680);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblbg = new JLabel("");
		lblbg.setBounds(10, 10, 405, 662);
		Image img = new ImageIcon (this.getClass().getResource("/menuu.png")).getImage();
		contentPane.setLayout(null);
		Image img6 = new ImageIcon (this.getClass().getResource("/back.png")).getImage();
		
		JButton btnbl = new JButton("BACK");
		btnbl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h = new Home();
				h.setVisible(true);
				dispose();
			}
		});
		btnbl.setForeground(Color.BLACK);
		btnbl.setBackground(Color.WHITE);
		btnbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnbl.setHorizontalAlignment(SwingConstants.LEFT);
		btnbl.setIcon(new ImageIcon(img6));
		btnbl.setBounds(10, 10, 107, 21);
		contentPane.add(btnbl);
		
		JButton btnBEV = new JButton("BEVERAGES");
		btnBEV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Beverages bv = new Beverages ();
				bv.setVisible(true);
				dispose();
			}
		});
		btnBEV.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnBEV.setBounds(143, 425, 136, 37);
		contentPane.add(btnBEV);
		
		JButton btnDES = new JButton("DESSERTS");
		btnDES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desserts de = new Desserts();
				de.setVisible(true);
				dispose();
			}
		});
		btnDES.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnDES.setBounds(143, 476, 136, 37);
		contentPane.add(btnDES);
		
		JButton btnBAK = new JButton("BAKING");
		btnBAK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bak bk=new Bak();
				bk.setVisible(true);
				dispose();
			}
		});
		btnBAK.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnBAK.setBounds(143, 372, 136, 37);
		contentPane.add(btnBAK);
		lblbg.setIcon(new ImageIcon(img));
		contentPane.add(lblbg);
	}

}
