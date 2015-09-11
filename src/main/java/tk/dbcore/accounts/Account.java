package tk.dbcore.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    private String loginId;

    private String password;


}
