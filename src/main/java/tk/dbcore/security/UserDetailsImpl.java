package tk.dbcore.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import tk.dbcore.accounts.Account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Project: amugona
 * FileName: UserDetailsImpl
 * Date: 2015-09-25
 * Time: 오후 4:56
 * Author: Hadeslee
 * Note:
 * To change this template use File | Settings | File Templates.
 */

public class UserDetailsImpl extends User {

    public UserDetailsImpl(Account account) {
        super(account.getUsername(), account.getPassword(), authorities(account));
    }

    private static Collection<? extends GrantedAuthority> authorities(Account account) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (account.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }
}
