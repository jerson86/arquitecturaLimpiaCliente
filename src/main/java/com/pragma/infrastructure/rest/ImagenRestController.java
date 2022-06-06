package com.pragma.infrastructure.rest;

import com.pragma.application.dto.ImagenDTO;
import com.pragma.application.mapper.ImagenMapper;
import com.pragma.domain.port.inbound.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@RestController
public class ImagenRestController {
    private final ImagenService imagenService;
    @Autowired
    private ImagenMapper imagenMapper;

    public ImagenRestController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @RequestMapping(value = "api/mongo/imagenes", method = RequestMethod.GET)
    public ResponseEntity<List<ImagenDTO>> getAllImagenes(){
        return  ResponseEntity.ok().body(
                imagenMapper.mapToDTOList(
                        imagenService.getAllImages()
                )
        );
    }

    @RequestMapping(value = "api/mongo/imagenes/{id}", method = RequestMethod.GET)
    public ResponseEntity<ImagenDTO> getImagen(@PathVariable String id){
        return ResponseEntity.ok().body(
                imagenMapper.mapToDTO(
                        imagenService.getImagenById(id)
                )
        );
    }

    /*@RequestMapping(value = "api/mongo/imagenes/{id}/{idCliente}", method = RequestMethod.GET)
    public ResponseEntity<ImagenDTO> getImagenByIdCliente(@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok().body(
                imagenService.getImagenByCliente(clienteDTO)
        );
    }

     */

    @RequestMapping(value = "api/mongo/imagenes/{idCliente}", method = RequestMethod.POST)
    public ResponseEntity<ImagenDTO> createImagen(@RequestParam("foto") String foto, @PathVariable long idCliente) {
        return ResponseEntity.ok().body(
                imagenMapper.mapToDTO(
                    imagenService.createImage(foto,idCliente)
                )
        );
    }

    @RequestMapping(value = "api/mongo/imagenes/{id}/{idCliente}", method = RequestMethod.PUT)
    public ResponseEntity<ImagenDTO> updateImagen(@RequestParam("foto") String foto, @PathVariable String id, @PathVariable long idCliente){
        return ResponseEntity.ok().body(
                imagenMapper.mapToDTO(
                    imagenService.updateImagen(id, foto, idCliente)
                )
        );
    }

    @RequestMapping(value = "api/mongo/imagenes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ImagenDTO> deleteImagen(@PathVariable String id){
        return ResponseEntity.ok().body(
                imagenMapper.mapToDTO(
                    imagenService.deleteImagen(id)
                )
        );
    }

}
