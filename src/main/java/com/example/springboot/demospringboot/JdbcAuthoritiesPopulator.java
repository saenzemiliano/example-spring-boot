/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author esaenz
 */
public class JdbcAuthoritiesPopulator extends JdbcDaoSupport implements LdapAuthoritiesPopulator {
    // ~ Static fields/initializers
    // =====================================================================================

    private static final Log LOGGER = LogFactory
            .getLog(JdbcAuthoritiesPopulator.class);

    // ~ Instance fields
    // ================================================================================================

    public static final String DEF_USERS_BY_USERNAME_QUERY = "select username,password,enabled "
            + "from users " + "where username = ?";
    public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select username,authority "
            + "from authorities " + "where username = ?";
    public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY = "select g.id, g.group_name, ga.authority "
            + "from groups g, group_members gm, group_authorities ga "
            + "where gm.username = ? " + "and g.id = ga.group_id "
            + "and g.id = gm.group_id";
    /**
     * The role prefix that will be prepended to each role name
     */
    private String rolePrefix = "" /*ROLE_*/;

    /**
     * Should we convert the role name to uppercase
     */
    private boolean convertToUpperCase = true;

    private String authoritiesByUsernameQuery;
    private String groupAuthoritiesByUsernameQuery;
    private String usersByUsernameQuery;
    private boolean enableAuthorities = true;
    private boolean enableGroups;

    // ~ Constructors
    // ===================================================================================================
    public JdbcAuthoritiesPopulator() {
        this.usersByUsernameQuery = DEF_USERS_BY_USERNAME_QUERY;
        this.authoritiesByUsernameQuery = DEF_AUTHORITIES_BY_USERNAME_QUERY;
        this.groupAuthoritiesByUsernameQuery = DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY;
    }

    JdbcAuthoritiesPopulator(DataSource dataSource) {
        this();
        this.setDataSource(dataSource);
    }

    /**
     * Allows subclasses to add their own granted authorities to the list to be
     * returned in the <tt>UserDetails</tt>.
     *
     * @param username the username, for use by finder methods
     * @param authorities the current granted authorities, as populated from the
     * <code>authoritiesByUsername</code> mapping
     */
    protected void addCustomAuthorities(String username,
            List<GrantedAuthority> authorities) {
    }

