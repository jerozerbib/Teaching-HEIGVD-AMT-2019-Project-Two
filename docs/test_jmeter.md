# Tests JMeter

Lors de nos tests JMeter, nous avons pu remarquer que la pagination aide à accélérer le débit des serveurs.

Avec 100 entrées dans la table et pas de pagination.

| Label        | \# Samples | Average | Min  | Max  | Std\. Dev\. | Error % | Throughput | Received KB/sec | Sent KB/sec | Avg\. Bytes |
| ------------ | ---------- | ------- | ---- | ---- | ----------- | ------- | ---------- | --------------- | ----------- | ----------- |
| HTTP Request | 10         | 32      | 16   | 67   | 13\.65      | 0\.000% | 11\.12347  | 212\.17         | 4\.38       | 19532\.0    |
| TOTAL        | 10         | 32      | 16   | 67   | 13\.65      | 0\.000% | 11\.12347  | 212\.17         | 4\.38       | 19532\.0    |

Avec 1000 entrées dans la tables et pas de pagination

| Label        | \# Samples | Average | Min  | Max  | Std\. Dev\. | Error % | Throughput | Received KB/sec | Sent KB/sec | Avg\. Bytes |
| ------------ | ---------- | ------- | ---- | ---- | ----------- | ------- | ---------- | --------------- | ----------- | ----------- |
| HTTP Request | 10         | 18      | 14   | 27   | 4\.89       | 0\.000% | 10\.95290  | 7\.58           | 4\.32       | 709\.0      |
| TOTAL        | 10         | 18      | 14   | 27   | 4\.89       | 0\.000% | 10\.95290  | 7\.58           | 4\.32       | 709\.0      |

Avec 10000 entrées dans la table et pas de pagination

| Label        | \# Samples | Average | Min  | Max  | Std\. Dev\. | Error % | Throughput | Received KB/sec | Sent KB/sec | Avg\. Bytes |
| ------------ | ---------- | ------- | ---- | ---- | ----------- | ------- | ---------- | --------------- | ----------- | ----------- |
| HTTP Request | 10         | 14      | 8    | 46   | 10\.80      | 0\.000% | 11\.87648  | 1\.46           | 4\.70       | 126\.0      |
| TOTAL        | 10         | 14      | 8    | 46   | 10\.80      | 0\.000% | 11\.87648  | 1\.46           | 4\.70       | 126\.0      |

Avec 10000 entrées dans la table et une pagination permettant 20 utilisateurs par page 

| Label        | \# Samples | Average | Min  | Max  | Std\. Dev\. | Error % | Throughput | Received KB/sec | Sent KB/sec | Avg\. Bytes |
| ------------ | ---------- | ------- | ---- | ---- | ----------- | ------- | ---------- | --------------- | ----------- | ----------- |
| HTTP Request | 10         | 20      | 8    | 43   | 10\.64      | 0\.000% | 11\.65501  | 45\.58          | 4\.58       | 4005\.0     |
| TOTAL        | 10         | 20      | 8    | 43   | 10\.64      | 0\.000% | 11\.65501  | 45\.58          | 4\.58       | 4005\.0     |

En effet, nous pouvons voir que la charge est mieux répartie lorsque la pagination est mise en place.