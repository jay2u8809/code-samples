import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule


class BookCrawlSpider(CrawlSpider):
    # Crawler Name
    name = 'book_crawl'
    # Crawler Execute URL
    allowed_domains = ['hanbit.co.kr']
    # Start Point URL(list type)
    start_urls = [
                  'http://hanbit.co.kr/store/books/category_list.html?cate_cd=001',
                  'http://hanbit.co.kr/store/books/category_list.html?cate_cd=002',
                  'http://hanbit.co.kr/store/books/category_list.html?cate_cd=003',
                  'http://hanbit.co.kr/store/books/category_list.html?cate_cd=004',
                  'http://hanbit.co.kr/store/books/category_list.html?cate_cd=005',
                  'http://hanbit.co.kr/store/books/category_list.html?cate_cd=006',
                  'http://hanbit.co.kr/store/books/category_list.html?cate_cd=007',
                  'http://hanbit.co.kr/store/books/category_list.html?cate_cd=008',
                  ]

    # Rules of Crawling : 스파이더가 크롤링할 링크의 규칙을 정의
    # 크롤러는 시작점의 모든 링크를 검사한 후 규칙에 맞는 링크가 있으면 정해진 콜백 메소드를 실행한다.
    # follow가 True이면 해당 페이지의 링크를 대상으로 재귀적으로 앞 작업을 반복한다.
    rules = (
        Rule(
            # 크롤링할 링크를 정규표현식을 이용해 표현 -> 수집할 데이터 및 링크가 담긴 주소를 현재 도메인에 상대 주소로 적는다.
            # ex> 'http://www.hanbit.co.kr/store/books/look.php?p_code=B8463' -> 정규표현식 : 'store/books/look.php\?p_code=.*'
            LinkExtractor(allow=r'store/books/look.php\?p_code=.*'),
            # 해당 링크에 요청을 보내고 응답이 오면 실행할 콜백 메소드를 지정
            callback='parse_item',
            # True 로 설정되어 있으면, 응답에 다시 한 번 rules를 적용해 재귀적으로 실행한다
            follow=True),

        # 다수의 Rule 설정도 가능(callback, follow를 생략할 경우 정규표현식을 만족하는 링크들을 크롤링할 대상에 넣어두기만 한다)
        Rule(LinkExtractor(allow=r'store/books/category_list\.html\?page=\d+&cate_cd=00\d+&srt=p_pub_date'))
    )

    '''
        응답받은 Html 코드에서 XPath나 CSS 선택자 형식으로 데이터를 뽑아낼 수 있다. 
    '''
    def parse_item(self, response):
        '''
            rules 를 통과한 링크에 요청을 보내 응답을 받으면 Rule() 에 설정한 콜백 메소드를 해당 응답 결과에 실행한다.
            response 를 파라미터로 받고 XPath나 Css 선택자를 이용해서 원하는 요소를 추출할 수 있다.
        '''

        # 앞서 설정한 아이템에 맞춰서 딕셔너리를 채우고 반환
        item = {}
        #item['domain_id'] = response.xpath('//input[@id="sid"]/@value').get()
        #item['name'] = response.xpath('//div[@id="name"]').get()
        #item['description'] = response.xpath('//div[@id="description"]').get()

        # 마지막의 text() 메소드를 통해 책 이름 텍스트를 가져온다
        # Book Name
        item['book_title'] = response.xpath('//*[@id="container"]/div[1]/div[1]/div[1]/div[2]/h3/text()').extract()
        # Author Name
        item['book_author'] = response.xpath('//*[@id="container"]/div[1]/div[1]/div[1]/div[2]/ul/li[strong/text()="저자 : "]/span/text()').extract()
        # Translator Name
        item['book_translator'] = response.xpath('//*[@id="container"]/div[1]/div[1]/div[1]/div[2]/ul/li[strong/text()="번역 : "]/span/text()').extract()
        # Published Day
        item['book_pub_date'] = response.xpath('//*[@id="container"]/div[1]/div[1]/div[1]/div[2]/ul/li[strong/text()="출간 : "]/span/text()').extract()
        # ISBN
        item['book_isbn'] = response.xpath('//*[@id="container"]/div[1]/div[1]/div[1]/div[2]/ul/li[strong/text()="ISBN : "]/span/text()').extract()

        return item
