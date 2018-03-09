package com.su.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.util.List;

/**
 * @author suyupeng
 */
@Data
@Entity
@Table(name = "`CATEGORY`")
@NoArgsConstructor
public class Category {
    /**
     * id是由网站名加分类名的md5
     */
    @Id
    @Column(name="`ID`", length = 40)
    private String id;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="category")
    private List<Novel> novels;

    public Category(String id, String website, String name, String url) {
        this.id = id;
        this.website = website;
        this.name = name;
        this.url = url;
    }

    public Category(String id) {
        this.id = id;
    }
}
