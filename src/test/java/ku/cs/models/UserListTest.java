package ku.cs.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserListTest {

    @Test
    @DisplayName("User should be found in UserList")
    public void testUserListFindUser() {
        // Step 1: Add 3 users to UserList
        UserList userList = new UserList();
        userList.addUser("user01", "AAA");
        userList.addUser("user02", "BBB");
        userList.addUser("user03", "CCC");

        // Step 2: Find one of them
        String usernameToFind = "user02";
        User foundUser = userList.findUserByUsername(usernameToFind);

        // Step 3: Assert that UserList found User
        String expectedUsername = "user02";
        assertNotNull(foundUser, "User should be found");
        String actualUsername = foundUser.getUsername();
        assertEquals(expectedUsername, actualUsername, "Username should match expected value");
    }

    @Test
    @DisplayName("User can change password")
    public void testUserCanChangePassword() {
        // Step 1: Add 3 users to UserList
        UserList userList = new UserList();
        userList.addUser("user01", "AAA");
        userList.addUser("user02", "BBB");
        userList.addUser("user03", "CCC");

        // Step 2: Change password of one user
        String usernameToChange = "user02";
        String oldPassword = "BBB";
        String newPassword = "ZZZ";

        boolean passwordChanged = userList.changePassword(usernameToChange, oldPassword, newPassword);

        // Step 3: Assert that user can change password
        assertTrue(passwordChanged, "Password should be successfully changed");
        User loggedInUser = userList.login(usernameToChange, newPassword);
        assertNotNull(loggedInUser, "User should be found");
        assertEquals(usernameToChange, loggedInUser.getUsername(), "Username should match expected value");
        assertTrue(loggedInUser.validatePassword(newPassword), "Password should match expected value");
    }

    @Test
    @DisplayName("User with correct password can login")
    public void testUserListShouldReturnObjectIfUsernameAndPasswordIsCorrect() {
        // Step 1: Add 3 users to UserList
        UserList userList = new UserList();
        userList.addUser("user01", "AAA");
        userList.addUser("user02", "BBB");
        userList.addUser("user03", "CCC");

        // Step 2: Call login() with correct username and password
        String usernameToLogin = "user02";
        String passwordToLogin = "BBB";
        User loggedInUser = userList.login(usernameToLogin, passwordToLogin);

        // Step 3: Assert that User object is found
        assertNotNull(loggedInUser, "User should be found");
        assertEquals(usernameToLogin, loggedInUser.getUsername(), "Username should match expected value");
        assertTrue(loggedInUser.validatePassword(passwordToLogin), "Password should match expected value");
    }

    @Test
    @DisplayName("User with incorrect password cannot login")
    public void testUserListShouldReturnNullIfUsernameAndPasswordIsIncorrect() {
        // Step 1: Add 3 users to UserList
        UserList userList = new UserList();
        userList.addUser("user01", "AAA");
        userList.addUser("user02", "BBB");
        userList.addUser("user03", "CCC");

        // Step 2: Call login() with incorrect username or incorrect password

        // Test with incorrect username
        String incorrectUsername = "user99";
        String password = "BBB";
        User resultWithIncorrectUsername = userList.login(incorrectUsername, password);

        // Test with incorrect password
        String username = "user02";
        String incorrectPassword = "ZZZ";
        User resultWithIncorrectPassword = userList.login(username, incorrectPassword);

        // Step 3: Assert that the method returns null
        assertNull(resultWithIncorrectUsername, "Login should return null for incorrect username");
        assertNull(resultWithIncorrectPassword, "Login should return null for incorrect password");
    }
}
