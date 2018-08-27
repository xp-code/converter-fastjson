Fastjson Converter
=================

A `Converter` which uses [Fastjson][1] for serialization to and from JSON.

A default `JSON` instance will be created or one can be configured and passed to the
`FastjsonConverterFactory` construction to further control the serialization.


Download
--------

Download [the latest JAR][2] or grab via [Maven][3]:
```xml
<dependency>
   <groupId>xyz.xpcoder.common.retrofit2</groupId>
   <artifactId>converter-fastjson</artifactId>
   <version>1.0-SNAPSHOT</version>
</dependency>
```
or [Gradle][3]:
```groovy
implementation 'com.squareup.retrofit2:converter-jackson:latest.version'
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].



 [1]: https://github.com/alibaba/fastjson/wiki/Quick-Start-CN
 [2]: https://search.maven.org/remote_content?g=com.squareup.retrofit2&a=converter-jackson&v=LATEST
 [3]: http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.retrofit2%22%20a%3A%22converter-jackson%22
 [snap]: https://oss.sonatype.org/content/repositories/snapshots/