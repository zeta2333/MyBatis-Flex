package usts.pycro.mybatisflex.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import usts.pycro.mybatisflex.entity.Account;
import usts.pycro.mybatisflex.mapper.AccountMapper;
import usts.pycro.mybatisflex.service.AccountService;

/**
 * @author Pycro
 * @version 1.0
 * 2023-08-07 1:09 PM
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
}
