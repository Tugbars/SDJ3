import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1043, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 46, 845, 121);
		contentPane.add(scrollPane);
		
		JLabel lblParts = new JLabel("Registered Cars");
		lblParts.setBounds(79, 21, 199, 14);
		contentPane.add(lblParts);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(75, 178, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(185, 178, 89, 23);
		contentPane.add(btnRemove);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(79, 252, 845, 121);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(79, 436, 845, 121);
		contentPane.add(scrollPane_2);
		
		JLabel lblParts_1 = new JLabel("Parts");
		lblParts_1.setBounds(79, 229, 46, 14);
		contentPane.add(lblParts_1);
		
		JLabel lblPallets = new JLabel("Pallets");
		lblPallets.setBounds(92, 411, 81, 14);
		contentPane.add(lblPallets);
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(79, 384, 59, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(149, 384, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
