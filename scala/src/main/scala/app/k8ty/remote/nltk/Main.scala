package app.k8ty.remote.nltk

import app.k8ty.remote.nltk.k8ty_nltk.{K8tyNLTKGrpc, Request, Response}
import io.grpc.ManagedChannelBuilder

object Main extends App {

  val builder = ManagedChannelBuilder
    .forAddress("localhost", 50051)
  builder.usePlaintext()

  val channel = builder.build()
  val blocker = K8tyNLTKGrpc.blockingStub(channel)

  blocker.stopWords(Request()).data.foreach(println)
  val response: Response = blocker.wordTokenize(Request(data = Seq("Here are some words to tokenize.")))
  println(response)
}
