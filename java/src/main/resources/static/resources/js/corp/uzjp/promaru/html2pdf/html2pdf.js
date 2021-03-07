//==============
/**
 * Generate PDF
 * @author : J.ian
 */
//==============

// PDF File Name
var orderReceiptFilename = "Sample.pdf"

// PDF Instance Constructor
var jsPdfInit = {
    orientation      : 'p',         // "portrait" or "landscape" (or shortcuts "p" or "l")
    unit             : 'mm',        // "mm", "cm", "m", "in" or "px"
    format           : 'a4',        // a4, b5, letter ...
    putOnlyUsedFonts : false,
    compress         : true,
    precision        : 2,
    userUnit         : 1.0,
};

var HTML2PDF = {
    init: function() {
        let self = this;

        // Button 1
        let pdfArea = document.querySelector('#pdf_area')
        $('#btn-generate-pdf1').on('click', function() {
            self.generateHtml2PdfBundle(pdfArea);
        });

        // Button 2
        let bodyArea = document.body;
        $('#btn-generate-pdf2').on('click', function() {
            self.generateHtml2Pdf(bodyArea);
        });

        // Button 3
//        let tableArea = $('#pdf_area')[0];
//        let tableArea = document.querySelector('#pdf_area');
//        let tableArea = $('#tbody')[0];
        let tableArea = document.querySelector('#tbody');
        $('#btn-generate-pdf3').on('click', function() {
            self.generateHtml2Pdf(tableArea);
        });
    },
    /**
     * Html2Pdf Library
     */
    generateHtml2Pdf: (pdfArea) => {

        html2canvas(pdfArea, {

            // Documents : https://html2canvas.hertzen.com/configuration/
            // Html2Canvas Options
            scale: 0.8,
            logging: true,
            allowTaint: false,
            backgroundColor: null

        }).then(function(canvas) {  // then.(canvas => {

            // Canvas -> Image
            let imageData = canvas.toDataURL('image/png');

            // Image Width (mm) : A4 Size
            let imageWidth = 210;

            // Image Height (mm) : A4 Size
            let pageHeight = imageWidth * 1.414;
            let imageHeight = canvas.height * imageWidth / canvas.width;
            let heightLeft = imageHeight;

            // Make JSPDF Instance
            let doc = new jsPDF(jsPdfInit);
            let position = 0;

            // Documents : https://artskydj.github.io/jsPDF/docs/jsPDF.html
            // Image -> PDF
            // addImage(Image Data File, File Type, Start X, Start Y, Width、Height, Alias, Compression, Ratio)
            doc.addImage(imageData, 'PNG', 0, position, imageWidth, imageHeight, null, 'FAST', 0);
            heightLeft -= pageHeight;

            while (heightLeft >= 20) {
                position = heightLeft - imageHeight;
                doc.addPage();
                doc.addImage(imageData, 'PNG', 0, position, imageWidth, imageHeight, null, 'FAST', 0);
                heightLeft -= pageHeight;
            }

            // Save File
            let result = doc.save(orderReceiptFilename);
        });
    },
    /**
     * Html2PdfBundle Library
     * Copyright : MIT License
     * Reference：https://github.com/eKoopmans/html2pdf.js
     */
     generateHtml2PdfBundle: (pdfArea) => {
        // Make PDF File
        html2pdf()
            .from(pdfArea)
            .set({
                margin      : [15, 15, 15, 15],         // PDF margin : [vMargin, hMargin] or [top, left, bottom, right]
                filename    : orderReceiptFilename,
                html2canvas : {
                    width: pdfArea.scrollWidth
                },
                pagebreak   : {                         // Paging
                    mode : 'avoid-all',
                    after: '#pdf_area'
                },
                jsPDF       : jsPdfInit
            })
            .save();
     }
};

HTML2PDF.init();