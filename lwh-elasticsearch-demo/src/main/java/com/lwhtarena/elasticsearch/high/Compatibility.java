package com.lwhtarena.elasticsearch.high;

/**
 * @author liwh
 * @Title: Compatibility
 * @Package com.lwhtarena.elasticsearch.high
 * @Description: 兼容性
 * @Version 1.0.0
 * @date 2020/4/6 16:36
 */
public class Compatibility {
    /**
     *兼容性
     *
     * Java高级REST客户端需要Java 1.8，并取决于Elasticsearch核心项目。客户端版本与为其开发客户端的Elasticsearch版本相同。
     * 它接受与TransportClient相同的请求参数，并返回相同的响应对象。如果需要将应用程序从TransportClient迁移到新的REST客户
     * 端，请参阅《迁移指南》。
     *
     * 确保高级客户端能够与在相同主要版本和较大或相等的次要版本上运行的任何Elasticsearch节点进行通信。它不需要与与其通信的
     * Elasticsearch节点使用相同的次要版本，因为它具有前向兼容性，这意味着它支持与为其开发的较新版本的Elasticsearch进行通信。
     *
     * 6.0客户端可以与任何6.x Elasticsearch节点进行通信，而6.1客户端可以与6.1、6.2和任何更高版本的6.x版本进行通信，但是与先
     * 前的Elasticsearch节点进行通信时可能会出现不兼容问题。如果6.1客户端支持6.0节点未知的某些API的新请求正文字段，则版本介于
     * 6.1和6.0之间。
     *
     * 建议将Elasticsearch集群升级到新的主要版本时升级High Level Client，因为REST API的重大更改可能会导致意外结果，具体取决
     * 于请求所命中的节点，并且新添加的API仅受支持。客户端的较新版本。一旦集群中的所有节点都已升级到新的主要版本，客户端应始终最后
     * 更新。
     */
    public void test001(){

    }
}
