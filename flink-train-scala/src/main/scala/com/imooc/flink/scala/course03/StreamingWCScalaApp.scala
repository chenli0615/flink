package com.imooc.flink.scala.course03

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object StreamingWCScalaApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("localhost", 9999)

    val counts = text.flatMap { _.toLowerCase.split(",") filter { _.nonEmpty } }
      .map { x => WC(x.toLowerCase, 1) }
//      .keyBy("word")
      .keyBy(_.word)
      .timeWindow(Time.seconds(5))
      .sum("count")
      .setParallelism(1)

    counts.print()

    env.execute("Window Stream WordCount")
  }

  case class WC(word:String, count:Int)
}
