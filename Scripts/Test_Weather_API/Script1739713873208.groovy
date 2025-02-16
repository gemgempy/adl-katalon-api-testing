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
import com.kms.katalon.core.logging.KeywordLogger
import groovy.json.JsonOutput
import java.io.File
import java.io.FileWriter

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import com.kms.katalon.core.logging.KeywordLogger

KeywordLogger log = new KeywordLogger()

// Kirim request ke API cuaca
def response = WS.sendRequest(findTestObject('Object Repository/Get_5_Days_Weather_Kuningan'))

// Verifikasi status response harus 200
WS.verifyResponseStatusCode(response, 200)

// Parsing JSON Response
def jsonResponse = new JsonSlurper().parseText(response.getResponseBodyContent())

// Validasi JSON Schema (sesuai dengan API Docs)
assert jsonResponse.city.name != null
assert jsonResponse.list.size() > 0

// Log hasil test
log.logInfo("✅ API Cuaca berjalan sukses. Kota: " + jsonResponse.city.name)
log.logInfo("🌡️ Temperatur hari pertama: " + jsonResponse.list[0].main.temp)
