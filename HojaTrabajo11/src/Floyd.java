/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase de Floyd
 * @version 1.0
 * Esta es la clase de Floyd
 * @author David Soto 17551 & Alejandro Tejada 17584
 */

public class Floyd {

 
    public String FloydAlgoritmo(long[][] matriz, ArrayList<String> ciudad, String origen, String destino){
        int VER = matriz.length;
        long MA[][] = matriz;
        
        int R1 = ciudad.indexOf(origen);
        int R2 = ciudad.indexOf(destino);
        
        String CAM[][] = new String[VER][VER];
        String CA[][] = new String[VER][VER];
        String CR = "";
        String cadena = "", ruta = "";
        
        int i, j, k;
        float t1, t2, t3, t4, minimo;
        
        for(i = 0; i < VER; i++){
            for(j = 0; j < VER; j++){
                CAM[i][j] = "";
                CA[i][j] = "";
            }
        }
        
        for (k = 0; k < VER; k++) {
            for (i = 0; i < VER; i++) {
                for (j = 0; j < VER; j++) {
                    t1 = MA[i][j];
                    t2 = MA[i][k];
                    t3 = MA[k][j];
                    t4 = t2 + t3;
                    
                    minimo = Math.min(t1, t4);
                    if(t1 != t4){
                        if(minimo == t4){
                            CR = "";
                            CA[i][j] = k + "";
                            CAM[i][j] = RutaReciente(i, k, CA, CR) + (k + 1);                            
                        }
                    }
                    MA[i][j] = (long) minimo;
                }
            }
        }
        
        ArrayList<Integer> rutaA = new ArrayList<>();
        
        for (i = 0; i < VER; i++) {
            for (j = 0; j < VER; j++) {
                if(MA[i][j] != 1000000000){
                    if(i != j){
                        if(CAM[i][j].equals("") && (j == R2) && (i == R1)){
                            ruta += "De " + ciudad.get(i) + " ---> " + ciudad.get(j) + " debe irse por: " + ciudad.get(i) + ", " + ciudad.get(j) + "\n";
                        }else if(!CAM[i][j].equals("") && (j == R2) && (i == R1)){
                            String texto = CAM[i][j];
                            if(!texto.contains(",")){
                                rutaA.add(Integer.parseInt(texto));
                            }
                            while(texto.contains(",")){
                                String walk = texto.substring(0, texto.indexOf(","));
                                texto = texto.substring(texto.indexOf(",") + 2);
                                rutaA.add(Integer.parseInt(walk));
                                if(!texto.contains(",")){
                                    rutaA.add(Integer.parseInt(texto));
                                }
                            }
                            
                            String c = "";
                            for(Integer in: rutaA){
                                
                                c += ciudad.get(in - 1) + ", ";
                            }
                            ruta += "De " + ciudad.get(i) + " ---> " + ciudad.get(j) + " deve viajar por: " + ciudad.get(i) + ", " + c + ciudad.get(j) + "\n";
                        }
                    }
                }
            }
        }
        return "\n" + ruta;
    }
    
    
    public String RutaReciente(int i, int k, String[][] CA, String CR){
        if(CA[i][k].equals("")){
            return "";
        }else{
            CR += RutaReciente(i, Integer.parseInt(CA[i][k]), CA, CR) + (Integer.parseInt(CA[i][k]) + 1) + ", ";
            return CR;
        }
    }
    
    public long[][] crearMatriz(ArrayList<String> ciudad, ArrayList<Ruta> city){
        long matriz[][] = new long[ciudad.size()][ciudad.size()];
        
        for (int i = 0; i < ciudad.size(); i++) {
            for (int j = 0; j < ciudad.size(); j++) {
                if(i == j){
                    matriz[i][j] = 0;
                }else{
                    String origen = ciudad.get(i);
                    String destino = ciudad.get(j);
                    int distancia = 999999999;
                    for(Ruta c: city){
                        if(origen.equals(c.getCiudad1()) && destino.equals(c.getCiudad2())){
                            distancia = c.getDistancia();
                        }
                    }
                    matriz[i][j] = distancia;
                }
            }
        }
        
        return matriz;
    }

    public int centroGrafo(long[][] matriz){
        ArrayList<Long> suma = new ArrayList<>();
        ArrayList<Long> maximo = new ArrayList<>();
        long max;
        int cont = 0;
        
        while(cont != matriz.length){
            max = 0;
            for (int i = 0; i < matriz.length; i++) {
                suma.add(matriz[i][cont]);
            }
            for(Long l: suma){
                if((l <= 9999999) && (l != 0)){
                    if(l > max){
                        max = l;
                    }
                }
            }
            maximo.add(max);
            suma.clear();
            cont++;
        }
        
        
        max = 0;
        cont = 0;
        for(Long l: maximo){
            if(l > max){
                max = l;
            }
        }
        int pos = maximo.indexOf(max);
        
        long min = max;
        
        for (int i = 0; i < matriz.length; i++) {
            if((matriz[i][pos]<=999999) && (matriz[i][pos]!=0)){
                if(matriz[i][pos] < min){
                    min = matriz[i][pos];
                }
            }
        }
        
        int resultado = 0;
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[i][pos] == min){
                resultado = i;
                break;
            }
        }
        
        return resultado;
    }
     public boolean CheckExistencia(String origen, String destino, ArrayList<String> ciudad){
        boolean existencia;
        existencia = ciudad.contains(origen) && ciudad.contains(destino);
        return existencia;
    }
     
    public ArrayList<Ruta> crearLista() throws FileNotFoundException, IOException{
        ArrayList<Ruta> ciudad = new ArrayList<>();
        File archivo = new File ("guategrafo.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br1 = new BufferedReader(fr);
        String linea = "";
        Scanner scanner = new Scanner(fr);
        
        String ciudad1, ciudad2;
        int distancia;
        int contador = 0;
        while (scanner.hasNextLine()) {
            linea = scanner.nextLine();
            
            ciudad1 = linea.substring(0, linea.indexOf(" "));
            linea = linea.substring(linea.indexOf(" ") + 1, linea.length());
            
            ciudad2 = linea.substring(0, linea.indexOf(" "));
            linea = linea.substring(linea.indexOf(" ") + 1, linea.length());
            
            distancia = Integer.parseInt(linea.substring(0, linea.length()));

            contador++;
            
            ciudad.add(new Ruta(ciudad1, ciudad2, distancia));
            
            fr.close();
            br1.close();
        }
        
        return ciudad;
    }
}
