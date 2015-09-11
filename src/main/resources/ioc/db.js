var ioc = {
    config : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
//            paths : ["custom/db.properties"]
            paths : ["custom/"]
        }
    },
    dataSource: {
        type: "com.alibaba.druid.pool.DruidDataSource",
        events: {
            depose: 'close'
        },
        fields: {
            driverClassName      : {java : "$config.get('db.driver')"},
            url                  : {java : "$config.get('db.url')"},
            username             : {java : "$config.get('db.username')"},
            password             : {java : "$config.get('db.password')"},
            testWhileIdle        : {java : "$config.get('db.testWhileIdle')"},
            validationQuery      : {java : "$config.get('db.validationQuery')"},
            maxActive            : {java : "$config.get('db.maxActive')"},
            filters              : {java : "$config.get('db.maxActive')"},
            connectionProperties : {java : "$config.get('db.connectionProperties')"}
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
    dao: {
        type: "org.nutz.dao.impl.NutDao",
        args: [
            {refer: "dataSource"}
        ]
    }
};
