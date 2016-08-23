var ioc = {
    db_conf : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            paths : ["custom/db.properties"]
            //paths : ["custom/"]
        }
    },
    dataSource: {
        type: "com.alibaba.druid.pool.DruidDataSource",
        events: {
            depose: 'close'
        },
        fields: {
            driverClassName      : {java : "$db_conf.get('db.driver')"},
            url                  : {java : "$db_conf.get('db.url')"},
            username             : {java : "$db_conf.get('db.username')"},
            password             : {java : "$db_conf.get('db.password')"},
            testWhileIdle        : {java : "$db_conf.get('db.testWhileIdle')"},
            validationQuery      : {java : "$db_conf.get('db.validationQuery')"},
            maxActive            : {java : "$db_conf.get('db.maxActive')"},
            filters              : {java : "$db_conf.get('db.maxActive')"},
            connectionProperties : {java : "$db_conf.get('db.connectionProperties')"}
            /*
             initialSize: 1,
             minIdle: 1,
             maxWait: 60000,
             timeBetweenEvictionRunsMillis: 60000,
             minEvictableIdleTimeMillis: 300000,
             poolPreparedStatements: true,
             maxPoolPreparedStatementPerConnectionSize: 20
             */
        }
    },
    sqlManager: {
        type: "org.nutz.dao.impl.FileSqlManager",
        args: "sqls/"
    },
    dao: {
        type: "org.nutz.dao.impl.NutDao",
        args: [
            {refer: "dataSource"},
            {refer: "sqlManager"}
        ]
    }
};
