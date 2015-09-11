package tk.dbcore.accounts;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Spring boot";
    }

}
