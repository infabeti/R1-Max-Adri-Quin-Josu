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
import modelo.CalidadAire;
import modelo.Municipios;

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
	String filtro ="";
	String nick;
	String consultaRanking = "SELECT DISTINCT EM FROM EstacionesMeteorologicas EM JOIN CalidadAire CA ON EM.nombre = CA.nomEstMet ORDER BY CA.Nogm3 DESC";

	JButton botonSalir = new JButton("Salir");
	JButton btnTodosLosMunicipios = new JButton("Listar Todos Los Municipios");
	JButton btnfiltro = new JButton("Ir");
	JButton btnTiempo = new JButton("Datos meteorologicos");
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
		btnTiempo.addActionListener(this);
		btnRanking.addActionListener(this);
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
		btnTiempo.setEnabled(false);
		
		btnRanking.setBounds(420, 205, 200, 30);
		getContentPane().add(btnRanking);
		
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
			String[] separado = new String[10];
			try {
				String buscar = (String) list.getSelectedValue().toString();				
				separado = buscar.split(" ");
				System.out.println(separado[0]);
			filtro = "select c from CalidadAire c where c.nomEstMet = '" + separado[0].trim() + "'";
			admin.enviarMensaje("CONSULTA--> " + filtro);
			btnTiempo.setEnabled(false);
			
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un minicipio");
			}
		}else if (e.getSource() == btninfo) { //accion del boton de la info
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
			filtro = "select b from EstacionesMeteorologicas b where b.municipios = "
					+ "(select nombre from Municipios where nombre = '" + buscar.trim() + "')";
			admin.enviarMensaje("CONSULTA--> " + filtro);
			btnTiempo.setEnabled(true);
			btninfo.setEnabled(false);
			Thread.sleep(500);
			int tamanio = list.getModel().getSize();
			if(tamanio == 0) {
				JOptionPane.showMessageDialog(null, "Este municipio no tiene estaciones meteorologicas");
				btnTodosLosMunicipios.doClick();
			}
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un minicipio");
			}
		}else if(e.getSource()== btnTodosLosMunicipios) { //sacar todos los municipios
			String texto = "CONSULTA--> " + "select a from Municipios a";
			admin.enviarMensaje(texto);
			btnTiempo.setEnabled(false);
			btninfo.setEnabled(true);
		}else if (e.getSource() == botonSalir) { //ACCIÓN AL PULSAR SALIR
			String texto = nick + " > Abandona el Chat ... \n" ;
			admin.enviarMensaje(texto);
			admin.enviarMensaje("ADIOS");
			dispose();
		}else if (e.getSource() == btnfiltro) {
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
			btnTiempo.setEnabled(false);
			btninfo.setEnabled(true);
		}else if(e.getSource() == btnRanking) {
			//admin.enviarMensaje("CONSULTA--> " + consultaRanking);
		}
		if (e.getSource() == radiobutonComgm3) {
        	radiobutonCO8hmgm3.setSelected(false);
        	radiobutonNogm3.setSelected(false);
        	radiobutonNO2gm3.setSelected(false);
        	radiobutonNOXgm3.setSelected(false);
        	radiobutonPM10gm3.setSelected(false);
        	radiobutonPM25gm3.setSelected(false);
        	radiobutonSO2gm3.setSelected(false);
        	if(!radiobutonComgm3.isSelected()) {
        		radiobutonComgm3.setSelected(true);
        	}
        	
        }
        if (e.getSource() == radiobutonCO8hmgm3) {
        	radiobutonComgm3.setSelected(false);
        	radiobutonNogm3.setSelected(false);
        	radiobutonNO2gm3.setSelected(false);
        	radiobutonNOXgm3.setSelected(false);
        	radiobutonPM10gm3.setSelected(false);
        	radiobutonPM25gm3.setSelected(false);
        	radiobutonSO2gm3.setSelected(false);
        	if(!radiobutonCO8hmgm3.isSelected()) {
        		radiobutonCO8hmgm3.setSelected(true);
        	}
        	
        }
        if (e.getSource() == radiobutonNogm3) {
        	radiobutonComgm3.setSelected(false);
        	radiobutonCO8hmgm3.setSelected(false);
        	radiobutonNO2gm3.setSelected(false);
        	radiobutonNOXgm3.setSelected(false);
        	radiobutonPM10gm3.setSelected(false);
        	radiobutonPM25gm3.setSelected(false);
        	radiobutonSO2gm3.setSelected(false);
        	if(!radiobutonNogm3.isSelected()) {
        		radiobutonNogm3.setSelected(true);
        	}
        	
        }  
        if (e.getSource() == radiobutonNO2gm3) {
        	radiobutonComgm3.setSelected(false);
        	radiobutonNogm3.setSelected(false);
        	radiobutonCO8hmgm3.setSelected(false);
        	radiobutonNOXgm3.setSelected(false);
        	radiobutonPM10gm3.setSelected(false);
        	radiobutonPM25gm3.setSelected(false);
        	radiobutonSO2gm3.setSelected(false);
        	if(!radiobutonNO2gm3.isSelected()) {
        		radiobutonNO2gm3.setSelected(true);
        	}
        	
        }
        if (e.getSource() == radiobutonNOXgm3) {
        	radiobutonComgm3.setSelected(false);
        	radiobutonNogm3.setSelected(false);
        	radiobutonNO2gm3.setSelected(false);
        	radiobutonCO8hmgm3.setSelected(false);
        	radiobutonPM10gm3.setSelected(false);
        	radiobutonPM25gm3.setSelected(false);
        	radiobutonSO2gm3.setSelected(false);
        	if(!radiobutonNOXgm3.isSelected()) {
        		radiobutonNOXgm3.setSelected(true);
        	}
        	
        }  
        if (e.getSource() == radiobutonPM10gm3) {
        	radiobutonComgm3.setSelected(false);
        	radiobutonNogm3.setSelected(false);
        	radiobutonNO2gm3.setSelected(false);
        	radiobutonNOXgm3.setSelected(false);
        	radiobutonCO8hmgm3.setSelected(false);
        	radiobutonPM25gm3.setSelected(false);
        	radiobutonSO2gm3.setSelected(false);
        	if(!radiobutonPM10gm3.isSelected()) {
        		radiobutonPM10gm3.setSelected(true);
        	}
        	
        }
        if (e.getSource() == radiobutonPM25gm3) {
        	radiobutonComgm3.setSelected(false);
        	radiobutonNogm3.setSelected(false);
        	radiobutonNO2gm3.setSelected(false);
        	radiobutonNOXgm3.setSelected(false);
        	radiobutonPM10gm3.setSelected(false);
        	radiobutonCO8hmgm3.setSelected(false);
        	radiobutonSO2gm3.setSelected(false);
        	if(!radiobutonPM25gm3.isSelected()) {
        		radiobutonPM25gm3.setSelected(true);
        	}
        	
        }  
        if (e.getSource() == radiobutonSO2gm3) {
        	radiobutonComgm3.setSelected(false);
        	radiobutonNogm3.setSelected(false);
        	radiobutonNO2gm3.setSelected(false);
        	radiobutonNOXgm3.setSelected(false);
        	radiobutonPM10gm3.setSelected(false);
        	radiobutonPM25gm3.setSelected(false);
        	radiobutonSO2gm3.setSelected(false);
        	if(!radiobutonSO2gm3.isSelected()) {
        		radiobutonSO2gm3.setSelected(true);
        	}
        	
        }
	}
	
	public void stateChanged(ChangeEvent e) {
        
    }
}
