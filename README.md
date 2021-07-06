# Show Api


### Requisitos

- Base de datos (para este ejemplo utilizamos postgres)
- Para precarga de datos ejecutar el script data.sql ubicado en el directorio test/resources/
- Variables de entorno necesarias con los valores correspondientes: PSQL_HOST, PSQL_PORT, PSQL_USER, PSQL_PASSWORD, PSQL_DB
- JAVA 8
- Maven >= 3


### Run local

Para levantar el proyecto ejecutar el siguiente comando:

mvn spring-boot:run

### Container

- Genarar JAR: mvn clean package
- Generar imagen local: parado en la raiz del proyecto ejecutar 
 sudo docker build -t matiasciarla/show-api:{tag} .
- Subier imagen a la registry: ejecutar el siguiente comando 
sudo docker push matiasciarla/show-api:{tag}
- Instanciar imagen: correr el siguiente comando reemplazando los valores de las environments por los correspondientes al ambiente  
sudo docker run -d -e PSQL_HOST={host ambiente} -e PSQL_PORT={puerto ambiente} -e PSQL_USER={user ambiente} -e PSQL_PASSWORD={password ambiente} -e PSQL_DB={base de datos ambiente} --name show-api --restart=always -p 80:8080 matiasciarla/show-api:{tag}


### API

Los servicios estan expuestos en AWS a traves del host ec2-18-231-73-41.sa-east-1.compute.amazonaws.com

Los endpoints expuestos son los siguientes:

- Servicio para buscar funciones:

TIPO: GET <br>
PATH: /api/funciones <br>
QUERY PARAMS: 

![Screenshot from 2021-07-06 12-35-02](https://user-images.githubusercontent.com/5760749/124628218-a3b47b00-de56-11eb-92a8-b21cc7ab3df0.png)

Response ejemplo:
<pre><code>
[
    {
        "id": 1,
        "fecha": "2021-12-18T08:21:36.175+0000",
        "sala": {
            "id": 1,
            "teatro": {
                "id": 1,
                "descripcion": "Luna Park"
            }
        },
        "show": {
            "id": 1,
            "descripcion": "Superman"
        },
        "butacaTickets": [
            {
                "id": 1,
                "precio": 200.0,
                "seccion": "A",
                "ticket": {
                    "id": 1,
                    "nroTicket": "A-1",
                    "nombre": "pepa"
                },
                "butaca": {
                    "id": 1,
                    "sala": {
                        "id": 1,
                        "teatro": {
                            "id": 1,
                            "descripcion": "Luna Park"
                        }
                    }
                }
            },
            {
                "id": 4,
                "precio": 200.0,
                "seccion": "A",
                "ticket": null,
                "butaca": {
                    "id": 4,
                    "sala": {
                        "id": 1,
                        "teatro": {
                            "id": 1,
                            "descripcion": "Luna Park"
                        }
                    }
                }
            }
        ]
    }
]</pre></code>

- Servicio para buscar funciones por ID:

TIPO: GET <br>
PATH: /api/funciones/{id} <br>

- Servicio que retorna todos los shows

TIPO: GET <br>
PATH: /api/shows <br>

- Servicio que genera un nuevo ticket para un asiento en particular

TIPO: POST <br>
PATH: /api/butacaTicket/{id}/ticket <br>

Ejemplo de request:

<pre><code> 

{
    "dni": "12345678",
    "nombre": "Juan Perez"
}

</pre></code> 

