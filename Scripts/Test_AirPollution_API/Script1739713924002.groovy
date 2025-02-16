import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil
import groovy.json.JsonSlurper
import java.io.File
import java.io.FileWriter

// Kirim request ke API OpenWeather
def pollutionResponse = WS.sendRequest(findTestObject('Object Repository/Get_Air_Pollution_Kuningan'))
WS.verifyResponseStatusCode(pollutionResponse, 200)

// Parsing JSON Response
def pollutionJson = new JsonSlurper().parseText(pollutionResponse.getResponseBodyContent())

// Ambil data kualitas udara (Air Quality Index)
def aqi = pollutionJson.list[0].main.aqi
def co = pollutionJson.list[0].components.co
def no2 = pollutionJson.list[0].components.no2
def pm10 = pollutionJson.list[0].components.pm10

def pollutionData = [[1, "Get Current Air Pollution", 200, "PASSED", "-", "-", aqi, getCurrentTimestamp()]]
KeywordUtil.logInfo("✅ Air Pollution Data: AQI=${aqi} | CO=${co} | NO2=${no2} | PM10=${pm10}")

// Simpan hasil ke CSV dalam folder Katalon
def csvFilePath = "Reports/Air_Pollution_Report.csv"
saveToCSV(csvFilePath, pollutionData)

// Fungsi untuk menyimpan ke CSV
def saveToCSV(String filePath, List<List> data) {
    try {
        File file = new File(filePath)
        file.parentFile.mkdirs() // Buat folder jika belum ada
        FileWriter writer = new FileWriter(file)

        // Tulis Header
        writer.append("No,TestCase,StatusCode,Result,Temperature,Humidity,AQI,DateTime\n")

        // Tulis Data
        data.each { row ->
            writer.append(row.join(",")).append("\n")
        }

        writer.flush()
        writer.close()

        KeywordUtil.logInfo("✅ Data polusi udara disimpan ke: " + filePath)
    } catch (Exception e) {
        KeywordUtil.markFailed("❌ Gagal menyimpan ke CSV: " + e.message)
    }
}

// Fungsi untuk mendapatkan timestamp saat ini
def getCurrentTimestamp() {
    return new Date().format("yyyy-MM-dd HH:mm:ss")
}
