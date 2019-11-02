package com.imooc.flink.course08


import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.fs.StringWriter
import org.apache.flink.streaming.connectors.fs.bucketing.{BucketingSink, DateTimeBucketer}

object FileSystemSinkApp {

  def main(args: Array[String]): Unit = {

//    hdfs connector 写入的是很多小文件，hdfs上扛不住。  可以用自定义sink写入到hdfs

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val data = env.socketTextStream("localhost", 9999)

    val filePath = "file:///F:\\project\\data\\flink-train-java\\course08"
    val sink = new BucketingSink[String](filePath)
    sink.setBucketer(new DateTimeBucketer("yyyy-MM-dd--HHmm"))
    sink.setWriter(new StringWriter())
//    sink.setBatchSize(1024 * 1024 * 400) // this is 400 MB,
    sink.setBatchRolloverInterval(20); // this is 20 mins20 * 60 * 1000

    data.addSink(sink)

    env.execute("FileSystemSinkApp")
  }

}
