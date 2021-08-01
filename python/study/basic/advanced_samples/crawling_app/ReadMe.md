# Install Scrapy
```shell
    $> pip install scrapy
```

# Generate Project
- `scrapy startproject` **PROJECT_NAME**
```shell
    $> scrapy startproject sample_project
```

# Make spider
- `scrapy genspider -t crawl` **SPIDER_FILE_NAME** **DOMAIN_PATH**
- DOMAIN_PATH : 프로토콜(https, www)은 제외한다.
```shell
    $> scrapy genspider -t crawl book_crawl hanbit.co.kr
```
- 옵션
```text
    scrapy genspider -t [option]
    
    basic : 가장 기본적인 크롤러, 도메인에서 설정한 페이지만 크롤링한다.
    crawl : 설정한 규칙에 맞는 링크들을 재귀적으로 탐색한다.
    xmlfeed : xml 피드(xml의 각 노드)를 크롤링한다.
    csvfeed : xmlfeed 크롤러와 비교해서 각 행을 크롤링한다는 차이가 있다.
```

# Execute Crawling
- `scrapy crawl` **CRAWLER_NAME** `-o` **OUTPUT_FILE_NAME** `-t` **OUTPUT_TYPE**
```shell
    $> scrapy crawl book_crawl -o book_list.csv -t csv
```
- File Type : jsonlines, json, xml, csv, pickle, jl, marshal