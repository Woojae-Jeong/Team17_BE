package homeTry.product.model.entity;

import homeTry.tag.productTag.model.entity.ProductTag;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductTagMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_tag_id", nullable = false)
    private ProductTag productTag;


    protected ProductTagMapping() {

    }

    public ProductTagMapping(Product product, ProductTag productTag) {
        this.product = product;
        this.productTag = productTag;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public ProductTag getProductTag() {
        return productTag;
    }

}
