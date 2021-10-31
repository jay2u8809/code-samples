import pytesseract
import fitz
from PIL import Image
import enum

TESSERACT_PATH = r'/usr/local/Cellar/tesseract/4.1.1/bin/tesseract'
IMG_PATH = './img/'
FILENAME = ''


# ENUM: LangCode
class LangCode(enum.Enum):
    En = 'eng'
    Ko = 'kor'
    Jp = 'jpn'


pytesseract.pytesseract.tesseract_cmd = TESSERACT_PATH

# English OCR Processing
FILENAME = 'receipt_sample_001_en.jpg'
img = Image.open(IMG_PATH + FILENAME)
result = pytesseract.image_to_string(img)
print(result)

# Japanese OCR Processing
FILENAME = 'receipt_sample_001_ja.png'
img = Image.open(IMG_PATH + FILENAME)
result = pytesseract.image_to_string(img, lang=LangCode.Jp.value)
print(result)

# Japanese OCR Processing
FILENAME = 'receipt_sample_002_ja.png'
img = Image.open(IMG_PATH + FILENAME)
result = pytesseract.image_to_string(img, lang=LangCode.Jp.value)
print(result)

# PDF -> IMG(*.png)
doc = fitz.open('img/receipt_sample_001_ko.pdf')
page = doc.load_page(1)
mat = fitz.Matrix(2, 2)
pix = page.get_pixmap(matrix=mat)
output = './img/receipt_sample_001_ko.png'
pix.save(output)

# Korean OCR Processing
img = Image.open(output)
result = pytesseract.image_to_string(img, lang=LangCode.Ko.value)
print(result)
