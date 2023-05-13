package domain;

import java.util.*;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*No olviden adicionar la documentacion*/
public class CellularAutomata implements Serializable {
    private static final long serialVersionUID=6529685098267757690L;
    private static int LENGTH = 30;
    private Item[][] automata;
    public Item[][] automataCopy;

    public CellularAutomata() {
        automata = new Item[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                automata[r][c] = null;
            }
        }
        this.automataCopy = automataCopy();
    }

    public int getLength() {
        return LENGTH;
    }

    public Item getItem(int r, int c) {
        return automata[r][c];
    }

    public Item getItemCopy(int r, int c) {
        return automataCopy[r][c];
    }

    public void setItem(int r, int c, Item e) {
        automata[r][c] = e;
    }

    public void someItems(int r, int c) {
        new Cell(this, r, c);
    }

    public void someItemsSocial(int r, int c) {
        new Sociable(this, r, c);
    }

    public void someSensibleItems(int r, int c) {
        new Sensible(this, r, c);
    }

    public void someItemsHeater(int r, int c) {
        new Heater(this, r, c);
    }

    public void someItemsLightBulb(int r, int c) {
        new LightBulb(this, r, c);
    }

    public void someItemsConway(int r, int c) {
        new Conway(this, r, c);
    }

    public void ticTac() {
        Item[][] automataCopy = new Item[LENGTH][LENGTH];

        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                automataCopy[r][c] = this.getItem(r, c);
            }
        }

        this.newAutomataCopy();
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                if (automataCopy[r][c] != null) {
                    // System.out.println(r+","+c);
                    automataCopy[r][c].decide();
                } else {
                    Conway newCell = new Conway(this, r, c);
                    if (newCell.contarTodosVecinos() < 3) {
                        newCell.deleteItem();
                    }
                }
            }
        }

        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                if (automataCopy[r][c] != null) {
                    automataCopy[r][c].change();
                }
            }
        }
        this.newAutomataCopy();

    }

    public Item[][] automataCopy() {
        Item[][] automataCopy = new Item[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                automataCopy[r][c] = this.getItem(r, c);
            }
        }
        return automataCopy;
    }

    public void newAutomataCopy() {
        this.automataCopy = automataCopy();
    }

    /**
     * Metodo que identifica los vecinos del item.
     * @param, row fila del item.
     * 
     * @param column, columna del item
     * @return arreglo de Item
     */
    public Item[] getVecinos(int row, int column) {
        Item[] vecinos = new Item[4];
        // Se mira vecino al norte
        try {
            if (getItemCopy(row - 1, column) != null) {
                vecinos[0] = getItem(row - 1, column);
            }
        } catch (IndexOutOfBoundsException v) {
            vecinos[0] = null;
        }
        // Se mira vecino al este
        try {
            if (getItemCopy(row, column + 1) != null) {
                vecinos[1] = getItem(row, column + 1);
            }
        } catch (IndexOutOfBoundsException v) {
            vecinos[1] = null;
        }
        // Se mira vecino al sur
        try {
            if (getItemCopy(row + 1, column) != null) {
                vecinos[2] = getItem(row + 1, column);
            }
        } catch (IndexOutOfBoundsException v) {
            vecinos[2] = null;
        }
        // Se mira vecino al oeste
        try {
            if (getItemCopy(row, column - 1) != null) {
                vecinos[3] = getItem(row, column - 1);
            }
        } catch (IndexOutOfBoundsException v) {
            vecinos[3] = null;
        }
        return vecinos;
    }

    public void resetAutomata() {
        automata = new Item[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                automata[r][c] = null;
            }
        }
    }

    /**
     * Metodo para abrir un archivo(ObjectInputStream).
     * 
     * @param file , archivo que se desa abrir
     * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public CellularAutomata open(File file) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        CellularAutomata newAutomata = (CellularAutomata) in.readObject();
        in.close();
        return newAutomata;
    }

    /**
     * Metodo para salvar un archivo con extension de programa (ObjectOutputStream).
     * 
     * @param file , archivo que se desa abrir
     * @throws IOException
     * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public void save(File file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(this);
        out.close();
    }

    /**
     * Metodo para importar un archivo (FileInputStream).
     * 
     * @param file , archivo que se desa abrir
     * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public void import_(File file) throws IOException,AutomataException{
        PrintWriter pw = new PrintWriter(new FileOutputStream(file));
        for(int i = 0; i<automata.length;i++){
            for(int j= 0;j<automata.length;j++){
                if(automata[i][j]!=null && automata[i][j] instanceof Agent){
                    Agent agente = (Agent) automata[i][j];
                    pw.println(automata[i][j].getClass().getSimpleName() + " " + i + " " + j + " " + (agente.getState() == 'a' ? "alive" : "dead"));
                }
            }
        }
        pw.flush();
        pw.close();
    }

    /**
     * Metodo para exportar un archivo con extension de texto (texto plano
     * FileOutputStream).
     * 
     * @param file , archivo que se desa abrir
     * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public void export(File file) throws IOException,AutomataException {
        BufferedReader bIn = new BufferedReader(new FileReader(file));
        resetAutomata();
        String line = bIn.readLine();
        while (line != null) {
            line = line.trim();
            //System.out.println(line);
            String[] cell = line.split(" ");
            if (cell.length == 4) {
                int row;
                int column;

                try {
                    row = Integer.parseInt(cell[1]);
                } catch (Exception e) {
                    throw new AutomataException("'"+cell[1]+"' "+AutomataException.ROW_NOT_NUMBER);
                }
                try {
                    column = Integer.parseInt(cell[2]);
                } catch (Exception e) {
                    throw new AutomataException("'"+cell[2]+"' "+AutomataException.ROW_NOT_NUMBER);
                }
                
                if (cell[0].toLowerCase().equals("cell")) {
                    someItems(row, column);
                } else if (cell[0].toLowerCase().equals("sociable")) {
                    someItemsSocial(row, column);
                } else if (cell[0].toLowerCase().equals("sensible")) {
                    someSensibleItems(row, column);
                } else if (cell[0].toLowerCase().equals("heater")) {
                    someItemsHeater(row, column);
                } else if (cell[0].toLowerCase().equals("lightbulb")) {
                    someItemsLightBulb(row, column);
                } else if (cell[0].toLowerCase().equals("conway")) {
                    someItemsConway(row, column);
                } else {
                    throw new AutomataException(AutomataException.NO_CELL + cell[0]);
                }
                if (cell[3].toLowerCase().equals("alive")) {
                    Cell celula = (Cell) getItem(row, column);
                    celula.setState('a');
                } else if (cell[3].toLowerCase().equals("dead")) {
                    Cell celula = (Cell) getItem(row, column);
                    celula.setState('d');
                    celula.setNextStateDead();
                }else{
                    throw new AutomataException("'"+cell[2]+"' "+AutomataException.NO_VALID);
                }
            }else if(!line.equals("")){
                throw new AutomataException("'"+line+"' "+AutomataException.NO_VALID);
            }
            line = bIn.readLine();
        }
        bIn.close();
    }

    /**
     * Metodo para abrir un archivo.
     * 
     * @param file , archivo que se desa abrir
     * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public void open00(File file) throws AutomataException {
        throw new AutomataException(AutomataException.IN_PROCESS + " open");
    }

    /**
     * Metodo para salvar un archivo con extension de programa .
     * 
     * @param file , archivo que se desa abrir
     * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public void save00(File file) throws AutomataException {
        throw new AutomataException(AutomataException.IN_PROCESS + " save");
    }

    /**
     * Metodo para importar un archivo (FileInputStream).
     * 
     * @param file , archivo que se desa abrir
     * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public void import_00(File file) throws AutomataException {
        throw new AutomataException(AutomataException.IN_PROCESS + " import");
    }

    /**
     * Metodo para exportar un archivo con extension de texto (texto plano
     * FileOutputStream).
     * 
     * @param file , archivo que se desa abrir
     * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public void export00(File file) throws AutomataException {
        throw new AutomataException(AutomataException.IN_PROCESS + " export");
    }

}
