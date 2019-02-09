# Akka HTTP URL scraping

- Run command to build image "sbt docker:publishLocal"
- Run docker image "docker run -it -p 8010:8010 akkahttp-customurl:0.1"
- port number is 8010
- example url => localhost:8010/uri?url=http://www.google.com (GET)