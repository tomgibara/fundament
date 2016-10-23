This small Java library provides a minimal set of unifying abstractions for packages under the `com.tomgibara` namespace. To the extent that the library is useful in other projects it may be introduced with the following Maven dependency:

```
<dependency>
  <groupId>com.tomgibara.fundament</groupId>
  <artifactId>fundament</artifactId>
  <version>1.1.0</version>
</dependency>
```

##Dependency Chart

| **Fundament** |   **Choose**  |               |
|:--------------|:--------------|:--------------|
|  **Streams**  |
|   Fundament   |
|  **Hashing**  |
|    Streams    |
|     Choose    |
|   **Bits**    |
|   Fundament   |
|    Hashing    |
|   **Coding**  |  **Storage**  |   **Tries**   |
|     Bits      |   Fundament   |   Fundament   |
|               |     Bits      |    Streams    |
|               |               |     Bits      |
|  **Permute**  |  **Collect**  |
|     Bits      |   Fundament   |
|    Storage    |    Hashing    |
|               |    Storage    |
|  **Algebra**  |
|   Fundament   |
|     Choose    |
|    Hashing    |
|    Permute    |
|    Collect    |
|   **Bloom**   |
|   Fundament   |
|    Hashing    |
|     Bits      |
|    Storage    |
|    Algebra    |
|  **Perfect**  |
|    Hashing    |
|    Storage    |
|    Permute    |
|    Collect    |
|     Bloom     |
