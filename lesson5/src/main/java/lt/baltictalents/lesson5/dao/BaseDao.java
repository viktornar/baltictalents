package lt.baltictalents.lesson5.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Base superclass for all DAO's.
 *
 * @author Sindre Mehus
 * @author v.nareiko
 */
class BaseDao {
    private Logger logger = LoggerFactory.getLogger(BaseDao.class);
    private DaoHelper daoHelper;

    public void setDaoHelper(DaoHelper daoHelper) {
        this.daoHelper = daoHelper;
    }

    /**
     * Returns a JDBC template for performing database operations.
     *
     * @return The JDBC template.
     */
    protected JdbcTemplate getJdbcTemplate() {
        return daoHelper.getJdbcTemplate();
    }

    /**
     * Similar to {@link #getJdbcTemplate()}, but with named parameters.
     *
     * @return The JDBC template
     */
    protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return daoHelper.getNamedParameterJdbcTemplate();
    }

    /**
     * Replace column names with question marks.
     *
     * @param columns The columns in table
     * @return The columns as question marks
     */
    public String questionMarks(@NonNull String columns) {
        int count = columns.split(", ").length;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append('?');
            if (i < count - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    /**
     * Add prefix to given column names.
     *
     * @param columns The comma seperated columns in string e.g. \"NAME1, NAME2\".
     * @param prefix  The prefix to use in table column.
     * @return columns names with given prefix
     */
    protected String prefix(@NonNull String columns, @NonNull String prefix) {
        StringBuilder builder = new StringBuilder();
        for (String s : columns.split(", ")) {
            builder.append(prefix).append(".").append(s).append(",");
        }
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 1);
        }
        return builder.toString();
    }

    /**
     * Update table by given values.
     *
     * @param sql  The sql statement to execute.
     * @param args The column values to update in table.
     * @return The number of updated rows.
     */
    protected int update(@NonNull String sql, @NonNull Object... args) {
        long t = System.nanoTime();
        int result = getJdbcTemplate().update(sql, args);
        log(sql, t);
        return result;
    }

    /**
     * Write sql execution time logs. For internal use only.
     *
     * @param sql           The sql statement that are executed.
     * @param startTimeNano The sql statament start execution time in nano sec.
     */
    private void log(String sql, long startTimeNano) {
        long millis = (System.nanoTime() - startTimeNano) / 1000000L;

        // Log queries that take more than 2 seconds.
        if (millis > TimeUnit.SECONDS.toMillis(2L)) {
            logger.debug(millis + " ms:  " + sql);
        }
    }

    /**
     * Execute sql query
     *
     * @param sql       The sql statement that are executed.
     * @param rowMapper @see RowMapper.
     * @param <T>       The type that will be mapped to location.
     * @return The query location as list with given type.
     */
    protected <T> List<T> query(@NonNull String sql, @NonNull RowMapper rowMapper) {
        long t = System.nanoTime();
        List<T> result = getJdbcTemplate().query(sql, rowMapper);
        log(sql, t);
        return result;
    }

    /**
     * @param sql       The sql statement that are executed.
     * @param rowMapper @see RowMapper.
     * @param args      The sql statement parameters as array.
     * @param <T>       The type that will be mapped to location.
     * @return The query location as list with given type.
     */
    protected <T> List<T> query(@NonNull String sql, @NonNull RowMapper rowMapper, @NonNull Object... args) {
        long t = System.nanoTime();
        List<T> result = getJdbcTemplate().query(sql, args, rowMapper);
        log(sql, t);
        return result;
    }

    /**
     * Similar to {@link #getJdbcTemplate()}, but with named parameters.
     */
    private <T> List<T> namedQuery(@NonNull String sql, @NonNull RowMapper rowMapper, @NonNull Map<String, Object> args) {
        long t = System.nanoTime();
        List<T> result = getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
        log(sql, t);
        return result;
    }

    /**
     * Similar to {@link #query(String, RowMapper, Object...)}, but returns location as string list
     *
     * @param sql  The sql statement to execute.
     * @param args The sql statement parameters as array.
     * @return The query location as list of strings.
     */
    protected List<String> queryForStrings(@NonNull String sql, @NonNull Object... args) {
        long t = System.nanoTime();
        List<String> result = getJdbcTemplate().queryForList(sql, args, String.class);
        log(sql, t);
        return result;
    }

    /**
     * Similar to {@link #query(String, RowMapper, Object...)}, but returns location as integer list
     *
     * @param sql  The sql statement to execute.
     * @param args The sql statement parameters as array.
     * @return The query location as list of integers.
     */
    protected List<Integer> queryForInts(@NonNull String sql, @NonNull Object... args) {
        long t = System.nanoTime();
        List<Integer> result = getJdbcTemplate().queryForList(sql, args, Integer.class);
        log(sql, t);
        return result;
    }

    /**
     * Similar to {@link #namedQuery(String, RowMapper, Map)}, but returns location as string list
     *
     * @param sql  The sql statement to execute.
     * @param args The sql statement parameters as array.
     * @return The query location as list of strings.
     */
    protected List<String> namedQueryForStrings(@NonNull String sql, @NonNull Map<String, Object> args) {
        long t = System.nanoTime();
        List<String> result = getNamedParameterJdbcTemplate().queryForList(sql, args, String.class);
        log(sql, t);
        return result;
    }

    /**
     * Similar to {@link #query(String, RowMapper, Object...)}, but returns location as integer
     *
     * @param sql          The sql statement to execute.
     * @param defaultValue The default value to return after query.
     * @param args         The sql statement parameters as array.
     * @return The query location as integer.
     */
    protected Integer queryForInt(@NonNull String sql, @NonNull Integer defaultValue, @NonNull Object... args) {
        long t = System.nanoTime();
        List<Integer> list = getJdbcTemplate().queryForList(sql, args, Integer.class);
        Integer result = list.isEmpty() ? defaultValue : list.get(0) == null ? defaultValue : list.get(0);
        log(sql, t);
        return result;
    }

    /**
     * Similar to {@link #namedQuery(String, RowMapper, Map)} , but returns location as integer
     *
     * @param sql          The sql statement to execute.
     * @param defaultValue The default value to return after query.
     * @param args         The sql statement parameters as array.
     * @return The query location as integer.
     */
    protected Integer namedQueryForInt(@NonNull String sql, @NonNull Integer defaultValue, @NonNull Map<String, Object> args) {
        long t = System.nanoTime();
        List<Integer> list = getNamedParameterJdbcTemplate().queryForList(sql, args, Integer.class);
        Integer result = list.isEmpty() ? defaultValue : list.get(0) == null ? defaultValue : list.get(0);
        log(sql, t);
        return result;
    }

    /**
     * Similar to {@link #query(String, RowMapper, Object...)}, but returns location as date
     *
     * @param sql          The sql statement to execute.
     * @param defaultValue The default value to return after query
     * @param args         The sql statement parameters as array.
     * @return The query location as integer.
     */
    protected Date queryForDate(@NonNull String sql, @NonNull Date defaultValue, @NonNull Object... args) {
        long t = System.nanoTime();
        List<Date> list = getJdbcTemplate().queryForList(sql, args, Date.class);
        Date result = list.isEmpty() ? defaultValue : list.get(0) == null ? defaultValue : list.get(0);
        log(sql, t);
        return result;
    }

    /**
     * Similar to {@link #query(String, RowMapper, Object...)}, but returns location as long
     */
    protected Long queryForLong(@NonNull String sql, @NonNull Long defaultValue, @NonNull Object... args) {
        long t = System.nanoTime();
        List<Long> list = getJdbcTemplate().queryForList(sql, args, Long.class);
        Long result = list.isEmpty() ? defaultValue : list.get(0) == null ? defaultValue : list.get(0);
        log(sql, t);
        return result;
    }

    /**
     * Similar to {@link #query(String, RowMapper, Object...)}, but returns only first record
     */
    protected <T> T queryOne(@NonNull String sql, @NonNull RowMapper rowMapper, @NonNull Object... args) {
        List<T> list = query(sql, rowMapper, args);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * Similar to {@link #namedQuery(String, RowMapper, Map)}, but returns only first record
     */
    protected <T> T namedQueryOne(@NonNull String sql, @NonNull RowMapper rowMapper, @NonNull Map<String, Object> args) {
        List<T> list = namedQuery(sql, rowMapper, args);
        return list.isEmpty() ? null : list.get(0);
    }
}
