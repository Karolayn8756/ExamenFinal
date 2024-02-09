package ec.edu.puce.voto.formulario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.voto.dominio.Cursos;
import ec.edu.puce.voto.dominio.Mesas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;

public class CrearCursos extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;

	private Cursos curso;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private String[] nombreMesas;
	private List<Cursos> cursos;
	private List<Mesas> mesas;
	private JComboBox comboBox;
	
	public CrearCursos(List<Mesas> mesas, List<Cursos> cursos) {
		getContentPane().setBackground(new Color(74, 149, 149));
		this.cursos=cursos;
		this.mesas = mesas;
		setTitle("CREAR CURSOS");
		setBounds(100, 100, 443, 385);
		getContentPane().setLayout(null);
		
		
		int i = 0;
		this.nombreMesas = new String[mesas.size()];
		for (Mesas mesa : mesas) {
			nombreMesas[i] = mesa.getNombre();
			i++;
		}
		
		JLabel lblNombres = new JLabel("Mesa");
		lblNombres.setFont(new Font("Cambria Math", Font.BOLD, 13));
		lblNombres.setBounds(31, 33, 70, 15);
		getContentPane().add(lblNombres);

		txtNombre = new JTextField();
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		txtNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarCurso();
			}
		});
		txtNombre.setBounds(130, 72, 136, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(286, 16, 117, 25);
		getContentPane().add(btnNuevo);

		btnGuardar = new JButton("Agregar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(286, 88, 117, 25);
		getContentPane().add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(286, 53, 117, 25);
		getContentPane().add(btnCancelar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 135, 371, 193);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(model.getValueAt(0, 0));
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre" }));
		scrollPane.setViewportView(table);
		
		JLabel lblNombres_1 = new JLabel("Curso");
		lblNombres_1.setFont(new Font("Cambria Math", Font.BOLD, 13));
		lblNombres_1.setBounds(30, 75, 90, 15);
		getContentPane().add(lblNombres_1);
		
		comboBox = new JComboBox(new DefaultComboBoxModel(this.nombreMesas));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarFilas();
			}
		});
		comboBox.setBounds(130, 27, 136, 24);
		getContentPane().add(comboBox);

		model = (DefaultTableModel) table.getModel();
		cambiarFilas();
	}

	private void nuevo() {
		curso = new Cursos("", null);
		txtNombre.setText(curso.getNombreCurso());
	}
	
	private void cambiarFilas() {
		model.setRowCount(0);
		
		for (Cursos curso : cursos) {
			if(curso.getMesa().getNombre() == mesas.get(this.comboBox.getSelectedIndex()).getNombre()) {
				Object[] fila = new Object[1];
				fila[0] = curso.getNombreCurso();
				model.addRow(fila);
			}
		}
	}

	private void agregarCurso() {
		curso = new Cursos(txtNombre.getText(), mesas.get(this.comboBox.getSelectedIndex()));
		cursos.add(curso);
		mesas.get(this.comboBox.getSelectedIndex()).getCursos().add(curso);
		agregarFila();
		cambiarFilas();
		txtNombre.setText("");
		
	}

	private void agregarFila() {
		model.setRowCount(0);
		for (Cursos curso : cursos) {
			Object[] fila = new Object[1];
			fila[0] = curso.getNombreCurso();
			model.addRow(fila);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo) {
			nuevo();
		} else if (e.getSource() == btnGuardar) {
			agregarCurso();
		} else if (e.getSource() == btnCancelar) {
			dispose();
		}
		else if (e.getSource() == txtNombre) {
			agregarCurso();
		}
	}	
}