package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import modelo.Administrador;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class VentanaAdministrador extends JFrame implements ActionListener {
	Socket socket = null;
	String filtro ="";
	String nick;

	JButton botonSalir = new JButton("Salir");
	JButton btnTodosLosMunicipios = new JButton("Listar Todos Los Municipios");
	JButton btnfiltro = new JButton("Ir");
	JButton btnTiempo = new JButton("Datos meteorologicos");
	JButton btninfo = new JButton("Info del municipio");
	JRadioButton radiobutonaraba = new JRadioButton("Araba");
	JRadioButton radiobutonbizkaia = new JRadioButton("Bizkaia");
	JRadioButton radiobutonguipuzkoa = new JRadioButton("Guipuzkoa");
	Administrador admin  = null;
	JScrollPane scrollPane;
	static JList list;
	

	// constructor
	public VentanaAdministrador(Socket s, String nombre) throws IOException {
		super(" CONEXIÓN DEL ADMINISTRADOR: " + nombre);
		getContentPane().setLayout(null);
		
		botonSalir.setBounds(420, 320, 200, 30);
		getContentPane().add(botonSalir);
		botonSalir.addActionListener(this);
		btnTodosLosMunicipios.addActionListener(this);
		btnfiltro.addActionListener(this);
		btninfo.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		socket = s;
		this.nick = nombre;
		
		admin = new Administrador();
		socket = new Socket(admin.getHost(),admin.getPuerto());
		
		btnTodosLosMunicipios.setBounds(420, 11, 200, 30);
		getContentPane().add(btnTodosLosMunicipios);
		
		radiobutonbizkaia.setBounds(430, 48, 89, 23);
		getContentPane().add(radiobutonbizkaia);
		
		radiobutonguipuzkoa.setBounds(430, 74, 89, 23);
		getContentPane().add(radiobutonguipuzkoa);
		
		radiobutonaraba.setBounds(430, 100, 89, 23);
		getContentPane().add(radiobutonaraba);
		
		btnfiltro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnfiltro.setBounds(525, 52, 89, 75);
		getContentPane().add(btnfiltro);
		
		//ni se os ocurra tocar el jlist o el jscrollpane, que son muy tocapelotas y dejaran de funcionar
		list = new JList();
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 11, 400, 328);
		getContentPane().add(scrollPane);
		
		
		list.setBounds(10, 11, 400, 328);
		getContentPane().add(scrollPane);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btninfo.setBounds(432, 138, 139, 23);
		getContentPane().add(btninfo);
		
		
		btnTiempo.setBounds(430, 172, 141, 23);
		getContentPane().add(btnTiempo);
		
		admin = new Administrador(socket);
		admin.enviarMensaje("> " + nick + " se ha conectado\n");
			
	}// fin constructor
	
	public static void editarjlist(Object object) {
		List hql =  (List) object;
		DefaultListModel modelo = new DefaultListModel();
		for(int x = 0; x <=hql.size()-1;x++) {
			modelo.addElement(hql.get(x));
		}
			list.setModel(modelo);
	}
	// accion cuando pulsamos botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTiempo) { //sacar el tiempo atmosferico
			int tiempo=0;
			try {
				String buscar = (String) list.getSelectedValue().toString();				
//				for(int x = 0; x <=buscar.length()-1;x++) {
//					if(buscar.indexOf("1")>-1) {
//						tiempo = buscar.indexOf("1");
//					}else if(buscar.indexOf("2")>-1) {
//						tiempo = buscar.indexOf("2");
//					}else if(buscar.indexOf("4")>-1){
//						tiempo = buscar.indexOf("4");
//					}else {
//						JOptionPane.showInputDialog("NO!!");
//					}
//					x = buscar.length()-1;
//				}
//				buscar = buscar.substring(0, tiempo);
				System.out.println(buscar);
			filtro = "select c from CalidadAire c where c.nomEstMet = "
					+ "(select nombre from EstacionesMeteorologicas where nombre = '" + buscar.trim() + "')";
			admin.enviarMensaje("CONSULTA--> " + filtro);
			}catch(Exception e1) {
				JOptionPane.showInputDialog("Por favor, seleccione un minicipio");
		}
		}
		if (e.getSource() == btninfo) { //accion del boton de la info
			int espacio=0;
			try {
				String buscar = (String) list.getSelectedValue().toString();				
				for(int x = 0; x <=buscar.length()-1;x++) {
					if(buscar.indexOf("1")>-1) {
						espacio = buscar.indexOf("1");
					}else if(buscar.indexOf("2")>-1) {
						espacio = buscar.indexOf("2");
					}else if(buscar.indexOf("4")>-1){
						espacio = buscar.indexOf("4");
					}else {
						JOptionPane.showInputDialog("NO!!");
					}
					x = buscar.length()-1;
				}
				buscar = buscar.substring(0, espacio);
			filtro = "select b from EstacionesMetereologicas b where b.municipios = "
					+ "(select nombre from Municipios where nombre = '" + buscar.trim() + "')";
			admin.enviarMensaje("CONSULTA--> " + filtro);
			}catch(Exception e1) {
				JOptionPane.showInputDialog("Por favor, seleccione un minicipio");
			}
		}
		
		if(e.getSource()== btnTodosLosMunicipios) { //sacar todos los municipios
			String texto = "CONSULTA--> " + "select a from Municipios a";
			admin.enviarMensaje(texto);
		}
		
		if (e.getSource() == botonSalir) { //ACCIÓN AL PULSAR SALIR
			String texto = nick + " > Abandona el Chat ... \n" ;
			admin.enviarMensaje(texto);
			admin.enviarMensaje("ADIOS");
			System.exit(0);
		}
		
		if (e.getSource() == btnfiltro) {
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
		}
		
	}
}
