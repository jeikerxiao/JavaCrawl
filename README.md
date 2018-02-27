# JavaCrawl

jsoup 使用的例子

# pom.xml

```
<!-- Jsoup -->
<dependency>
	<groupId>org.jsoup</groupId>
	<artifactId>jsoup</artifactId>
	<version>1.11.2</version>
</dependency>
```

# 关键代码

```java
public Article getTitle(String url) {

    Article article = new Article();
    try {
        // 解析html
        Document doc = Jsoup.connect(url).get();
        Element titleElement = doc.select("h2#activity-name").first();
        String title = titleElement.text();
        Element contentElement = doc.selectFirst("div#js_content");
        Element image = contentElement.selectFirst("img");
        String imageUrl = image.attr("data-src");
        String summary = getSummary(contentElement.text());
        // 填充数据模型
        article.setTitle(title);
        article.setUrl(url);
        article.setImageUrl(imageUrl);
        article.setSummary(summary);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return article;
}
```

# 测试

1.Get请求：

http://localhost:8080/test/hi

响应：

```json
{
    "message": "hello"
}
```

2.Post请求：

http://localhost:8080/test/article

```json
{
	"url":"https://mp.weixin.qq.com/s?src=3&timestamp=1519715236&ver=1&signature=volzI9BiiaUzRR7Fe-BUR1RPLZ962-SfIRegmjci6G3z8gpnnKb1s39RSqyMu2vJe4t3DFQlDjdWJHFan1NdCn1F2uSn-lfGQCcTPHo-s8ietwN78i4Y9aaVnmyikJ55IlUswyf9Vo13qq9OPN7hKg=="
}
```

```json
{
    "title": "1型糖友骑行西藏——出征了",
    "url": "https://mp.weixin.qq.com/s?src=3&timestamp=1519715236&ver=1&signature=volzI9BiiaUzRR7Fe-BUR1RPLZ962-SfIRegmjci6G3z8gpnnKb1s39RSqyMu2vJe4t3DFQlDjdWJHFan1NdCn1F2uSn-lfGQCcTPHo-s8ietwN78i4Y9aaVnmyikJ55IlUswyf9Vo13qq9OPN7hKg==",
    "imageUrl": "http://mmbiz.qpic.cn/mmbiz/r2h5QWaR71HBrFl5ph407Diab5m4fqYvFeZGlLj0Fia6T8QAXzskcSwrdATILjvAHaO8Kb7XO8T8ycSyWb5nRZMQ/0?wx_fmt=jpeg",
    "summary": "友情提醒：照片超多，请开wifi，土豪随意。 1型糖友小栋骑..."
}
```