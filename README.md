# API REST Culturarte Grupo 2

## API para gestionar Propuestas y sus Colaboraciones.


## Estructura del proyecto
```
src/main/java/iiss/api_culturarte/
├─ Controller/       
├─ Service/          
├─ Repositorios/     
├─ Seguridad/        
├─ Excepciones/      
├─ Propuesta.java    
└─ Colaboracion.java 

build.sh             # Compila y levanta la app (Maven + Docker)
docker-compose.yml   # Contenedores para la app
Dockerfile           # Imagen de la aplicación
mvnw / mvnw.cmd      # Wrapper de Maven
target/              # Archivos compilados
```


## Requisitos

Docker y Docker Compose
Permisos de ejecución para scripts:
```
chmod +x mvnw
chmod +x build.sh
```

## Cómo ejecutar

Abrir terminal en la carpeta raíz.

Ejecutar:
```
./build.sh
```
Esto compila el proyecto con Maven y levanta la app en Docker.

## La API estará disponible en:
```
http://localhost:8080/api
```
## Ejemplo de uso con curl:
```
# Listar propuestas
curl -u user:CONTRASEÑA http://localhost:8080/api/propuestas

# Crear propuesta
curl -u user:CONTRASEÑA -X POST http://localhost:8080/api/propuestas \
     -F 'propuesta={
           "titulo": "Hamlet",
           "descripcion": "Interpretación de Hamlet",
           "lugar": "Teatro Cantegril",
           "fechaRealizar": "2025-11-23",
           "precioEntrada": 640,
           "montoReunir": 50000.75,
           "categoria": "Teatro clásico",
           "tiposRetorno": ["Entrada gratis","Porcentaje de ganancias"]
         };type=application/json' \
     -F "imagen=@/path/a/tu/imagen.jpg;type=image/jpeg"

# Obtener imagen de propuesta
curl -u user:CONTRASEÑA http://localhost:8080/api/propuestas/TITULO \
     -H "Accept: image/jpeg"
```

## Seguridad
La API usa Basic Auth.

Al iniciar, se genera una contraseña temporal en los logs, por ejemplo:
```
culturarte-app  | Using generated security password: fcf10809-ab34-4d01-9100-9fff82ab664f
```
Para ver los logs se debe utilizar
```
docker compose logs
```

