# Financy App
[![Clojure CI](https://github.com/aristotelesbr/financy-app/actions/workflows/clojure.yml/badge.svg)](https://github.com/aristotelesbr/financy-app/actions/workflows/clojure.yml)

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

Install deps:

    lein deps

Run tests:

    lein midje

To start a web server for the application, run:

    lein ring server

Colverage:

```md
|-----------------+---------+---------|
|       Namespace | % Forms | % Lines |
|-----------------+---------+---------|
| financy.handler |  100.00 |  100.00 |
|-----------------+---------+---------|
|       ALL FILES |  100.00 |  100.00 |
|-----------------+---------+---------|
```

## License

Copyright © 2022
