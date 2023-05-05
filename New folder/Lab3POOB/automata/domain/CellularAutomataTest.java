package domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

/**
 * Write a description of class CellularAutomataTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CellularAutomataTest
{
   @Test
   public void shouldTicTac(){
       CellularAutomata auto = new CellularAutomata();
       Cell indiana = new Cell(auto,1,1);
       Cell ceroCeroSiete = new Cell(auto,2,2);
       doTicTac(auto,3);
       assertTrue(indiana.isAlive());
       assertTrue(ceroCeroSiete.isAlive());
   }
   
   @Test
   public void shouldSociableDo(){
       CellularAutomata auto = new CellularAutomata();
       Sociable agamenon = new Sociable(auto,1,2);
       Sociable venus = new Sociable(auto,2,2);
       doTicTac(auto,3);
       assertTrue(agamenon.isAlive());
       assertTrue(venus.isAlive());
       doTicTac(auto,8);
       
       assertTrue(auto.getItem(1,1).isAlive());
       
       assertTrue(auto.getItem(2,2).isAlive());
       doTicTac(auto,70);
       assertFalse(agamenon.isAlive());
   }
   
   @Test
   public void shouldHeaterDo(){
       CellularAutomata auto = new CellularAutomata();
       Heater suroeste = new Heater(auto,28,1);
       Heater noreste = new Heater(auto,1,28);
       doTicTac(auto,5);
       assertEquals(suroeste.getColor(),Color.orange);
       assertTrue(suroeste.isAlive());
       assertTrue(noreste.isAlive());
       doTicTac(auto,10);
       assertEquals(suroeste.getColor(),Color.gray);
       assertFalse(suroeste.isAlive());
       assertFalse(noreste.isAlive());
   }
   
   
   
   public void doTicTac(CellularAutomata au,int times){
       for(int i =0;i<times;i++){
           au.ticTac();
       }
   }
   
   @Test
   public void SensibleShouldCreate(){
       CellularAutomata auto = new CellularAutomata();
       Sensible Camilo = new Sensible(auto,1,2);
       Sociable profesor = new Sociable(auto,2,2);
       Sensible Juan = new Sensible(auto,0,0);
       Sociable profesora = new Sociable(auto,1,0);
       doTicTac(auto,5);
       assertTrue(auto.getItem(2,3) != null && auto.getItem(2,3) instanceof Cell && !(auto.getItem(2,3) instanceof Sociable || auto.getItem(2,3) instanceof Sensible));
       assertTrue(auto.getItem(1,1) != null && auto.getItem(1,1) instanceof Cell && !(auto.getItem(1,1) instanceof Sociable || auto.getItem(1,1) instanceof Sensible));
   }
   
   @Test
   public void BulbShouldLive(){
       CellularAutomata auto = new CellularAutomata();
       LightBulb Maria = new LightBulb(auto,0,0);
       auto.ticTac();
       assertTrue(Maria.isAlive());
       assertEquals(Maria.getColor(), Color.yellow);
       LightBulb Jose = new LightBulb(auto,5,5);
       Cell Luis = new Cell(auto,5,6);
       assertFalse(Jose.isAlive());
       assertEquals(Jose.getColor(), Color.cyan);
   }
   
   @Test
   public void shouldConwayDo(){
       CellularAutomata auto = new CellularAutomata();
       Conway jhon = new Conway(auto,4,10);
       Conway horton = new Conway(auto,4,11);
       doTicTac(auto,3);
       assertFalse(jhon.isAlive());
       assertFalse(horton.isAlive());
       CellularAutomata autoBloque = new CellularAutomata();
       Conway m = new Conway(autoBloque,26,1);
       Conway i = new Conway(autoBloque,26,2);
       Conway n = new Conway(autoBloque,27,1);
       Conway e = new Conway(autoBloque,27,2);
       doTicTac(autoBloque,20);
       assertTrue(m.isAlive());
       assertTrue(i.isAlive());
       assertTrue(n.isAlive());
       assertTrue(e.isAlive());
       CellularAutomata autoParpadeador = new CellularAutomata();
       Conway jet1 = new Conway(autoParpadeador,7,7);
       Conway jet2 = new Conway(autoParpadeador,7,8);
       Conway jet3 = new Conway(autoParpadeador,7,9);
       doTicTac(autoParpadeador,2);
       assertTrue(jet1.isAlive());
       assertTrue(jet1.isAlive());
       assertTrue(jet1.isAlive());
    
   }
}
