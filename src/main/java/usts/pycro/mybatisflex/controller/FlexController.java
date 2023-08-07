package usts.pycro.mybatisflex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usts.pycro.mybatisflex.entity.Account;
import usts.pycro.mybatisflex.service.AccountService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Pycro
 * @version 1.0
 * 2023-08-07 1:08 PM
 */
@RequestMapping("flex")
@RestController
public class FlexController {
    @Resource
    private AccountService accountService;

    @GetMapping("listAccounts")
    public List<Account> getAllAccounts() {
        return accountService.list();
    }
}
