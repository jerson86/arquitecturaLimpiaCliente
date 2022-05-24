package com.pragma.cliente.controller;

import com.pragma.cliente.dto.ClienteDTO;
import com.pragma.cliente.dto.ImagenMongoDTO;
import com.pragma.cliente.service.ImagenMongoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ImagenMongoController {
    @Autowired
    ImagenMongoServiceImpl imagenService;

    @RequestMapping(value = "api/mongo/imagenes", method = RequestMethod.GET)
    public ResponseEntity<List<ImagenMongoDTO>> getAllImagenes(){
        return  ResponseEntity.ok().body(imagenService.getAllImagenes());
    }

    @RequestMapping(value = "api/mongo/imagenes/{id}", method = RequestMethod.GET)
    public ResponseEntity<ImagenMongoDTO> getImagen(@PathVariable String id){
        return ResponseEntity.ok().body(imagenService.getImagenById(id));
    }

    @RequestMapping(value = "api/mongo/imagenes/{id}/{idCliente}", method = RequestMethod.GET)
    public ResponseEntity<ImagenMongoDTO> getImagenByIdCliente(@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok().body(imagenService.getImagenByCliente(clienteDTO));
    }

    @RequestMapping(value = "api/mongo/imagenes/{idCliente}", method = RequestMethod.POST)
    public ResponseEntity<ImagenMongoDTO> createImagen(@RequestParam("foto") MultipartFile foto, @PathVariable Long idCliente) {
        return ResponseEntity.ok().body(imagenService.createImagen(foto,idCliente));
    }

    @RequestMapping(value = "api/mongo/imagenes/{id}/{idCliente}", method = RequestMethod.PUT)
    public ResponseEntity<ImagenMongoDTO> updateImagen(@RequestParam("foto") MultipartFile foto, @PathVariable Long idCliente, @PathVariable String id){
        return ResponseEntity.ok().body(imagenService.updateImagen(id, foto, idCliente));
    }

    @RequestMapping(value = "api/mongo/imagenes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ImagenMongoDTO> deleteImagen(@PathVariable String id){
        return ResponseEntity.ok().body(imagenService.deleteImagen(id));
    }
}
