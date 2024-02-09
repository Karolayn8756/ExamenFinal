package ec.edu.puce.voto.formulario;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

import ec.edu.puce.voto.dominio.Prefecto;

public class BocaDeUrna extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private List<Prefecto> prefectos;
    private JPanel panel;
    private JButton btnCancelar;
    private String nombreEstudiante;
    private JButton btnResultadosGenerales;
    private ResultadosPorMesa resultadosPorMesaFrame;

    public BocaDeUrna(List<Prefecto> prefectos, String nombreEstudiante) {
        this.prefectos = prefectos;
        this.nombreEstudiante = nombreEstudiante;
        this.resultadosPorMesaFrame = resultadosPorMesaFrame; 
        setTitle("BOCA DE URNA - REGISTRO");
        setBounds(100, 100, 600, 427);
        getContentPane().setLayout(null);

        panel = new JPanel();
        panel.setBounds(12, 12, 566, 84);
        getContentPane().add(panel);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setBounds(157, 351, 117, 25);
        getContentPane().add(btnCancelar);

        cargarCandidatos();
    }

    private void cargarCandidatos() {
        int x = 0;

        // Agregar el nombre del estudiante en la parte superior
        JLabel lblNombreEstudiante = new JLabel("Estudiante: " + nombreEstudiante);
        panel.add(lblNombreEstudiante);

        for (Prefecto prefecto : prefectos) {
            JButton btnPrefecto = new JButton(prefecto.getNombre());
            if (!prefecto.getNombre().isEmpty()) {
                btnPrefecto.setBounds(x * 155, 0, 150, 80);
                btnPrefecto.addActionListener(this);
                panel.setLayout((new FlowLayout()));
                panel.add(btnPrefecto);
                x++;
            }
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            dispose();
        } else {
            String textoBotonPulsado = e.getActionCommand();
            for (Prefecto prefecto : prefectos) {
                if (textoBotonPulsado.equals(prefecto.getNombre())) {
                    prefecto.setVotos(prefecto.getVotos() + 1);

                    // Mostrar la ventana de Gracias
                    mostrarGraciasFrame();
                }else if (e.getSource() == btnResultadosGenerales) {
                        // Mostrar la ventana de Resultados Generales
                        mostrarResultadosGenerales();
                    break;
                }
            }
        }
    }
    private void mostrarResultadosGenerales() {
        ResultadosGenerales resultadosGenerales = new ResultadosGenerales(prefectos);
        resultadosGenerales.setVisible(true);
    }
    private void mostrarGraciasFrame() {
        Gracias gracias = new Gracias();
        gracias.setVisible(true);
    }

    public void setPrefectos(List<Prefecto> prefectos) {
        this.prefectos = prefectos;
    }
    
}

