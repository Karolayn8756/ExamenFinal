package ec.edu.puce.voto.formulario;



import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gracias extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JButton btnCerrar;

    public Gracias() {
        setTitle("¡Gracias por Votar!");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        JLabel lblGraciasPorVotar = new JLabel("¡Gracias por votar!");
        lblGraciasPorVotar.setForeground(new Color(0, 128, 0)); // Color verde
        lblGraciasPorVotar.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblGraciasPorVotar.setBounds(97, 94, 256, 49);
        getContentPane().add(lblGraciasPorVotar);

        // Botón de cerrar
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(180, 180, 90, 25);
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        getContentPane().add(btnCerrar);
    }

    public static void mostrarGracias() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gracias frame = new Gracias();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
