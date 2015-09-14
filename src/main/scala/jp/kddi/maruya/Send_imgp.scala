/***
 * send igmp membership report
 * @auther maruya
 */

package jp.kddi.maruya
import java.net._
import java.nio.channels._
import java.nio._
import java.util.Properties
import java.io.FileInputStream
import java.io.InputStream
import java.io.File

/**
 * Send igmp join packet
 */
object Send_imgp extends App {
  //load properties
  val is = new FileInputStream("app.prop")
  val prop = new Properties()
  prop.load(is)
  is.close
  val (ni, port, group) = (prop.getProperty("ni"), prop.getProperty("port").toInt, prop.getProperty("group"))

  println("workspace : " + (new File("./").getAbsolutePath))
  println(s"ni => ${ni}, port => ${port}, group => ${port}")

  try {
    //set interface
    val dc = DatagramChannel.open(StandardProtocolFamily.INET).
      setOption(StandardSocketOptions.SO_REUSEADDR, true: java.lang.Boolean).
      bind(new InetSocketAddress(port)).
      setOption(StandardSocketOptions.IP_MULTICAST_IF, NetworkInterface.getByName(ni))


    //send igmp
    val source = prop.getProperty("source")

    if (source != null) {
      dc.join(InetAddress.getByName(group), NetworkInterface.getByName(ni), InetAddress.getByName(source))
      println(s"send member ship report on igmpv3 : source => ${source}")
    } else {
      dc.join(InetAddress.getByName(group), NetworkInterface.getByName(ni))
      println(s"sned member ship report on igmpv2")
    }
    println("push ctrl + C...")

    var ok = true
    while (ok) {
      val ln = scala.io.Source.stdin.getLines()
      if (ln.toList.length != 0) {
        println("aaa")
        ok = false
      }
    }

  } catch {
    case e: java.lang.IllegalArgumentException => {
      println(s"${ni} doesn't find")
    }
    case e: java.lang.UnsupportedOperationException => {
      println("igmpv3 doesn't support.")
    }
    case e : Exception => {
      e.printStackTrace()
    }
  }
}
