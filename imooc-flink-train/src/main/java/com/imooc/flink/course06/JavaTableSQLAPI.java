package com.imooc.flink.course06;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;

public class JavaTableSQLAPI {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment tableEnv = BatchTableEnvironment.getTableEnvironment(env);
        String filePath = "file:///F:\\project\\data\\flink-train-java\\invoke_col_config.csv";

        DataSet<Sales> csv = env.readCsvFile(filePath).pojoType(Sales.class, "id", "col1", "col2");
        csv.print();

        Table sales = tableEnv.fromDataSet(csv);

        tableEnv.registerTable("sales", sales);
        Table resultTable = tableEnv.sqlQuery("select distinct id from sales");

        DataSet<Row> result = tableEnv.toDataSet(resultTable, Row.class);

        result.print();




    }

    public static class Sales {
        public Integer id;
        public String col1;
        public String col2;

    }
}
