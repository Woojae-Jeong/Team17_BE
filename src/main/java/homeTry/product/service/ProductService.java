package homeTry.product.service;

import homeTry.member.dto.MemberDTO;
import homeTry.product.dto.response.ProductResponse;
import homeTry.product.exception.badRequestException.InvalidMemberException;
import homeTry.product.model.entity.Product;
import homeTry.product.model.entity.ProductTagMapping;
import homeTry.product.repository.ProductRepository;
import homeTry.product.repository.ProductTagMappingRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTagMappingRepository productTagMappingRepository;

    public ProductService(ProductRepository productRepository,
        ProductTagMappingRepository productTagMappingRepository) {
        this.productRepository = productRepository;
        this.productTagMappingRepository = productTagMappingRepository;
    }

    public List<ProductResponse> getProducts(List<Long> tagIds, MemberDTO memberDTO) {

        if (memberDTO == null) {
            throw new InvalidMemberException();
        }

        List<Product> products;

        // TODO : if-else 분리
        // tag O -> 해당 태그에 맞는 상품들을 가격순으로 정렬
        // tag X -> 전체 상품을 가격순으로 정렬
        if (tagIds != null && !tagIds.isEmpty()) {
            List<ProductTagMapping> mappings = productTagMappingRepository.findByProductTagIdIn(tagIds);

            List<Long> productIds = mappings.stream()
                .map(ProductTagMapping::getProductId) // productId 추출
                .toList();

            products = productRepository.findByIdInOrderByPriceAsc(productIds);
        } else {
            products = productRepository.findAllByOrderByPriceAsc();
        }

        return products
            .stream()
            .map(ProductResponse::from)
            .toList();

    }

}
