/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jose Tejada
 */
public class FloydTest {
    
    public FloydTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of FloydAlgoritmo method, of class Floyd.
     */
    @Test
    public void testFloydAlgoritmo() {
        System.out.println("FloydAlgoritmo");
        long[][] matriz =  new long[0][0];
        ArrayList<String> ciudad = new ArrayList<>();
        ciudad.add("Guatemala");
        String origen = "";
        String destino = "";
        Floyd instance = new Floyd();
        String result = instance.FloydAlgoritmo(matriz, ciudad, origen, destino);
    }

    /**
     * Test of RutaReciente method, of class Floyd.
     */
    @Test
    public void testRutaReciente() {
        long[][] matriz =  new long[0][0];
        ArrayList<String> ciudad = new ArrayList<>();
        ciudad.add("Guatemala");
        String origen = "";
        String destino = "";
        Floyd instance = new Floyd();
        String result = instance.FloydAlgoritmo(matriz, ciudad, origen, destino);
        
        System.out.println("RutaReciente");
        int i = 0;
        int k = 0;
        String[][] CA = new String[1][1];
        CA[0][0]="";
        String CR = "";
        Floyd instance2 = new Floyd();
        String result2 = instance2.RutaReciente(i, k, CA, CR);  
    }

    /**
     * Test of centroGrafo method, of class Floyd.
     */
    @Test
    public void testCentroGrafo() {
        System.out.println("centroGrafo");
        long[][] matriz = new long[0][0];
        Floyd instance = new Floyd();
        int result = instance.centroGrafo(matriz);
        
      
    }

    /**
     * Test of CheckExistencia method, of class Floyd.
     */
    @Test
    public void testCheckExistencia() {
        System.out.println("CheckExistencia");
        String origen = "";
        String destino = "";
        ArrayList<String> ciudad =  new ArrayList<>();
        ciudad.add("Guatemala");
        Floyd instance = new Floyd();
        instance.CheckExistencia(origen, destino, ciudad);   
    }

    /**
     * Test of crearLista method, of class Floyd.
     */
    @Test
    public void testCrearLista() throws Exception {
        System.out.println("crearLista");
        Floyd instance = new Floyd();
        ArrayList<Ruta> result = instance.crearLista();   
    }
    
}
