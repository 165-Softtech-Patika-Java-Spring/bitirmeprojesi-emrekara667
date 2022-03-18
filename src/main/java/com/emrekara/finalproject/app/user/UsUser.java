package com.emrekara.finalproject.app.user;

import com.emrekara.finalproject.app.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "US_USER")
@Getter
@Setter
public class UsUser extends BaseEntity {

    @Id
    @SequenceGenerator(name = "UsUser", sequenceName = "US_USER_ID_SEQ")
    @GeneratedValue(generator = "UsUser")
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 100, nullable = false)
    private String surname;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_NAME", length = 100,nullable = false)
    private String userName;

}
