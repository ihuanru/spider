package com.su.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * @author suyupeng
 */
@Data
@Entity
@Table(name = "`CHAPTER`")
@NoArgsConstructor
public class Chapter {
    /**
     * id是url的md5
     */
    @Id
    @Column(name="`ID`", length = 40)
    private String id;

    @Column(name="`NAME`")
    private String name;

    @Column(name="`URL`")
    private String url;

    @Column(name="`CONTENT`", columnDefinition = "text")
    private String content;

//    @Column(name="`NOVEL_ID`", length = 40)
//    private String novelId;

    @ManyToOne(fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="NOVEL_ID")
    private Novel novel;

    public Chapter(String id) {
        this.id = id;
    }
}
