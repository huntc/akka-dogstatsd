package com.typesafe.dogstatsd

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Keep, Sink, Source}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}

class MetricsPublisherTest extends WordSpec with Matchers with ScalaFutures {
  "Metrics publication" should {
    "publish if there is demand" in {
      implicit val system = ActorSystem("reactive-tweets")
      implicit val materializer = ActorMaterializer()
      val (metrics, result) = Source.actorPublisher(MetricsPublisher.props).toMat(Sink.head[Metric[_]])(Keep.both).run()
      val metric = Timer("some-timer", 0)
      metrics ! metric
      result.futureValue shouldBe metric
    }
  }
}
