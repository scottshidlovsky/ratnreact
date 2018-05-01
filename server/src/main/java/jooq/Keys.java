/**
 * This class is generated by jOOQ
 */
package jooq;


import javax.annotation.Generated;

import jooq.tables.Todo;
import jooq.tables.User;
import jooq.tables.records.TodoRecord;
import jooq.tables.records.UserRecord;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>PUBLIC</code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<TodoRecord, Long> IDENTITY_TODO = Identities0.IDENTITY_TODO;
    public static final Identity<UserRecord, Long> IDENTITY_USER = Identities0.IDENTITY_USER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<TodoRecord> CONSTRAINT_2 = UniqueKeys0.CONSTRAINT_2;
    public static final UniqueKey<UserRecord> CONSTRAINT_27 = UniqueKeys0.CONSTRAINT_27;
    public static final UniqueKey<UserRecord> CONSTRAINT_27E = UniqueKeys0.CONSTRAINT_27E;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<TodoRecord, Long> IDENTITY_TODO = createIdentity(Todo.TODO, Todo.TODO.ID);
        public static Identity<UserRecord, Long> IDENTITY_USER = createIdentity(User.USER, User.USER.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<TodoRecord> CONSTRAINT_2 = createUniqueKey(Todo.TODO, "CONSTRAINT_2", Todo.TODO.ID);
        public static final UniqueKey<UserRecord> CONSTRAINT_27 = createUniqueKey(User.USER, "CONSTRAINT_27", User.USER.ID);
        public static final UniqueKey<UserRecord> CONSTRAINT_27E = createUniqueKey(User.USER, "CONSTRAINT_27E", User.USER.USERNAME);
    }
}
