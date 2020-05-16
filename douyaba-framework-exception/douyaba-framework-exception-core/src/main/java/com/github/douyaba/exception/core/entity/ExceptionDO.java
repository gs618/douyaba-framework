package com.github.douyaba.exception.core.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author s.c.gao
 */
@Table(name = "t_exception")
@Entity
@Data
public class ExceptionDO implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", nullable = false, length = 128)
    private String id;

    @Column(name = "code", unique = true, nullable = false)
    private Integer code;

    /**
     *
     */
    @OneToMany(mappedBy = "exception", cascade = CascadeType.ALL)
    private List<I18nMessageDO> I18nMessages;
}
