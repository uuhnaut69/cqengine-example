# CQEngine Example
Ultra-fast SQL-like queries on Java collections

CQEngine – Collection Query Engine – is a high-performance Java collection which can be searched with SQL-like queries, with extremely low latency.

- Achieve millions of queries per second, with query latencies measured in microseconds

- Offload query traffic from databases - scale your application tier

- Outperform databases by a factor of thousands, even on low-end hardware

<h3>Running Example </h3>

1. Extract data file in data folder.

2. Generate dummy data

```bash
curl -X POST localhost:8080/employees?csvPath={path-to-data-file}
```
