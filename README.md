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

TIPO: GET
PATH: /api/funciones
PARAMS: 

![Screenshot from 2021-07-06 12-01-28](https://user-images.githubusercontent.com/5760749/124622981-f3447800-de51-11eb-97d4-75562f3740e0.png)






