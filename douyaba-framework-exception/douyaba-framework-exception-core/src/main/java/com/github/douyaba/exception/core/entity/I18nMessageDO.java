package com.github.douyaba.exception.core.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author s.c.gao
 */
@Table(name = "t_i18n_message")
@Entity
@Data
public class I18nMessageDO implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", nullable = false, length = 128)
    private String id;

//    @Column(name = "exceptionCode", nullable = false)
//    private Integer exceptionCode;

    @ManyToOne
    @JoinColumn(name = "exceptionCode", referencedColumnName = "code")
    private ExceptionDO exception;

    @Column(name = "language", nullable = false, length = 8)
    private String language;

    @Column(name = "message", nullable = false, length = 2000)
    private String message;
}
