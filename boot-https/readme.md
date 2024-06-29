1. 先生成一个PKCS12类型的keystore

   ```shell
   keytool -genkeypair -alias rico -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore d:/rico.p12 -validity 3650
   ```

   密码是123456

2. 配置一个基本的SpringSecurity项目

   账号密码: rico 0987654321

3. 将rico.p12拷贝到src/main/resources/keystore下

4. SSL相关配置

   ```yaml
   server:
     ssl:
       # The format used for the keystore. It could be set to JKS in case it is a JKS file
       key-store-type: PKCS12
       # The path to the keystore containing the certificate
       key-store: classpath:keystore/rico.p12
       # The password used to generate the certificate
       key-password: 123456
       # The alias mapped to the certificate
       key-alias: rico
       # Since we are using Spring Security enabled application, let's configure it to accept only HTTPS requests
       enabled: true
   ```

5. 客户端调用HTTPS endpoint with the self-signed certificate.

   先得创建一个trust store, 这个用上面生成的rico.p12就可以了

6. 





