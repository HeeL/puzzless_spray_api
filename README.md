# API for the Puzzless project

This application provides an API for the [Puzzless](http://puzzless.com) project. It has been built with Scala and [Spray](http://www.spray.io).
The main purpose of this API is to provide all the information from Puzzless website to an Android app written on [Scaloid](https://github.com/pocorall/scaloid/).
As for now the API represents the resources:
* riddles
* categories of riddles (logic, math...)
* user comments to riddles.

There is an [API documentation](http://heel.github.io/puzzless_spray_api/) with detailed information about all the routes and parameters. Here is a very brief overview of available routes to get an idea what you can do:

### Categories:

      GET     /v1/categories                => list all the categories
      GET     /v1/categories/:uuid          => get a single category by uuid
      POST    /v1/categories                => create a new category from params
      PUT     /v1/categories/:uuid          => update the category by uuid
      DELETE  /v1/categories/:uuid          => delete the category by uuid

### Riddles:

      GET     /v1/riddles                   => list all the riddles
      GET     /v1/riddles/category/:uuid    => riddles from specified category
      GET     /v1/riddles/:uuid             => get a single riddle by uuid
      POST    /v1/riddles                   => create a new riddle from params
      PUT     /v1/riddles/:uuid             => update the riddle by uuid from params
      DELETE  /v1/riddles/:uuid             => delete the riddle by uuid

### Comments:

      GET     /v1/comments                  => list of all the comments
      GET     /v1/comments/riddle/:uuid     => comments to the specified riddle
      GET     /v1/comments/:uuid            => get comment with specified uuid
      POST    /v1/comments                  => create a new comment from params
      PUT     /v1/comments/:uuid            => update the comment with specified uuid
      DELETE  /v1/comments/:uuid            => delete the comment with specified uuid

All the details and specifications about the requests and responses can be found [here](http://heel.github.io/puzzless_spray_api/).

## Installation

* You will need a JDK 7+ (e.g. [http://www.oracle.com/technetwork/java/javase/downloads/index.html](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Install sbt ([http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html)

## Run and Test

Run sbt

        $ sbt

Compile everything and run all tests:

        > test

Start the application:

        > re-start

Browse to [http://localhost:8080](http://localhost:8080/)

You can stop the application by running:

        > re-stop
