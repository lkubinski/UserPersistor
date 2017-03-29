package com.lukasz;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by LKubinski on 28/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDBLayerTest {

    @Mock
    SessionFactory mockSessionFactory;

    @Mock
    Session mockSession;

    @Mock
    Criteria mockCriteria;

    @Test
    public void ensureItQueriesTheDatabaseForTheUserBeforePersisting(){
        when(mockSessionFactory.openSession()).thenReturn(mockSession);
        when(mockSession.createCriteria(UserDB.class)).thenReturn(mockCriteria); // Why not use any matcher mockSession.createCriteria(any(UserDB.class))
        when(mockCriteria.add(any(Criterion.class))).thenReturn(mockCriteria);

        UserDBLayer userDBLayer = new UserDBLayer(mockSessionFactory);
        userDBLayer.getUserByLoginName("login name");


        verify(mockCriteria).add(any(Criterion.class));
        verify(mockCriteria).list();
    }
}