package org.sid.comptecqrseventsourcing.query.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.sid.comptecqrseventsourcing.commonapi.queries.GetAccountByIdQuery;
import org.sid.comptecqrseventsourcing.commonapi.queries.GetAllAccountsQuery;
import org.sid.comptecqrseventsourcing.query.entities.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/accounts")
@Slf4j
@AllArgsConstructor
public class AccountQueryController {

    private QueryGateway queryGateway;


    @GetMapping("/allAccounts")
    public List<Account> accountList()
    {
        List<Account> response = queryGateway.query(new GetAllAccountsQuery(), ResponseTypes.multipleInstancesOf(Account.class)).join();
       return response;
    }
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable String id)
    {
        Account response = queryGateway.query(new GetAccountByIdQuery(id), ResponseTypes.instanceOf(Account.class)).join();
        return response;
    }

}
