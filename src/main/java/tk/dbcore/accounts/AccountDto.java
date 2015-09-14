package tk.dbcore.accounts;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Project: amugona
 * FileName: AccountDto
 * Date: 2015-09-14
 * Time: 오후 2:34
 * Author: Hadeslee
 * Note:
 * To change this template use File | Settings | File Templates.
 */
public class AccountDto {

    @Data
    public static class Create{
        @NotBlank
        @Size(min = 5)
        private String username;

        @NotBlank
        @Size(min = 5)
        private String password;
    }
}
