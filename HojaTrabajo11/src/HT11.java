/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Principal 
 * @version 1.0
 * Este es el main de la hoja de trabajo 11
 * @author David Soto 17551  Alejandro Tejada 17584
 */
public class HT11 {
    
    public static void main(String[] args) throws IOException {
        //Se declaran los valores de vectores de ArrayList para el uso
        File archivo = new File ("guategrafo.txt");
        ArrayList<Ruta> ciudad = new ArrayList<>();
        ArrayList<String> cabecera = new ArrayList<>();
        Ruta Conexion = new Ruta();
        //Leemos el archivo de text
        FileReader fr = new FileReader(archivo);
        BufferedReader br1 = new BufferedReader(fr);
        String linea = " ";
        Scanner scanner = new Scanner(fr);
  
        String city1,city2;
        int distancia,contador = 0;
        //Variables de Floyd
        Scanner entrada = new Scanner(System.in);
        int seleccion;
        
        Floyd floyd = new Floyd();
        long matriz[][];
        
        while (scanner.hasNextLine()) {
           //-------------------Lectura de archivo 
            linea = scanner.nextLine();
            city1 = linea.substring(0, linea.indexOf(" "));
            linea = linea.substring(linea.indexOf(" ") + 1, linea.length());
            city2 = linea.substring(0, linea.indexOf(" "));
            linea = linea.substring(linea.indexOf(" ") + 1, linea.length());
            distancia = Integer.parseInt(linea.substring(0, linea.length()));
           //------------Fin Lectura Archivo
            contador++;
            ciudad.add(new Ruta(city1, city2, distancia));
            fr.close();//Cerramos la conexion
            br1.close();
        }
       
        
        
        OUTER:
        while (true) {
            System.out.println("1) Ruta más Corta");
            System.out.println("2) Alterar Grafo");
            System.out.println("3) Centro del Grafo");
            System.out.println("4) Salir");
            seleccion = entrada.nextInt();
            //Hcemos un case
            switch (seleccion) {
                case 1:
                    System.out.println("Ingrese la Ciudad-Origen");
                    String o = entrada.nextLine();
                    o = entrada.nextLine();
                    System.out.println("Ingrese la Ciudad-Destino");
                    String d = entrada.nextLine();
                    
                    cabecera.clear();
                    cabecera = Conexion.crearLista(ciudad);
                    matriz = floyd.crearMatriz(cabecera, ciudad);
                    if(floyd.CheckExistencia(o, d, cabecera)){
                        
                        System.out.println(floyd.FloydAlgoritmo(matriz, cabecera, o, d));
                    }else{
                        System.out.println("Las ciudades ingresadas no están conectadas por Grafo.");
                    }
                    break;
                case 3:
                    cabecera.clear();
                    cabecera = Conexion.crearLista(ciudad);
                    matriz = floyd.crearMatriz(cabecera, ciudad);
                    floyd.centroGrafo(matriz);
                    
                    System.out.println("La ciudad del centro del Grafo es:.... " + cabecera.get(floyd.centroGrafo(matriz)));
                    
                    break;
                case 2:
                    String seleccion2 = "";
                    System.out.println("a) La razon es que...¿Hay tráfico entre las dos ciudades?....");
                    System.out.println("b) La razón es que...¿Quiere crear una nueva conexion?....");
                    seleccion2 = entrada.next();
                    //Convertimos a Mayúsculas
                    if("a".equals(seleccion2.toLowerCase())){
                        System.out.println("Ingrese la Ciudad-Origen");
                        String ori = entrada.next();
                        System.out.println("Ingrese la Ciudad-Destino");
                        String dest = entrada.next();
                        
                        boolean hubo = false;
                        for(Ruta c: ciudad){
                            if((c.getCiudad1().equals(ori)) && (c.getCiudad2().equals(dest))){
                                ciudad.remove(c);
                                hubo = true;
                                System.out.println("se comprobó y ya se colocó la interrupcion.");
                                break;
                            }
                        }
                        if(hubo == false){
                            System.out.println("No se encontraron las ciudades, revise su ingreso.");
                        }
                    }else if("b".equals(seleccion2.toLowerCase())){
                        System.out.println("Ingrese la Ciudad-Origen");
                        String ori = entrada.next();
                        System.out.println("Ingrese la Ciudad-Destino");
                        String dest = entrada.next();
                        System.out.println("Ingrese la distancia entre " + ori + " y " + dest);
                        int dist = entrada.nextInt();
                        
                        ciudad.add(new Ruta(ori, dest, dist));
                        System.out.println("Se agregaron las ciudades y su respectiva distancia de forma correcta.");
                    }
                    
                    break;
                case 4:
                    break OUTER;
                default:
                    break;
            }
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" Gracias por intentarlo, hagalo de nuevo..............");
            
        }
    }
}
