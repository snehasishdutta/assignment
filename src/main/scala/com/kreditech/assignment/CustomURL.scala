package com.kreditech.assignment

import java.net.{HttpURLConnection, URL}

import akka.http.scaladsl.Http

import scala.collection.JavaConverters._
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, path, post}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model._
import akka.util.ByteString
import spray.json._
import spray.json.{JsString, _}
import java.util.UUID

import akka.actor.ActorSystem
import akka.http.scaladsl.server.ExceptionHandler
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.jsoup._
import akka.http.scaladsl.unmarshalling.{FromRequestUnmarshaller, Unmarshaller}
import akka.stream.{ActorMaterializer, Materializer}
import com.kreditech.assignment.CustomUrlModels.{UrlClass, UrlList}
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable.ListBuffer
import scala.util.Try

object CustomUrlModels {
  case class UrlClass(uri:Option[String] = None, reachable:Option[Boolean] = None, statusCode:Option[Int] = None, errorMessage:Option[String] = None)
  case class UrlList(urls:Array[UrlClass])
  object ServiceJsonProtocolUrl extends DefaultJsonProtocol {
    implicit val urlclassformat = jsonFormat4(UrlClass)
    implicit val urllistformat = jsonFormat1(UrlList)
  }
}
object CustomURL  {

  implicit val sys = ActorSystem("IntroductionToAkkaHttp")
  implicit val mat:Materializer = ActorMaterializer()
  import com.kreditech.assignment.CustomUrlModels.ServiceJsonProtocolUrl._
  implicit def myExceptionHandler = ExceptionHandler {
    case ex: Exception =>
      val errorObj = UrlClass(statusCode = Some(StatusCodes.BadRequest.intValue), errorMessage = Some(ex.getMessage))
      complete(s"erorr occured ${errorObj.toJson}")
    case e: Throwable => {
      println(e.getMessage)
      complete(HttpResponse(StatusCodes.BadRequest, entity = e.getMessage))
    }
  }


  def isReachable(url:String) = {
    HttpURLConnection.setFollowRedirects(false)
    val u = new URL(url)
    val conn = u.openConnection.asInstanceOf[HttpURLConnection]
    conn.setRequestMethod("HEAD")
    val status = Try(conn.getResponseCode).toOption
    conn.disconnect()
    status match {
      case Some(responseCode) if responseCode == 200 => true
      case _ => false
    }
  }

  def getLinks(url:String) = {
    import com.kreditech.assignment.CustomUrlModels.ServiceJsonProtocolUrl._
    val urlBuffer = new scala.collection.mutable.ArrayBuffer[UrlClass]()
    val doc = Jsoup.connect(url).get()
    val links = doc.select("a").iterator().asScala.map(element=>element.absUrl("href")).toList.filter(_.nonEmpty)
    links.foreach(link => urlBuffer += UrlClass(uri = Some(link),reachable = Some(isReachable(link))))
    UrlList(urlBuffer.toArray).toJson
  }

  def main(args: Array[String]): Unit = {
    Http().bindAndHandle(Route.route, "localhost", 8010)
  }
}
