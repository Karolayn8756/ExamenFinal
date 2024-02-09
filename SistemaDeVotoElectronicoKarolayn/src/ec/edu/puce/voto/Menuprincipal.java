package ec.edu.puce.voto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import ec.edu.puce.voto.dominio.Cursos;
import ec.edu.puce.voto.dominio.Estudiante;
import ec.edu.puce.voto.dominio.Mesas;
import ec.edu.puce.voto.dominio.Prefecto;
import ec.edu.puce.voto.formulario.BocaDeUrna;
import ec.edu.puce.voto.formulario.CedulaEstudiante;
import ec.edu.puce.voto.formulario.CrearCursos;
import ec.edu.puce.voto.formulario.CrearEstudiantes;
import ec.edu.puce.voto.formulario.CrearMesas;
import ec.edu.puce.voto.formulario.CrearPrefecto;
import ec.edu.puce.voto.formulario.Reporte;
import ec.edu.puce.voto.formulario.ResultadosGenerales;
import ec.edu.puce.voto.formulario.ResultadosPorMesa;

import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Menuprincipal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contenedor;
    private JDesktopPane desktopPane;
    private JMenuItem mntmSalir;
    private JMenuItem mntmMesas;

    public List<Prefecto> prefectos = new ArrayList<>();
    public List<Mesas> mesas = new ArrayList<Mesas>();
    public List<Estudiante> estudiantes = new ArrayList<Estudiante>();
    public List<Cursos> cursos = new ArrayList<Cursos>();
    public String[] nombresMesas = {};

    public int idCandidato = 1;
    public int idEstudiantes = 1;
    private JMenuItem mntmResultadosBarras;
    private JMenuItem mntmResultadosPastel;
    private JMenuItem mntmPadronElectoral;
    private JMenuItem mntmCrearPrefectos;
    private JMenuItem mntmBocaDeUrna;
    private BocaDeUrna bocaDeUrna;
    private JMenuItem mntmResultadosGenerales;
    private JMenuItem mntmresultadosMesa;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menuprincipal frame = new Menuprincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Menuprincipal() {

        for (String mesa : nombresMesas) {
            Mesas mesaE = new Mesas(mesa);
            mesas.add(mesaE);
        }
        setTitle("SISTEMA DE VOTO ELECTRÓNICO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setForeground(new Color(0, 64, 128));
        menuBar.setBackground(new Color(192, 191, 188));
        setJMenuBar(menuBar);

        JMenu mnArchivo = new JMenu("Archivo");
        mnArchivo.setFont(new Font("Cambria Math", Font.BOLD, 16));
        menuBar.add(mnArchivo);

        mntmSalir = new JMenuItem("Salir");
        mntmSalir.addActionListener(this);
        mntmSalir.setFont(new Font("Cambria Math", Font.BOLD, 16));
        mnArchivo.add(mntmSalir);

        JMenu mnAdministracin = new JMenu("Administración");
        mnAdministracin.setFont(new Font("Cambria Math", Font.BOLD, 16));
        menuBar.add(mnAdministracin);

        mntmMesas = new JMenuItem("Mesas");
        mntmMesas.setIcon(new ImageIcon(Menuprincipal.class.getResource("/ec/edu/puce/icon/icon5.png")));
        mntmMesas.setFont(new Font("Cambria Math", Font.BOLD, 16));
        mntmMesas.addActionListener(this);
        mnAdministracin.add(mntmMesas);

        JMenuItem mntmCursos = new JMenuItem("Cursos");
        mntmCursos.setIcon(new ImageIcon(Menuprincipal.class.getResource("/ec/edu/puce/icon/icon4.png")));
        mntmCursos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cursoVentana();
            }
        });
        mntmCursos.setFont(new Font("Cambria Math", Font.BOLD, 16));
        mnAdministracin.add(mntmCursos);

        JMenuItem mntmEstudiantes = new JMenuItem("Estudiantes");
        mntmEstudiantes.setIcon(new ImageIcon(Menuprincipal.class.getResource("/ec/edu/puce/icon/icon3.png")));
        mntmEstudiantes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                estudianteVentana();
            }
        });
        mntmEstudiantes.setFont(new Font("Cambria Math", Font.BOLD, 16));
        mnAdministracin.add(mntmEstudiantes);

        mntmCrearPrefectos = new JMenuItem("Candidatos");
        mntmCrearPrefectos.setIcon(new ImageIcon(Menuprincipal.class.getResource("/ec/edu/puce/icon/icon3.png")));
        mntmCrearPrefectos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                candidatoVentana();
            }
        });
        mntmCrearPrefectos.setFont(new Font("Cambria Math", Font.BOLD, 16));
        mnAdministracin.add(mntmCrearPrefectos);

        JMenu mnSufragar = new JMenu("Sufragar");
        mnSufragar.setFont(new Font("Cambria Math", Font.BOLD, 16));
        menuBar.add(mnSufragar);

        JMenu mnReportes = new JMenu("Reportes");
        mnReportes.setFont(new Font("Cambria Math", Font.BOLD, 16));
        menuBar.add(mnReportes);

        mntmPadronElectoral = new JMenuItem("Reportes PE");
        mntmPadronElectoral.setIcon(new ImageIcon(Menuprincipal.class.getResource("/ec/edu/puce/icon/icon6.png")));
        mntmPadronElectoral.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reporte rpe = new Reporte(mesas, cursos, estudiantes);
                desktopPane.add(rpe);
                rpe.setVisible(true);
            }
        });
        
        mntmPadronElectoral.setFont(new Font("Cambria Math", Font.BOLD, 16));
        mnReportes.add(mntmPadronElectoral);

        mntmResultadosGenerales = new JMenuItem("Resultados Generales");
        mntmResultadosGenerales.setIcon(new ImageIcon(Menuprincipal.class.getResource("/ec/edu/puce/icon/icon6.png")));
        mntmResultadosGenerales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResultadosGenerales rpe = new ResultadosGenerales(prefectos);
                desktopPane.add(rpe);
                rpe.setVisible(true);
            }
        });
        mntmResultadosGenerales.setFont(new Font("Cambria Math", Font.BOLD, 16));
        mnReportes.add(mntmResultadosGenerales);
        
        mntmresultadosMesa = new JMenuItem("Resultados por mesas");
        mntmresultadosMesa.setIcon(new ImageIcon(Menuprincipal.class.getResource("/ec/edu/puce/icon/icon6.png")));
        mntmresultadosMesa.setFont(new Font("Cambria Math", Font.BOLD, 16));
        mntmresultadosMesa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResultadosPorMesa rpe = new ResultadosPorMesa(prefectos, cursos);
                desktopPane.add(rpe);
                rpe.setVisible(true);
            }
        });
        mnReportes.add(mntmresultadosMesa);
            
      
        JMenu mnGrficos = new JMenu("Gráficos");
		mnGrficos.setBackground(new Color(0, 128, 192));
		mnGrficos.setFont(new Font("Cambria Math", Font.BOLD, 16));
		menuBar.add(mnGrficos);

		mntmResultadosBarras = new JMenuItem("Resultados Barras");
		mntmResultadosBarras.setIcon(new ImageIcon(Menuprincipal.class.getResource("/ec/edu/puce/icon/icon7.png")));
		mntmResultadosBarras.setBackground(new Color(145, 200, 255));
		mnGrficos.add(mntmResultadosBarras);
		mntmResultadosBarras.addActionListener(this);
		mntmResultadosBarras.setFont(new Font("Dialog", Font.BOLD, 16));

		mntmResultadosPastel = new JMenuItem("Resultados Pastel");
		mntmResultadosPastel.setIcon(new ImageIcon(Menuprincipal.class.getResource("/ec/edu/puce/icon/icon8.png")));
		mntmResultadosPastel.setBackground(new Color(145, 200, 255));
		mnGrficos.add(mntmResultadosPastel);
		mntmResultadosPastel.addActionListener(this);
		mntmResultadosPastel.setFont(new Font("Dialog", Font.BOLD, 16));
		contenedor = new JPanel();
		contenedor.setBackground(new Color(255, 255, 255));
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
        

        JMenu mnProceso = new JMenu("Proceso");
        mnProceso.setFont(new Font("Dialog", Font.BOLD, 16));

        mntmBocaDeUrna = new JMenuItem("Voto");
        mntmBocaDeUrna.setFont(new Font("Cambria Math", Font.BOLD, 16));
        mntmBocaDeUrna.addActionListener(this);
        mnSufragar.add(mntmBocaDeUrna);

       

		setContentPane(contenedor);
		contenedor.setLayout(new CardLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		contenedor.add(desktopPane, "name_35522358088801");

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Menuprincipal.class.getResource("/ec/edu/puce/icon/icon.png")));
        lblNewLabel.setBounds(146, 93, 472, 313);
        desktopPane.add(lblNewLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mntmSalir) {
            dispose();
        } else if (e.getSource() == mntmMesas) {
            CrearMesas crearMesa = new CrearMesas(mesas);
            desktopPane.add(crearMesa);
            crearMesa.setVisible(true);
        } else if (e.getSource() == mntmCrearPrefectos) {
            CrearPrefecto crearPrefecto = new CrearPrefecto(prefectos);
            desktopPane.add(crearPrefecto);
            crearPrefecto.setVisible(true);
        } else if (e.getSource() == mntmBocaDeUrna) {
            // Mostrar primero la ventana CedulaEstudiante
            CedulaEstudiante cedulaEstudiante = new CedulaEstudiante(estudiantes, prefectos, desktopPane);
            desktopPane.add(cedulaEstudiante);
            cedulaEstudiante.setVisible(true);
        }else if (e.getSource() == mntmResultadosBarras) {
			crearResultadosEnBarras();
		} else if (e.getSource() == mntmResultadosPastel) {
			crearResultadosEnPastel();
		}
    }
    private void ResultadosPorMesa() {
		// TODO Auto-generated method stub
		
	}

	private void crearResultadosEnBarras() {
		final JInternalFrame frame = new JInternalFrame("Resultado en Barras", true);

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Prefecto prefecto : prefectos) {
			dataset.addValue(prefecto.getVotos(), "Prefecto", prefecto.getNombre());
		}
		final JFreeChart chart = ChartFactory.createBarChart("Bar Chart", "Category", "Series", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(600, 400));

		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 400);
		panel.setLayout(new BorderLayout());
		panel.add(chartPanel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});

		panel.add(btnCancelar, BorderLayout.SOUTH);
		frame.getContentPane().add(panel);
		desktopPane.add(frame);
		frame.pack();
		frame.setVisible(true);
	}

	private void crearResultadosEnPastel() {
		final JInternalFrame frame = new JInternalFrame("Resultado en Pastel", true);

		DefaultPieDataset datos = new DefaultPieDataset();
		for (Prefecto prefecto : prefectos) {
			datos.setValue(prefecto.getNombre(), prefecto.getVotos());
		}

		JFreeChart grafico = ChartFactory.createPieChart("Prefectos", datos);
		ChartPanel chartPanel = new ChartPanel(grafico);
		chartPanel.setBounds(10, 50, 450, 350);

		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 400);
		panel.setLayout(new BorderLayout());
		panel.add(chartPanel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});

		panel.add(btnCancelar, BorderLayout.SOUTH);
		frame.getContentPane().add(panel);
		desktopPane.add(frame);
		frame.pack();
		frame.setVisible(true);
	}

    public void candidatoVentana() {
        CrearPrefecto crearPrefecto = new CrearPrefecto(prefectos);
        desktopPane.add(crearPrefecto);
        crearPrefecto.setVisible(true);
    }

    public void estudianteVentana() {
        CrearEstudiantes crearEstudiante = new CrearEstudiantes(cursos, estudiantes, idEstudiantes);
        desktopPane.add(crearEstudiante);
        crearEstudiante.setVisible(true);
    }

    public void cursoVentana() {
        CrearCursos crearCurso = new CrearCursos(mesas, cursos);
        desktopPane.add(crearCurso);
        crearCurso.setVisible(true);
    }
    public void MesaaaaVentana() {
        CrearCursos crearCurso = new CrearCursos(mesas, cursos);
        desktopPane.add(crearCurso);
        crearCurso.setVisible(true);
    }
}
