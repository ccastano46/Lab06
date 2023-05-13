package domain;
import java.awt.Color;

/**
 * Write a description of class Calentador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Heater extends Agent implements Item
{
    protected char nextState;
    protected Color color;
    private CellularAutomata automata;
    private int row,column;
    
    public Heater(CellularAutomata ac,int row, int column){
        automata=ac;
        this.row=row;
        this.column=column;
        setColor();
        nextState=Agent.DEAD;
        automata.setItem(row,column,(Item)this);
        turn();
    }
    
    public final int shape(){
        if(state == Agent.ALIVE){
            return Item.SQUARE;
        }
        return Item.ROUND;
    }
    
    private void setColor(){
        if(state == Agent.ALIVE){
            color = Color.orange;
        }else{
            color = Color.gray;
        }
    }
    
    public final Color getColor(){
        return color;
    }
    
    public void decide(){
        if(getAge()>=5 && state == Agent.DEAD){
            nextState=Agent.ALIVE;
            resetAge();
        }else if(getAge()>=10 && state == Agent.ALIVE){
            nextState=Agent.DEAD;
            resetAge();
        }
    }
    
    public void change(){
        turn();
        state=nextState;
        setColor();
    }
}
