package com.example.auth_app_new.Entity.Component;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.auth_app_new.Entity.Role;
import com.example.auth_app_new.Entity.UserForNewsApp;
import com.example.auth_app_new.Enumss.Permission;
import static com.example.auth_app_new.Enumss.Permission.ADD_COMMENT;
import static com.example.auth_app_new.Enumss.Permission.DELETE_MY_COMMENT;
import static com.example.auth_app_new.Enumss.Permission.EDIT_COMMENT;
import static com.example.auth_app_new.Enumss.Permission.VIEW_COMMENT;
import static com.example.auth_app_new.Enumss.Permission.VIEW_POST;
import com.example.auth_app_new.Repository.RoleRepository;
import com.example.auth_app_new.Repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Value(value="${spring.sql.init.mode}")
    private String SqlInitMode;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository  userRepository;
    @Override
    public void run(String... args) throws Exception {
       if(SqlInitMode.equals("always")){
        Role roleadmin = new Role();
        roleadmin.setName("admin");
        roleadmin.setDescription("barcha ishni qiladi");
        roleadmin.setPermissions(Set.of(Permission.values()));
        Role saverole = roleRepository.save(roleadmin);

        Role roleuser = new Role();
        roleuser.setName("user");
        roleuser.setDescription("barcha ishni qila olmaydi oddiy foydalanuvchi");
        roleuser.setPermissions(Set.of(VIEW_POST,ADD_COMMENT,EDIT_COMMENT,DELETE_MY_COMMENT,VIEW_COMMENT));
        Role saveroleuser = roleRepository.save(roleuser);

        UserForNewsApp admin = new UserForNewsApp();
        admin.setFullName("Shukurjon");
        admin.setPhonenumber("998912868589");
        admin.setEnabled(true);
        admin.setPasword(passwordEncoder.encode("rootadmin"));
        admin.setRole(saverole);
        userRepository.save(admin);


        UserForNewsApp user = new UserForNewsApp();
        user.setFullName("Boqijon");
        user.setPhonenumber("998912860456");
        user.setEnabled(true);
        user.setPasword(passwordEncoder.encode("rootuser"));
        user.setRole(saverole);
        userRepository.save(user);


       }
    }

}
