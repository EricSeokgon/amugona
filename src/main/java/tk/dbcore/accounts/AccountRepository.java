package tk.dbcore.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: amugona
 * FileName: AccountRepository
 * Date: 2015-09-14
 * Time: 오후 2:17
 * Author: Hadeslee
 * Note:
 * To change this template use File | Settings | File Templates.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
