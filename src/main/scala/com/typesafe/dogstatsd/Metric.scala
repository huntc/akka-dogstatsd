package com.typesafe.dogstatsd

/**
 * The basic unit of what is communicated to statsd - a metric. Metrics know how to be serialised to UTF-8 Strings.
 * @param name The name given to the actual statistic
 * @param value The value of the statistic
 * @param tags Any requisite tags
 */
sealed abstract class Metric[T](val name: String, val value: T, val tags: Seq[(String, Option[String])]) {
  protected def formatTags(tags: Seq[(String, Option[String])]): String = {
    val strings = tags
      .map {
        case (k, Some(v)) => k + ":" + v
        case (k, None) => k
      }
    if (strings.nonEmpty) strings.mkString("|#", ",", "") else ""
  }
}

/**
 * Record a statistic for the time taken to do something of a given aspect.
 */
case class Timer(
                 override val name: String,
                 override val value: Long,
                 override val tags: Seq[(String, Option[String])] = Seq.empty) extends Metric(name, value, tags) {

  override def toString: String =
    s"$name:$value|ms${formatTags(tags)}"
}