package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modelo.Administrador;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class VentanaAdministrador extends JFrame implements ActionListener {
	Socket socket = null;
	String filtro ="";
	String nick;

	private JScrollPane scrollpane1;
	static public JTextArea textarea1;
	JButton botonSalir = new JButton("Salir");
	JButton btnTodosLosMunicipios = new JButton("Listar Todos Los Municipios");
	JButton btnfiltro = new JButton("Ir");
	JRadioButton radiobutonaraba = new JRadioButton("Araba");
	JRadioButton radiobutonbizkaia = new JRadioButton("Bizkaia");
	JRadioButton radiobutonguipuzkoa = new JRadioButton("Guipuzkoa");
	Administrador admin  = null;

	// constructor
	public VentanaAdministrador(Socket s, String nombre) throws IOException {
		super(" CONEXIÃ“N DEL ADMINISTRADOR: " + nombre);
		getContentPane().setLayout(null);
		
		textarea1 = new JTextArea();
		scrollpane1 = new JScrollPane(textarea1);
		scrollpane1.setBounds(10, 11, 400, 339);
		getContentPane().add(scrollpane1);

		textarea1.setBounds(new Rectangle(10, 50, 400, 300));
		getContentPane().add(scrollpane1);
		botonSalir.setBounds(420, 320, 200, 30);
		getContentPane().add(botonSalir);
		botonSalir.addActionListener(this);
		btnTodosLosMunicipios.addActionListener(this);
		btnfiltro.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		socket = s;
		this.nick = nombre;
		
		admin = new Administrador();
		
		socket = new Socket(admin.getHost(),admin.getPuerto());
		
		btnTodosLosMunicipios.setBounds(420, 11, 200, 30);
		getContentPane().add(btnTodosLosMunicipios);
		
		textarea1.setEditable(false);
		
		radiobutonbizkaia.setBounds(430, 48, 89, 23);
		getContentPane().add(radiobutonbizkaia);
		
		radiobutonguipuzkoa.setBounds(430, 74, 89, 23);
		getContentPane().add(radiobutonguipuzkoa);
		
		radiobutonaraba.setBounds(430, 100, 89, 23);
		getContentPane().add(radiobutonaraba);
		
		btnfiltro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnfiltro.setBounds(525, 52, 89, 75);
		getContentPane().add(btnfiltro);
		
		admin = new Administrador(socket,textarea1,btnTodosLosMunicipios);
		admin.enviarMensaje("> " + nick + " se ha conectado\n");
			
	}// fin constructor

	// accion cuando pulsamos botones
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()== btnTodosLosMunicipios) { //sacar todos los municipios
			textarea1.setText("");
			String texto = "CONSULTA--> " + "select a from Municipios a";

			admin.enviarMensaje(texto);
		}
		
		if (e.getSource() == botonSalir) { //ACCIÃ“N AL PULSAR SALIR
			textarea1.setText("");
			String texto = nick + " > Abandona el Chat ... \n" ;
			admin.enviarMensaje(texto);
			admin.enviarMensaje("ADIOS");
			System.exit(0);
		}
		
		if (e.getSource() == btnfiltro) {
			textarea1.setText("");
			if(!radiobutonbizkaia.isSelected() && !radiobutonguipuzkoa.isSelected() && radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 1";
				admin.enviarMensaje("CONSULTA--> " + filtro);
				}
			if(radiobutonbizkaia.isSelected() && !radiobutonguipuzkoa.isSelected() && !radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 48";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			if(!radiobutonbizkaia.isSelected() && radiobutonguipuzkoa.isSelected() && !radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 20";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			if(radiobutonbizkaia.isSelected() && radiobutonguipuzkoa.isSelected() && !radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 20 or a.idProvincia = 48";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			if(!radiobutonbizkaia.isSelected() && radiobutonguipuzkoa.isSelected() && radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 1 or a.idProvincia = 20";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			if(radiobutonbizkaia.isSelected() && !radiobutonguipuzkoa.isSelected() && radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 1 or a.idProvincia = 48";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			if(radiobutonbizkaia.isSelected() && radiobutonguipuzkoa.isSelected() && radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			textarea1.setText(textarea1.getText().replace(",", ""));
		}
		
	}
}
