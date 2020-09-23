package th.ac.ku.atm.service;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Customer;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {
    
    private ArrayList<BankAccount> bankAccountList = new ArrayList<>();

    public void createBankAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }

    public List<BankAccount> getBankAccounts() {
        return new ArrayList<BankAccount>(bankAccountList);
    }

    public BankAccount findBankAccount(int id) {
        for (BankAccount bankAccount : bankAccountList) {
            if (bankAccount.getId() == id)
                return bankAccount;
        }
        return null;
    }
}