package com.lukasz;

/**
 * Created by LKubinski on 22/03/2017.
 */
public class UserPersistor {

    private final UserDBLayer userDBLayer;
    private final PasswordEncryption passwordEncryption;

    public UserPersistor(PasswordEncryption passwordEncryption, UserDBLayer userDBLayer) {
        this.passwordEncryption = passwordEncryption;
        this.userDBLayer = userDBLayer;
    }

    public void persistUserIfNew(User user, String password) {
        final UserDB existingUser = userDBLayer.getUserByLoginName(user.getLoginName());
        throwIfUserAlreadyExists(existingUser);
        String hexPassword = passwordEncryption.encrypt(password);
        UserDB userdb = new UserDB(user, hexPassword);
        userDBLayer.persist(userdb);
    }

    private void throwIfUserAlreadyExists(UserDB userDB) {
        final boolean userAlreadyExists = userDB != null;
        if (userAlreadyExists){
            throw new UserAlreadyExistsException();
        }
    }

}
