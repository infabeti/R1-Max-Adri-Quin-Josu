package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaLogin extends JFrame{
	private JPanel contentPane;
	private JTextField textField;
	
	//Lanza la conexi�n de un administrador
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crea la ventana
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setBounds(44, 47, 149, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Introduce tu nick:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(44, 22, 128, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(44, 77, 250, 19);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { //Acci�n del bot�n Aceptar
				lblNewLabel_1.setVisible(false);
				if(!textField.getText().equals("") && ( 
					textField.getText().equals("AdrianQ") || 
					textField.getText().equals("AdrianM") || 
					textField.getText().equals("Josu") || 
					textField.getText().equals("Maxi"))) {
					
					Socket s = null;
					VentanaAdministrador ventAdmin;
					try {
						ventAdmin = new VentanaAdministrador(s,textField.getText());
						ventAdmin.setBounds(0, 0, 650, 400);
						ventAdmin.setVisible(true);
						setVisible(false);
					} catch (IOException e1) {
						lblNewLabel_1.setVisible(true);
						lblNewLabel_1.setText("Error al conectarse");
					}				
				}
				else {
					lblNewLabel_1.setVisible(true);
					lblNewLabel_1.setText("Debe introducir el nick de un Administrador");
				}
			}
		});
		btnNewButton.setBounds(108, 95, 85, 21);
		contentPane.add(btnNewButton);				
	}
}
