package OnlineBaking;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 700, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblpic = new JLabel("");
		Image img = new ImageIcon (this.getClass().getResource("/bak.png")).getImage();
		lblpic.setIcon(new ImageIcon(img));
		lblpic.setBounds(-13, 33, 333, 517);
		contentPane.add(lblpic);
		
		JButton btnmenu = new JButton("   MENU");
		btnmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Menu me = new Menu();
				me.setVisible(true);
				dispose();
			}
		});
		btnmenu.setBackground(Color.LIGHT_GRAY);
		btnmenu.setHorizontalAlignment(SwingConstants.LEFT);
		Image img6 = new ImageIcon (this.getClass().getResource("/menu.png")).getImage();
		btnmenu.setIcon(new ImageIcon(img6));
		btnmenu.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		btnmenu.setBounds(374, 186, 258, 43);
		contentPane.add(btnmenu);
		
		JButton btnAboutUs = new JButton("  ABOUT US");
		btnAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutUs ab=new AboutUs();
				ab.setVisible(true);
				dispose();
			}
		});
		btnAboutUs.setHorizontalAlignment(SwingConstants.LEFT);
		btnAboutUs.setBackground(Color.LIGHT_GRAY);
		Image img5 = new ImageIcon (this.getClass().getResource("/store.png")).getImage();
		btnAboutUs.setIcon(new ImageIcon(img5));
		btnAboutUs.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		btnAboutUs.setBounds(374, 359, 258, 50);
		contentPane.add(btnAboutUs);
		
		JLabel lblTITLE = new JLabel("ONLINE BAKING SYSTEM");
		lblTITLE.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblTITLE.setBounds(166, 28, 484, 43);
		contentPane.add(lblTITLE);
		
		JButton btncus = new JButton("CUSTOMER LIST");
		btncus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cust cu=new Cust();
				cu.setVisible(true);
				dispose();
			}
		});
		btncus.setHorizontalAlignment(SwingConstants.LEFT);
		Image img4 = new ImageIcon (this.getClass().getResource("/peo.png")).getImage();
		btncus.setIcon(new ImageIcon(img4));
		btncus.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btncus.setBackground(Color.LIGHT_GRAY);
		btncus.setBounds(374, 271, 259, 50);
		contentPane.add(btncus);
		
		JButton btnback = new JButton("BACK");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lo=new Login();
				lo.setVisible(true);
				dispose();
			}
		});
		btnback.setHorizontalAlignment(SwingConstants.LEFT);
		btnback.setBackground(Color.WHITE);
		btnback.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnback.setBounds(0, 2, 76, 21);
		contentPane.add(btnback);
	}
}