    /**
     * Loads authorities by executing the SQL from
     * <tt>authoritiesByUsernameQuery</tt>.
     *
     * @return a list of GrantedAuthority objects for the user
     */
    protected List<GrantedAuthority> loadUserAuthorities(String username) {
        return getJdbcTemplate().query(this.authoritiesByUsernameQuery, new String[]{username}, new RowMapper<GrantedAuthority>() {
            @Override
            public GrantedAuthority mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                String roleName = JdbcAuthoritiesPopulator.this.rolePrefix + rs.getString(2);
                if (convertToUpperCase) {
                    roleName = roleName.toUpperCase();
                }

                LOGGER.info("loadUserAuthorities(...) -> [" + username + "," + roleName + "]");
                return new SimpleGrantedAuthority(roleName);
            }
        });
    }

    /**
     * Loads authorities by executing the SQL from
     * <tt>groupAuthoritiesByUsernameQuery</tt>.
     *
     * @param username
     * @return a list of GrantedAuthority objects for the user
     */
    protected List<GrantedAuthority> loadGroupAuthorities(String username) {
        return getJdbcTemplate().query(this.groupAuthoritiesByUsernameQuery, new String[]{username}, new RowMapper<GrantedAuthority>() {
            @Override
            public GrantedAuthority mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                String roleName = JdbcAuthoritiesPopulator.this.rolePrefix + rs.getString(3);
                if (convertToUpperCase) {
                    roleName = roleName.toUpperCase();
                }
                LOGGER.info("loadGroupAuthorities(...) -> [" + username + "," + roleName + "]");
                return new SimpleGrantedAuthority(roleName);
            }
        });
    }

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
        Set<GrantedAuthority> dbAuthsSet = new HashSet<>();
        if (this.enableAuthorities) {
            dbAuthsSet.addAll(loadUserAuthorities(username));
        }

        if (this.enableGroups) {
            dbAuthsSet.addAll(loadGroupAuthorities(username));
        }

        List<GrantedAuthority> dbAuths = new ArrayList<>(dbAuthsSet);

        addCustomAuthorities(username, dbAuths);

        if (dbAuths.isEmpty()) {
            LOGGER.debug("User '" + username
                    + "' has no authorities and will be treated as 'not found'");
            throw new UsernameNotFoundException(MessageFormat.format(
                    "User {0} has no GrantedAuthority", 
                    new Object[]{username}));
        }

        return dbAuths;
    }

    /**
     * Convert the role to uppercase
     *
     * @param convertToUpperCase
     */
    public void setConvertToUpperCase(boolean convertToUpperCase) {
        this.convertToUpperCase = convertToUpperCase;
    }

    /**
     * Returns true if role names are converted to uppercase Method available so
     * that classes extending this can override
     *
     * @return true if role names are converted to uppercase.
     * @see #setConvertToUpperCase(boolean)
     */
    protected final boolean isConvertToUpperCase() {
        return this.convertToUpperCase;
    }

    /**
     * Allows the default query string used to retrieve authorities based on
     * username to be overridden, if default table or column names need to be
     * changed. The default query is {@link #DEF_AUTHORITIES_BY_USERNAME_QUERY};
     * when modifying this query, ensure that all returned columns are mapped
     * back to the same column positions as in the default query.
     *
     * @param queryString The SQL query string to set
     */
    public void setAuthoritiesByUsernameQuery(String queryString) {
        this.authoritiesByUsernameQuery = queryString;
    }

    protected String getAuthoritiesByUsernameQuery() {
        return this.authoritiesByUsernameQuery;
    }

    /**
     * Allows the default query string used to retrieve group authorities based
     * on username to be overridden, if default table or column names need to be
     * changed. The default query is
     * {@link #DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY}; when modifying this
     * query, ensure that all returned columns are mapped back to the same
     * column positions as in the default query.
     *
     * @param queryString The SQL query string to set
     */
    public void setGroupAuthoritiesByUsernameQuery(String queryString) {
        this.groupAuthoritiesByUsernameQuery = queryString;
    }

    /**
     * Allows a default role prefix to be specified. If this is set to a
     * non-empty value, then it is automatically prepended to any roles read in
     * from the db. This may for example be used to add the <tt>ROLE_</tt>
     * prefix expected to exist in role names (by default) by some other Spring
     * Security classes, in the case that the prefix is not already present in
     * the db.
     *
     * @param rolePrefix the new prefix
     */
    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }

    protected String getRolePrefix() {
        return this.rolePrefix;
    }

    /**
     * Enables loading of authorities (roles) from the authorities table.
     * Defaults to true
     * @param enableAuthorities
     */
    public void setEnableAuthorities(boolean enableAuthorities) {
        this.enableAuthorities = enableAuthorities;
    }

    protected boolean getEnableAuthorities() {
        return this.enableAuthorities;
    }

    /**
     * Enables support for group authorities. Defaults to false
     *
     * @param enableGroups
     */
    public void setEnableGroups(boolean enableGroups) {
        this.enableGroups = enableGroups;
    }

    protected boolean getEnableGroups() {
        return this.enableGroups;
    }

    /**
     * Allows the default query string used to retrieve users based on username
     * to be overridden, if default table or column names need to be changed.
     * The default query is {@link #DEF_USERS_BY_USERNAME_QUERY}; when modifying
     * this query, ensure that all returned columns are mapped back to the same
     * column positions as in the default query. If the 'enabled' column does
     * not exist in the source database, a permanent true value for this column
     * may be returned by using a query similar to
     *
     * <pre>
     * &quot;select username,password,'true' as enabled from users where username = ?&quot;
     * </pre>
     *
     * @param usersByUsernameQueryString The query string to set
     */
    public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
        this.usersByUsernameQuery = usersByUsernameQueryString;
    }

    public String getUsersByUsernameQuery() {
        return this.usersByUsernameQuery;
    }
}
