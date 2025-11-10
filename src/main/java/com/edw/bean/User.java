package com.edw.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.infinispan.api.annotations.indexing.Basic;
import org.infinispan.api.annotations.indexing.Indexed;
import org.infinispan.api.annotations.indexing.Keyword;

/**
 * <pre>
 *  com.edw.bean.User
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 10 Nov 2025 9:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Indexed

@Entity
@Table(name = "t_user")
public class User {
    @Id
    @Keyword(projectable = true, sortable = true, normalizer = "lowercase", indexNullAs = "unnamed", norms = false)
    private String name;

    @Column(name = "age")
    @Basic(projectable = true, sortable = true, indexNullAs = "0")
    private Integer age;

    @Column(name = "address")
    @Basic(projectable = true, sortable = true, indexNullAs = "")
    private String address;

    @Column(name = "province")
    @Basic(projectable = true, sortable = true, indexNullAs = "")
    private String province;
}
