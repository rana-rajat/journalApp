package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

//@SpringBootTest
@ActiveProfiles("dev")
public class UserDetailServiceImplTest {

//    @Autowired
//    UserDetailServiceImpl userDetailService;
//
//    @MockBean
//    UserRepo userRepo;

//    @InjectMocks
//    UserDetailServiceImpl userDetailService;
//
//    @Mock
//    UserRepo userRepo;

//    @BeforeEach()
//    void setUserRepo(){
//        MockitoAnnotations.initMocks(this);
//    }

//    @Test
//   void loadUserByUsernameTest(){
//       when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Rajat").password("Rajat").roles(new ArrayList<>()) .build());
//       UserDetails user = userDetailService.loadUserByUsername("Rajat");
//        Assertions.assertNotNull(user);
//   }
}
