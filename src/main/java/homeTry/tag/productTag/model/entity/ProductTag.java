package homeTry.tag.productTag.model.entity;

import homeTry.tag.model.entity.Tag;
import homeTry.tag.model.vo.TagName;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "tag_name")})
public class ProductTag extends Tag {

    protected ProductTag() {
        super();
    }

    public ProductTag(String name) {
        super(new TagName(name));
    }

    public String getTagNameValue() {
        return getTagName().value();
    }

}
