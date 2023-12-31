## Hexagonal Architecture demo

The main idea behind this architecture is to isolate domain logic from external components when designing software applications.

Access to domain logic from the outside is available through ports and adapters.

![](https://miro.medium.com/v2/resize:fit:640/format:webp/1*N7tlxqOLXiPLy_yrb0q7-Q.png)

A port is just an interface to be implemented by an adapter. There are two types of ports: input and output. We implement the first ones in the domain layer, while the infrastructure module will contain the implementation of the second ones.

With this approach, you will have well-defined interfaces to communicate in and out of the domain layer without depending on implementation details.


## Load test via k6 framework

run app and tools:
```shell
docker compose -f load-tests.docker.compose.yml up --build
```

run only tests:
```shell
docker compose -f load-tests.docker.compose.yml run k6
```

go to `http://localhost:3000` and configure grafana with influx datasource (host: http://influxdb:8086/ , database: loadtests).

You can use dashboard located in [here](tests/grafana/dashboard/2587_rev3.json)

Results:

![](docs/img/loadtests.grafana.png)

cleanup
```shell
docker compose -f load-tests.docker.compose.yml down
```

### See also:
 - https://beyondxscratch.com/2017/08/19/hexagonal-architecture-the-practical-guide-for-a-clean-architecture/
 - https://blog.allegro.tech/2020/05/hexagonal-architecture-by-example.html
 - https://www.arhohuttunen.com/hexagonal-architecture/
 - https://betterprogramming.pub/hexagonal-architecture-with-spring-boot-74e93030eba3