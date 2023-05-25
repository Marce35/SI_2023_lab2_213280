import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    @Test
    public void everyBranchTest(){


        assertAll(
                //Test case 1: user == null, allUsers = any
                () -> assertThrows(RuntimeException.class, () -> SILab2.function(null, Arrays.asList(new User("user123", "testPassword", "testEmail")))),

                () -> {
                    User user1 = new User(null, "hardPassword", "test@gmail.com");
                    List<User> allUsers1 = new ArrayList<>();
                    allUsers1.add(new User("user123", "testPassword", "test@gmail.com"));
                    allUsers1.add(new User("admin123", "any", "random@mail"));
                    SILab2.function(user1, allUsers1);
                    assertEquals("test@gmail.com", user1.getUsername());
                },

                () -> {
                    User user2 = new User("user123", "test$User", "user@gmail.com");
                    List<User> allUsers2 = Arrays.asList(
                            new User("user123", "passTest", "any")
                    );
                    boolean result2 = SILab2.function(user2, allUsers2);
                    assertFalse(result2);
                },

                () -> {
                    User user3 = new User("user123", "test test", "test-email");
                    List<User> allUsers3 = Arrays.asList(
                            new User("user123", "passTest", "any")
                    );
                    boolean result3 = SILab2.function(user3, allUsers3);
                    assertFalse(result3);
                },

                () -> {
                    User user4 = new User("user123", "test12345", "test-email");
                    List<User> allUser4 = Arrays.asList(
                            new User("user123", "passTest", "any")
                    );
                    boolean result4 = SILab2.function(user4, allUser4);
                    assertFalse(result4);
                }
////This test is not required to fulfill the every branch method, but it covers the case when the
////"same" variable is changed in the 8 branch. When the list is empty and the password is valid it will return true in the  24th branch.
//                () -> {
//                    User random = new User("user123", "pass$test", "test@gmail.com");
//                    List<User> emptyUserList = new ArrayList<>();
//                    boolean result5 = SILab2.function(random, emptyUserList);
//                    assertTrue(result5);
//                }


        );

    }
    @Test
    public void multipleConditionsTest(){

        assertAll(

                () -> assertThrows(RuntimeException.class, () -> SILab2.function(null, Arrays.asList(new User("user123", "test", "testEmail")))),
                () -> assertThrows(RuntimeException.class, () -> SILab2.function(new User("user123", null, "test"), Arrays.asList(new User("user123", "test", "testEmail")))),
                () -> assertThrows(RuntimeException.class, () -> SILab2.function(new User("user123", "hardPassword", null), Arrays.asList(new User("user123", "test", "testEmail")))),
                () -> {
                    User user = new User("user123", "hardPassword", "test@gmail.com");
                    boolean result = SILab2.function(user, Arrays.asList(new User("user123", "test", "testEmail")));
                    assertFalse(result);
                }
        );
    }
}
