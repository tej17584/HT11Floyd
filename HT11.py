#Hoja Trabajo 11
#David Soto / Alejandro Tejada
#Programa para la aplicacion del algoritmo de Floyd para encontrar la ruta mas corta entre dos puntos y el centro del grafo

from FuncionesHT11 import *

desicion = True
opcion = 0
opcion2 = 0

#------------------El programa inicia creando el grafo----------------------

crearGrafo()

#----------------Se hace el ciclo del menu------------------
while (desicion):
    #-----------Se imprime el menu de opciones para el usuario------------#
    opcion = input("\nEl menu de opciones para este Grafo es:\n1.Mostrar ruta mas corta entre dos ciudades\n2.Mostra el nombre de la ciudad en el centro del grafo\n3.Modificar el grafo\n4.Salir del programa\nIngrese el numero de opcion que desee: ")
    
    #------------Se muestra la ruta mas corta entre 2 ciudades--------------
    if (opcion==1):
        #--------Se verifica que las ciudades que ingrese el usuario existan en el grafo--------------
        ciudad1,ciudad2 = buscarCiudadesEnGrafo()
        #----------Se busca la ruta mas corta-------------------------
        rutaMasCorta(ciudad1,ciudad2)

    #-----------Se muestra el centro del grafo--------------------
    elif (opcion==2):
        centroGrafo();        

    #-----------Se muestran las opciones para modificar el grafo--------------
    elif (opcion==3):

        opcion2 = input("\n1.Hay trafico entre un par de ciudades\n2.Establecer una conexion entre un par de ciudades\nIngrese el numero de opcion que desee: ")    
        #-------Se crea una interrupcion entre 2 ciudades si existe relacion---------
        if (opcion2 == 1):
            ciudad1,ciudad2 = buscarCiudadesEnGrafo()
            indicarTrafico(ciudad1, ciudad2)
        #--------Se crea una relacion entre 2 ciudades para el grafo-------------    
        elif (opcion2 == 2):
            ciudad1 = raw_input("\nIngrese el nombre de la ciudad de origen: ")
            ciudad2 = raw_input("\nIngrese el nombre de la ciudad destino: ")
            distancia = input("\nIngrese la distancia entre las ciudades: ")            
            establecerRuta(ciudad1, ciudad2, distancia)

        else:
            print("Ingreso una opcion invalida\n")
    #-------------Salida y despedida del programa-------------------
    elif (opcion==4):
        print ("Hasta pronto!")
        desicion = False;

    else:
        print ("\nOpcion Invalida\nIngrese una opcion valida\n")

