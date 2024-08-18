package com.semillero.ecosistema.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semillero.ecosistema.entidad.Categoria;
import com.semillero.ecosistema.repositorio.ICategoriaRepositorio;
import com.semillero.ecosistema.servicio.DashboardServicio;
import com.semillero.ecosistema.servicio.ProveedorServicio;

@RestController
public class DashboardControlador {

	@Autowired
	private DashboardServicio dashboardServicio;
	
	@Autowired
	private ICategoriaRepositorio categoriaRepositorio;
	
	
	@GetMapping("/estadisticasProveedores")
    public Map<String, Long> obtenerEstadisticasProveedores() {
        Map<String, Long> estadisticas = new HashMap<>();
        estadisticas.put("aceptados", dashboardServicio.proveedoresAceptados());
        estadisticas.put("enEspera", dashboardServicio.proveedoresEnEspera());
        estadisticas.put("denegados", dashboardServicio.proveedoresDenegador());

        return estadisticas;
    }
	
	@GetMapping("/proveedoresPorCategoria")
    public Map<String, Long> obtenerEstadisticasPorCategoria() {
        Map<String, Long> estadisticas = new HashMap<>();
        List<Categoria> categorias = categoriaRepositorio.findAll();
        for (Categoria categoria : categorias) {
            estadisticas.put(categoria.getNombre(), dashboardServicio.contarProveedoresPorCategoria(categoria));
        }
        return estadisticas;
    }
	
	
}
