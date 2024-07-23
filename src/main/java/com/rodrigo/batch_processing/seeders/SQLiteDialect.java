//package com.rodrigo.batch_processing.seeders;
//
//import org.hibernate.MappingException;
//import org.hibernate.dialect.Dialect;
//import org.hibernate.dialect.function.SQLFunctionTemplate;
//import org.hibernate.dialect.function.StandardSQLFunction;
//import org.hibernate.dialect.function.VarArgsSQLFunction;
//import org.hibernate.dialect.identity.IdentityColumnSupport;
//import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
//import org.hibernate.type.StringType;
//
//import java.sql.Types;
//
//public class SQLiteDialect extends Dialect {
//
//    public SQLiteDialect() {
//        registerColumnType(Types.BIT, "integer");
//        registerColumnType(Types.TINYINT, "tinyint");
//        registerColumnType(Types.SMALLINT, "smallint");
//        registerColumnType(Types.INTEGER, "integer");
//        registerColumnType(Types.BIGINT, "bigint");
//        registerColumnType(Types.FLOAT, "float");
//        registerColumnType(Types.REAL, "real");
//        registerColumnType(Types.DOUBLE, "double");
//        registerColumnType(Types.NUMERIC, "numeric");
//        registerColumnType(Types.DECIMAL, "decimal");
//        registerColumnType(Types.CHAR, "char");
//        registerColumnType(Types.VARCHAR, "varchar");
//        registerColumnType(Types.LONGVARCHAR, "longvarchar");
//        registerColumnType(Types.DATE, "date");
//        registerColumnType(Types.TIME, "time");
//        registerColumnType(Types.TIMESTAMP, "datetime");
//        registerColumnType(Types.BINARY, "blob");
//        registerColumnType(Types.VARBINARY, "blob");
//        registerColumnType(Types.LONGVARBINARY, "blob");
//        registerColumnType(Types.BLOB, "blob");
//        registerColumnType(Types.CLOB, "clob");
//        registerColumnType(Types.BOOLEAN, "integer");
//        registerFunction("concat", new VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""));
//        registerFunction("mod", new SQLFunctionTemplate(StringType.INSTANCE, "?1 % ?2"));
//        registerFunction("substr", new StandardSQLFunction("substr", StringType.INSTANCE));
//        registerFunction("substring", new StandardSQLFunction("substr", StringType.INSTANCE));
//    }
//
//    @Override
//    public IdentityColumnSupport getIdentityColumnSupport() {
//        return new SQLiteIdentityColumnSupport();
//    }
//
//    @Override
//    public boolean supportsLimit() {
//        return true;
//    }
//
//    @Override
//    public String getLimitString(String query, boolean hasOffset) {
//        return new StringBuffer(query.length() + 20)
//                .append(query)
//                .append(hasOffset ? " limit ? offset ?" : " limit ?")
//                .toString();
//    }
//
//    private static class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {
//        @Override
//        public boolean supportsIdentityColumns() {
//            return true;
//        }
//
//        @Override
//        public boolean hasDataTypeInIdentityColumn() {
//            return false; // As per SQLite documentation
//        }
//
//        @Override
//        public String getIdentitySelectString(String table, String column, int type) {
//            return "select last_insert_rowid()";
//        }
//
//        @Override
//        public String getIdentityColumnString(int type) throws MappingException {
//            return "integer";
//        }
//    }
//
//    @Override
//    public boolean supportsCurrentTimestampSelection() {
//        return true;
//    }
//
//    @Override
//    public boolean isCurrentTimestampSelectStringCallable() {
//        return false;
//    }
//
//    @Override
//    public String getCurrentTimestampSelectString() {
//        return "select current_timestamp";
//    }
//
//    @Override
//    public boolean supportsUnionAll() {
//        return true;
//    }
//
//    @Override
//    public boolean hasAlterTable() {
//        return false; // As specify in NHibernate dialect
//    }
//
//    @Override
//    public boolean dropConstraints() {
//        return false;
//    }
//
//    @Override
//    public String getDropForeignKeyString() {
//        return "";
//    }
//
//    @Override
//    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) {
//        return "";
//    }
//
//    @Override
//    public String getAddPrimaryKeyConstraintString(String constraintName) {
//        return "";
//    }
//
//    @Override
//    public boolean supportsIfExistsBeforeTableName() {
//        return true;
//    }
//
//    @Override
//    public boolean supportsCascadeDelete() {
//        return false;
//    }
//
//    @Override
//    public String getAddColumnString() {
//        return "add column";
//    }
//
//}