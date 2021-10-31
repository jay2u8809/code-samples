# How to use Tesseract

## 1. Install Python
## 2. Install Tesseract
### 2-1. install brew
### 2-2. install tesseract
```shell
    # install
    $ brew install tesseract-lang
    
    # confirm
    $ which tesseract
    /usr/local/bin/tesseract

    $ brew list tesseract
    /usr/local/Cellar/tesseract/4.1.1/bin/tesseract
    ... 
```

## 3. IMG OCR Processing 
+ Install Python dependencies
  - move project directory
```shell
    $ pip install pytesseract
    $ pip install Pillow
```
+ Sample Code
  - lang code : https://tesseract.patagames.com/langs/
```python
    import pytesseract
    from PIL import Image

    pytesseract.pytesseract.tesseract_cmd = r'<TESSERACT_FILE_PATH>'
    
    # OCR Processing
    img = Image.open('<IMAGE_FILE_PATH>')
    result = pytesseract.image_to_string(img, lang='<LANG_CODE>')
    
    print(result)
```

## 4. PDF OCR Processing
+ Install Python dependency
  - move project directory
```shell
    $ pip install pymupdf
```
+ Sample Code
  - PDF -> IMG OCR Processing
```python
    import pytesseract
    from PIL import Image
    import fitz

    pytesseract.pytesseract.tesseract_cmd = r'<TESSERACT_FILE_PATH>'

    # PDF -> IMG(*.png)
    doc = fitz.open('<PDF_FILE_PATH>')
    page = doc.load_page(3) # convert page
    mat = fitz.Matrix(2,2)
    pix = page.get_pixmap(matrix = mat)
    output = '<OUTPUT_IMAGE_FILE_PATH>'
    pix.save(output)

    # OCR Processing
    img = Image.open(output)
    result = pytesseract.image_to_string(img, lang='<LANG_CODE>')
    
    print(result)
```
