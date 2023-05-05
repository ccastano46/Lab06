package domain;

import java.awt.Color;

/**Information about a cell<br>
<b>(automata,row,column,age,state,nextState, color)</b><br>
<br>
 */
public class Cell extends Agent implements Item{
    protected char nextState;
    protected Color color;
    private CellularAutomata automata;
    private int row,column;


    /**Create a new cell (<b>row,column</b>) in the automata <b>ac</b>.
     * Every new cell is going to be alive in the following state.
     * @param ac 
     * @param row 
     * @param column 
     */
    public Cell(CellularAutomata ac,int row, int column){
        automata=ac;
        this.row=row;
        this.column=column;
        nextState=Agent.ALIVE;
        automata.setItem(row,column,(Item)this);    
        color=Color.red;
    }
    
    public final void deleteItem(){
        automata.setItem(row,column,null);
    }
    
    /**Returns the shape
    @return 
     */
    public final int shape(){
        return Item.ROUND;
    }
    
    /**Returns the row
    @return 
     */
    public final int getRow(){
        return row;
    }

    /**Returns tha column
    @return 
     */
    public final int getColumn(){
        return column;
    }
    
    public CellularAutomata getAutomata(){
        return automata;
    }

    /**Returns the color
    @return 
     */
    public final Color getColor(){
        return color;
    }


    /**Decide its next state
     */
    public void decide(){
        if (getAge()>=3){
            nextState=Agent.DEAD;
        }   
    }

    /**Change its actual state
     */
    public final void change(){
        turn();
        state=nextState;
    }
    
    public int contarVecinos(){
        int cont = 0;
        int lenght = automata.getLength();
        //norte
        if(automata.getItemCopy((row+lenght-1)%lenght,(column+lenght)%lenght)!=null && automata.getItemCopy((row+lenght-1)%lenght,(column+lenght)%lenght).isAlive() && automata.getItemCopy((row+lenght-1)%lenght,(column+lenght)%lenght) instanceof Cell ) cont++;
        //System.out.println(cont);
        //sur
        if(automata.getItemCopy((row+lenght+1)%lenght,(column+lenght)%lenght)!=null && automata.getItemCopy((row+lenght+1)%lenght,(column+lenght)%lenght).isAlive() && automata.getItemCopy((row+lenght+1)%lenght,(column+lenght)%lenght) instanceof Cell) cont++;
        //System.out.println(cont);
        //este
        if(automata.getItemCopy((row+lenght)%lenght,(column+lenght+1)%lenght)!=null && automata.getItemCopy((row+lenght)%lenght,(column+lenght+1)%lenght).isAlive() && automata.getItemCopy((row+lenght)%lenght,(column+lenght+1)%lenght) instanceof Cell) cont++;
        //System.out.println(cont);
        //oeste
        if(automata.getItemCopy((row+lenght)%lenght,(column+lenght-1)%lenght)!=null && automata.getItemCopy((row+lenght)%lenght,(column+lenght-1)%lenght).isAlive() && automata.getItemCopy((row+lenght)%lenght,(column+lenght-1)%lenght) instanceof Cell) cont++;
        //System.out.println(cont+"bruh");
        return cont;
    }
    
    public int contarVecinosMuertos(){
        int cont = 0;
        int lenght = automata.getLength();
        //norte
        if(automata.getItemCopy((row+lenght-1)%lenght,(column+lenght)%lenght)!=null && !automata.getItemCopy((row+lenght-1)%lenght,(column+lenght)%lenght).isAlive() && automata.getItemCopy((row+lenght-1)%lenght,(column+lenght)%lenght) instanceof Cell ) cont++;
        //System.out.println(cont);
        //sur
        if(automata.getItemCopy((row+lenght+1)%lenght,(column+lenght)%lenght)!=null && !automata.getItemCopy((row+lenght+1)%lenght,(column+lenght)%lenght).isAlive() && automata.getItemCopy((row+lenght+1)%lenght,(column+lenght)%lenght) instanceof Cell) cont++;
        //System.out.println(cont);
        //este
        if(automata.getItemCopy((row+lenght)%lenght,(column+lenght+1)%lenght)!=null && !automata.getItemCopy((row+lenght)%lenght,(column+lenght+1)%lenght).isAlive() && automata.getItemCopy((row+lenght)%lenght,(column+lenght+1)%lenght) instanceof Cell) cont++;
        //System.out.println(cont);
        //oeste
        if(automata.getItemCopy((row+lenght)%lenght,(column+lenght-1)%lenght)!=null && !automata.getItemCopy((row+lenght)%lenght,(column+lenght-1)%lenght).isAlive() && automata.getItemCopy((row+lenght)%lenght,(column+lenght-1)%lenght) instanceof Cell) cont++;
        //System.out.println(cont);
        return cont;
    }
    
    public int contarTodosVecinos(){
        int cont = 0;
        int lenght = automata.getLength();
        for(int i = -1; i<2; i++){
            for(int j = -1; j<2; j++){
               if((i!=0 || j!=0) && automata.getItemCopy((row+lenght+i)%lenght,(column+lenght+j)%lenght)!=null && automata.getItemCopy((row+lenght+i)%lenght,(column+lenght+j)%lenght).isAlive() && automata.getItemCopy((row+lenght+i)%lenght,(column+lenght+j)%lenght) instanceof Cell){
                 //System.out.println(i+"en"+j);
                   cont++;  
               }
            }
        }
        //System.out.println(cont);
        return cont;
    }
    
}
