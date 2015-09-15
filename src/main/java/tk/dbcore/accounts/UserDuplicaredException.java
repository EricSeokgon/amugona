package tk.dbcore.accounts;

/**
 * Project: amugona
 * FileName: UserDuplicaredException
 * Date: 2015-09-15
 * Time: 오전 10:49
 * Author: Hadeslee
 * Note:
 * To change this template use File | Settings | File Templates.
 */
public class UserDuplicaredException extends RuntimeException {

    String username;

    public UserDuplicaredException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
