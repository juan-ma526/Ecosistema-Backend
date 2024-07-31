package com.semillero.ecosistema.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.semillero.ecosistema.cloudinary.dto.ImageModel;
import com.semillero.ecosistema.dto.ProveedorDto;
import com.semillero.ecosistema.entidad.Proveedor;
import com.semillero.ecosistema.servicio.ProveedorServicio;

@RestController
@RequestMapping
public class ProveedorControlador {

	@Autowired
	private ProveedorServicio proveedorServicio;
	
	@PreAuthorize("hasRole('USUARIO')")
	@PostMapping(value="/crearProveedor/usuario/{usuarioId}/pais/{paisId}/provincia/{provinciaId}/categoria/{categoriaId}",consumes = "multipart/form-data")
	public ResponseEntity<?> crearProveedor(@PathVariable Long usuarioId,@PathVariable Long paisId,@PathVariable Long provinciaId,@PathVariable Long categoriaId,@ModelAttribute ProveedorDto proveedorDto,@RequestPart("imagenes") List<MultipartFile> files) {
		try {
			
			List<ImageModel>imageModels=new ArrayList<>();
			for(MultipartFile file : files) {
				String nombreArchivo=file.getOriginalFilename();
				
				ImageModel imageModel= new ImageModel();
				imageModel.setFile(file);
				imageModel.setNombre(nombreArchivo);
				
				imageModels.add(imageModel);
				
			}
			
			proveedorDto.setUsuarioId(usuarioId);
			proveedorServicio.crearProveedor(usuarioId, proveedorDto, paisId, provinciaId,categoriaId,imageModels);
           
			return ResponseEntity.ok("Proveedor creado con éxito");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PreAuthorize("hasRole('USUARIO')")
	@PutMapping(value = "/editarProveedor/usuario/{usuarioId}/proveedor/{proveedorId}/pais/{paisId}/provincia/{provinciaId}/categoria/{categoriaId}", consumes = "multipart/form-data")
	public ResponseEntity<?> editarProveedor(
	        @PathVariable Long usuarioId,
	        @PathVariable Long proveedorId,
	        @PathVariable Long paisId,
	        @PathVariable Long provinciaId,
	        @PathVariable Long categoriaId,
	        @ModelAttribute ProveedorDto proveedorDto,
	        @RequestPart("imagenes") List<MultipartFile> files) {

	    try {
	        // Llamar al servicio para editar el proveedor
	        Proveedor proveedorActualizado = proveedorServicio.editarProveedor(usuarioId, proveedorId, proveedorDto, paisId, provinciaId, categoriaId, files);
	        return ResponseEntity.ok(proveedorActualizado);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Proveedor>>buscarProveedoresPorNombre(@RequestParam String query){
		try {
			List<Proveedor> proveedores=proveedorServicio.buscarPorNombre(query);
			return ResponseEntity.ok(proveedores);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/buscarPorCategoria/{categoriaId}")
    public ResponseEntity<List<Proveedor>> buscarProveedoresPorCategoria(@PathVariable Long categoriaId) {
		return ResponseEntity.ok(proveedorServicio.buscarPorCategoriaId(categoriaId));
    }
	
	@GetMapping
	public ResponseEntity<List<Proveedor>> mostrarProveedorActivo(){
		return ResponseEntity.ok(proveedorServicio.mostrarProveedoresActivos());
	}
}
