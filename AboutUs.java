package OnlineBaking;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class AboutUs extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUs frame = new AboutUs();
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
	public AboutUs() {
		setTitle("About Us");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 180, 850, 430);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblimag = new JLabel("");
		Image img6 = new ImageIcon (this.getClass().getResource("/bakk.jpg")).getImage();
		lblimag.setIcon(new ImageIcon(img6));
		lblimag.setBounds(0, 0, 265, 393);
		contentPane.add(lblimag);
		
		JLabel lblTITLE = new JLabel("ONLINE BAKING SYSTEM");
		lblTITLE.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lblTITLE.setBounds(275, 10, 551, 64);
		contentPane.add(lblTITLE);
		
		JButton btnback = new JButton("BACK");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home ho = new Home();
				ho.setVisible(true);
				dispose();
			}
		});
		btnback.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnback.setBounds(510, 337, 92, 22);
		contentPane.add(btnback);
		
		JLabel lblNewLabel = new JLabel("Mail Id");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(514, 102, 71, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblWebsite = new JLabel("Website");
		lblWebsite.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWebsite.setBounds(510, 180, 92, 27);
		contentPane.add(lblWebsite);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhone.setBounds(510, 250, 92, 27);
		contentPane.add(lblPhone);
		
		JLabel lblNewLabel_1 = new JLabel("oninebaking111@yahoo.com");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(463, 137, 200, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("www.oninebaking112.com");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(480, 205, 200, 27);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("+44 1002 420103");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(480, 274, 140, 27);
		contentPane.add(lblNewLabel_1_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(463, 167, 188, 10);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(463, 230, 188, 10);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(463, 305, 188, 10);
		contentPane.add(separator_2);
	}
}
