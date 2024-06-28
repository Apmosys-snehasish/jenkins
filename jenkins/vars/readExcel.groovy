import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.ss.usermodel.*

def call(String filePath) {
    def serverDetails = [:]
    def file = new File(filePath)
    def workbook = new XSSFWorkbook(file)
    def sheet = workbook.getSheetAt(0)
    def headers = sheet.getRow(0).collect { it.toString() }

    sheet.each { row ->
        if (row.getRowNum() != 0) {  // Skip header row
            def rowData = [:]
            headers.eachWithIndex { header, idx ->
                rowData[header] = row.getCell(idx).toString()
            }
            serverDetails[row.getRowNum()] = rowData
        }
    }
    workbook.close()
    return serverDetails
}
