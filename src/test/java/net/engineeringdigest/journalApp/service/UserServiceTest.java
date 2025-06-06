package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.apache.coyote.http11.Constants.a;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
public class UserServiceTest {

//    @Autowired
//    UserRepo userRepo;
//
//    @Autowired
//    UserService userService;

//    @Disabled
//    @Test
//    public void testFindByUserName() {
//        assertEquals(4, 2 + 1);
//        assertNotNull(userRepo.findByUserName("Rana"));
//    }

//    @Disabled
//    @ParameterizedTest
//    @ValueSource(strings = {
//            "Ram",
//            "Rajat",
//            "Rana"
//    })
//    public void testFindByUserName(String username) {
//        assertNotNull(userRepo.findByUserName(username), "FAILED for" + username);
//    }

//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentProvider.class)
//    public void testNewSaveUser(User user){
//        assertNotNull(userService.saveNewEntry(user));
//    }
//
//    @Disabled
//    @Test
//    public void testDeleteUser() {
//        User user = userRepo.findByUserName("Rana");
//        assertFalse(user.getJournalEntryList().isEmpty());
//    }

//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "2,10,12",
//            "3,3,9"
//    })
//    public void parametrizedTest(int a, int b, int expected) {
//        assertEquals(expected, a + b);
//    }

}
