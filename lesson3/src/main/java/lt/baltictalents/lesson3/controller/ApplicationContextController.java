package lt.baltictalents.lesson3.controller;

import lt.baltictalents.lesson3.beans.example.BeansGetter;
import lt.baltictalents.lesson3.beans.example.dao.AccountDao;
import lt.baltictalents.lesson3.beans.example.dao.AllDao;
import lt.baltictalents.lesson3.beans.example.dao.UserDao;
import lt.baltictalents.lesson3.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationContextController {
    @Autowired
    BeansGetter beansGetter;

    @Autowired
    @Qualifier("accountDaoAnnotated")
    AccountDao accountDao;

    @Autowired
    MessageService emailService;

    @Autowired
    MessageService smsService;

    @GetMapping(value = "/account")
    @ResponseBody
    public String getAccountDao() {
        return beansGetter.getAccountDao().toString();
    }

    @GetMapping(value = "/user")
    @ResponseBody
    public String getUserDao() {
        UserDao userDao = beansGetter.getUserDao();

        return String.format("%s %s %s", userDao.getName(), userDao.getAge(), userDao.getSurname());
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public String getAll() {
        AllDao allDao = beansGetter.getAllDao();

        return String.format("all: %s %s", allDao.getUserDao(), allDao.getAccountDao());
    }

    @GetMapping(value = "/all-constructor")
    @ResponseBody
    public String getAllConstructor() {
        AllDao allDao = beansGetter.getAllDao();

        return String.format("all-constructor: %s %s", allDao.getUserDao(), allDao.getAccountDao());
    }

    @GetMapping(value = "/account-annotated")
    @ResponseBody
    public String getAccountAnnotated() {

        return String.format("account-annotated: %s", accountDao);
    }

    @GetMapping(value = "/email")
    @ResponseBody
    public String getEmail() {
        String message = emailService.getMessage("Hello from email", "viktor@abc.com");

        return message;
    }


    @GetMapping(value = "/sms")
    @ResponseBody
    public String getSms() {
        String message = emailService.getMessage("Hello from sms", "viktor@abc.com");

        return message;
    }
}
