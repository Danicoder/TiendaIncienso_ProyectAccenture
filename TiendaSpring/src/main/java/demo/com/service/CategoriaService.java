package demo.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.com.dominio.Categoria;
import demo.com.exception.DomainException;
import demo.com.objetos.Repository.ICategoriaRepo;
import demo.com.util.Validator;

@SuppressWarnings("serial")
@Service
public class CategoriaService extends DomainException {
	@Autowired // para que carge en el DAO Spring
	private ICategoriaRepo CategoriaRepository;

	public Categoria getById(int id) {
		return CategoriaRepository.findById(id).get();
	}

	public List<Categoria> getList() {
		return CategoriaRepository.findAll();
	}
	//Responde a Post
	public Categoria update(Categoria categoria) {
		// Hace un insert sí existe, de lo contrario lo actualiza
		if (CategoriaRepository.existsById(categoria.getId_categoria())
			&& Validator.isVacio(categoria.getCat_nombre())) {
			new DomainException("Error 404 Not_found, Categoria no encontrada");
		}
		return CategoriaRepository.save(categoria);
	}

	// Sí existe la cuenta hace un update, si no la inserta
	//Responde a Put
	public Categoria actualizarById(Categoria categoria, int id) {
		if (!CategoriaRepository.existsById(categoria.getId_categoria())) {
			new DomainException("Error 404 Not_found, Categoria no encontrada");
		}
		categoria.setId_categoria(id);
		return CategoriaRepository.save(categoria);
	}

	public void deleteById(int id) {
		CategoriaRepository.deleteById(id);
	}
}
