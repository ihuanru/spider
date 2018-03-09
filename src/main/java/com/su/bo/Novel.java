package com.su.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author suyupeng
 */
@Data
@Entity
@Table(name = "`NOVEL`")
@NoArgsConstructor
public class Novel {
    /**
     * id是url的md5
     */
    @Id
    @Column(name="`ID`", length = 40)
    private String id;

    @Column(name="`NAME`")
    private String name;

    @Column(name="`AUTHOR`")
    private String author;

    @Column(name="`DATE`", columnDefinition = "timestamp default CURRENT_TIMESTAMP()")
    private Date date;

    @Column(name="`INTRODUCTION`", columnDefinition = "text")
    private String introduction;

    @Column(name="`URL`")
    private String url;

    @Column(name="`IMAGE_URL`")
    private String imageUrl;

//    @Column(name="`CATEGORY_ID`", length = 40)
//    private String categoryId;

    @ManyToOne(fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="novel")
    private List<Chapter> chapters;

    public Novel(String id) {
        this.id = id;
    }
}
