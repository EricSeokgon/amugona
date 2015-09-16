package tk.dbcore.accounts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tk.dbcore.commons.ErrorResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    private AccountRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Create create,
                                        BindingResult result) {
        if (result.hasErrors()) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("잘못된 요청입니다.");
            errorResponse.setCode("bad.request");
            // TODO bindingResult 안에 들어있는 에러 정보 사용하기.
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // 1.리턴 타임으로 판단.
        // 2.파라미터 이용.
        Account newAccount = service.createAccount(create);
        return new ResponseEntity<>(modelMapper.map(newAccount, AccountDto.Response.class), HttpStatus.CREATED);

    }

    @ExceptionHandler(UserDuplicaredException.class)
    public ResponseEntity handleUserDuplicatedException(UserDuplicaredException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("[" + e.getUsername() + "] 중복된 username 입니다.");
        errorResponse.setCode("duplicated.username.exception");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //TODO stream() vs parallelStream()
    //TODO 로깅
    //TODO HEATEOAS
    //TODO 뷰
    // NSPA 1. Thymeleaf
    // SPA 2. 리엑트
    // /accounts?page=0&sizw=2&sort=usernmae,desc&sort=joined,desc

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity getAccounts(Pageable pageable) {
        Page<Account> page = repository.findAll(pageable);
        List<AccountDto.Response> content = page.getContent().parallelStream()
                .map(account -> modelMapper.map(account, AccountDto.Response.class))
                .collect(Collectors.toList());
        PageImpl<AccountDto.Response> result = new PageImpl<>(content, pageable, page.getTotalElements());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
