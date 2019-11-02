package com.imooc.flink.course04

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.core.fs.FileSystem.WriteMode

object DataSetSinkApp {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    val data = 1.to(10)
    val text = env.fromCollection(data)

    val filePath = "F:\\project\\data\\flink-train-java\\out"

    text.writeAsText(filePath, WriteMode.OVERWRITE).setParallelism(2)

    env.execute("DataSetSinkApp")

  }
}
