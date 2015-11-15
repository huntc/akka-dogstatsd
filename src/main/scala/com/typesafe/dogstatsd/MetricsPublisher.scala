package com.typesafe.dogstatsd

import akka.actor.Props
import akka.stream.actor.ActorPublisher

object MetricsPublisher {
  def props(metricFactory: Long => Metric): Props =
    Props(new MetricsPublisher(metricFactory))
}

/**
 * Metrics are sent to this source for the purposes of publishing as a stream. If downstream is not ready
 * then metrics are simply dropped. Metrics are always sent with statsd on a best-effort basis, so the
 * strategy of dropping them here is reasonable.
 */
class MetricsPublisher(metricFactory: Long => Metric)
  extends ActorPublisher[Metric] {

  override def receive: Receive = {
    case value: Int if isActive && totalDemand > 0  => onNext(metricFactory(value))
    case value: Long if isActive && totalDemand > 0 => onNext(metricFactory(value))
  }
}
