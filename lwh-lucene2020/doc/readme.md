# lucene 搜索引擎

## 涉及的几个类和关键词

几个类：

+ org.apache.lucene.analysis.standard.StandardAnalyzer 标准分析器

+ org.apache.lucene.document.Document

+ org.apache.lucene.index.IndexWriter 索引器


几个词：

- Analyzer 分析器
- 

## 索引存放路径

Directory(4个子类)
* DbDirectory 
* FSDirectory  对文件系统目录的操作
* JEDirectory
* RAMDirectory  对内存目录的操作

## Lucene创建索引核心API
- Directory  索引操作目录
- Analyzer   分词器
- Document 索引中文档对象
- IndexableField 文档内部数据信息
- IndexWriterConfig 索引生成配置信息
- IndexWriter  索引生成对象

