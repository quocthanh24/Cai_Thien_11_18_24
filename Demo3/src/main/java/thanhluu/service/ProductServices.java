package thanhluu.service;

import java.util.List;

import thanhluu.entity.Product;

public interface ProductServices {
	
	void delete(Long id);
	
	Product get(Long id);
	
	Product save(Product product);
	
	List<Product> listAll();
}
