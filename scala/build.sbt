name := "k8ty-nltk"
organization := "app.k8ty.remote"
version := "0.0.0"
scalaVersion := "2.13.3"

lazy val rootProject = file(".")

libraryDependencies ++= Seq(
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
)

PB.protoSources in Compile := Seq(file("../proto"))
PB.targets in Compile := Seq(
  scalapb.gen(grpc = true) -> (sourceManaged in Compile).value / "scalapb",
  scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value / "scalapb",
)
