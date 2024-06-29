* 自动化配置类 MybatisAutoConfiguration

* 在每个MapperXXX上加@Mapper, 或者在配置类上加 @MapperScan(basePackages = "com.loserico.bootmybatis.mapper")

* mybatis的配置前缀是mybatis, 可以参考org.mybatis.spring.boot.autoconfigure.MybatisProperties

  ```yaml
  mybatis:
    configuration:
      map-underscore-to-camel-case: true
    mapper-locations: classpath:/mybatis/mapper/*.xml
  ```

  