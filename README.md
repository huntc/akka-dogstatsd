akka-dogstatsd
===

A client for dogstatsd that leverages Akka and Akka streams. The end goal is that, given an actor to publish metrics to, you
can simply publish your metric e.g.:

```scala
someTimer ! 843
```

In the above case, 843ms is sent to a timer metric publisher. The template for creating the timer publisher is as follows:

```scala
MetricsPublisher.props(Timer("some-timer", _))
```
The `props` function of `MetricsPublisher` takes a function that receives a timing value and returns one of statd's
metric types, in this case a `Timer`. Here's a full example that uses Akka streams to create a source around the
publisher and then yield both the publisher and the first result published:

```scala
val (someTimer, result) =
  Source.actorPublisher(MetricsPublisher.props(Timer("some-timer", _)))
    .toMat(Sink.head[Metric])(Keep.both)
    .run()
```

`MetricsPublishers` are in fact Akka's of `ActorPublisher`'s type and can thus be used in an Akka streams context.

Please refer to the tests for more examples.

(c)opyright Typesafe Inc, 2015