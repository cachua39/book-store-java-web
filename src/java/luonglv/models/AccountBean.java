/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.models;

import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;
import luonglv.daos.AccountDAO;
import luonglv.dtos.AccountDTO;

/**
 *
 * @author LeVaLu
 */
public class AccountBean implements Serializable{
    private final AccountDAO accountDAO;
    private String userId, password;
    private AccountDTO accountDTO;

    public AccountBean() {
        accountDAO = new AccountDAO();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public String checkLogin() throws NamingException, SQLException {
        return accountDAO.checkLogin(userId, password);
    }
    
    public boolean insert() throws NamingException, SQLException {
        return accountDAO.insert(accountDTO);
    }
}
