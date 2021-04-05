# springboot-xa

A simple (albeit contrived) example showing how to use configure multiple resources with Atomikos when using spring boot. The demo application writes to two databases (in memory) and one messaging resource (ActiveMQ) in a single (XA) transaction. For those in rush, main configuration is in [Configuration.kt](https://github.com/amitdawle/springboot-xa/blob/main/src/main/kotlin/com/example/demo/configuration/Configuration.kt) and in [application.yaml](https://github.com/amitdawle/springboot-xa/blob/main/src/main/resources/application.yml).

This setup is useful for monoliths or if you are in process of gradually [migrating](https://martinfowler.com/bliki/StranglerFigApplication.html) a monolith to microsevices architecture with spring boot and want to initially write to multiple DB in a single microservice.

A non XA approach would be a combination of [Database per service](https://microservices.io/patterns/data/database-per-service.html), [Idempotent Consumer](https://microservices.io/patterns/communication-style/idempotent-consumer.html) and [Transaction Outbox](https://microservices.io/patterns/data/transactional-outbox.html).
