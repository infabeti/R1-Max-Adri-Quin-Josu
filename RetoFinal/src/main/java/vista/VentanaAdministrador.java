package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import modelo.Administrador;
import modelo.CalidadAire;
import modelo.Municipios;
import modelo.InsertarDatosAtmosfericos;

import javax.swing.JRadioButton;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class VentanaAdministrador extends JFrame implements ActionListener {
	Socket socket = null;
	String filtro = "";
	String nick;
	String consultaRanking = "SELECT DISTINCT EM FROM EstacionesMeteorologicas EM JOIN CalidadAire CA ON EM.nombre = CA.nomEstMet ORDER BY CA.Nogm3 DESC";

	JButton botonSalir = new JButton("Salir");
	JButton btnTodosLosMunicipios = new JButton("Listar Todos Los Municipios");
	JButton btnfiltro = new JButton("Ir");
	JButton btnTiempo = new JButton("Datos meteorológicos");
	JButton btninfo = new JButton("Info del municipio");
	JButton btnRanking = new JButton("Top calidad del aire");
	JRadioButton radiobutonaraba = new JRadioButton("Araba");
	JRadioButton radiobutonbizkaia = new JRadioButton("Bizkaia");
	JRadioButton radiobutonguipuzkoa = new JRadioButton("Guipuzkoa");

	JRadioButton radiobutonComgm3 = new JRadioButton("Comgm3");
	JRadioButton radiobutonCO8hmgm3 = new JRadioButton("CO8hmgm3");
	JRadioButton radiobutonNogm3 = new JRadioButton("Nogm3");
	JRadioButton radiobutonNO2gm3 = new JRadioButton("NO2gm3");
	JRadioButton radiobutonNOXgm3 = new JRadioButton("NOXgm3");
	JRadioButton radiobutonPM10gm3 = new JRadioButton("PM10gm3");
	JRadioButton radiobutonPM25gm3 = new JRadioButton("PM25gm3");
	JRadioButton radiobutonSO2gm3 = new JRadioButton("SO2gm3");

	Administrador admin = null;
	JScrollPane scrollPane;
	static JList list;

	InsertarDatosAtmosfericos iDA = new InsertarDatosAtmosfericos();
	CalidadAire[] datosAtmosfericos = iDA.ObtenerDatosAtmosfericos();
	Double[] datos = new Double[datosAtmosfericos.length];
	String[] estacion = new String[datosAtmosfericos.length];
	String[] fechaHora = new String[datosAtmosfericos.length];

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
		btnTiempo.addActionListener(this);
		btnRanking.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		socket = s;
		this.nick = nombre;

		admin = new Administrador();
		socket = new Socket(admin.getHost(), admin.getPuerto());

		btnTodosLosMunicipios.setBounds(420, 11, 200, 30);
		getContentPane().add(btnTodosLosMunicipios);

		radiobutonbizkaia.setBounds(430, 48, 89, 23);
		getContentPane().add(radiobutonbizkaia);

		radiobutonguipuzkoa.setBounds(430, 74, 89, 23);
		getContentPane().add(radiobutonguipuzkoa);

		radiobutonaraba.setBounds(430, 100, 89, 23);
		getContentPane().add(radiobutonaraba);

		radiobutonComgm3.setBounds(420, 235, 100, 23);
		radiobutonComgm3.addActionListener(this);
		getContentPane().add(radiobutonComgm3);

		radiobutonCO8hmgm3.setBounds(520, 235, 100, 23);
		radiobutonCO8hmgm3.addActionListener(this);
		getContentPane().add(radiobutonCO8hmgm3);

		radiobutonNogm3.setBounds(420, 255, 100, 23);
		radiobutonNogm3.addActionListener(this);
		getContentPane().add(radiobutonNogm3);

		radiobutonNO2gm3.setBounds(520, 255, 100, 23);
		radiobutonNO2gm3.addActionListener(this);
		getContentPane().add(radiobutonNO2gm3);

		radiobutonNOXgm3.setBounds(420, 275, 100, 23);
		radiobutonNOXgm3.addActionListener(this);
		getContentPane().add(radiobutonNOXgm3);

		radiobutonPM10gm3.setBounds(520, 275, 100, 23);
		radiobutonPM10gm3.addActionListener(this);
		getContentPane().add(radiobutonPM10gm3);

		radiobutonPM25gm3.setBounds(420, 295, 100, 23);
		radiobutonPM25gm3.addActionListener(this);
		getContentPane().add(radiobutonPM25gm3);

		radiobutonSO2gm3.setBounds(520, 295, 100, 23);
		radiobutonSO2gm3.addActionListener(this);
		getContentPane().add(radiobutonSO2gm3);

		btnfiltro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnfiltro.setBounds(525, 52, 89, 75);
		getContentPane().add(btnfiltro);

		// ni se os ocurra tocar el jlist o el jscrollpane, que son muy tocapelotas y
		// dejaran de funcionar
		list = new JList();
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 11, 400, 328);
		getContentPane().add(scrollPane);

		list.setBounds(10, 11, 400, 328);
		getContentPane().add(scrollPane);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btninfo.setBounds(432, 138, 139, 23);
		getContentPane().add(btninfo);

		btnTiempo.setBounds(430, 172, 175, 23);
		getContentPane().add(btnTiempo);
		btnTiempo.setEnabled(false);

		btnRanking.setBounds(420, 205, 200, 30);
		getContentPane().add(btnRanking);

		admin = new Administrador(socket);
		admin.enviarMensaje("> " + nick + " se ha conectado\n");

	}// fin constructor

	public static void editarjlist(Object object) {
		List hql = (List) object;
		DefaultListModel modelo = new DefaultListModel();

		if (hql.get(0).getClass().toString().contentEquals("class modelo.CalidadAire")) {

			modelo.addElement(hql.get(0));
			modelo.addElement(((CalidadAire) modelo.getElementAt(0)).getNomEstMet());
			modelo.addElement("\n");
			modelo.addElement("Fecha - Hora = " + ((CalidadAire) modelo.getElementAt(0)).getFechaHora());
			modelo.addElement("C08hmgm3 = " + ((CalidadAire) modelo.getElementAt(0)).getCo8hmgm3());
			modelo.addElement("C0hmgm3 = " + ((CalidadAire) modelo.getElementAt(0)).getComgm3());
			modelo.addElement("N0gm3 = " + ((CalidadAire) modelo.getElementAt(0)).getNogm3());
			modelo.addElement("N02gm3 = " + ((CalidadAire) modelo.getElementAt(0)).getNo2gm3());
			modelo.addElement("N0Xgm3 = " + ((CalidadAire) modelo.getElementAt(0)).getNoxgm3());
			modelo.addElement("PM10gm3 = " + ((CalidadAire) modelo.getElementAt(0)).getPm10gm3());
			modelo.addElement("PM25gm3 = " + ((CalidadAire) modelo.getElementAt(0)).getPm25gm3());
			modelo.addElement("S02gm3 = " + ((CalidadAire) modelo.getElementAt(0)).getSo2gm3());
			list.setModel(modelo);

		} else {
			for (int x = 0; x <= hql.size() - 1; x++) {
				modelo.addElement(hql.get(x));
			}
			list.setModel(modelo);
		}

//		list.setModel(modelo);
	}

	// accion cuando pulsamos botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTiempo) { // sacar el tiempo atmosferico
			int tiempo = 0;
			String[] separado = new String[10];
			try {
				String buscar = (String) list.getSelectedValue().toString();
				separado = buscar.split(" ");
				filtro = "select c from CalidadAire c where c.nomEstMet = '" + separado[0].trim() + "'";
				admin.enviarMensaje("CONSULTA--> " + filtro);
				btnTiempo.setEnabled(false);

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un minicipio");
			}
		} else if (e.getSource() == btninfo) { // accion del boton de la info
			int espacio = 0;
			try {
				String buscar = (String) list.getSelectedValue().toString();
				for (int x = 0; x <= buscar.length() - 1; x++) {
					if (buscar.indexOf("1") > -1) {
						espacio = buscar.indexOf("1");
					} else if (buscar.indexOf("2") > -1) {
						espacio = buscar.indexOf("2");
					} else if (buscar.indexOf("4") > -1) {
						espacio = buscar.indexOf("4");
					} else {
						JOptionPane.showInputDialog("NO!!");
					}
					x = buscar.length() - 1;
				}
				buscar = buscar.substring(0, espacio);
				filtro = "select b from EstacionesMeteorologicas b where b.municipios = "
						+ "(select nombre from Municipios where nombre = '" + buscar.trim() + "')";
				admin.enviarMensaje("CONSULTA--> " + filtro);
				btnTiempo.setEnabled(true);
				btninfo.setEnabled(false);
				Thread.sleep(500);
				int tamanio = list.getModel().getSize();
				if (tamanio == 0) {
					JOptionPane.showMessageDialog(null, "Este municipio no tiene estaciones meteorologicas");
					btnTodosLosMunicipios.doClick();
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un minicipio");
			}
		} else if (e.getSource() == btnTodosLosMunicipios) { // sacar todos los municipios
			String texto = "CONSULTA--> " + "select a from Municipios a";
			admin.enviarMensaje(texto);
			btnTiempo.setEnabled(false);
			btninfo.setEnabled(true);
		} else if (e.getSource() == botonSalir) { // ACCIÓN AL PULSAR SALIR
			String texto = nick + " > Abandona el Chat ... \n";
			admin.enviarMensaje(texto);
			admin.enviarMensaje("ADIOS");
			dispose();
		} else if (e.getSource() == btnfiltro) {
			if (!radiobutonbizkaia.isSelected() && !radiobutonguipuzkoa.isSelected() && radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 1";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			if (radiobutonbizkaia.isSelected() && !radiobutonguipuzkoa.isSelected() && !radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 48";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			if (!radiobutonbizkaia.isSelected() && radiobutonguipuzkoa.isSelected() && !radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 20";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			if (radiobutonbizkaia.isSelected() && radiobutonguipuzkoa.isSelected() && !radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 20 or a.idProvincia = 48";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			if (!radiobutonbizkaia.isSelected() && radiobutonguipuzkoa.isSelected() && radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 1 or a.idProvincia = 20";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			if (radiobutonbizkaia.isSelected() && !radiobutonguipuzkoa.isSelected() && radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a where a.idProvincia = 1 or a.idProvincia = 48";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			if (radiobutonbizkaia.isSelected() && radiobutonguipuzkoa.isSelected() && radiobutonaraba.isSelected()) {
				filtro = "select a from Municipios a";
				admin.enviarMensaje("CONSULTA--> " + filtro);
			}
			btnTiempo.setEnabled(false);
			btninfo.setEnabled(true);
		} else if (e.getSource() == btnRanking) {
//============================================================================	

			DefaultListModel modelo = new DefaultListModel();
			modelo.addElement("Elige criterio de calidad de aire");
			list.setModel(modelo);

// ============================================================================
		}
		if (e.getSource() == radiobutonComgm3) {
			radiobutonCO8hmgm3.setSelected(false);
			radiobutonNogm3.setSelected(false);
			radiobutonNO2gm3.setSelected(false);
			radiobutonNOXgm3.setSelected(false);
			radiobutonPM10gm3.setSelected(false);
			radiobutonPM25gm3.setSelected(false);
			radiobutonSO2gm3.setSelected(false);
			if (!radiobutonComgm3.isSelected())
				radiobutonComgm3.setSelected(true);

			DefaultListModel modelo = new DefaultListModel();
			modelo.addElement(""); // Limpia el Jlist

			String num = "", num2 = "";
			for (int i = 0; i < datosAtmosfericos.length; i++) {
				if (!datosAtmosfericos[i].getComgm3().isEmpty()) {
					num = datosAtmosfericos[i].getComgm3();
					for (int j = 0; j < num.length(); j++) {
						if (num.charAt(j) == ',')
							num2 += ".";
						else
							num2 += num.charAt(j);
					}
					datos[i] = Double.parseDouble(num2);
					estacion[i] = datosAtmosfericos[i].getNomEstMet();
					fechaHora[i] = datosAtmosfericos[i].getFechaHora();
				} else {
					datos[i] = 0.0;
				}
				num = "";
				num2 = "";
			}

			list.setModel(obtenerTop(datos, estacion, fechaHora, "Comgm3"));

		}
		if (e.getSource() == radiobutonCO8hmgm3) {
			radiobutonComgm3.setSelected(false);
			radiobutonNogm3.setSelected(false);
			radiobutonNO2gm3.setSelected(false);
			radiobutonNOXgm3.setSelected(false);
			radiobutonPM10gm3.setSelected(false);
			radiobutonPM25gm3.setSelected(false);
			radiobutonSO2gm3.setSelected(false);
			if (!radiobutonCO8hmgm3.isSelected()) 
				radiobutonCO8hmgm3.setSelected(true);
			
			String num = "", num2 = "";
			for (int i = 0; i < datosAtmosfericos.length; i++) {
				if (!datosAtmosfericos[i].getCo8hmgm3().isEmpty()) {
					num = datosAtmosfericos[i].getCo8hmgm3();
					for (int j = 0; j < num.length(); j++) {
						if (num.charAt(j) == ',')
							num2 += ".";
						else
							num2 += num.charAt(j);
					}
					datos[i] = Double.parseDouble(num2);
					estacion[i] = datosAtmosfericos[i].getNomEstMet();
					fechaHora[i] = datosAtmosfericos[i].getFechaHora();
				} else {
					datos[i] = 0.0;
				}
				num = "";
				num2 = "";
			}
			list.setModel(obtenerTop(datos, estacion, fechaHora, "CO8hmgm3"));			
		}
		if (e.getSource() == radiobutonNogm3) {
			radiobutonComgm3.setSelected(false);
			radiobutonCO8hmgm3.setSelected(false);
			radiobutonNO2gm3.setSelected(false);
			radiobutonNOXgm3.setSelected(false);
			radiobutonPM10gm3.setSelected(false);
			radiobutonPM25gm3.setSelected(false);
			radiobutonSO2gm3.setSelected(false);
			if (!radiobutonNogm3.isSelected()) 
				radiobutonNogm3.setSelected(true);	
			
			String num = "", num2 = "";
			for (int i = 0; i < datosAtmosfericos.length; i++) {
				if (!datosAtmosfericos[i].getNogm3().isEmpty()) {
					num = datosAtmosfericos[i].getNogm3();
					for (int j = 0; j < num.length(); j++) {
						if (num.charAt(j) == ',')
							num2 += ".";
						else
							num2 += num.charAt(j);
					}
					datos[i] = Double.parseDouble(num2);
					estacion[i] = datosAtmosfericos[i].getNomEstMet();
					fechaHora[i] = datosAtmosfericos[i].getFechaHora();
				} else {
					datos[i] = 0.0;
				}
				num = "";
				num2 = "";
			}
			list.setModel(obtenerTop(datos, estacion, fechaHora, "Nogm3"));
			
		}
		if (e.getSource() == radiobutonNO2gm3) {
			radiobutonComgm3.setSelected(false);
			radiobutonNogm3.setSelected(false);
			radiobutonCO8hmgm3.setSelected(false);
			radiobutonNOXgm3.setSelected(false);
			radiobutonPM10gm3.setSelected(false);
			radiobutonPM25gm3.setSelected(false);
			radiobutonSO2gm3.setSelected(false);
			if (!radiobutonNO2gm3.isSelected()) 
				radiobutonNO2gm3.setSelected(true);
			
			String num = "", num2 = "";
			for (int i = 0; i < datosAtmosfericos.length; i++) {
				if (!datosAtmosfericos[i].getNo2gm3().isEmpty()) {
					num = datosAtmosfericos[i].getNo2gm3();
					for (int j = 0; j < num.length(); j++) {
						if (num.charAt(j) == ',')
							num2 += ".";
						else
							num2 += num.charAt(j);
					}
					datos[i] = Double.parseDouble(num2);
					estacion[i] = datosAtmosfericos[i].getNomEstMet();
					fechaHora[i] = datosAtmosfericos[i].getFechaHora();
				} else {
					datos[i] = 0.0;
				}
				num = "";
				num2 = "";
			}
			list.setModel(obtenerTop(datos, estacion, fechaHora, "NO2gm3"));	
		}
		if (e.getSource() == radiobutonNOXgm3) {
			radiobutonComgm3.setSelected(false);
			radiobutonNogm3.setSelected(false);
			radiobutonNO2gm3.setSelected(false);
			radiobutonCO8hmgm3.setSelected(false);
			radiobutonPM10gm3.setSelected(false);
			radiobutonPM25gm3.setSelected(false);
			radiobutonSO2gm3.setSelected(false);
			if (!radiobutonNOXgm3.isSelected()) 
				radiobutonNOXgm3.setSelected(true);
			
			String num = "", num2 = "";
			for (int i = 0; i < datosAtmosfericos.length; i++) {
				if (!datosAtmosfericos[i].getNoxgm3().isEmpty()) {
					num = datosAtmosfericos[i].getNoxgm3();
					for (int j = 0; j < num.length(); j++) {
						if (num.charAt(j) == ',')
							num2 += ".";
						else
							num2 += num.charAt(j);
					}
					datos[i] = Double.parseDouble(num2);
					estacion[i] = datosAtmosfericos[i].getNomEstMet();
					fechaHora[i] = datosAtmosfericos[i].getFechaHora();
				} else {
					datos[i] = 0.0;
				}
				num = "";
				num2 = "";
			}
			list.setModel(obtenerTop(datos, estacion, fechaHora, "NOXgm3"));
		}
		if (e.getSource() == radiobutonPM10gm3) {
			radiobutonComgm3.setSelected(false);
			radiobutonNogm3.setSelected(false);
			radiobutonNO2gm3.setSelected(false);
			radiobutonNOXgm3.setSelected(false);
			radiobutonCO8hmgm3.setSelected(false);
			radiobutonPM25gm3.setSelected(false);
			radiobutonSO2gm3.setSelected(false);
			if (!radiobutonPM10gm3.isSelected()) 
				radiobutonPM10gm3.setSelected(true);
			
			String num = "", num2 = "";
			for (int i = 0; i < datosAtmosfericos.length; i++) {
				if (!datosAtmosfericos[i].getPm10gm3().isEmpty()) {
					num = datosAtmosfericos[i].getPm10gm3();
					for (int j = 0; j < num.length(); j++) {
						if (num.charAt(j) == ',')
							num2 += ".";
						else
							num2 += num.charAt(j);
					}
					datos[i] = Double.parseDouble(num2);
					estacion[i] = datosAtmosfericos[i].getNomEstMet();
					fechaHora[i] = datosAtmosfericos[i].getFechaHora();
				} else {
					datos[i] = 0.0;
				}
				num = "";
				num2 = "";
			}
			list.setModel(obtenerTop(datos, estacion, fechaHora, "PM10gm3"));
		}
		if (e.getSource() == radiobutonPM25gm3) {
			radiobutonComgm3.setSelected(false);
			radiobutonNogm3.setSelected(false);
			radiobutonNO2gm3.setSelected(false);
			radiobutonNOXgm3.setSelected(false);
			radiobutonPM10gm3.setSelected(false);
			radiobutonCO8hmgm3.setSelected(false);
			radiobutonSO2gm3.setSelected(false);
			if (!radiobutonPM25gm3.isSelected()) 
				radiobutonPM25gm3.setSelected(true);
			
			String num = "", num2 = "";
			for (int i = 0; i < datosAtmosfericos.length; i++) {
				if (!datosAtmosfericos[i].getPm25gm3().isEmpty()) {
					num = datosAtmosfericos[i].getPm25gm3();
					for (int j = 0; j < num.length(); j++) {
						if (num.charAt(j) == ',')
							num2 += ".";
						else
							num2 += num.charAt(j);
					}
					datos[i] = Double.parseDouble(num2);
					estacion[i] = datosAtmosfericos[i].getNomEstMet();
					fechaHora[i] = datosAtmosfericos[i].getFechaHora();
				} else {
					datos[i] = 0.0;
				}
				num = "";
				num2 = "";
			}
			list.setModel(obtenerTop(datos, estacion, fechaHora, "PM25gm3"));
		}
		if (e.getSource() == radiobutonSO2gm3) {
			radiobutonComgm3.setSelected(false);
			radiobutonNogm3.setSelected(false);
			radiobutonNO2gm3.setSelected(false);
			radiobutonNOXgm3.setSelected(false);
			radiobutonPM10gm3.setSelected(false);
			radiobutonPM25gm3.setSelected(false);
			radiobutonSO2gm3.setSelected(false);
			if (!radiobutonSO2gm3.isSelected()) 
				radiobutonSO2gm3.setSelected(true);
			
			String num = "", num2 = "";
			for (int i = 0; i < datosAtmosfericos.length; i++) {
				if (!datosAtmosfericos[i].getSo2gm3().isEmpty()) {
					num = datosAtmosfericos[i].getSo2gm3();
					for (int j = 0; j < num.length(); j++) {
						if (num.charAt(j) == ',')
							num2 += ".";
						else
							num2 += num.charAt(j);
					}
					datos[i] = Double.parseDouble(num2);
					estacion[i] = datosAtmosfericos[i].getNomEstMet();
					fechaHora[i] = datosAtmosfericos[i].getFechaHora();
				} else {
					datos[i] = 0.0;
				}
				num = "";
				num2 = "";
			}
			list.setModel(obtenerTop(datos, estacion, fechaHora, "SO2gm3"));
		}
	}

	public void stateChanged(ChangeEvent e) {

	}

	public DefaultListModel obtenerTop(Double[] datos, String[] estacion, String[] fechaHora, String medicion) {
		DefaultListModel modelo = new DefaultListModel();
		modelo.addElement(""); // Limpia el Jlist

		double max = datos[0];
		int posicion = 0;
		for (int i = 1; i < datos.length; i++) { // Busco el máximo y guardo su posición para recuperar el nombre y
													// la hora
			if (datos[i] > max) {
				max = datos[i];
				posicion = i;
			}
		}

		modelo.addElement("TOP EUSKADI " + medicion);
		modelo.addElement("\n");
		modelo.addElement("+-----------------------------------------+");
		modelo.addElement(" Estación --> " + estacion[posicion]);
		modelo.addElement(" Fecha/Hora --> " + fechaHora[posicion]);
		modelo.addElement(" Medición --> " + datos[posicion]);
		modelo.addElement("+-----------------------------------------+");

		return modelo;
	}
}
