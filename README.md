# Getting Start

1. Create  a `application.yml` file on `resources/` folder.
```yml
server:
  port: 8080
  address: 0.0.0.0

spring:
  application:
    name: devgram
  devtools:
    restart:
      enabled: true
  datasource:
    url: jdbc:mysql://localhost:3306/dbName?serverTimezone=UTC&characterEncoding=UTF-8
    username: yourUsername
    password: yourPassword
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.zaxxer.hikari.HikariDataSource
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: yourClientId
            client-secret: yourClientSecret
            scope: user:email
            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
            authorization-grant-type: authorization_code
            client-name: GitHub
          google:
            client-id: yourClientId
            client-secret: yourClientSecret
            scope: openid, profile, email
        provider:
          github:
            authorization-uri: https://github.com/login/oauth/authorize
            token-uri: https://github.com/login/oauth/access_token
            user-info-uri: https://api.github.com/user

myapp:
  url: http://localhost:3000

amazonProperties:
  endpointUrl: yourEndPointUrl
  accessKey: yourAccAccessKey
  secretKey: yourAccSecretKey
  bucketName: yourS3BucketName

pusher:
  app_id: yourAppId
  key: yourKey
  secret: yourSecret
  cluster: "ap1"

firebase:
  configuration-file: "firebaseServiceAccountKey.json"

jwt:
  secret_key: generate256BitSecretKey
```

2. Put your `firebaseServiceAccountKey.json` file on `resources/` folder.