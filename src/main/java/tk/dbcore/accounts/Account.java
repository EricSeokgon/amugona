package tk.dbcore.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: amugona
 * FileName: Account
 * Date: 2015-09-11
 * Time: 오후 4:03
 * Author: sklee
 * Note:
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    private String fullName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joined;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    private boolean admin;


}
