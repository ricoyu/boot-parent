## boot-jdbc

Druid控制台 http://localhost:8080/druid

admin 123456

配置好Servlet和Filter后

```java
@Bean
public ServletRegistrationBean statViewServlet() {
  ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
  Map<String, String> initParameters = new HashMap<>();
  initParameters.put("loginUsername", "admin");
  initParameters.put("loginPassword", "123456");
  servletRegistrationBean.setInitParameters(initParameters);
  return servletRegistrationBean;
}

@Bean
public FilterRegistrationBean filterRegistrationBean() {
  FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
  filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
  
  Map<String, Object> initParams = new HashMap<>();
  initParams.put("exclusions", "*.js,*.css, /druid/*");
  filterRegistrationBean.setInitParameters(initParams);
  return filterRegistrationBean;
}
```

```yaml
spring:
  datasource:
    druid:
      filters: stat
```

JdbcTemplateAutoConfiguration