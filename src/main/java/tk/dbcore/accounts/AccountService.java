package tk.dbcore.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project: amugona
 * FileName: AccountService
 * Date: 2015-09-14
 * Time: 오후 2:21
 * Author: Hadeslee
 * Note:
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository repository;

}
