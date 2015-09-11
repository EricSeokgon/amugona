package tk.dbcore.accounts;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Project: amugona
 * FileName: AccountTest
 * Date: 2015-09-11
 * Time: 오후 4:40
 * Author: sklee
 * Note:
 * To change this template use File | Settings | File Templates.
 */
public class AccountTest {

    @Test
    public void getterSetter() {
        Account account = new Account();
        account.setLoginId("sklee");
        account.setPassword("password");

        assertThat(account.getLoginId(), is("sklee"));
        assertThat(account.getPassword(), is("password"));
    }


}