package com.imooc.flink.course06


import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.types.Row
object TableSQLAPI {

  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment
    val tableEnv = TableEnvironment.getTableEnvironment(env)
    val filePath = "file:///F:\\project\\data\\flink-train-java\\invoke_col_config.csv"
    val csv = env.readCsvFile[SalesLog](filePath)
    val salesTable = tableEnv.fromDataSet(csv)
    tableEnv.registerTable("sales", salesTable)
    val resultTale = tableEnv.sqlQuery("select distinct id from sales")

    tableEnv.toDataSet[Row](resultTale).print()


  }

  case class SalesLog(id:Int, col1:String, col2:String)

}
