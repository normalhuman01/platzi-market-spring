# Dependencias
1. pgAdmin
2. IntelliJ IDEA IDE
# Instrucciones
1. Ejecutar las consultas sql de los archivos BD/schema y DB/data en pgAdmin para crear y poblar la base de datos
2. Agregar el plugin MapStruct al IDE desde el siguiente link: https://plugins.jetbrains.com/plugin/10036-mapstruct-support
3. Abrir el proyecto con el archivo build.gradle desde el IDE
4. Correr el proyecto
# Rutas
<h2> Productos </h2>

1. Obtener productos: GET http://localhost:5000/platzi-market/api/products/all
2. Obtener producto por id: GET http://localhost:5000/platzi-market/api/products/{id}
3. Guardar producto: POST http://localhost:5000/platzi-market/api/products<br>
Ejemplo de Body: 
<pre>
{
        "name": "Palta",
        "categoryId": 1,
        "price": 20.0,
        "stock": 100,
        "active": true
 }
</pre>

4. Borrar producto: DELETE http://localhost:5000/platzi-market/api/products/{id}
<h2> Compras </h2>

1. Obtener compras: GET http://localhost:5000/platzi-market/api/purchases/all
2. Obtener compra por id: GET http://localhost:5000/platzi-market/api/purchases/{id}
3. Obtener compra por id del cliente: GET http://localhost:5000/platzi-market/api/purchases/client/{id}
4. Guardar compra: POST http://localhost:5000/platzi-market/api/purchases<br>
Ejemplo de body:
<pre>
{
        "clientId": "4546221",
        "date": "1992-08-10T17:30:00",
        "paymentMethod": "E",
        "comment": "",
        "state": "P",
        "items": []
}
</pre>

5. Borrar compra: DELETE http://localhost:5000/platzi-market/api/purchases/{id}
 # Documentacion (Swagger)
 1. Acceder a traves del siguiente link: http://localhost:5000/platzi-market/api/swagger-ui.html
 # Seguridad (Spring Security)
 1. La seguridad se maneja a través de JWT, para autenticarse se accede a la API de autorizacion a través de la siguiente ruta: http://localhost:5000/platzi-market/api/auth/authenticate<br>
junto con un Body:
<pre>
{
  "username": "*****",
  "password": "*****"
}
</pre>
Luego, se generará un token JWT que tendrá que ser usado en el <b> header </b> de cada petición http a las API's.

<br>Si se desea autenticarse una única vez, también se puede llevar a cabo la autenticación haciendo click en el siguiente boton en la documentación swagger (http://localhost:5000/platzi-market/api/swagger-ui.html):
<img src="/media/img/authorize.jpg" alt="Botón de autorizacion"/>
Luego se mostrará el siguiente modal:
<br>
<img src="/media/img/bearer_modal.PNG" alt="Modal de autenticacion"/>
<br>
En el campo "Value" se ingresará la palabra "Bearer " mas el token de autenticación generado previamente, una vez hecho esto, se habilitarán todas las peticiones en esta sesión  a través de la documentación swagger al dar click en el botón "Authorize".
 # Estado del proyecto
 1. En desarrollo (90%)
 # Caracteristicas futuras
 1. Dockerización
