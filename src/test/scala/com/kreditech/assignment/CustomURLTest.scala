package com.kreditech.assignment

import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString
import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, path, post}
import com.kreditech.assignment.CustomUrlModels.{UrlClass, UrlList}
import com.kreditech.assignment.CustomUrlModels.ServiceJsonProtocolUrl._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.{HttpEntity, MediaTypes, StatusCodes}
import akka.util.ByteString
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.util.ByteString
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json._


class CustomURLTest extends WordSpec with Matchers with ScalatestRouteTest  {
  /*"should test url" in {
  Post("/uri", HttpEntity(MediaTypes.`application/json`, ByteString("""{"url":"http://www.google.com"}"""))) ~> Route.route ~> check {
      status shouldEqual StatusCodes.OK
    }
  }*/
}
