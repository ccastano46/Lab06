package domain;
import java.awt.Color;

/**
 * Write a description of class Bulb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public final class LightBulb implements Item
{
    private  final int  on = 0, off = 1;
    private int state;
    private int nextState;
    private Color color;
    private CellularAutomata automata;
    private int row,column;
    
    /**
     * Constructor de bombillo
     */
    
    public LightBulb(CellularAutomata ac, int row, int column){
        automata = ac;
        state = off;
        nextState = on;
        color = Color.cyan;
        this.row = row;
        this.column = column;
        automata.setItem(row,column,(Item)this);
    }
    
    /**Returns the shape
    @return Item.ROUND
     */
    public final int shape(){
        return Item.ROUND;
    }
    
    
    /**Returns the color
    @return color
     */
    
    public final Color getColor(){
        return color;
    }
    
    /**
     * Metodo que ajusta el color de acuerdo si esta prendido o apagado.
     * Si esta prendido, amarillo.
     * Apagado, cyan
     */
    private void setColor(){
        if(isAlive()){
            color = Color.yellow;
        }else{
            color = Color.cyan;
        }
    }
    
    /**
     * Función que indica si el bombillo esta prendido o apagado
     */
    public final boolean isAlive(){
      if(state == on){
            return true;
        }
        return false;
  }
  
  /**
   * Metodo que cambia el estado del bombillo
   */
  public final void change(){
      if(isAlive()){
          state = off;
          nextState = on;
      }else{
          state = on;
          nextState = off;
      }
      setColor();
  }
  
  /**
   *Se decide el estado del bombillo
   *Si todos sus vecinos estan vivos o si no tiene ningun vecino, esta vivo.
     */
    public void decide(){
        Item[] vecinos = automata.getVecinos(row,column);
        boolean sePuedePrender = true;
        for(int i = 0; i < 4; i++){
            if(vecinos[i] != null && vecinos[i].isAlive()) sePuedePrender = true;
            else sePuedePrender = false;
            if(!sePuedePrender){
              state = off;  
              break;  
            }
        }
        if(sePuedePrender) state = on;
        change();
    }
  
    
}
