package com.imooc.flink.course04

import org.apache.commons.io.FileUtils
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

object DistributedCacheApp {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val filePath = "file:///F:\\project\\data\\flink-train-java\\hello.txt"

    // 1、注册一个本地HDFS文件
    env.registerCachedFile(filePath, "dc")
    import org.apache.flink.api.scala._

    val data = env.fromElements("aa","bb","cc")
    data.map(new RichMapFunction[String,String] {
      // 2、在open方法中获取到分布式缓存的内容即可
      override def open(parameters: Configuration): Unit = {
        val dcFile = getRuntimeContext.getDistributedCache().getFile("dc")
        val lines = FileUtils.readLines(dcFile)  // java list
        import scala.collection.JavaConverters._
        //  解决java集合和scala集合不兼容问题
        for (line <- lines.asScala){ // scala 方法
          println(line)
        }

      }
      override def map(value: String): String = {
        value
      }
    }).print()


  }

}
