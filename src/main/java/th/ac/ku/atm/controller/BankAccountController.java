package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Transaction;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService accountService;

    public BankAccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        accountService.openAccount(bankAccount);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:bankaccount";
    }

    @GetMapping("/transaction/{id}")
    public String getTransactionPage(@PathVariable int id, Model model) {
        BankAccount account = accountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-transaction";
    }

    @PostMapping("/transaction/{id}")
    public String transaction(@PathVariable int id,
                              @RequestBody MultiValueMap<String, String> formData,
                              Model model) {

        String transactionType = formData.get("type").get(0);
        double transactionAmount = Double.parseDouble(formData.get("amount").get(0));

        Transaction transaction = new Transaction(
                id,
                transactionType,
                transactionAmount
        );

        accountService.makeTransaction(transaction);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model) {

        accountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }
}
