This small Java library provides a minimal set of unifying abstractions for packages under the `com.tomgibara` namespace. To the extent that the library is useful in other projects it may be introduced with the following Maven dependency:

```
<dependency>
  <groupId>com.tomgibara.fundament</groupId>
  <artifactId>fundament</artifactId>
  <version>1.1.0</version>
</dependency>
```

##Dependency Chart

|               |               |               |
|:--------------|:--------------|:--------------|
|[Fundament] [0]|[Choose]    [1]|               |
|[Streams]   [2]|
|   Fundament   |
|[Hashing]   [3]|
|    Streams    |
|     Choose    |
|[Bits]      [4]|
|   Fundament   |
|    Hashing    |
|[Coding]    [5]|[Storage]   [6]|[Tries]     [7]|
|     Bits      |   Fundament   |   Fundament   |
|               |     Bits      |    Streams    |
|               |               |     Bits      |
|[Permute]   [8]|[Collect]   [9]|
|     Bits      |   Fundament   |
|    Storage    |    Hashing    |
|               |    Storage    |
|[Algebra]  [10]|
|   Fundament   |
|     Choose    |
|    Hashing    |
|    Permute    |
|    Collect    |
|[Bloom]    [11]|
|   Fundament   |
|    Hashing    |
|     Bits      |
|    Storage    |
|    Algebra    |
|[Perfect]  [12]|
|    Hashing    |
|    Storage    |
|    Permute    |
|    Collect    |
|     Bloom     |

 [0]: https://github.com/tomgibara/fundament
 [1]: https://github.com/tomgibara/choose
 [2]: https://github.com/tomgibara/streams
 [3]: https://github.com/tomgibara/hashing
 [4]: https://github.com/tomgibara/bits
 [5]: https://github.com/tomgibara/coding
 [6]: https://github.com/tomgibara/storage
 [7]: https://github.com/tomgibara/tries
 [8]: https://github.com/tomgibara/permute
 [9]: https://github.com/tomgibara/collect
[10]: https://github.com/tomgibara/algebra
[11]: https://github.com/tomgibara/bloom
[12]: https://github.com/tomgibara/perfect
