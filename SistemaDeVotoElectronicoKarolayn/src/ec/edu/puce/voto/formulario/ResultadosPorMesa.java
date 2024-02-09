package ec.edu.puce.voto.formulario;



import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import ec.edu.puce.voto.dominio.Cursos;
import ec.edu.puce.voto.dominio.Prefecto;

public class ResultadosPorMesa extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JTable table;
    private DefaultTableModel model;

    private List<Prefecto> prefectos;
    private List<Cursos> cursos;
    private JButton btnCancelar;
    private JComboBox<String> comboBox;
    private JLabel lblMesa;

    public ResultadosPorMesa(List<Prefecto> prefectos, List<Cursos> cursos) {
        getContentPane().setBackground(new Color(0, 128, 192));
        this.prefectos = prefectos;
        this.cursos = cursos;

        setTitle("REPORTE GENERAL POR MESA");
        setBounds(100, 100, 600, 309);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 48, 566, 167);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    String nombreCandidato = (String) model.getValueAt(filaSeleccionada, 0);
                    for (Prefecto prefecto : prefectos) {
                        if (prefecto.getNombre().equals(nombreCandidato)) {
                            prefecto.setVotos(prefecto.getVotos() + 1);
                            llenarTabla();
                            break;
                        }
                    }
                }
            }
        });
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre", "Votos"}
        ));
        scrollPane.setViewportView(table);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Cambria Math", Font.BOLD, 12));
        btnCancelar.setBackground(new Color(128, 128, 128));
        btnCancelar.addActionListener(this);
        btnCancelar.setBounds(234, 227, 117, 25);
        getContentPane().add(btnCancelar);

        lblMesa = new JLabel("Mesa");
        lblMesa.setFont(new Font("Cambria Math", Font.BOLD, 12));
        lblMesa.setBounds(12, 21, 70, 15);
        getContentPane().add(lblMesa);

        comboBox = new JComboBox<>();
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                llenarTabla();
            }
        });
        comboBox.setBounds(89, 18, 231, 24);
        getContentPane().add(comboBox);

        model = (DefaultTableModel) table.getModel();
        llenarComboBox();
        llenarTabla();
    }

    private void llenarComboBox() {
        for (Cursos curso : cursos) {
            comboBox.addItem(curso.getNombreCurso());
        }
    }

    private void llenarTabla() {
        model.setRowCount(0);
        String cursoSeleccionado = (String) comboBox.getSelectedItem();

        for (Prefecto prefecto : prefectos) {
            List<Cursos> cursosPrefecto = prefecto.getCursos();
            if (cursosPrefecto != null) {
                for (Cursos curso : cursosPrefecto) {
                    String nombreCurso = curso.getNombreCurso();
                    if (nombreCurso != null && nombreCurso.equals(cursoSeleccionado)) {
                        Object[] fila = new Object[2];
                        fila[0] = prefecto.getNombre();
                        fila[1] = prefecto.getVotos();
                        model.addRow(fila);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            dispose();
        }
    }

    public List<Prefecto> getPrefectos() {
        return prefectos;
    }

    public void setPrefectos(List<Prefecto> prefectos) {
        this.prefectos = prefectos;
    }
}
