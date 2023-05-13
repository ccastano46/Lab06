package domain;

import java.awt.Color;
import java.io.Serializable;

/**
 * Write a description of class Conway here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Conway extends Cell implements Serializable
{
    private static final long serialVersionUID=6529685098267757690L;
    /**
     * Contructor de la cèlula Conway.
     */
    public Conway(CellularAutomata ac,int row, int column){
        super(ac,row,column);
        state=Agent.ALIVE;
        color = Color.blue;
    }
    
    /**
     * Metodo en el cual la celula decide su proximo estado
     */
    public void decide(){
        CellularAutomata au=this.getAutomata();
        int cuenta = contarTodosVecinos();
        //System.out.println(cuenta);
        //Una célula muerta con exactamente 3 células vecinas vivas "revive" (al tiempo siguiente estará viva).
        if(!this.isAlive() && cuenta==3) {
            //System.out.println("revive");
            nextState = Agent.ALIVE;
        }
        //Una célula viva con 2 ó 3 células vecinas vivas sigue viva
        if(this.isAlive() && (cuenta == 2 || cuenta == 3)){
            //System.out.println("vive");
           nextState = Agent.ALIVE; 
        }
        //Si la célula tiene menos de dos o más de tres vecinas vivas muere o permanece muerta por "soledad" o superpoblación".
        if(this.isAlive() && (cuenta < 2 || cuenta >3)){
            //System.out.println("muere");
           nextState = Agent.DEAD; 
        }
    }
}
