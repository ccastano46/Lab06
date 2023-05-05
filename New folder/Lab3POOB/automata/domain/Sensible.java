package domain;
import java.awt.Color;

/**
 * Write a description of class Sensible here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sensible extends Cell
{
    
    public Sensible(CellularAutomata ac,int row, int column){
        super(ac,row,column);
        color = Color.blue;
    }
    
    
    public void decide() {
        CellularAutomata au=this.getAutomata();
        int row = getRow();
        int column = getColumn();
        if(isAlive()){
            if(contarVecinosMuertos() > 0){
                nextState=Agent.DEAD;
            }
            if(getAge()>=50){
                nextState=Agent.DEAD;
            }
            if(au.getVecinos(row,column)[2] != null
            &&
            (au.getVecinos(row,column)[2] instanceof Sociable) 
 
            && 
            au.getVecinos(row,column)[2].isAlive()
            && 
            ((getAge())%5 == 0) 
            &&
            au.getItem(row + 1,column + 1) == null){
                au.someItems(row + 1, column + 1);
            }
        }
    }
    

}
