var ioc = {
    config : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            paths : ["db.properties"]
        }
    },
    dataSource: {
        type: "com.alibaba.druid.pool.DruidDataSource",
        events: {
            depose: 'close'
        },
        fields: {
            driverClassName : {java :"$config.get('db-driver')"},
            url             : {java :"$config.get('db-url')"},
            username        : {java :"$config.get('db-username')"},
            password        : {java :"$config.get('db-password')"},
            initialSize: 1,
            minIdle: 1,
            maxActive: 2,
            maxWait: 60000,
            timeBetweenEvictionRunsMillis: 60000,
            minEvictableIdleTimeMillis: 300000,
            poolPreparedStatements: true,
            maxPoolPreparedStatementPerConnectionSize: 20,
            testWhileIdle:true
        }
    },
    dao: {
        type: "org.nutz.dao.impl.NutDao",
        args: [
            {refer: "dataSource"}
        ]
    }
};
