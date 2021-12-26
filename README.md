# Financy App

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

Copyright © 2021 FIXME
