package com.project.ecommerce.product;



/*import com.project.ecommerce.aws.S3Service;*/
import com.project.ecommerce.exception.PurchaseInformationError;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.project.ecommerce.product.ProductMapper.toProductResponse;

@Service
@RequiredArgsConstructor
public class ProductService {

   final    ProductRepository repository;
   final  ProductMapper mapper;
   /*final S3Service s3Service ;*/

    public Integer saveProduct(ProductRequest request) {
        try {
          Product product =  repository.save(mapper.toProduct(request));
          /*s3Service.uploadFiles( request.images() , product.getId());*/
          return product.getId();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Transactional(rollbackFor = PurchaseInformationError.class)
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {

        List<ProductPurchaseResponse> productPurchaseResponse = new ArrayList<ProductPurchaseResponse>();

        var productsIds = request.stream()
                .map(ProductPurchaseRequest::productId).toList();
        var storedProducts =  repository.findAllByIdInOrderById(productsIds);
        if(storedProducts.size() != productsIds.size()) {
            throw  new PurchaseInformationError("the required products do not exist ");
        }

        for(int i=0  ; i< storedProducts.size(); i++){

            var storedProduct = storedProducts.get(i);
            var requestProduct = request.get(i);
            if(!storedProduct.getId().equals(requestProduct.productId())){
                throw  new PurchaseInformationError("not the same id  " + request.get(i).productId() +" "+ storedProduct.getId());

            }
            if(requestProduct.quantity() > storedProduct.getQuantity()){
                throw  new PurchaseInformationError("we don't have enough stock for product with id : " + request.get(i).productId() +" "+ storedProduct.getQuantity());
            }

            var newQuantity = storedProduct.getQuantity() - requestProduct.quantity();
            storedProduct.setQuantity(newQuantity);
            repository.save(storedProduct);
            productPurchaseResponse.add(mapper.toProductPurchaseResponse(storedProduct,requestProduct.quantity()));
        }


        return productPurchaseResponse;

    }

    public ProductResponse findById(Integer productId) {

        List<String> imageUrls =  new ArrayList<>();

        return repository.findById(productId).map(product -> toProductResponse(product ,imageUrls))
                .orElseThrow( () -> new EntityNotFoundException("product Not found !"));

    }

    public List<ProductResponse> findAll() {

        return repository.findAll().stream().map(product ->{
            List<String> imageUrls =  new ArrayList<>();

            return toProductResponse(product ,imageUrls) ;
        }).toList();



    }
}
