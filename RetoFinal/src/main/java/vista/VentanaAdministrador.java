package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Administrador;

@SuppressWarnings("serial")
public class VentanaAdministrador extends JFrame implements ActionListener {
	Socket socket = null;

	String nick;
	static JTextField consulta = new JTextField();

	private JScrollPane scrollpane1;
	static JTextArea textarea1;
	JButton botonEnviar = new JButton("Consultar en BBDD");
	JButton botonSalir = new JButton("Salir");
	Administrador admin  = null;

	// constructor
	public VentanaAdministrador(Socket s, String nombre) throws IOException {
		super(" CONEXIÓN DEL ADMINISTRADOR: " + nombre);
		getContentPane().setLayout(null);

		consulta.setText("SELECT * FROM usuarios");
		consulta.setBounds(10, 10, 400, 30);
		getContentPane().add(consulta);

		textarea1 = new JTextArea();
		scrollpane1 = new JScrollPane(textarea1);
		scrollpane1.setBounds(10, 50, 400, 300);
		getContentPane().add(scrollpane1);

		botonEnviar.setBounds(420, 10, 200, 30);
		getContentPane().add(botonEnviar);
		botonSalir.setBounds(420, 50, 200, 30);
		getContentPane().add(botonSalir);

		textarea1.setEditable(false);
		botonEnviar.addActionListener(this);
		botonSalir.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		socket = s;
		this.nick = nombre;
		
		admin = new Administrador();
		
		socket = new Socket(admin.getHost(),admin.getPuerto());
		
		admin = new Administrador(socket,textarea1, consulta,botonEnviar);
		admin.enviarMensaje("> " + nick + " se ha conectado\n");
			
	}// fin constructor

	// accion cuando pulsamos botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonEnviar) { // ACCIÓN AL PULSAR ENVIAR

			if (consulta.getText().trim().length() == 0)
				return;
			String texto = "CONSULTA--> " + consulta.getText();

			admin.enviarMensaje(texto);
			consulta.setText(""); //Limpia el campo de texto para la siguiente consulta
				
			
		}
		if (e.getSource() == botonSalir) { //ACCIÓN AL PULSAR SALIR
			String texto = nick + " > Abandona el Chat ... \n" ;
			admin.enviarMensaje(texto);
			admin.enviarMensaje("ADIOS");
			System.exit(0);
		}
		
	}
}
