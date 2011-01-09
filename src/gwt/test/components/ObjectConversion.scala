package gwt.test.components

import scala.collection.JavaConversions.JListWrapper
import gwt.test.client.ChartPoint
import gwt.test.client.StockQuote

object ObjectConversion {

  implicit def getJavaList[T](list: List[T]): java.util.List[T] = {
    val javaList = new java.util.ArrayList[T]
    val buffer = new JListWrapper(javaList)
    buffer ++ list
    javaList.addAll(buffer)
    javaList
  }

  implicit def getJavaChartList(list: List[StockQuote]): java.util.List[ChartPoint] = {
    val javaList = new java.util.ArrayList[gwt.test.client.ChartPoint]
    list.foreach(stockQuote => {
      if (stockQuote.getLast != null) {
        val last = stockQuote.getLast.toDouble / 100
        val chartPoint = new ChartPoint(last, stockQuote.getTime)
        javaList.add(chartPoint)
      }
    })
    javaList
  }
}