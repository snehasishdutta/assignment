package com.kreditech.assignment

import akka.http.scaladsl.server.Directives.{complete, get, parameters, pathPrefix}
import com.kreditech.assignment.CustomURL.isReachable
import com.kreditech.assignment.CustomUrlModels.{UrlClass, UrlList}
import org.jsoup.Jsoup

object Route {
  import com.kreditech.assignment.CustomUrlModels.ServiceJsonProtocolUrl._
  val route =
    pathPrefix("uri") {
      get {
        parameters('url) { url =>
          complete(s"URL Found ${CustomURL.getLinks(url)}")
        }
      }
    }

}
