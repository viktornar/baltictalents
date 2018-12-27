package lt.baltictalents.lesson5.migration.schema;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.*;

import java.util.List;

public abstract class Schema {
    Logger logger = LoggerFactory.getLogger(Schema.class);

    public abstract void execute(JdbcTemplate template);

    public abstract void drop(JdbcTemplate template);

    protected boolean tableExists(JdbcTemplate template, String table) {
        assert template != null;
        assert table != null && !table.isEmpty();

        try {
            template.execute(String.format("select 1 from %s", table));
        } catch (Exception x) {
            return false;
        }
        return true;
    }

    protected boolean columnExists(JdbcTemplate template, String column, String table) {
        assert template != null;
        assert column != null && !column.isEmpty();
        assert table != null && !table.isEmpty();

        try {
            template.execute(String.format("select %s from %s where 1 = 0", column, table));
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    protected boolean rowExists( JdbcTemplate template, String whereClause, String table) {
        assert template != null;
        assert whereClause != null && !whereClause.isEmpty();
        assert table != null && !table.isEmpty();

        try {
            Integer rowCount = template.queryForObject(
                    String.format("select count(*) from %s where %s", table, whereClause),
                    Integer.class
            );
            return rowCount > 0;
        } catch (Exception x) {
            return false;
        }
    }

    protected void drop(JdbcTemplate template, List<String> tablesToDrop) {
        assert template != null;
        assert tablesToDrop != null;

        if (tablesToDrop.size() > 0) {
            String tableList = StringUtils.join(tablesToDrop.toArray(), ", ");
            String dropSql = String.format("DROP TABLE %s", tableList);
            template.execute(dropSql);
            logger.info(String.format("Database tables '%s' was dropped successfully.", tableList));
        } else {
            logger.info(String.format("There are no tables to drop in database."));
        }
    }
}
