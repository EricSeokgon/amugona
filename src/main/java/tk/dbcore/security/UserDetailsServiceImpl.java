package tk.dbcore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.dbcore.accounts.Account;
import tk.dbcore.accounts.AccountRepository;

/**
 * Project: amugona
 * FileName: UserDetailsServiceImpl
 * Date: 2015-09-25
 * Time: 오후 5:16
 * Author: Hadeslee
 * Note:
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(account);
    }
}
