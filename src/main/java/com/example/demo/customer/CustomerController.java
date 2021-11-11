package com.example.demo.customer;

import java.util.List;

import com.example.demo.form.PersonForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customers")
    public String greeting(CustomerForm customerForm, Model model) {
        model.addAttribute("customers", repository.findAll());
        return "customers";
    }

    @PostMapping("/customers")
    public String checkPersonInfo(@Validated CustomerForm customerForm, BindingResult bindingResult, Model model) {
        model.addAttribute("customers", repository.findAll());

        if (bindingResult.hasErrors()) {
            // find email in repository
            return "customers";
        }

        List<CustomerEntity> list = repository.findByEmail(customerForm.getEmail());
        if (list.size() > 0) {
            model.addAttribute("emailExist", "Email already exist");
            return "customers";
        }
        
        repository
                .save(new CustomerEntity(customerForm.getName(), customerForm.getLastName(), customerForm.getEmail()));
        return "redirect:/customers";
    }
}
