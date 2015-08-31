organization  := "com.example"

version       := "0.1"

scalaVersion  := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"                     %%  "spray-can"           % sprayV,
    "io.spray"                     %%  "spray-routing"       % sprayV,
    "com.typesafe.akka"            %%  "akka-actor"          % akkaV,
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.6.0-1",
    "org.sorm-framework"           %   "sorm"                % "0.3.18",
    "org.postgresql"               %   "postgresql"          % "9.4-1201-jdbc41",
    "io.spray"                     %%  "spray-testkit"       % sprayV   % "test",
    "com.typesafe.akka"            %%  "akka-testkit"        % akkaV    % "test",
    "org.specs2"                   %%  "specs2-core"         % "2.3.11" % "test"
  )
}

Revolver.settings