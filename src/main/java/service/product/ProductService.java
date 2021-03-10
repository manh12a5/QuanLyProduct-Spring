package service.product;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import repository.IProductRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        List<Product> productList = (List<Product>) productRepository.findAll();
        return productList;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findProductName(name);
    }

}
