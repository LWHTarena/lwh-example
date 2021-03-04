# commons-configuration 的1.x 与2.x

`commons-configuration` 和 `commons-configuration2` 是Apache开源组织提供的用于操作配置文件的工具包。配置参数的来源可以是：
- Properties files、
- XML documents、
- Windows INI files、
- Property list files (plist)、
- JNDI、
- JDBC Datasource、
- System properties、
- Applet parameters
- Servlet parameters。

`commons-configuration2` 是在已经广泛使用的commons-configuration 1.x版本基础上的一个升级版本，与1.x版本并不保持兼容。

## 常用方法

**初始化配置(version1.10)**

```java
# 使用有参构造来加载配置文件
Configuration config = new PropertiesConfiguration("usergui.properties");

# 使用无参构造初始化，然后通过load()方法来加载配置文件
Configuration config = new PropertiesConfiguration();
```

**初始化配置(Version2.1.1)（Recommend）**

```java
Configurations configs = new Configurations();
try
{
    Configuration config = configs.properties(new File("config.properties"));
    // access configuration properties

    String dbHost = config.getString("database.host");
    String dbPassword = config.getString("database.password", "secret");  // provide a default
    int dbPort = config.getInt("database.port");
    long dbTimeout = config.getLong("database.timeout");
    ...
}catch (ConfigurationException cex){
    // Something went wrong
}
```

**扫描规则:**

Commons-Configuration 1.x

- 如果指定的是绝对路径按绝对路径扫描
- 如果指定的是相对路径，那么按如下顺序扫描
  - in the current directory
  - in the user home directory
  - in the classpath
  
Commons-Configuration 2.x

在Version 2.0开始，对于文件扫描策略，用接口FileLocationStrategy来实现，该接口只有一个单一的方法locate(),