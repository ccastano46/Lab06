package domain;
import java.util.*;
import java.io.File;


/*No olviden adicionar la documentacion*/
public class CellularAutomata{
    static private int LENGTH=30;
    private Item[][] automata;
    public Item[][] automataCopy;
    
    public CellularAutomata() {
        automata=new Item[LENGTH][LENGTH];
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                automata[r][c]=null;
            }
        }
        this.automataCopy=automataCopy();
    }

    public int  getLength(){
        return LENGTH;
    }

    public Item getItem(int r,int c){
        return automata[r][c];
    }
    
    public Item getItemCopy(int r,int c){
        return automataCopy[r][c];
    }

    public void setItem(int r, int c, Item e){
        automata[r][c]=e;
    }

    public void someItems(int r, int c){
        new Cell(this, r, c);
    }
    
    public void someItemsSocial(int r, int c){
        new Sociable(this, r, c);
    }
    
    public void someSensibleItems(int r, int c){
        new Sensible(this, r, c);
    }
    
    public void someItemsHeater(int r, int c){
        new Heater(this,r,c);
    }
    
    public void someItemsLightBulb(int r, int c){
        new LightBulb(this,r,c);
    }
    
    public void someItemsConway(int r, int c){
        new Conway(this,r,c);
    }
    
    public void ticTac(){
        Item[][] automataCopy = new Item[LENGTH][LENGTH];

        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                automataCopy[r][c]=this.getItem(r,c);
            }
        }
        
        this.newAutomataCopy();
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                if(automataCopy[r][c] != null){
                    //System.out.println(r+","+c);
                    automataCopy[r][c].decide();
                }else{
                    Conway newCell = new Conway(this,r,c);
                    if(newCell.contarTodosVecinos()<3){
                        newCell.deleteItem();
                    }
                }
            }
        }
        
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                if(automataCopy[r][c] != null){
                    automataCopy[r][c].change();
                }
            }
        }
        this.newAutomataCopy();
        
    }
    public Item[][] automataCopy(){
        Item[][] automataCopy = new Item[LENGTH][LENGTH];
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                automataCopy[r][c]=this.getItem(r,c);
            }
        }
        return automataCopy;
    }
    
    public void newAutomataCopy(){
        this.automataCopy=automataCopy();
    }
    
    /**
     * Metodo que identifica los vecinos del item.
     * @param, row fila del item.
     * @param column, columna del item
     * @return arreglo de Item
     */
    public Item[] getVecinos(int row, int column){
        Item[] vecinos = new Item[4];
        //Se mira vecino al norte
        try{
            if(getItemCopy(row - 1,column) != null){
                vecinos[0] = getItem(row - 1,column);
            }
        }catch(IndexOutOfBoundsException v){
            vecinos[0] = null;
        }
        //Se mira vecino al este
        try{
            if(getItemCopy(row,column + 1) != null){
                vecinos[1] = getItem(row,column + 1);
            }
        }catch(IndexOutOfBoundsException v){
            vecinos[1] = null;
        }
        //Se mira vecino al sur
        try{
            if(getItemCopy(row + 1,column) != null){
                vecinos[2] = getItem(row + 1,column);
            }
        }catch(IndexOutOfBoundsException v){
            vecinos[2] = null;
        }
        //Se mira vecino al oeste
        try{
            if(getItemCopy(row,column - 1) != null){
               vecinos[3] = getItem(row,column - 1); 
            }
        }catch(IndexOutOfBoundsException v){
            vecinos[3] = null;
        }
        return vecinos;
    }

    /**
    * Metodo para abrir un archivo(ObjectInputStream).
    * @param file , archivo que se desa abrir
    * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public void open(File file) throws AutomataException{
        throw new AutomataException(AutomataException.IN_PROCESS+"open");
    }
    /**
    * Metodo para salvar un archivo con extension de programa (ObjectOutputStream).
    * @param file , archivo que se desa abrir
    * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public void save(File file) throws AutomataException{
        throw new AutomataException(AutomataException.IN_PROCESS+"save");
    }
    /**
    * Metodo para importar un archivo (FileInputStream).
    * @param file , archivo que se desa abrir
    * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public void import_(File file) throws AutomataException{
        throw new AutomataException(AutomataException.IN_PROCESS+"import");
    }
    /**
    * Metodo para exportar un archivo con extension de texto (texto plano FileOutputStream).
    * @param file , archivo que se desa abrir
    * @throws AutomataExeption IN_PROCESS Si el metodo se esta construyendo
     */
    public void export(File file) throws AutomataException{
        throw new AutomataException(AutomataException.IN_PROCESS+"export");
    }
}
