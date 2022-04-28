package com.system.backend.manage.building.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.service.IUploadFileService;
import com.system.backend.manage.building.service.PropietarioService;

@RestController
@RequestMapping("/api/uploads")
public class ImagenesController {
	@Autowired
	private IUploadFileService uploadService;
	@Autowired
	private PropietarioService propietarioService;
	@GetMapping("/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> nombre foto "+ nombreFoto);
		Resource recurso=null;
		try {
			recurso=uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders cabecera=new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"");
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
	
	@PostMapping("/foto/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Propietario propietario=propietarioService.obtenerPropietarioPorId(id);
		Response response;
		ResponseDetails detalle;
		if(!archivo.isEmpty()) {
			String nombreArchivo=null;
			try {
				nombreArchivo=uploadService.copiar(archivo);
			} catch (IOException e) {
				throw new CustomAppException(
						"Error al subir la imagen del cliente", 400,
						e.getMessage() + ":" + e.getCause().getMessage(), "IOException", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			String nombreFotoAnterior=propietario.getPersona().getUsuario().getFoto();
			uploadService.eliminar(nombreFotoAnterior);
			
			propietario.getPersona().getUsuario().setFoto(nombreArchivo);
			propietarioService.savePropietario(propietario);
			
			
			detalle = new ResponseDetails(200, "Propietario Actualizado", propietario);
			response = new Response("Success","Has subido correctamente la imagen",detalle);
		}else {
			detalle = new ResponseDetails(400, "No se ha subido ingresado ningún archivo", propietario);
			response = new Response("error","ingrese un archivo válido para continuar",detalle);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
}
