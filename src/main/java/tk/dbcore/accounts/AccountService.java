package tk.dbcore.accounts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

    @Autowired
    private ModelMapper modelMapper;

    public Account createAccount(AccountDto.Create dto){
        /*Account account =new Account();
        account.setUsername(dto.getUsername());
        account.setPassword(dto.getPassowrd());*/

        Account account = modelMapper.map(dto, Account.class);
        Date now = new Date();
        account.setJoined(now);
        account.setUpdated(now);
        return repository.save(account);
    }

}
