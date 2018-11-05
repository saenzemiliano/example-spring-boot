/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.util.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author esaenz
 */
public class JdbcAuthoritiesPopulator implements LdapAuthoritiesPopulator {
    // ~ Static fields/initializers
    // =====================================================================================

    private static final Log logger = LogFactory
            .getLog(JdbcAuthoritiesPopulator.class);

    @Nullable
    private JdbcTemplate jdbcTemplate;

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
    private String rolePrefix = "ROLE_";

    /**
     * Should we convert the role name to uppercase
     */
    private boolean convertToUpperCase = true;

    private String authoritiesByUsernameQuery;
    private String groupAuthoritiesByUsernameQuery;
    private String usersByUsernameQuery;
    private boolean enableAuthorities = true;
    private boolean enableGroups;

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    public JdbcAuthoritiesPopulator() {
        this.jdbcTemplate = null;
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
                String roleName = rs.getString(2);
                if (convertToUpperCase) {
                    roleName = roleName.toUpperCase();
                }

                JdbcAuthoritiesPopulator.this.logger.info("loadUserAuthorities(...) -> ["+ username + ","+ roleName +"]");
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
                String roleName = rs.getString(3);
                if (convertToUpperCase) {
                    roleName = roleName.toUpperCase();
                }
                JdbcAuthoritiesPopulator.this.logger.info("loadGroupAuthorities(...) -> ["+ username + ","+ roleName +"]");
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
            this.logger.debug("User '" + username
                    + "' has no authorities and will be treated as 'not found'");

            throw new UsernameNotFoundException(this.messages.getMessage(
                    "JdbcDaoImpl.noAuthority", new Object[]{username},
                    "User {0} has no GrantedAuthority"));
        }

        return dbAuths;
    }

    /**
     * Sets the prefix which will be prepended to the values loaded from the
     * directory. Defaults to "ROLE_" for compatibility with <tt>RoleVoter</tt>.
     *
     * @param rolePrefix
     */
    public void setRolePrefix(String rolePrefix) {
        Assert.notNull(rolePrefix, "rolePrefix must not be null");
        this.rolePrefix = rolePrefix;
    }

    /**
     * Returns the role prefix used by this populator Method available so that
     * classes extending this can override
     *
     * @return the role prefix
     * @see #setRolePrefix(String)
     */
    protected final String getRolePrefix() {
        return this.rolePrefix;
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
     * Set the JdbcTemplate for this DAO explicitly, as an alternative to
     * specifying a DataSource.
     *
     * @param jdbcTemplate
     */
    public final void setJdbcTemplate(@Nullable JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        initTemplateConfig();
    }

    /**
     * Return the JdbcTemplate for this DAO, pre-initialized with the DataSource
     * or set explicitly.
     *
     * @return
     */
    @Nullable
    public final JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    /**
     * Initialize the template-based configuration of this DAO. Called after a
     * new JdbcTemplate has been set, either directly or through a DataSource.
     * <p>
     * This implementation is empty. Subclasses may override this to configure
     * further objects based on the JdbcTemplate.
     *
     * @see #getJdbcTemplate()
     */
    protected void initTemplateConfig() {
    }

    /**
     * Set the JDBC DataSource to be used by this DAO.
     *
     * @param dataSource
     */
    public final void setDataSource(DataSource dataSource) {
        if (this.jdbcTemplate == null || dataSource != this.jdbcTemplate.getDataSource()) {
            this.jdbcTemplate = createJdbcTemplate(dataSource);
            initTemplateConfig();
        }
    }

    /**
     * Create a JdbcTemplate for the given DataSource. Only invoked if
     * populating the DAO with a DataSource reference!
     * <p>
     * Can be overridden in subclasses to provide a JdbcTemplate instance with
     * different configuration, or a custom JdbcTemplate subclass.
     *
     * @param dataSource the JDBC DataSource to create a JdbcTemplate for
     * @return the new JdbcTemplate instance
     * @see #setDataSource
     */
    protected JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * Return the JDBC DataSource used by this DAO.
     *
     * @return
     */
    @Nullable
    public final DataSource getDataSource() {
        return (this.jdbcTemplate != null ? this.jdbcTemplate.getDataSource() : null);
    }

}
