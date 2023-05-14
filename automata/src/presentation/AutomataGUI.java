package presentation;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AutomataGUI extends JFrame {
    public static final int SIDE = 21;
    public static final int SIZE = 31;

    private CellularAutomata automata;

    /** Board */
    private JButton buttonTicTac;
    private JPanel panelControl;
    private PhotoAutomata photo;

    /** Menu */
    private JMenuBar menuBar;
    private JFileChooser selectorArchivos;
    private JMenuItem nuevo, abrir, guardarComo, importar, exportarComo, salir;

    private AutomataGUI() {
        automata = new CellularAutomata();
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setTitle("Automata celular");
        prepareElementsMenu();
        photo = new PhotoAutomata(this);
        buttonTicTac = new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo, BorderLayout.NORTH);
        add(buttonTicTac, BorderLayout.SOUTH);
        setSize(new Dimension(SIDE * SIZE, SIDE * SIZE + 50));
        setResizable(false);
        photo.repaint();
    }

    private void prepareElementsMenu() {
        // Se instancia el JMenuBar y se le asigna al Frame
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        // Declaramos e instanciamos el objeto de la clase JMenu y le aniadimos sus
        // items
        JMenu menu = new JMenu("Menu");
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        guardarComo = new JMenuItem("Guardar como");
        importar = new JMenuItem("Importar");
        exportarComo = new JMenuItem("Exportar como");
        salir = new JMenuItem("Salir");
        menu.add(nuevo);
        menu.add(abrir);
        menu.add(guardarComo);
        menu.add(importar);
        menu.add(exportarComo);
        menu.add(salir);
        menuBar.add(menu);
        selectorArchivos = new JFileChooser();
    }

    private void prepareActions() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buttonTicTac.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonTicTacAction();
                    }
                });
        prepareActionsMenu();
    }

    /**
     * Metodo que implementa los metodos necesarios que toman lugar cuando sucede un
     * evento por parte de los componentes del menu.
     */
    private void prepareActionsMenu() {
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int siNo = JOptionPane.showConfirmDialog(AutomataGUI.this, "¿Esta seguro de terminar el juego?");
                if (siNo == 0) {
                    dispose();
                    System.exit(0);
                }
            }
        });
        abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int option = selectorArchivos.showOpenDialog(AutomataGUI.this);
                    if (option == 0) {
                        automata = automata.open(selectorArchivos.getSelectedFile());
                        photo.repaint();
                    }
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                    JOptionPane.showMessageDialog(AutomataGUI.this, "Se ha producido un error ");
                }
            }
        });
        guardarComo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int option = selectorArchivos.showSaveDialog(AutomataGUI.this);
                    if (option == 0)
                        automata.save(selectorArchivos.getSelectedFile());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(AutomataGUI.this, "Se ha producido un error ");

                }
            }
        });
        importar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int option = selectorArchivos.showOpenDialog(AutomataGUI.this);
                    if (option == 0) {
                        automata.import_01(selectorArchivos.getSelectedFile());
                        photo.repaint();
                    }
                } catch (Exception e1) {
                    try {
                        automata.import_02(selectorArchivos.getSelectedFile());
                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(AutomataGUI.this, e2.getMessage());
                    }
                    
                    JOptionPane.showMessageDialog(AutomataGUI.this, e1.getMessage());
                }
            }
        });
        exportarComo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int option = selectorArchivos.showSaveDialog(AutomataGUI.this);
                    if (option == 0) {
                        automata.export01(selectorArchivos.getSelectedFile());
                        photo.repaint();
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(AutomataGUI.this, e1.getMessage());
                    photo.repaint();
                }
            }
        });
        nuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                automata = new CellularAutomata();
                photo.repaint();
            }
        });
    }

    private void buttonTicTacAction() {
        automata.ticTac();
        photo.repaint();
    }

    public CellularAutomata getAutomata() {
        return automata;
    }

    public static void main(String[] args) {
        AutomataGUI ca = new AutomataGUI();
        ca.setVisible(true);
    }
}

class PhotoAutomata extends JPanel {
    private AutomataGUI gui;

    public PhotoAutomata(AutomataGUI gui) {
        this.gui = gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE * gui.SIZE, gui.SIDE * gui.SIZE));
    }

    public void paintComponent(Graphics g) {
        CellularAutomata automata = gui.getAutomata();
        super.paintComponent(g);

        for (int f = 0; f <= automata.getLength(); f++) {
            g.drawLine(f * gui.SIDE, 0, f * gui.SIDE, automata.getLength() * gui.SIDE);
        }
        for (int c = 0; c <= automata.getLength(); c++) {
            g.drawLine(0, c * gui.SIDE, automata.getLength() * gui.SIDE, c * gui.SIDE);
        }
        for (int f = 0; f < automata.getLength(); f++) {
            for (int c = 0; c < automata.getLength(); c++) {
                if (automata.getItem(f, c) != null) {
                    g.setColor(automata.getItem(f, c).getColor());
                    if (automata.getItem(f, c).shape() == Item.SQUARE) {
                        if (automata.getItem(f, c).isAlive()) {
                            g.fillRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        } else {
                            g.drawRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        }
                    } else {
                        if (automata.getItem(f, c).isAlive()) {
                            g.fillOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        } else {
                            g.drawOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        }
                    }
                }
            }
        }
    }
}