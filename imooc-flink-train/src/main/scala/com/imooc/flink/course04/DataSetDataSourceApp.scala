package com.imooc.flink.course04


import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration


object DataSetDataSourceApp {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
//    fromCollection(env)
//    textFile(env)
//    csvFile(env)
//    readRecursiveFiles(env)
    compressionFile(env)


  }

  def fromCollection(env:ExecutionEnvironment)={
    import org.apache.flink.api.scala._
    val data = 1 to 10
    env.fromCollection(data).print()
  }

  def textFile(env:ExecutionEnvironment)={
    val filePath = "file:///F:\\project\\data\\flink-train-java"
    env.readTextFile(filePath).print()
  }

  def csvFile(env:ExecutionEnvironment)={
    import org.apache.flink.api.scala._
    val filePath = "file:///F:\\project\\data\\flink-train-java\\invoke_col_config.csv"
    //用java类读取pojoFields方式
//    env.readCsvFile[MyCase](filePath,pojoFields=Array("stepId", "col1", "col2")).print()
//    case class方式读取
//    env.readCsvFile[MyCase](filePath,includedFields=Array(1,2)).print()
//    env.readCsvFile[(String, String)](filePath,includedFields=Array(1,2)).print()
  }

//  case class MyCase(stepId:String, colName:String)


  def readRecursiveFiles(env:ExecutionEnvironment): Unit ={
    val filePath = "file:///F:\\project\\data\\flink-train-java\\recursive"
    val parameters = new Configuration()
    parameters.setBoolean("recursive.file.enumeration", true)
    env.readTextFile(filePath).withParameters(parameters).print()
  }


  def compressionFile(env:ExecutionEnvironment)={
    val filePath = "file:///F:\\project\\data\\flink-train-java\\compression"
    env.readTextFile(filePath).print()
  }


}
