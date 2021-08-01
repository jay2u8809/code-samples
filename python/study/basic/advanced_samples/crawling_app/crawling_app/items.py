# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class CrawlingAppItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()

    # Book Name
    book_title = scrapy.Field()

    # Author Name
    book_author = scrapy.Field()

    # Translator Name
    book_translator = scrapy.Field()

    # Published Day
    book_pub_date = scrapy.Field()

    # ISBN
    book_isbn = scrapy.Field()

    pass
