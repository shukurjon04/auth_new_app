package com.example.auth_app_new.Service;

import com.example.auth_app_new.Exeption.AppLoginException;
import com.example.auth_app_new.JWTTT.JWtProvider;
import com.example.auth_app_new.Service.SmsService.Sms;
import com.example.auth_app_new.Utilsap.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_app_new.Dto.ApiResponse;
import com.example.auth_app_new.Dto.RegisterUserDTo;
import com.example.auth_app_new.Entity.UserForNewsApp;
import com.example.auth_app_new.Exeption.NewsAppResourceNotFoundExeption;
import com.example.auth_app_new.Repository.RoleRepository;
import com.example.auth_app_new.Repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    private final JWtProvider jWtProvider;

    private final Sms sms;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder, RoleRepository roleRepository,@Lazy JWtProvider jWtProvider, Sms sms,@Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jWtProvider = jWtProvider;
        this.sms = sms;
        this.authenticationManager = authenticationManager;
    }



    public ApiResponse register(RegisterUserDTo dTo){
        if(userRepository.existsByPhonenumber(dTo.getPhone())){
            return new ApiResponse("phone number already used ", false, 409, null);
        }
        UserForNewsApp user = new UserForNewsApp();
        user.setFullName(dTo.getFullname());
        user.setPhonenumber(dTo.getPhone());
        user.setPasword(passwordEncoder.encode(dTo.getPassword()));
        user.setRole(roleRepository.findByName("user").orElseThrow(()-> new NewsAppResourceNotFoundExeption(" role name not found ")));
        String code = AppUtils.generateCode();
        sms.SendMessage(dTo.getPhone(),code);
        user.setVerificationCOde(code);
        UserForNewsApp save = userRepository.save(user);
        return new ApiResponse("Created", true, 201, save);
    }



    public ApiResponse verifyUser(String phoneNumber, String code) {
        UserForNewsApp userver = userRepository.findByPhonenumber(phoneNumber).orElseThrow(() -> new NewsAppResourceNotFoundExeption("user not found"));
        if (userver.getVerificationCOde().equals(code)) {
            userver.setVerificationCOde(null);
            userver.setEnabled(true);
            userRepository.save(userver);
            return new ApiResponse("verified",true,200,jWtProvider.generateToken(userver.getPhonenumber()));
        }
        return new ApiResponse("Error",false,400,null);

    }

    public ApiResponse ResendCode(String phoneNumber) {
        UserForNewsApp userver = userRepository.findByPhonenumber(phoneNumber).orElseThrow(() -> new NewsAppResourceNotFoundExeption("user not found"));
        if (userver.getVerificationCOde().isEmpty()||userver.getVerificationCOde()==null) {
            String s = AppUtils.generateCode();
            userver.setVerificationCOde(s);
            sms.SendMessage(userver.getPhonenumber(),s);
            UserForNewsApp save = userRepository.save(userver);
            return new ApiResponse("verified",true,200,save);
        }
        return new ApiResponse("Error",false,400,null);

    }

    public ApiResponse login(String phoneNumber,String password) {
        try {

            UserForNewsApp user = (UserForNewsApp) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phoneNumber, password)).getPrincipal();
            return new ApiResponse("code sended",true,200,jWtProvider.generateToken(user.getPhonenumber()));
        }catch (Exception e){
            throw  new AppLoginException("user something wrong!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String phonennumber) throws UsernameNotFoundException {
        return userRepository.findByPhonenumber(phonennumber).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }
}
