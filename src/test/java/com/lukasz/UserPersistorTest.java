package com.lukasz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by LKubinski on 22/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserPersistorTest {

    @Mock
    PasswordEncryption mockPasswordEncryption;

    @Mock
    UserDBLayer mockUserDBLayer;

    @Test(expected = UserAlreadyExistsException.class)
    public void ensureItDoesNotSaveIfTheUserAlreadyExists(){
        final UserDB mockUserDB = mock(UserDB.class);
        final UserPersistor persistor = new UserPersistor(mockPasswordEncryption, mockUserDBLayer);

        when(mockUserDBLayer.getUserByLoginName(any(String.class))).thenReturn(mockUserDB);
        persistor.persistUserIfNew(mock(User.class), "password");
    }


    @Test
    public void ensureItDoesSaveIfTheUserIsCompletelyNew(){
        final UserPersistor persistor = new UserPersistor(mockPasswordEncryption, mockUserDBLayer);

        persistor.persistUserIfNew(mock(User.class), "password");

        verify(mockUserDBLayer).persist(any(UserDB.class));
    }

    @Test
    public void ensureItSavesThePasswordAsAHash() {
        final String password = "password";
        final UserPersistor persistor = new UserPersistor(mockPasswordEncryption, mockUserDBLayer);

        persistor.persistUserIfNew(mock(User.class), password);
        verify(mockUserDBLayer).persist(new UserDB(any(User.class), "passwordASHex"));
    }

}