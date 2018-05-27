#Hoja Trabajo 11
#David Soto / Alejandro Tejada
#Funciones para uso del programa principal y la aplicacion del Floyd

import networkx as nx

ciudadesDestino = {}
ciudadesOrigen = {}

grafo = nx.DiGraph()

def crearGrafo():
#---------Se lee el archivo .txt y se ingresan los valores al grafo------------
    file = open("guategrafo.txt","r+")
    for linea in file:
        #-----Se separa la ciudad1, ciudad2, distancia y se agrega al grafo---------
        divisiones = linea.split(" ")
        ciudad1 = divisiones[0]
        ciudad2 = divisiones[1]
        distancia = float(divisiones[2])

        #-----Se realiza el ingreso de relaciones entre 2 ciudades con una distancia--------
        grafo.add_edge(ciudad1,ciudad2,weight=distancia)

    file.close() 

#Busca ciudades existentes dentro del grafo
def buscarCiudadesEnGrafo():

    #Se imprime la lista de nodos
    Nodes = grafo.nodes()
    print ("\nEste es el listado de las ciudades dentro del grafo: ")
    print ("\n".join(Nodes))

    #Se ingresa la ciudad origen
    ciudad1 = raw_input("\nIngrese el nombre de la ciudad origen: ")    

    while ciudad1 not in Nodes:        
        ciudad1 = raw_input("\nIngrese una ciudad que este dentro de la lista: ")


    #Se ingresa la ciudad destino
    ciudad2 = raw_input("\nIngrese el nombre de la ciudad destino: ")
    
    while ciudad2 == ciudad1 or ciudad2 not in Nodes:

        if (ciudad2 == ciudad1):
            ciudad2 = raw_input("\nIngrese una ciudad diferente a la origen: ")
            
        else:                
            ciudad2 = raw_input("\nIngrese una ciudad que este dentro de la lista: ")

    return ciudad1,ciudad2


#Muestra la ruta mas corta entre 2 ciudades
def rutaMasCorta(ciudad1,ciudad2):
    ciudadesIntermedias = []
    control = True 
    path = nx.floyd_warshall_predecessor_and_distance(grafo)    
    try: 
        #Ruta a traves de nodos
        intermedia = path[0][ciudad1][ciudad2]
        distancia = path[1][ciudad1][ciudad2]
        ciudadesIntermedias.append(intermedia)
        
        while control:
            #Se crea una lista para saber por donde pasa a traves de los nodos
            if not(intermedia == ciudad1):
                intermedia1 = path[0][ciudad1][intermedia]
                ciudadesIntermedias.append(intermedia1)
                intermedia = intermedia1    
            else:
                control = False

        #Ordenamos la lista
        ciudadesIntermedias.reverse()
        #Se hace la impresion
        print ("\nLa ruta mas corta entre es pasando por: ")
        print (", ".join(ciudadesIntermedias),",",ciudad2)
        print ("\nLa distancia total es de", distancia, "km")
        
    except KeyError:
        print ("No hay ruta directa entre las ciudades\n")                

#Muestra el centro del grafo
def centroGrafo():
    pass


#Hace una interrupcion entre 2 ciudades
def indicarTrafico(ciudad1, ciudad2):    
    
    if((grafo.has_edge(ciudad1,ciudad2))==True): 
        grafo.remove_edge(ciudad1,ciudad2)
        print("Se realizo la interrupcion de trafico con exito")
    elif((grafo.has_edge(ciudad2,ciudad1))==True):
        grafo.remove_edge(ciudad2,ciudad1)
        print("Se realizo la interrupcion de trafico con exito")
    else:
        print("No se puede realizar la interrupcion debido a que no existe una ruta directa entre las 2 ciudades\n")

#Ingresa una nueva ruta al grafo
def establecerRuta(ciudad1, ciudad2, distancia):
    
    paths = nx.floyd_warshall_predecessor_and_distance(grafo)

    grafo.add_edge(ciudad1,ciudad2,weight=distancia)

    print("Se ha realizado la conecxion entre las ciudades")


