package com.imooc.flink.course04

import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._

import scala.collection.mutable.ListBuffer

object DataSetTransformationApp {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
//    mapFunction(env)
//    mapPartitionFunction(env)

//    flatMapFunction(env)
//    joinFunction(env)
    crossFunction(env)
  }

  def mapFunction(env:ExecutionEnvironment): Unit ={

    val data = env.fromCollection(List(1,2,3,4))
//    data.map((x:Int) => x+1).print()
//    data.map(x => x+1).print()
    data.map(_+1).print()
  }

  def mapPartitionFunction(env:ExecutionEnvironment) ={
    val students = new ListBuffer[String]
    for ( i<-1 to 10){
      students.append("student: "+ i)
    }
    val data = env.fromCollection(students).setParallelism(4)
//    data.map(x=>{
//      val connection = DBUtils.getConnection()
//      println(connection + "...........")
//      DBUtils.returnConnection(connection)
//      x
//    }).print()


    data.mapPartition(x=>{
      val connection = DBUtils.getConnection()
      println(connection + "...........")
      DBUtils.returnConnection(connection)
      x+"_"
    }).print()
  }

  def firstFunction(env: ExecutionEnvironment): Unit ={
    val info = ListBuffer[(Int, String)]()
    info.append((1, "a"))
    info.append((1, "b"))
    info.append((1, "c"))
    info.append((2, "a"))
    info.append((2, "b"))
    info.append((3, "a"))
    val data = env.fromCollection(info)
//    data.first(3).print()
    data.groupBy(0).first(2)
    data.groupBy(0).sortGroup(1, Order.ASCENDING).first(2)
  }

  def flatMapFunction(env: ExecutionEnvironment): Unit ={
    val info = ListBuffer[String]()
    info.append("aa,bb")
    info.append("cc,bb")
    info.append("dd,bb")
    info.append("aa,aa")
    info.append("dd,bb")
    val data = env.fromCollection(info)

//    data.flatMap(_.split(",")).print()
    data.flatMap(_.split(",")).map((_,1)).groupBy(0).sum(1).print()
  }

  def joinFunction(env: ExecutionEnvironment): Unit ={
    val info1 = ListBuffer[(Int, String)]()
    info1.append((1, "a"))
    info1.append((1, "b"))
    info1.append((2, "a"))
    info1.append((4, "a"))

    val info2 = ListBuffer[(Int, String)]()
    info2.append((1, "a2"))
    info2.append((1, "b2"))
    info2.append((2, "a2"))
    info2.append((3, "a2"))

    val data1 = env.fromCollection(info1)
    val data2 = env.fromCollection(info2)
    data1.leftOuterJoin(data2).where(0).equalTo(0).apply((d1, d2)=>{
      if(d2 == null){
        (d1._1, d1._2, "-")
      }else{
        (d1._1, d1._2, d2._2)
      }

    }).print()

  }

  def crossFunction(env: ExecutionEnvironment): Unit ={
    val info1 = List("曼联", "曼城")
    val info2 = List(3,1,0)

    val data1 = env.fromCollection(info1)
    val data2 = env.fromCollection(info2)

    data1.cross(data2).print()

  }
}
