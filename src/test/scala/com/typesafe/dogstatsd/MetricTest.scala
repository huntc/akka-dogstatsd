package com.typesafe.dogstatsd

import org.scalatest.{ Matchers, WordSpec }

class MetricTest extends WordSpec with Matchers {

  "Timer" should {
    "encode properly without tags" in {
      Timer("some-timer", 0).toString shouldBe "some-timer:0|ms"
    }

    "encode tags properly" in {
      Timer("some-timer", 0, List("k0" -> None, "k1" -> Some("v1"))).toString shouldBe "some-timer:0|ms|#k0,k1:v1"
    }
  }
}
