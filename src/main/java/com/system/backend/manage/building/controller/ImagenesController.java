package com.system.backend.manage.building.controller;

import java.net.MalformedURLException;
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

import com.system.backend.manage.building.service.IUploadFileService;

@RestController
@RequestMapping("/api/uploads")
public class ImagenesController {
	@Autowired
	private IUploadFileService uploadService;
	
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
//		Map<String, Object> response = new HashMap<String, Object>();
//		
//		Cliente cliente=clienteService.findById(id);
//		if (cliente == null) {
//			response.put("mensaje","Cliente con id: " + id + "; no existe en la base de datos!");
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//		}
//		if(!archivo.isEmpty()) {
//			String nombreArchivo=null;
//			try {
//				nombreArchivo=uploadService.copiar(archivo);
//			} catch (Exception e) {
//				response.put("mensaje", "Error al subir la imagen del cliente!");
//				response.put("error", e.getMessage() + ":" + e.getCause().getMessage());
//				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			String nombreFotoAnterior=cliente.getFoto();
//			uploadService.eliminar(nombreFotoAnterior);
//			
//			cliente.setFoto(nombreArchivo);
//			clienteService.save(cliente);
//			response.put("cliente", cliente);
//			response.put("mensaje","Has subido correctamente la imagen: "+nombreArchivo);
//		}
		return new ResponseEntity<>("", HttpStatus.CREATED);
		
	}
	
}
