
package com.proyectoasistencia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.mongodb.client.MongoCursor;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.apache.commons.io.IOUtils;
import org.bson.Document;
import java.io.File;
import java.io.FileOutputStream;
import org.bson.types.Binary;
import org.bson.types.ObjectId;



public class ConexionMongoDB  {
    public static void imagenesMongo() throws FileNotFoundException, IOException {

    String imagenRuta = System.getProperty("user.home") + "/Desktop/asistenciaprincipal.png";
    File archivoImagen = new File(imagenRuta);
    String connectionString = "mongodb://localhost:27017"; 
    MongoClientURI uri = new MongoClientURI(connectionString);
    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("imagenesMongo"); 
    MongoCollection<Document> collection = database.getCollection("imagenesPDF"); 
    
    // Leer la imagen como arreglo de bytes
    byte[] imageData = IOUtils.toByteArray(new FileInputStream(imagenRuta));

    // Crear e insertar un documento con la imagen en la coleccion
    Document document = new Document("imagen", imageData);
    collection.insertOne(document);
    
    //buscamos la imagen en mongoDB
    Document query = new Document("_id", new ObjectId("6475013d6fbabd4259e8d1c7")); // Reemplaza "ID_DEL_DOCUMENTO" con el ID del documento que contiene la imagen que deseas consultar

    Document result = collection.find(query).first();
    
    //Si se encuentro la imagen en mongoDB la guardamos en nuestra pc y le colocamos un nombre
    if (result != null) {
        Binary binaryData = (Binary) result.get("imagen");
        byte[] imageData2 = binaryData.getData();
        System.out.println("Se encontro la imagen");
        String rutaDestino = System.getProperty("user.home") + "/Desktop/asistenciaMongo.jpg";
        FileOutputStream fileOutputStream = new FileOutputStream(rutaDestino);
        fileOutputStream.write(imageData);
        fileOutputStream.close();
        File archivoGuardado = new File(rutaDestino);
    if (archivoGuardado.exists()) {
    System.out.println("La imagen se guardó correctamente en la carpeta especificada.");
    } else {
    System.out.println("No se pudo guardar la imagen en la carpeta especificada.");
    }
    // Ahora puedes utilizar el arreglo de bytes de la imagen según tus necesidades
    } else {
    System.out.println("La imagen no se encontró en la base de datos.");
    }
    }
}
