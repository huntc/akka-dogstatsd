package com.typesafe.dogstatsd

import akka.actor.Props
import akka.stream.actor.ActorPublisher

object MetricsPublisher {
  def props: Props =
    Props(new MetricsPublisher)
}

/**
 * Metrics are sent to this source for the purposes of publishing as a stream. If downstream is not ready
 * then metrics are simply dropped. Metrics are always sent with statsd on a best-effort basis, so the
 * strategy of dropping them here is reasonable.
 */
class MetricsPublisher extends ActorPublisher[Metric[_]] {

  override def receive: Receive = {
    case m: Metric[_] if isActive && totalDemand > 0 => onNext(m)
  }
}
