package usts.pycro.mybatisflex.mapper;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import usts.pycro.mybatisflex.entity.Account;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-30 9:19 PM
 */
@Repository
public interface AccountMapper extends BaseMapper<Account> {

    Account selectByName(@Param("name") String name);
}
