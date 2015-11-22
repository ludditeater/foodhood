package com.creative.foodwood.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.creative.foodwood.entity.UserEntity;

public class LoginDaoTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new ClassPathResource("spring-foodwood.xml").getPath());
        LoginDao personRepo = context.getBean(LoginDao.class);
        UserEntity personAchilles = new UserEntity();
        personAchilles.setFirstname("harsha");
        personAchilles.setLastname("Nimmaraju");
        personAchilles.setUsername("nimmaras");
        personAchilles.setPassword("AdpADP11");
        personAchilles.setUserrolename("Customer");
        
        personRepo.save(personAchilles);
        UserEntity personRambo = new UserEntity();
        personRambo.setFirstname("mahathi");
        personRambo.setLastname("Addanki");
        personRambo.setUsername("maha");
        personRambo.setPassword("Accenture123");
        personRambo.setUserrolename("chef");
        personRepo.save(personRambo);

        Iterable<UserEntity> personList = personRepo.findAll();
        System.out.println("Person List : ");
        for (UserEntity person : personList) {
            System.out.println(person);
        }

        System.out.println("Person with username is " + personRepo.fetchByUsernamePassword("nimmaras", "AdpADP11"));

        context.close();

    }
}
