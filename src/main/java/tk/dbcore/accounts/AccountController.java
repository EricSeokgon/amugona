package tk.dbcore.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Project: amugona
 * FileName: AccountController
 * Date: 2015-09-11
 * Time: 오후 4:08
 * Author: sklee
 * Note:
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService service;

    @Autowired
    private  AccountRepository repository;

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Create create,
                                        BindingResult result) {
        if (result.hasErrors()) {
            // TODO 에러 응답 본문 추가하기
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Account newAccount = service.createAccount(create);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }
}
