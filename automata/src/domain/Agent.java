package domain;

import java.awt.Color;
import java.io.Serializable;


public abstract class Agent implements Serializable{
    
    public final static char UNKNOWN='u', ALIVE='a', DEAD='d';
    protected char state;
    private int age;

    /**Create a new agent
     * 
     */
    public Agent(){
        state=DEAD;
        age=0;
    }


    /**The agent turns one life span old
     * 
     */
    protected void turn(){
        age++;
    }    
    
     /**Returns the age
    @return 
     */   
    public final int getAge(){
        return age;
    }
    
    public void resetAge(){
        age=0;
    }
    
    public char getState(){
        return state;
    }

    public void setState(char state){
        this.state=state;
    }

    /**Returns if alive
    @return true, if ALIVE; false, otherwise
     */
    public final boolean isAlive(){
        return (state == Agent.ALIVE) ;
    }
    
}
