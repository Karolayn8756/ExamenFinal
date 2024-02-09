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
import ec.edu.puce.voto.dominio.Estudiante;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;

public class CrearEstudiantes extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField txtNombre;
    private JTextField txtCedula;

    private Estudiante estudiante;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnGuardar;
    private JButton btnNuevo;
    private JButton btnCancelar;
    private String[] nombreCursos;
    private List<Estudiante> estudiantes;
    private List<Cursos> cursos;
    private int idEstudiante;
    private JComboBox comboBox;

    public CrearEstudiantes(List<Cursos> cursos, List<Estudiante> estudiantes, int idEstudiante) {
    	getContentPane().setBackground(new Color(74, 149, 149));
        this.idEstudiante = idEstudiante;
        this.estudiantes = estudiantes;
        this.cursos = cursos;
        setTitle("CREAR ESTUDIANTES ");
        setBounds(100, 100, 443, 385);
        getContentPane().setLayout(null);

        int i = 0;
        this.nombreCursos = new String[cursos.size()];
        for (Cursos curso : cursos) {
            nombreCursos[i] = curso.getNombreCurso();
            i++;
        }

        JLabel lblNombres = new JLabel("Curso");
        lblNombres.setFont(new Font("Cambria Math", Font.BOLD, 13));
        lblNombres.setBounds(30, 16, 70, 15);
        getContentPane().add(lblNombres);

        txtNombre = new JTextField();
        txtNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        txtNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                agregarEstudiante();
            }

        });

        txtCedula = new JTextField();
        txtCedula.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        txtCedula.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                agregarEstudiante();
            }

        });

        txtNombre.setBounds(109, 54, 191, 19);
        getContentPane().add(txtNombre);
        txtNombre.setColumns(10);

        txtCedula.setBounds(109, 89, 191, 19);
        getContentPane().add(txtCedula);
        txtCedula.setColumns(10);

        btnNuevo = new JButton("Nuevo");
        btnNuevo.addActionListener(this);
        btnNuevo.setBounds(322, 51, 81, 25);
        getContentPane().add(btnNuevo);

        btnGuardar = new JButton("Agregar");
        btnGuardar.addActionListener(this);
        btnGuardar.setBounds(322, 86, 81, 25);
        getContentPane().add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setBounds(322, 16, 81, 25);
        getContentPane().add(btnCancelar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 140, 416, 199);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(model.getValueAt(0, 0));
            }
        });
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Cedula" }));
        scrollPane.setViewportView(table);

        comboBox = new JComboBox(new DefaultComboBoxModel(this.nombreCursos));
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarFilas();
            }
        });
        comboBox.setBounds(109, 11, 191, 24);
        getContentPane().add(comboBox);

        JLabel lblNombres_1_1 = new JLabel("Nombres");
        lblNombres_1_1.setFont(new Font("Cambria Math", Font.BOLD, 13));
        lblNombres_1_1.setBounds(30, 57, 70, 15);
        getContentPane().add(lblNombres_1_1);

        JLabel lblCedula = new JLabel("Cedula");
        lblCedula.setFont(new Font("Cambria Math", Font.BOLD, 13));
        lblCedula.setBounds(30, 91, 70, 15);
        getContentPane().add(lblCedula);

        model = (DefaultTableModel) table.getModel();
        cambiarFilas();
    }

    private void nuevo() {
        estudiante = new Estudiante();
        txtNombre.setText(estudiante.getNombre());
        txtCedula.setText(estudiante.getId());
    }

    private void cambiarFilas() {
        model.setRowCount(0);

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCurso().getNombreCurso().equals(cursos.get(this.comboBox.getSelectedIndex()).getNombreCurso())) {
                Object[] fila = new Object[2];
                fila[0] = estudiante.getNombre();
                fila[1] = estudiante.getId();
                model.addRow(fila);
            }
        }
    }

    private void agregarEstudiante() {
        estudiante = new Estudiante();
        estudiante.setNombre(txtNombre.getText());
        estudiante.setId(txtCedula.getText());
        estudiante.setCurso(cursos.get(this.comboBox.getSelectedIndex()));
        estudiantes.add(estudiante);
        agregarFila();
        cambiarFilas();
        txtNombre.setText("");
        txtCedula.setText("");
        idEstudiante++;
    }

    private void agregarFila() {
        model.setRowCount(0);

        for (Estudiante estudiante : estudiantes) {
            Object[] fila = new Object[2];
            fila[0] = estudiante.getNombre();
            fila[1] = estudiante.getId();
            model.addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNuevo) {
            nuevo();
        } else if (e.getSource() == btnGuardar) {
            agregarEstudiante();
        } else if (e.getSource() == btnCancelar) {
            dispose();
        } else if (e.getSource() == txtNombre) {
            agregarEstudiante();
        }
    }
}
