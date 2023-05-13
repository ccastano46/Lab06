package domain;

import java.io.Serializable;

/**
 * Write a description of class Sociable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sociable extends Cell implements Serializable
{
    private static final long serialVersionUID=6529685098267757690L;
    public Sociable(CellularAutomata ac,int row, int column){
        super(ac,row,column);
    }
    
    public void decide(){
        CellularAutomata au=this.getAutomata();
        if(isAlive()){
            //contarTodosVecinos();
            //System.out.println("Decidiendo");
            if(contarVecinos() < 2){
                //System.out.println("muerte vecinos");
                nextState=Agent.DEAD;
            }
            if(getAge()>=80){
                //System.out.println("muerte edad");
                nextState=Agent.DEAD;
            }
            if(getColumn()+1<au.getLength()  
            && getAge()%9==0 
            && au.getItemCopy((getRow()+au.getLength())%au.getLength(),(getColumn()+au.getLength()+1)%au.getLength())==null){
                //System.out.println("celula normal");
                au.someItems(getRow(),getColumn()+1);
            }
            if(getColumn()-1>=0 
            && getAge()%9==0 
            && au.getItemCopy((getRow()+au.getLength())%au.getLength(),(getColumn()+au.getLength()-1)%au.getLength())==null){
                au.someItemsSocial(getRow(),getColumn()-1);
            }
        }
    }
}
