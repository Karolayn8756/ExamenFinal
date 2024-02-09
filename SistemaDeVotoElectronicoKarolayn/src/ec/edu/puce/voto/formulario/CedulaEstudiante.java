package ec.edu.puce.voto.formulario;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ec.edu.puce.voto.dominio.Estudiante;
import ec.edu.puce.voto.dominio.Prefecto;

public class CedulaEstudiante extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;

    // Agrega una referencia a la ventana BocaDeUrna
    private BocaDeUrna bocaDeUrna;

    // Agrega una referencia a la lista de estudiantes
    private List<Estudiante> estudiantes;
    public List<Prefecto> prefectos;
    private JDesktopPane desktopPane;

    public CedulaEstudiante(List<Estudiante> estudiantes, List<Prefecto> prefectos, JDesktopPane desktopPane) {
        this.estudiantes = estudiantes;
        this.prefectos = prefectos;
        this.desktopPane = desktopPane;

        setBounds(100, 100, 370, 222);
        getContentPane().setLayout(null);

        JLabel lblCedulaEstudiante = new JLabel("Cedula Estudiante:");
        lblCedulaEstudiante.setFont(new Font("Cambria Math", Font.BOLD, 13));
        lblCedulaEstudiante.setBounds(122, 44, 114, 15);
        getContentPane().add(lblCedulaEstudiante);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(84, 72, 191, 19);
        getContentPane().add(textField);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                verificarCedula();
            }
        });
        btnEntrar.setBounds(133, 113, 81, 25);
        getContentPane().add(btnEntrar);

        // Agrega un KeyAdapter al textField para escuchar la tecla Enter
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verificarCedula();
                }
            }
        });
    }
    private void verificarCedula() {
        String cedulaIngresada = textField.getText();

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId().equals(cedulaIngresada)) {
                // Pasa el nombre del estudiante a la ventana BocaDeUrna
                mostrarBienvenida(estudiante.getNombre());
                BocaDeUrna bocaDeUrna = new BocaDeUrna(prefectos, estudiante.getNombre());
                desktopPane.add(bocaDeUrna);
                bocaDeUrna.setVisible(true);
                

                // Cerrar la ventana actual
                dispose();

                return;
            }
        }

        System.out.println("No existe estudiante con esa cédula.");
    }

       
   
   private void mostrarBienvenida(String nombreEstudiante) {
       System.out.println("Bienvenido, " + nombreEstudiante + "!");
   }}

