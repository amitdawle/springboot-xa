# springboot-xa

A simple (albeit contrived) example showing how to use configure multiple resources with Atomikos when using spring boot. The demo application writes to two (inmemory) databases and one messaging resource (standalone ActiveMQ) in a single (XA) transaction. The main configuration is in [Configuration.kt](https://github.com/amitdawle/springboot-xa/blob/main/src/main/kotlin/com/example/demo/configuration/Configuration.kt) and in [application.yaml](https://github.com/amitdawle/springboot-xa/blob/main/src/main/resources/application.yml).

This setup is useful for monoliths or if you are in process of gradually [migrating](https://martinfowler.com/bliki/StranglerFigApplication.html) a monolith to microsevices architecture with spring boot and want to initially write to multiple DB in a single microservice and then eventually move to [Database per service](https://microservices.io/patterns/data/database-per-service.html).

If 2PC is not an option (for Mongo. Kafka etc), another approach would be a combination of [Database per service](https://microservices.io/patterns/data/database-per-service.html), [Idempotent Consumer](https://microservices.io/patterns/communication-style/idempotent-consumer.html) and [Transaction Outbox](https://microservices.io/patterns/data/transactional-outbox.html).
