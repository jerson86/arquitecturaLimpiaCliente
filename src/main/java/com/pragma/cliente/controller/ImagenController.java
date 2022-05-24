package com.pragma.cliente.controller;

import com.pragma.cliente.dto.ClienteDTO;
import com.pragma.cliente.dto.ImagenDTO;
import com.pragma.cliente.service.ImagenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
public class ImagenController {

    @Autowired
    ImagenServiceImpl imagenService = new ImagenServiceImpl();
    @RequestMapping(value = "api/imagenes", method = RequestMethod.GET)
    public ResponseEntity<List<ImagenDTO>> getAllImagenes(){
        return  ResponseEntity.ok().body(imagenService.getAllImagenes());
    }

    @RequestMapping(value = "api/imagenes/{id}", method = RequestMethod.GET)
    public ResponseEntity<ImagenDTO> getImagen(@PathVariable Long id){
        return ResponseEntity.ok().body(imagenService.getImagenById(id));
    }

    @RequestMapping(value = "api/imagenes/{id}/{idCliente}", method = RequestMethod.GET)
    public ResponseEntity<ImagenDTO> getImagenByIdCliente(@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok().body(imagenService.getImagenByCliente(clienteDTO));
    }

    @RequestMapping(value = "api/imagenes/{idCliente}", method = RequestMethod.POST)
    public ResponseEntity<ImagenDTO> createImagen(@RequestParam("foto") MultipartFile foto, @PathVariable Long idCliente) throws IOException {
        return ResponseEntity.ok().body(imagenService.createImagen(foto,idCliente));
    }

    @RequestMapping(value = "api/imagenes/{id}/{idCliente}", method = RequestMethod.PUT)
    public ResponseEntity<ImagenDTO> updateImagen(@RequestParam("foto") MultipartFile foto, @PathVariable Long idCliente, @PathVariable Long id){
        return ResponseEntity.ok().body(imagenService.updateImagen(id, foto, idCliente));
    }

    @RequestMapping(value = "api/imagenes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ImagenDTO> deleteImagen(@PathVariable Long id){
        return ResponseEntity.ok().body(imagenService.deleteImagen(id));
    }
}
