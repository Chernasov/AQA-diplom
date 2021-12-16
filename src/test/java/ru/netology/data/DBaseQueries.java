package ru.netology.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;

public class DBaseQueries {

    @Value
    public static class OrderEntity {
        String Id;
        String created;
        String credit_id;
        String payment_id;
    }

    @SneakyThrows
    public static OrderEntity getOrder() {
        var ordersSQL = "SELECT * FROM order_entity ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app_db", "user", "password"
                );
        ) {
            return runner.query(conn, ordersSQL, new BeanHandler<>(OrderEntity.class));
        }


    }
}
