package ec.edu.puce.voto.formulario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.voto.dominio.Mesas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Font;

public class CrearMesas extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;

	private Mesas mesa;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private List<Mesas> mesas;
	
	public CrearMesas(List<Mesas> mesas) {
		getContentPane().setBackground(new Color(74, 149, 149));
		this.mesas=mesas;
		setTitle("CREAR MESA");
		setBounds(100, 100, 443, 385);
		getContentPane().setLayout(null);
		
		JLabel lblNombres = new JLabel("Mesa:");
		lblNombres.setFont(new Font("Cambria Math", Font.BOLD, 14));
		lblNombres.setBounds(52, 43, 127, 15);
		getContentPane().add(lblNombres);

		txtNombre = new JTextField();
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		txtNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarMesa();
			}
		});
		txtNombre.setBounds(117, 40, 127, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(304, 56, 117, 25);
		getContentPane().add(btnNuevo);

		btnGuardar = new JButton("Agregar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(304, 21, 117, 25);
		getContentPane().add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(167, 310, 117, 25);
		getContentPane().add(btnCancelar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 416, 187);
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

		model = (DefaultTableModel) table.getModel();
		agregarFila();
	}

	private void nuevo() {
		mesa = new Mesas("");
		txtNombre.setText(mesa.getNombre());
	}
	
	

	private void agregarMesa() {
		mesa = new Mesas(txtNombre.getText());
		mesas.add(mesa);
		agregarFila();
		txtNombre.setText("");
	}

	private void agregarFila() {
		model.setRowCount(0);
		for (Mesas mesa : mesas) {
			Object[] fila = new Object[1];
			fila[0] = mesa.getNombre();
			model.addRow(fila);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo) {
			nuevo();
		} else if (e.getSource() == btnGuardar) {
			agregarMesa();
		} else if (e.getSource() == btnCancelar) {
			dispose();
		}
		else if (e.getSource() == txtNombre) {
			agregarMesa();
		}
	}	

}
