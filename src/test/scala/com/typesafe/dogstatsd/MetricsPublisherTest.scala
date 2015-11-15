package com.typesafe.dogstatsd

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{ Keep, Sink, Source }
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{ Matchers, WordSpec }

class MetricsPublisherTest extends WordSpec with Matchers with ScalaFutures {
  "Metrics publication" should {
    "publish if there is demand" in {
      implicit val system = ActorSystem("reactive-tweets")
      implicit val materializer = ActorMaterializer()
      val (someTimer, result) =
        Source.actorPublisher(MetricsPublisher.props(Timer("some-timer", _)))
          .toMat(Sink.head[Metric])(Keep.both)
          .run()
      for (_ <- 1 to 100) someTimer ! 843
      result.futureValue shouldBe Timer("some-timer", 843)
    }
  }
}
