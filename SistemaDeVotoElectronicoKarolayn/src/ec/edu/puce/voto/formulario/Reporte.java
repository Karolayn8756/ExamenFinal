package ec.edu.puce.voto.formulario;


import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.voto.dominio.Cursos;
import ec.edu.puce.voto.dominio.Estudiante;
import ec.edu.puce.voto.dominio.Mesas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Font;

public class Reporte extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel model;

	private List<Mesas> mesas;
	private List<Cursos> cursos;
	private List<Estudiante> estudiantes;
	private String[] mesasTexto;
	private String[] cursosTexto;
	private JButton btnCancelar;
	private JLabel lblNombres;
	private JComboBox comboBox;
	private JLabel lblCiudad;
	private JComboBox comboBox_1;

	public Reporte(List<Mesas> mesas, List<Cursos> cursos, List<Estudiante> estudiantes) {
		getContentPane().setBackground(new Color(74, 149, 149));
		this.mesas = mesas;
		this.cursos = cursos;
		this.estudiantes = estudiantes;
		this.mesasTexto = new String[this.mesas.size()];
		int i = 0;
		for (Mesas mesa : this.mesas) {
			this.mesasTexto[i] = mesa.getNombre();
			i++;
		}
		
		setTitle("REPORTE ");
		setBounds(100, 100, 600, 309);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 71, 566, 167);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(model.getValueAt(0, 0));
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre","Cedula", "Mesa Designada"
			}
		));
		scrollPane.setViewportView(table);
		model = (DefaultTableModel) table.getModel();
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(235, 244, 117, 25);
		getContentPane().add(btnCancelar);
		
		lblNombres = new JLabel("Mesa");
		lblNombres.setFont(new Font("Cambria Math", Font.BOLD, 12));
		lblNombres.setBounds(12, 21, 70, 15);
		getContentPane().add(lblNombres);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarMesa();
				llenarTabla();
				
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(this.mesasTexto));
		comboBox.setBounds(79, 12, 201, 24);
		getContentPane().add(comboBox);
		
		lblCiudad = new JLabel("Curso");
		lblCiudad.setFont(new Font("Cambria Math", Font.BOLD, 13));
		lblCiudad.setBounds(12, 47, 70, 15);
		getContentPane().add(lblCiudad);
		this.cursosTexto = new String[this.mesas.get(comboBox.getSelectedIndex()).getCursos().size()];
		int j = 0;
		for (Cursos curso : this.mesas.get(comboBox.getSelectedIndex()).getCursos()) {
			this.cursosTexto[j] = curso.getNombreCurso();
			j++;
		}
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTabla();
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(this.cursosTexto));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setBounds(79, 43, 201, 24);
		getContentPane().add(comboBox_1);
		
		
		llenarTabla();
	}

	
	private void llenarTabla() {
	    model.setRowCount(0);
	    for (Estudiante estudiante : estudiantes) {
	        if(estudiante.getCurso().getNombreCurso().equals(this.mesas.get(this.comboBox.getSelectedIndex()).getCursos().get(this.comboBox_1.getSelectedIndex()).getNombreCurso())) {
	            Object[] fila = new Object[4];
	            fila[0] = estudiante.getNombre();
	            fila[1] = estudiante.getId();
	            fila[2] = estudiante.getCurso().getMesa().getNombre();

	            

	            model.addRow(fila);
	        }
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			dispose();
		}
	}
	
	public void seleccionarMesa() {
		this.cursosTexto = new String[this.mesas.get(comboBox.getSelectedIndex()).getCursos().size()];
		int j = 0;
		for (Cursos curso : this.mesas.get(comboBox.getSelectedIndex()).getCursos()) {
			this.cursosTexto[j] = curso.getNombreCurso();
			j++;
		}
		comboBox_1.setModel(new DefaultComboBoxModel(cursosTexto));
	}
	
	
}