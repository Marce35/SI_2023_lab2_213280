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

                //Test case 2: user1 username gets set to user1 email so that it also covers the branches condition in lines 4-5, 11-12
                () -> {
                    User user1 = new User(null, "hardPassword", "test@gmail.com");
                    List<User> allUsers1 = new ArrayList<>();
                    allUsers1.add(new User("user123", "testPassword", "test@gmail.com"));
                    allUsers1.add(new User("admin123", "any", "random@mail"));
                    SILab2.function(user1, allUsers1);
                    assertEquals("test@gmail.com", user1.getUsername());
                },

                //Test case 3: user2 and allUsers2 have the same username to cover branches 13-14, and the user2 password is valid to cover lines 20 through 24
                () -> {
                    User user2 = new User("user123", "test$User", "user@gmail.com");
                    List<User> allUsers2 = Arrays.asList(
                            new User("user123", "passTest", "any")
                    );
                    boolean result2 = SILab2.function(user2, allUsers2);
                    assertFalse(result2);
                },

                //Test case 4: user3 has invalid password to cover branch 21-25
                () -> {
                    User user3 = new User("user123", "test test", "test-email");
                    List<User> allUsers3 = Arrays.asList(
                            new User("user123", "passTest", "any")
                    );
                    boolean result3 = SILab2.function(user3, allUsers3);
                    assertFalse(result3);
                },
                //Test case 5: user4 has invalid email to cover branch 7-15
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
                //Test case 1: covers arguments in the condition (True, any, any)
                () -> assertThrows(RuntimeException.class, () -> SILab2.function(null, Arrays.asList(new User("user123", "test", "testEmail")))),
                //Test case 2: covers arguments in the condition (False, True, any)
                () -> assertThrows(RuntimeException.class, () -> SILab2.function(new User("user123", null, "test"), Arrays.asList(new User("user123", "test", "testEmail")))),
                //Test case 3: covers arguments in the condition (False, False, True)
                () -> assertThrows(RuntimeException.class, () -> SILab2.function(new User("user123", "hardPassword", null), Arrays.asList(new User("user123", "test", "testEmail")))),
                //Test case 4: covers arguments in the condition (False, False, False)
                () -> {
                    User user = new User("user123", "hardPassword", "test@gmail.com");
                    boolean result = SILab2.function(user, Arrays.asList(new User("user123", "test", "testEmail")));
                    assertFalse(result);
                }
        );
    }
}
