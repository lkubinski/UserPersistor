package com.lukasz;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by LKubinski on 22/03/2017.
 */
//@Entity
//@Table(name = "User")
public class UserDB {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Embedded
    private final User user;

    @Column(name = "HEX_PASSWORD")
    private final String hexPassword;

    public UserDB(User user, String hexPassword) {
        this.user = user;
        this.hexPassword = hexPassword;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDB userDB = (UserDB) o;

        if (!id.equals(userDB.id)) return false;
        if (!user.equals(userDB.user)) return false;
        return hexPassword.equals(userDB.hexPassword);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + hexPassword.hashCode();
        return result;
    }
}
